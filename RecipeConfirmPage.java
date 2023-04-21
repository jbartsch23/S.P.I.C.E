import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import javafx.geometry.Pos;

public class RecipeConfirmPage extends Application {
    private final TableView<confirmRecipeInfo> table = new TableView<>();
    private final ObservableList<confirmRecipeInfo> data = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<TableColumn<confirmRecipeInfo, String>> columns = new ArrayList<>();

        TableColumn<confirmRecipeInfo, String> spiceCol1 = new TableColumn<>("Spice 1");
        spiceCol1.setStyle("-fx-alignment: CENTER");
        spiceCol1.setCellValueFactory(new PropertyValueFactory<>("Spice 1"));

        TableColumn<confirmRecipeInfo, String> spiceCol2 = new TableColumn<>("Spice 2");
        spiceCol2.setStyle("-fx-alignment: CENTER");
        spiceCol2.setCellValueFactory(new PropertyValueFactory<>("Spice 2"));

        TableColumn<confirmRecipeInfo, String> spiceCol3 = new TableColumn<>("Spice 3");
        spiceCol3.setStyle("-fx-alignment: CENTER");
        spiceCol3.setCellValueFactory(new PropertyValueFactory<>("Spice 3"));

        TableColumn<confirmRecipeInfo, String> quantityCol1 = new TableColumn<>("Quantity 1");
        quantityCol1.setStyle("-fx-alignment: CENTER");
        quantityCol1.setCellValueFactory(new PropertyValueFactory<>("Quantity 1"));

        TableColumn<confirmRecipeInfo, String> quantityCol2 = new TableColumn<>("Quantity 2");
        quantityCol2.setStyle("-fx-alignment: CENTER");
        quantityCol2.setCellValueFactory(new PropertyValueFactory<>("Quantity 2"));

        TableColumn<confirmRecipeInfo, String> quantityCol3 = new TableColumn<>("Quantity 3");
        quantityCol3.setStyle("-fx-alignment: CENTER");
        quantityCol3.setCellValueFactory(new PropertyValueFactory<>("Quantity 3"));

        columns.add(spiceCol1);
        columns.add(spiceCol2);
        columns.add(spiceCol3);
        columns.add(quantityCol1);
        columns.add(quantityCol2);
        columns.add(quantityCol3);

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
        
        Label confirmInstruction = new Label();
        confirmInstruction.setText("Do you want to dispense the following spices?");
        confirmInstruction.setTextFill(Color.WHITE);
        confirmInstruction.setFont(new Font("System Bold", 36.0));
        confirmInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(confirmInstruction, 0.0);
        AnchorPane.setRightAnchor(confirmInstruction, 0.0);
        confirmInstruction.setAlignment(Pos.CENTER);
        confirmInstruction.setPrefWidth(358.0);
        confirmInstruction.setPrefHeight(40.0);

        anchorPane.getChildren().add(confirmInstruction);

        Button backButton = new Button("←"); // back button to go back to recipes page
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

    public class confirmRecipeInfo { // class to hold all necessary spice data
        private String spice1;
        private String spice2;
        private String spice3;
        private double quantity1;
        private double quantity2;
        private double quantity3;

        public confirmRecipeInfo(String spice1, String spice2, String spice3, double quantity1, double quantity2, double quantity3) {
            this.spice1 = spice1;
            this.spice2 = spice2;
            this.spice3 = spice3;
            this.quantity1 = quantity1;
            this.quantity2 = quantity2;
            this.quantity3 = quantity3;
        }

        public String getSpice1() {
            return spice1;
        }

        public String getSpice2() {
            return spice2;
        }

        public String getSpice3() {
            return spice3;
        }

        public double getQuantity1() {
            return quantity1;
        }

        public double getQuantity2() {
            return quantity2;
        }

        public double getQuantity3() {
            return quantity3;
        }
    }

    private void backListener() { // go back to recipes page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        RecipePage recipePage = new RecipePage();
        recipePage.start(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}