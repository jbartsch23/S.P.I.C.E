import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.Node;

public class RecipeConfirmPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");
        anchorPane.getChildren().add(borderPane);
        
        Label confirmInstruction = new Label();
        confirmInstruction.setText("Do you want to dispense the following spices?");
        confirmInstruction.setTextFill(Color.WHITE);
        confirmInstruction.setFont(new Font("System Bold", 16.0));
        confirmInstruction.setAlignment(Pos.CENTER);
        confirmInstruction.setLayoutX(21.0);
        confirmInstruction.setLayoutY(14.0);
        confirmInstruction.setPrefWidth(358.0);
        confirmInstruction.setPrefHeight(17.0);

        anchorPane.getChildren().add(confirmInstruction);

        Button backButton = new Button("←"); // back button to go back to recipe page
        backButton.setLayoutX(7.0);
        backButton.setLayoutY(425.0);
        backButton.setPrefWidth(73.0);
        backButton.setPrefHeight(15.0);
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        backButton.setFont(new Font("System Bold", 33.0));
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                try {
                    backListener(event);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        anchorPane.getChildren().add(backButton);

        AnchorPane.setTopAnchor(borderPane, 100.0);
        AnchorPane.setLeftAnchor(borderPane, 100.0);
        AnchorPane.setRightAnchor(borderPane, 100.0);
        AnchorPane.setBottomAnchor(borderPane, 100.0);

        //borderPane.setCenter(table);

        Scene scene = new Scene(anchorPane, 400, 500);
        //scene.setFill(javafx.scene.paint.Color.web("1c1f21"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void backListener(ActionEvent event) throws IOException { // go back to recipe page
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        RecipePage recipePage = new RecipePage();
        recipePage.start(window);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}