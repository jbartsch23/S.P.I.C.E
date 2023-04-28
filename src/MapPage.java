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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class MapPage extends Application {
    private final TableView<mapInfo> table = new TableView<>();
    public static final ObservableList<mapInfo> data = FXCollections.observableArrayList( // populate table
        new mapInfo("Salt", 1),
        new mapInfo("Pepper", 2),
        new mapInfo("Garlic Powder", 3),
        new mapInfo("Garlic", 4),
        new mapInfo("Cinnamon", 5),
        new mapInfo("Paprika", 6),
        new mapInfo("Curry Powder", 7),
        new mapInfo("Turmeric", 8)
    );
    
    @Override
    public void start(Stage primaryStage) {
        ArrayList<TableColumn<mapInfo, String>> spiceColumns = new ArrayList<>();
        ArrayList<TableColumn<mapInfo, Integer>> mapColumns = new ArrayList<>();
        
        TableColumn<mapInfo, String> spiceCol = new TableColumn<>("Spice");
        spiceCol.setStyle("-fx-alignment: CENTER; -fx-font-size: 16px");
        spiceCol.setCellValueFactory(new PropertyValueFactory<>("Spice"));

        TableColumn<mapInfo, Integer> mapCol = new TableColumn<>("Mapping");
        mapCol.setStyle("-fx-alignment: CENTER; -fx-font-size: 16px");
        mapCol.setCellValueFactory(new PropertyValueFactory<>("Mapping"));

        spiceColumns.add(spiceCol);
        mapColumns.add(mapCol);

        table.getColumns().addAll(spiceColumns);
        table.getColumns().addAll(mapColumns);

        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(300);
        table.setPrefHeight(400);
        table.setStyle("-fx-font-size: 16px");
        
        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");
        anchorPane.getChildren().add(borderPane);

        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Label mapInstruction = new Label();
        mapInstruction.setText("Mappings Table for Spices");
        mapInstruction.setTextFill(Color.WHITE);
        mapInstruction.setFont(new Font("System Bold", 36.0));
        mapInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(mapInstruction, 0.0);
        AnchorPane.setRightAnchor(mapInstruction, 0.0);
        mapInstruction.setAlignment(Pos.CENTER);
        mapInstruction.setPrefWidth(358.0);
        mapInstruction.setPrefHeight(80.0);

        anchorPane.getChildren().add(mapInstruction);

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

        AnchorPane.setTopAnchor(borderPane, 140.0);
        AnchorPane.setLeftAnchor(borderPane, 140.0);
        AnchorPane.setRightAnchor(borderPane, 140.0);
        AnchorPane.setBottomAnchor(borderPane, 140.0);

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
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class mapInfo { // class to hold all necessary mapping data
        private String spice;
        private int mapping;

        public mapInfo(String spice, int mapping) {
            this.spice = spice;
            this.mapping = mapping;
        }

        public String getSpice() {
            return spice;
        }

        public int getMapping() {
            return mapping;
        }
    }

    private void backListener() { // go back to landing page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        LandingPage landingPage = new LandingPage();
        landingPage.start(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}