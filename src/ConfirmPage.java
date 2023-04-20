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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class ConfirmPage extends Application {
    private final TableView<confirmSpiceInfo> table = new TableView<>();
    private final ObservableList<confirmSpiceInfo> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        TableColumn<confirmSpiceInfo, String> spiceCol = new TableColumn<>("Spice");
        spiceCol.setStyle("-fx-alignment: CENTER");
        spiceCol.setCellValueFactory(new PropertyValueFactory<>("Spice"));

        TableColumn<confirmSpiceInfo, String> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setStyle("-fx-alignment: CENTER");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        table.getColumns().addAll(spiceCol);
        table.getColumns().addAll(quantityCol);

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

        Label confirmInstruction = new Label();
        confirmInstruction.setText("Do you want to dispense the following spices?");
        confirmInstruction.setTextFill(Color.WHITE);
        confirmInstruction.setFont(new Font("System Bold", 36.0));
        confirmInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(confirmInstruction, 0.0);
        AnchorPane.setRightAnchor(confirmInstruction, 0.0);
        confirmInstruction.setAlignment(Pos.CENTER);
        confirmInstruction.setPrefWidth(358.0);
        confirmInstruction.setPrefHeight(80.0);

        anchorPane.getChildren().add(confirmInstruction);

        Image logo = new Image("logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setPreserveRatio(true);
        logoView.setFitWidth(200); // desired width of image
        logoView.setFitHeight(200); // desired height of image

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

        HBox optionBox = new HBox();
        optionBox.setAlignment(Pos.CENTER);
        optionBox.setSpacing(300);

        Button yes = new Button("Yes");
        yes.setOnAction(event -> {

        });

        Button no = new Button("No");
        no.setOnAction(event -> {

        });

        optionBox.getChildren().addAll(yes, no);

        anchorPane.getChildren().add(optionBox);
        AnchorPane.setBottomAnchor(optionBox, 50.0);
        AnchorPane.setLeftAnchor(optionBox, 150.0);
        AnchorPane.setRightAnchor(optionBox, 150.0);

        Scene scene = new Scene(anchorPane, 1024, 600);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void backListener() { // go back to landing page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        MeasurementPage measurementPage = new MeasurementPage();
        measurementPage.start(stage);
    }

    public class confirmSpiceInfo { // class to hold all necessary spice data
        private String spice;
        private double quantity;

        public confirmSpiceInfo(String spice, double quantity) {
            this.spice = spice;
            this.quantity = quantity;
        }

        public String getSpice() {
            return spice;
        }

        public double getQuantity() {
            return quantity;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}