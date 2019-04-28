package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SampleApplication extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("SampleGUI.fxml"));
			arg0.setTitle("Sample");
			arg0.setScene(new Scene(root));
			arg0.show();
		}
			
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args)
	{
		launch(args);
	}


}
