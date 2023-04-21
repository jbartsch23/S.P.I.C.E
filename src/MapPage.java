import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class MapPage extends Application {
    private final TableView<mapInfo> table = new TableView<>();
    private final ObservableList<mapInfo> data = FXCollections.observableArrayList( // populate table
        new mapInfo("Map 1"),
        new mapInfo("Map 2"),
        new mapInfo("Map 3"),
        new mapInfo("Map 4"),
        new mapInfo("Map 5"),
        new mapInfo("Map 6"),
        new mapInfo("Map 7"),
        new mapInfo("Map 8")
    );
    
    @Override
    public void start(Stage primaryStage) {
        ArrayList<TableColumn<mapInfo, String>> columns = new ArrayList<>();
        
        TableColumn<mapInfo, String> mapCol = new TableColumn<>("Mapping");
        mapCol.setStyle("-fx-alignment: CENTER");
        mapCol.setCellValueFactory(new PropertyValueFactory<>("Mapping"));

        columns.add(mapCol);

        table.getColumns().addAll(columns);

        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(300);
        table.setPrefHeight(400);
        
        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");
        anchorPane.getChildren().add(borderPane);

        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Label mapInstruction = new Label();
        mapInstruction.setText("Assign mappings to each of the spices.");
        mapInstruction.setTextFill(Color.WHITE);
        mapInstruction.setFont(new Font("System Bold", 36.0));
        mapInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(mapInstruction, 0.0);
        AnchorPane.setRightAnchor(mapInstruction, 0.0);
        mapInstruction.setAlignment(Pos.CENTER);
        mapInstruction.setPrefWidth(358.0);
        mapInstruction.setPrefHeight(80.0);

        anchorPane.getChildren().add(mapInstruction);

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

    public class mapInfo { // class to hold all necessary mapping data
        private String mapping;

        public mapInfo(String mapping) {
            this.mapping = mapping;
        }

        public String getMapping() {
            return mapping;
        }
    }

    private void backListener() { // go back to landing page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        LandingPage landingPage = new LandingPage();
        landingPage.start(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}