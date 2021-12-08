package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CEOScreenController extends Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConfirmEmployerRegistration;

    @FXML
    private Button btnOpenNewAccount;

    @FXML
    private Button btnChangePersonalInformation;

    @FXML
    private Button btnConfirmSupplierRegistration;

    @FXML
    private Button btnUploadPDF;

    @FXML
    private Button btnViewBranchsReports;

    @FXML
    private Button btnCreateOrder;
    
    @FXML
    private ImageView BackImage;

    @FXML
    void initialize() {
    	setImage(BackImage,"background.jpeg");
        assert btnConfirmEmployerRegistration != null : "fx:id=\"btnConfirmEmployerRegistration\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert btnOpenNewAccount != null : "fx:id=\"btnOpenNewAccount\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert btnChangePersonalInformation != null : "fx:id=\"btnChangePersonalInformation\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert btnConfirmSupplierRegistration != null : "fx:id=\"btnConfirmSupplierRegistration\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert btnUploadPDF != null : "fx:id=\"btnUploadPDF\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert btnViewBranchsReports != null : "fx:id=\"btnViewBranchsReports\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert btnCreateOrder != null : "fx:id=\"btnCreateOrder\" was not injected: check your FXML file 'CEOScreen.fxml'.";
    }
   

        @FXML
        void Back(ActionEvent event) throws IOException {
        	startScreen(event,"LoginScreen","Login");
        }
        
        @FXML
        void ChangeInfoDBCEO(ActionEvent event) throws IOException {
        	startScreen(event, "ChangeInfoDB", "Change Information");
        }
}