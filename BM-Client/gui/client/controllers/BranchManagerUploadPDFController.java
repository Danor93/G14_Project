package client.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import Entities.homeBranches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ClientUI;
import main.PopUpMessage;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * @author Danor
 * this class handles the functionality of the Branch Manager to upload a PDF of the quarterly report.
 */
public class BranchManagerUploadPDFController extends Controller implements Initializable {
	public static String Quertar;
	public static String Year;
	public static Stage stage;
	public static Boolean yearandqflag=false;
	public static Boolean succesUpload=false;
	public StringBuilder yearandQ=new StringBuilder();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> QuertarComboBox;

	@FXML
	private Button UploadBtn;

	@FXML
	private ComboBox<String> YearComboBox;
	
    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;

    @FXML
    private Text userName;

	/**
	 * @param event - back to the home screen of the Branch Manager
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager",LoginScreenController.user.getFirstN());
	}

	/**
	 * @param event - logout the user.
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

	/**
	 * for choosing a Quarter
	 * @param event - for the combo box.
	 */
	@FXML
	void ChooseQuertar(ActionEvent event) {
		Quertar = QuertarComboBox.getSelectionModel().getSelectedItem().toString();
		YearComboBox.getItems().add("2022");
		YearComboBox.getItems().add("2021");
		YearComboBox.setDisable(false);
	}

	/**
	 * for choosing a Year
	 * @param event - for the combo box.
	 */
	@FXML
	void chooseYear(ActionEvent event) {
		Year = YearComboBox.getSelectionModel().getSelectedItem();
		yearandQ.append(Quertar);
		yearandQ.append("@");
		yearandQ.append(Year);
		UploadBtn.setDisable(false);
	}

	/**
	 * this method handles the upload the PDF file and send it to the DB.
	 * @param event - for the Upload PDF button.
	 */
	@FXML
	void UploadPDF(ActionEvent event) {
		ClientUI.chat.accept(new Message(MessageType.check_year_and_quertar, yearandQ.toString()));
		if(yearandqflag==true) {
			yearandqflag=false;
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
				File file = fileChooser.showOpenDialog(BranchManagerUploadPDFController.stage);
				if (file != null) {
					String path = file.getPath();
					File f = new File(path);
					MyFile msg = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
					try {
						File newFile = new File(path);
						byte[] mybytearray = new byte[(int) newFile.length()];
						msg.initArray(mybytearray.length);
						msg.setSize(mybytearray.length);
						FileInputStream fis = new FileInputStream(newFile);
						BufferedInputStream bis = new BufferedInputStream(fis);
						bis.read(msg.getMybytearray(), 0, mybytearray.length);
						msg.setQuarter(Quertar);
						msg.setYear(Year);
						msg.setHomebranch((LoginScreenController.user.getHomeBranch()));
						ClientUI.chat.accept(new Message(MessageType.send_PDF, msg));
						if(succesUpload==true)
						{
							PopUpMessage.successMessage("Succes to upload the " + Year + "' " + Quertar + " PDF file!");
						}
						else {
							PopUpMessage.errorMessage("Could not upload " + Year + "' " + Quertar + " PDF file!,try again");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			PopUpMessage.errorMessage("there is a report for " + Year + " " + Quertar + " already!");
		}	
	}

	/**
	 * initialize the combo box and the button functionality and style.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		QuertarComboBox.getItems().add("1");
		QuertarComboBox.getItems().add("2");
		QuertarComboBox.getItems().add("3");
		QuertarComboBox.getItems().add("4");
		YearComboBox.setDisable(true);
		UploadBtn.setDisable(true);
		UploadBtn.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
	}

	/**
	 * display the name of the user.
	 */
    @Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}

}
