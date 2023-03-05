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

public class LandingPageController {

    @FXML
    private ImageView logo;

    @FXML
    private Button map;

    @FXML
    private Label mapLabel;

    @FXML
    private Label proceed;

    @FXML
    private Button recipe;

    @FXML
    private Label recipeLabel;

    @FXML
    private Button spice;

    @FXML
    private Label spiceLabel;

    @FXML
    private Label welcome;

    @FXML
    private void mapListener(ActionEvent event) throws IOException { // action listener for map button
        Parent mapPageParent = FXMLLoader.load(getClass().getResource("MapPage.fxml"));
        Scene mapPageScene = new Scene(mapPageParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mapPageScene);
        window.show();
    }

    @FXML
    private void recipeListener(ActionEvent event) throws IOException { // action listener for recipe button
        Parent recipePageParent = FXMLLoader.load(getClass().getResource("RecipePage.fxml"));
        Scene recipePageScene = new Scene(recipePageParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(recipePageScene);
        window.show();
    }

    @FXML
    private void spiceListener(ActionEvent event) throws IOException { // action listener for spice button
        Parent spicePageParent = FXMLLoader.load(getClass().getResource("SpicePage.fxml"));
        Scene spicePageScene = new Scene(spicePageParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(spicePageScene);
        window.show();
    }

}
