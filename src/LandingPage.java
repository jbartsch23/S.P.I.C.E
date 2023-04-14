import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class LandingPage extends Application {
    @Override
    public void start(Stage primaryStage) {
  
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
            Scene scene = new Scene(root);
  
            primaryStage.setTitle("S.P.I.C.E.");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED); // hide title bar
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