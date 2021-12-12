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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuScreenController extends Controller implements ControllerInterface {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private ImageView desserts;

	@FXML
	private ImageView drinks;

	@FXML
	private ImageView main;

	@FXML
	private ImageView order;

	@FXML
	private Text restName;

	@FXML
	private ImageView salads;

	@FXML
	private ImageView starters;
	
	@Override
	public void Back(ActionEvent event) throws IOException {
		startScreen(event, "restListForm","Restaurant List");
	}

	@FXML
	void proceedToDish(MouseEvent event) {

	}

	@FXML
	void showDesserts(MouseEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();

		aFrame.display("Dessert");
		aFrame.start(primaryStage, root);
		

	}

	@FXML
	void showDrinks(MouseEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();

		aFrame.display("Drink");
		aFrame.start(primaryStage, root);

	}

	@FXML
	void showMainDishes(MouseEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();

		aFrame.display("Main dish");
		aFrame.start(primaryStage, root);
	}

	@FXML
	void showSalads(MouseEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();

		aFrame.display("Salad");
		aFrame.start(primaryStage, root);
	}

	@FXML
	void showStarters(MouseEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();

		// aFrame.display();
		aFrame.start(primaryStage, root);

	}

	/**
	 * @param stage
	 * @param root
	 * @throws IOException
	 */
	public void start(Stage stage, Parent root) throws IOException {
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param supplier
	 */
	public void display(String supplier) {
		restName.setText(supplier);

	}

	@FXML
	void initialize() {
		assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert desserts != null : "fx:id=\"desserts\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert drinks != null : "fx:id=\"drinks\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert main != null : "fx:id=\"main\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert order != null : "fx:id=\"order\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert restName != null : "fx:id=\"restName\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert salads != null : "fx:id=\"salads\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert starters != null : "fx:id=\"starters\" was not injected: check your FXML file 'MenuScreen.fxml'.";

	}



}