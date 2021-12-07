package client.controllers;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ChooseRestController implements Initializable {
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button next;
    
	@FXML
    private ComboBox<String> combo1;
	

    @FXML
    private Label noSelect;
	
	public static ArrayList<String> cities;
	
	ObservableList<String> observableList;
	
	public static String cityName;
	
	


    @FXML
    void selectCity(ActionEvent event) {
    	cityName=combo1.getSelectionModel().getSelectedItem().toString();
    }

	public void start(Stage stage) throws IOException  {
    	FXMLLoader loader = new FXMLLoader();
		Pane root;
			root = loader.load(getClass().getResource("/client/controllers/ChooseRestaurant.fxml").openStream());
			Scene scene = new Scene(root);			
			stage.setTitle("BiteMe");
			stage.setScene(scene);
			stage.show();		
	}
	

    @FXML
    void proceedToRest(ActionEvent event) {
    	if(cityName!=null && !cityName.equals("select"))
    	{
    		RestListFormController restListFormController = new RestListFormController();
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	restListFormController.start(stage);
    	}
    	
    	else
    	{
    		noSelect.setText("Please choose city");
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Message msg=new Message(MessageType.Show_Cities,null);
		ClientUI.chat.accept(msg);
		for(String s:cities)
		{
			System.out.println(s);
		}
		observableList=FXCollections.observableArrayList(cities);
		combo1.setItems(observableList);	
	}
	

}