import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class MeasurementPage extends Application {
    @Override
    public void start(Stage primaryStage) {
  
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("MeasurementPage.fxml"));
            Scene scene = new Scene(root);
  
            primaryStage.setTitle("S.P.I.C.E.");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}