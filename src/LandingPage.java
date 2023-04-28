import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
 
public class LandingPage extends Application {
    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");

        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Label welcome = new Label();
        welcome.setText("Welcome to S.P.I.C.E.");
        welcome.setTextFill(Color.WHITE);
        welcome.setFont(new Font("System Bold", 36.0));
        welcome.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(welcome, 0.0);
        AnchorPane.setRightAnchor(welcome, 0.0);
        welcome.setAlignment(Pos.CENTER);
        welcome.setPrefWidth(358.0);
        welcome.setPrefHeight(80.0);

        anchorPane.getChildren().add(welcome);

        Label proceed = new Label();
        proceed.setText("Select an option below to proceed.");
        proceed.setTextFill(Color.WHITE);
        proceed.setFont(new Font("System Bold", 24.0));
        proceed.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(proceed, 0.0);
        AnchorPane.setRightAnchor(proceed, 0.0);
        proceed.setAlignment(Pos.CENTER);
        proceed.setLayoutY(60.0);
        proceed.setPrefWidth(358.0);
        proceed.setPrefHeight(80.0);

        anchorPane.getChildren().add(proceed);

        Button exit = new Button("X"); // exit application
        exit.setLayoutX(anchorPane.getWidth() - exit.getPrefWidth() - 20);
        exit.setLayoutY(anchorPane.getHeight() - exit.getPrefHeight() - 20);
        exit.setPrefWidth(100.0);
        exit.setPrefHeight(100.0);
        exit.setTextFill(Color.RED);
        exit.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        exit.setFont(new Font("System Bold", 66.0));
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                try {
                    exitListener(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        anchorPane.getChildren().add(exit);

        anchorPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            exit.setLayoutX(newWidth.doubleValue() - exit.getPrefWidth() - 20);
        });

        anchorPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            exit.setLayoutY(newHeight.doubleValue() - exit.getPrefHeight() - 20);
        });

        Button spice = new Button("Spices"); // go to spices page
        spice.setLayoutX(anchorPane.getWidth() + spice.getPrefWidth() + 200);
        spice.setLayoutY(anchorPane.getHeight() + spice.getPrefHeight() + 150);
        spice.setPrefWidth(146.0);
        spice.setPrefHeight(50.0);
        spice.setStyle("-fx-cursor: hand;");
        spice.setFont(new Font("System Bold", 24.0));
        spice.setOnAction(event -> {
            primaryStage.close();
            spiceListener();
        });

        Label spiceLabel = new Label();
        spiceLabel.setText("Select spices of your choice.");
        spiceLabel.setTextFill(Color.WHITE);
        spiceLabel.setFont(new Font("System Bold", 24.0));
        spiceLabel.setLayoutX(anchorPane.getWidth() + spiceLabel.getPrefWidth() + 400);
        spiceLabel.setLayoutY(anchorPane.getHeight() + spiceLabel.getPrefHeight() + 150);
        spiceLabel.setPrefWidth(400.0);
        spiceLabel.setPrefHeight(50.0);

        Button recipe = new Button("Recipes"); // go to recipes page
        recipe.setLayoutX(anchorPane.getWidth() + recipe.getPrefWidth() + 200);
        recipe.setLayoutY(anchorPane.getHeight() + recipe.getPrefHeight() + 230);
        recipe.setPrefWidth(146.0);
        recipe.setPrefHeight(50.0);
        recipe.setStyle("-fx-cursor: hand;");
        recipe.setFont(new Font("System Bold", 24.0));
        recipe.setOnAction(event -> {
            primaryStage.close();
            recipeListener();
        });

        Label recipeLabel = new Label();
        recipeLabel.setText("Select spices based off a recipe.");
        recipeLabel.setTextFill(Color.WHITE);
        recipeLabel.setFont(new Font("System Bold", 20.0));
        recipeLabel.setLayoutX(anchorPane.getWidth() + recipeLabel.getPrefWidth() + 400);
        recipeLabel.setLayoutY(anchorPane.getHeight() + recipeLabel.getPrefHeight() + 230);
        recipeLabel.setPrefWidth(400.0);
        recipeLabel.setPrefHeight(50.0);

        Button map = new Button("Mappings"); // go to mappings page
        map.setLayoutX(anchorPane.getWidth() + map.getPrefWidth() + 200);
        map.setLayoutY(anchorPane.getHeight() + map.getPrefHeight() + 310);
        map.setPrefWidth(146.0);
        map.setPrefHeight(50.0);
        map.setStyle("-fx-cursor: hand;");
        map.setFont(new Font("System Bold", 20.0));
        map.setOnAction(event -> {
            primaryStage.close();
            mapListener();
        });

        Label mapLabel = new Label();
        mapLabel.setText("View mappings for each spice.");
        mapLabel.setTextFill(Color.WHITE);
        mapLabel.setFont(new Font("System Bold", 22.0));
        mapLabel.setLayoutX(anchorPane.getWidth() + mapLabel.getPrefWidth() + 400);
        mapLabel.setLayoutY(anchorPane.getHeight() + mapLabel.getPrefHeight() + 310);
        mapLabel.setPrefWidth(400.0);
        mapLabel.setPrefHeight(50.0);
        
        HBox spiceSelectionBox = new HBox();
        spiceSelectionBox.setAlignment(Pos.CENTER);
        spiceSelectionBox.setSpacing(50);

        spiceSelectionBox.getChildren().addAll(spice, spiceLabel);

        HBox recipeSelectionBox = new HBox();
        recipeSelectionBox.setAlignment(Pos.CENTER);
        recipeSelectionBox.setSpacing(50);

        recipeSelectionBox.getChildren().addAll(recipe, recipeLabel);

        HBox mapSelectionBox = new HBox();
        mapSelectionBox.setAlignment(Pos.CENTER);
        mapSelectionBox.setSpacing(50);

        mapSelectionBox.getChildren().addAll(map, mapLabel);

        VBox selectionBox = new VBox();
        selectionBox.setAlignment(Pos.CENTER);
        selectionBox.setSpacing(50);
        
        selectionBox.getChildren().addAll(spiceSelectionBox, recipeSelectionBox, mapSelectionBox);
        AnchorPane.setTopAnchor(selectionBox, 150.0);
        AnchorPane.setLeftAnchor(selectionBox, 50.0);
        AnchorPane.setRightAnchor(selectionBox, 50.0);
        AnchorPane.setBottomAnchor(selectionBox, 150.0);

        anchorPane.getChildren().add(selectionBox);

        Image logo = new Image("logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setPreserveRatio(true);
        logoView.setFitWidth(200); // image width
        logoView.setFitHeight(200); // image height

        anchorPane.getChildren().add(logoView);
        AnchorPane.setBottomAnchor(logoView, 0.0);
        AnchorPane.setLeftAnchor(logoView, (anchorPane.getWidth() - logoView.getFitWidth()) / 2); // center horizontally

        anchorPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            AnchorPane.setLeftAnchor(logoView, (newWidth.doubleValue() - logoView.getFitWidth()) / 2);
        });

        try {
            Scene scene = new Scene(anchorPane, 1024, 600);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void exitListener(ActionEvent event) throws IOException { // close the window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void spiceListener() { // go to spices page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        SpicePage spicePage = new SpicePage();
        spicePage.start(stage);
    }

    private void recipeListener() { // go to recipes page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        RecipePage recipePage = new RecipePage();
        recipePage.start(stage);
    }

    private void mapListener() { // go to mappings page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        MapPage mapPage = new MapPage();
        mapPage.start(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}