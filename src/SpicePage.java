import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class SpicePage extends Application {
    private final TableView<spiceInfo> table = new TableView<>();
    private final ObservableList<spiceInfo> data = FXCollections.observableArrayList( // populate table
        new spiceInfo("Spice 1"),
        new spiceInfo("Spice 2"),
        new spiceInfo("Spice 3"),
        new spiceInfo("Spice 4"),
        new spiceInfo("Spice 5"),
        new spiceInfo("Spice 6"),
        new spiceInfo("Spice 7"),
        new spiceInfo("Spice 8")
    );
    
    @Override
    public void start(Stage primaryStage) {
        ArrayList<TableColumn<spiceInfo, String>> columns = new ArrayList<>();
        
        TableColumn<spiceInfo, String> spiceCol = new TableColumn<>("Spice");
        spiceCol.setStyle("-fx-alignment: CENTER");
        spiceCol.setCellValueFactory(new PropertyValueFactory<>("Spice"));

        columns.add(spiceCol);

        table.getColumns().addAll(columns);

        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(300);
        table.setPrefHeight(400);

        table.setRowFactory(event -> { 
            TableRow<spiceInfo> row = new TableRow<>();
            row.setOnMouseClicked(click -> {
                if (click.getClickCount() == 1 && !row.isEmpty()) {
                    //recipeInfo selectedRecipe = row.getItem();
                    try {
                        primaryStage.close();
                        measurementListener();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        
        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");
        anchorPane.getChildren().add(borderPane);

        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Label spiceInstruction = new Label();
        spiceInstruction.setText("Choose a spice below to dispense.");
        spiceInstruction.setTextFill(Color.WHITE);
        spiceInstruction.setFont(new Font("System Bold", 36.0));
        spiceInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(spiceInstruction, 0.0);
        AnchorPane.setRightAnchor(spiceInstruction, 0.0);
        spiceInstruction.setAlignment(Pos.CENTER);
        spiceInstruction.setPrefWidth(358.0);
        spiceInstruction.setPrefHeight(80.0);

        anchorPane.getChildren().add(spiceInstruction);

        AnchorPane.setTopAnchor(borderPane, 100.0);
        AnchorPane.setLeftAnchor(borderPane, 100.0);
        AnchorPane.setRightAnchor(borderPane, 100.0);
        AnchorPane.setBottomAnchor(borderPane, 100.0);

        borderPane.setCenter(table);

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


        Scene scene = new Scene(anchorPane, 1024, 600);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class spiceInfo { // class to hold all necessary spice data
        private String spice;

        public spiceInfo(String spice) {
            this.spice = spice;
        }

        public String getSpice() {
            return spice;
        }
    }

    private void backListener() { // go back to landing page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        LandingPage landingPage = new LandingPage();
        landingPage.start(stage);
    }

    private void measurementListener() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        MeasurementPage measurementPage = new MeasurementPage();
        try {
            measurementPage.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}