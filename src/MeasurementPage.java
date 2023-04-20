import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class MeasurementPage extends Application {
    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");

        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Label measurementInstruction = new Label();
        measurementInstruction.setText("Measure the quantity of the spice below.");
        measurementInstruction.setTextFill(Color.WHITE);
        measurementInstruction.setFont(new Font("System Bold", 36.0));
        measurementInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(measurementInstruction, 0.0);
        AnchorPane.setRightAnchor(measurementInstruction, 0.0);
        measurementInstruction.setAlignment(Pos.CENTER);
        measurementInstruction.setPrefWidth(358.0);
        measurementInstruction.setPrefHeight(80.0);

        anchorPane.getChildren().add(measurementInstruction);

        Button backButton = new Button("←"); // back button to go back to landing page
        backButton.setLayoutX(10);
        backButton.setLayoutY(anchorPane.getHeight() - backButton.getPrefHeight() - 10);
        backButton.setPrefWidth(100.0);
        backButton.setPrefHeight(100.0);
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        backButton.setFont(new Font("System Bold", 44.0));
        backButton.setOnAction(event -> {
            primaryStage.close();
            backListener();
        });
        anchorPane.getChildren().add(backButton);

        anchorPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            backButton.setLayoutX(10);
        });
        
        anchorPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            backButton.setLayoutY(newHeight.doubleValue() - backButton.getPrefHeight() - 10);
        });

        Slider measurementSlider = new Slider(0, 100, 0);
        //measurementSlider.setLayoutX(62.0);
        //measurementSlider.setLayoutY(98.0);
        //measurementSlider.setPrefWidth(277.0);
        measurementSlider.setShowTickLabels(true);
        measurementSlider.setShowTickMarks(true);
        measurementSlider.setStyle("-fx-control-inner-background: #1c1f21; -fx-tick-label-fill: white; -fx-tick-mark-fill: white;");
        AnchorPane.setTopAnchor(measurementSlider, 100.0); 
        AnchorPane.setLeftAnchor(measurementSlider, 140.0); 
        AnchorPane.setRightAnchor(measurementSlider, 140.0); 

        anchorPane.getChildren().add(measurementSlider);

        Label measurementDisplay = new Label();
        measurementDisplay.setText("Quantity: __ tsp");
        measurementDisplay.setTextFill(Color.WHITE);
        measurementDisplay.setFont(new Font("System Bold", 36.0));
        measurementDisplay.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setTopAnchor(measurementDisplay, 150.0);
        AnchorPane.setLeftAnchor(measurementDisplay, 0.0);
        AnchorPane.setRightAnchor(measurementDisplay, 0.0);
        measurementDisplay.setAlignment(Pos.CENTER);
        measurementDisplay.setPrefWidth(358.0);
        measurementDisplay.setPrefHeight(40.0);

        anchorPane.getChildren().add(measurementDisplay);

        measurementSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            measurementDisplay.setText("Quantity: " + newValue.intValue() + " tsp");
        });

        Button add = new Button("Add Spice"); // go to recipes page
        //add.setLayoutX(anchorPane.getWidth() + add.getPrefWidth() + 200);
        //add.setLayoutY(anchorPane.getHeight() + add.getPrefHeight() + 230);
        add.setPrefWidth(146.0);
        add.setPrefHeight(50.0);
        AnchorPane.setTopAnchor(add, 250.0);
        AnchorPane.setLeftAnchor(add, 300.0);
        AnchorPane.setRightAnchor(add, 300.0);
        add.setStyle("-fx-cursor: hand;");
        add.setFont(new Font("System Bold", 24.0));
        /*add.setOnAction(event -> {
            primaryStage.close();
            recipeListener();
        });*/
        anchorPane.getChildren().add(add);

        Button view = new Button("View Selected Spices");
        view.setPrefWidth(146.0);
        view.setPrefHeight(50.0);
        AnchorPane.setTopAnchor(view, 400.0);
        AnchorPane.setLeftAnchor(view, 300.0);
        AnchorPane.setRightAnchor(view, 300.0);
        view.setStyle("-fx-cursor: hand;");
        view.setFont(new Font("System Bold", 24.0));
        view.setOnAction(event -> {
            primaryStage.close();
            confirmListener();
        });
        anchorPane.getChildren().add(view);

        Scene scene = new Scene(anchorPane, 1024, 600);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void backListener() { // go back to landing page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        SpicePage spicePage = new SpicePage();
        spicePage.start(stage);
    }

    private void confirmListener() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        ConfirmPage confirmPage = new ConfirmPage();
        confirmPage.start(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}