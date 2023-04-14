import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
 
public class RecipePage extends Application {
    private final TableView<recipeInfo> table = new TableView<>();
    private final ObservableList<recipeInfo> data = FXCollections.observableArrayList(
        new recipeInfo("Garlic Butter Chicken"),
        new recipeInfo("Honey Garlic Pork")
    );

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("S.P.I.C.E.");

        TableColumn<recipeInfo, String> recipeCol = new TableColumn<>("Recipe Name");
        recipeCol.setStyle("-fx-alignment: CENTER");
        recipeCol.setCellValueFactory(new PropertyValueFactory<>("RecipeName"));

        table.getColumns().addAll(recipeCol);

        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(300);
        table.setPrefHeight(400);

        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");
        anchorPane.getChildren().add(borderPane);

        Label recipeInstruction = new Label();
        recipeInstruction.setText("Select spices based on a recipe below.");
        recipeInstruction.setTextFill(Color.WHITE);
        recipeInstruction.setFont(new Font("System Bold", 18.0));
        recipeInstruction.setAlignment(Pos.CENTER);
        recipeInstruction.setLayoutX(21.0);
        recipeInstruction.setLayoutY(14.0);
        recipeInstruction.setPrefWidth(358.0);
        recipeInstruction.setPrefHeight(17.0);

        anchorPane.getChildren().add(recipeInstruction);

        Button backButton = new Button("←"); // back button to go back to landing page
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

        borderPane.setCenter(table);

        Scene scene = new Scene(anchorPane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void backListener(ActionEvent event) throws IOException { // go back to landing page
        Parent backPageParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
        Scene backPageScene = new Scene(backPageParent);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backPageScene);
        window.show();
    }

    public class recipeInfo {
        private String RecipeName;

        public recipeInfo(String RecipeName) {
            this.RecipeName = RecipeName;
        }

        public String getRecipeName() {
            return RecipeName;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}