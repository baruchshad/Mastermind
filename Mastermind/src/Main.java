import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

public class Main extends Application{
	
	Stage window;
	Scene scene1, scene2;

	
	/**
	 * 
	 * @param args
	 * 
	 * The Main method launches the application.
	 */
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		final TextField name = new TextField();
		name.setPromptText("Enter your first name.");
		
		GridPane gridpane = new GridPane();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Rules of Mastermind");
		alert.setHeaderText("These are the rules");
		alert.setContentText("Rule 1.");

		
		Label label1 = new Label("Enter a name");
		Label label2 = new Label();
		
		/** 
		 * @param button1 skips to scene 2
		 * @param button3 exits the program
		 */
		Button button1 = new Button("Go to scene 2");
		Button button4 = new Button("Rules");
		button1.setOnAction(e -> {
			if(name.getText() != null  && !name.getText().isEmpty()) {
				label2.setText("Thank you " + name.getText());
				window.setScene(scene2);
				name.clear();
			}	
			else label1.setText("you didn't enter a name");
			});
		
				
		button4.setOnAction(e -> alert.showAndWait());
		
		Button button3 = new Button("exit");
		button3.setOnAction(e -> System.exit(1));
	    
		BorderPane bp1 = new BorderPane();
		ToolBar toolBar = new ToolBar(button4, new Separator(), button3);
		toolBar.setOrientation(Orientation.HORIZONTAL);
	    HBox statusbar = new HBox(2, button1);
	    statusbar.setSpacing(10);
	    bp1.setTop(toolBar);
	    bp1.setCenter(statusbar);
	    
	    gridpane.add(label1,  25,  25);
	    gridpane.add(bp1, 0, 0);
	    gridpane.setGridLinesVisible(false);
	    Insets h = new Insets(10, 10, 10, 10);
	    gridpane.setPadding(h);
	    gridpane.setVgap(10);
	    gridpane.setHgap(10);
	    
	    name.setPrefColumnCount(10);
	    name.getText();
	    gridpane.getChildren().addAll(name, button1);
	    GridPane.setConstraints(name, 25, 20);
	    GridPane.setConstraints(button1, 35, 20);
		//layout 1
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(gridpane);
		scene1 = new Scene(layout1, 1000, 500);
		scene1.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		    	if(name.getText() != null  && !name.getText().isEmpty()) {
					label2.setText("Thank you " + name.getText());
					window.setScene(scene2);
					name.clear();
		    	}
		    }
		    	
		});
		
		//button 2
		Button button2 = new Button("Go to scene 1");
		button2.setOnAction(e -> {
			label1.setText("Enter a name");
			window.setScene(scene1);
		});
		
		//Layout 2
		StackPane layout2 = new StackPane();
		layout2.getChildren().addAll(button2, label2);
		StackPane.setAlignment(label2, Pos.TOP_CENTER);
		scene2 = new Scene(layout2, 600, 300);
		
		
		
		window.setScene(scene1);
		window.setTitle("Mastermind");
		window.show();
		
		}
	
	
	
}	