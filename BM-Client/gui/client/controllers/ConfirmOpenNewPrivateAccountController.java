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

public class ConfirmOpenNewPrivateAccountController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBackToBranchManager;
    
    @FXML
    private ImageView BackImage;

    @FXML
    void BackToBranchManagerScreen(ActionEvent event) throws IOException {
    	startScreen(event, "BranchManagerScreen", "Branch Manager");
    }

    @FXML
    void initialize() {
    	setImage(BackImage,"background.jpeg");
        assert btnBackToBranchManager != null : "fx:id=\"btnBackToBranchManager\" was not injected: check your FXML file 'ConfirmOpenNewPrivateAccount.fxml'.";

    }
}