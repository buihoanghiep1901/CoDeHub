package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.lang.Exception;
import items.Constant;



public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
			primaryStage.setTitle("Sorting Algorithm");
			primaryStage.setScene(new Scene(root, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT));
			primaryStage.setResizable(false);			
			primaryStage.show();
			
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
