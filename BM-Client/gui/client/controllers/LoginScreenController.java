package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Entities.Message;
import Entities.MessageType;
import Entities.User;
import Interfaces.ILoginInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ChatClient;
import main.ClientController;
import main.ClientUI;
import main.PopUpMessage;
import javafx.scene.control.PasswordField;

/**
 * @author Adi & Talia this class handles for login into the system with all the
 *         Entity related to BiteMe.
 */
public class LoginScreenController extends Controller {
	public static User user = null;
	public static String statusUser;
	public static ILoginInterface iLogin;
	
	
	// For regular uses
		public LoginScreenController() {

		}
	
	/**
	 * this is a contractor for the test case.
	 * @param user - user from the test case;
	 */
	public LoginScreenController(ILoginInterface iLogin) {
		this.iLogin=iLogin;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField PasswordField;

	@FXML
	private Button btnLogin;

	@FXML
	private Label WrongInputInLoggin;

	@FXML
	private ImageView BackImage;

	/**
	 * This method meant to connect the user to the BiteMe system.
	 * @param event - pressing the "login" button
	 */
	@FXML
	public void ConnectSystem(ActionEvent event) throws Exception {
		if (txtUserName.getText().isEmpty() || PasswordField.getText().isEmpty()) {
			WrongInputInLoggin.setText("Please fill both user name and password");
		} else {
			String[] DivededUandP;
			StringBuilder str = new StringBuilder();
			str.append(txtUserName.getText());
			str.append("@");
			str.append(PasswordField.getText());
			ClientUI.chat.accept(new Message(MessageType.loginSystem, str.toString()));
			if (!statusUser.equals("Active")) {
				WrongInputInLoggin.setText(statusUser);
				statusUser = null;
			}
			if (user != null) {
				switch (user.getRole()) {
				case "BranchManager": {
					start(event, "BranchManagerScreen", "Branch Manager", user.getFirstN());
					break;
				}

				case "Customer": {
					start(event, "CustomerScreen", "CustomerScreen", user.getFirstN());
					break;
				}

				case "CEO": {
					start(event, "CEOScreen", "CEO", user.getFirstN());
					break;
				}

				default: {
					DivededUandP = ((String) user.getRole()).split("-");
					if (DivededUandP[0].equals("HR")) {
						start(event, "HRManagerScreen", "HR Manager", user.getFirstN());
					} else {
						start(event, "SupplierScreen", "Supplier", user.getRole());
					}
					break;
				}
				}
			}
		
		}
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub

	}
}