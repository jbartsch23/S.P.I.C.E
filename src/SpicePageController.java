import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SpicePageController {

    @FXML
    private ImageView logo;
    
    @FXML
    private Label spiceInstruction;

    @FXML
    private Button back;

    @FXML
    private void backListener(ActionEvent event) throws IOException { // action listener to go back a page 
        Parent backPageParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
        Scene backPageScene = new Scene(backPageParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(backPageScene);
        window.show();
    }

    @FXML
    private void forwardListener(ActionEvent event) throws IOException { // action listener to go forward a page
        Parent forwardPageParent = FXMLLoader.load(getClass().getResource("MeasurementPage.fxml"));
        Scene forwardPageScene = new Scene(forwardPageParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(forwardPageScene);
        window.show();
    }
}