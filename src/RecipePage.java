import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
 
public class RecipePage extends Application {
    private final TableView<recipeInfo> table = new TableView<>();
    private final ObservableList<recipeInfo> data = FXCollections.observableArrayList( // populate table
        new recipeInfo("Garlic Butter Chicken", "Salt", "Pepper", "Garlic", 0.50, 0.75, 0.25),
        new recipeInfo("Honey Garlic Pork", "Pepper", "Garlic Powder", "Garlic Cloves", 0.80, 0.30, 0.40)
    );

    @Override
    public void start(Stage primaryStage) {
        //primaryStage.setTitle("S.P.I.C.E.");
        ArrayList<TableColumn<recipeInfo, String>> columns = new ArrayList<>();
        
        TableColumn<recipeInfo, String> recipeCol = new TableColumn<>("Recipe Name");
        recipeCol.setStyle("-fx-alignment: CENTER");
        recipeCol.setCellValueFactory(new PropertyValueFactory<>("RecipeName"));

        columns.add(recipeCol);

        table.getColumns().addAll(columns);

        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(300);
        table.setPrefHeight(400);

        table.setRowFactory(event -> { // to fetch spice and quantity data for selected recipe
            TableRow<recipeInfo> row = new TableRow<>();
            row.setOnMouseClicked(click -> {
                if (click.getClickCount() == 1 && !row.isEmpty()) {
                    //recipeInfo selectedRecipe = row.getItem();
                    try {
                        primaryStage.close();
                        confirmListener();
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

        Label recipeInstruction = new Label();
        recipeInstruction.setText("Select spices based on a recipe below.");
        recipeInstruction.setTextFill(Color.WHITE);
        recipeInstruction.setFont(new Font("System Bold", 36.0));
        recipeInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(recipeInstruction, 0.0);
        AnchorPane.setRightAnchor(recipeInstruction, 0.0);
        recipeInstruction.setAlignment(Pos.CENTER);
        recipeInstruction.setPrefWidth(358.0);
        recipeInstruction.setPrefHeight(40.0);

        anchorPane.getChildren().add(recipeInstruction);

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

        Scene scene = new Scene(anchorPane, 1024, 600);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void backListener() { // go back to landing page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        LandingPage landingPage = new LandingPage();
        landingPage.start(stage);
    }

    private void confirmListener() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        RecipeConfirmPage recipeConfirmPage = new RecipeConfirmPage();
        try {
            recipeConfirmPage.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class recipeInfo { // class to hold all necessary recipe data
        private String RecipeName;
        private String Spice1;
        private String Spice2;
        private String Spice3;
        private double Quantity1;
        private double Quantity2;
        private double Quantity3;

        public recipeInfo(String RecipeName, String Spice1, String Spice2, String Spice3, double Quantity1, double Quantity2, double Quantity3) {
            this.RecipeName = RecipeName;
            this.Spice1 = Spice1;
            this.Spice2 = Spice2;
            this.Spice3 = Spice3;
            this.Quantity1 = Quantity1;
            this.Quantity2 = Quantity2;
            this.Quantity3 = Quantity3;
        }

        public String getRecipeName() {
            return RecipeName;
        }

        public String getSpice1() {
            return Spice1;
        }

        public String getSpice2() {
            return Spice2;
        }

        public String getSpice3() {
            return Spice3;
        }

        public double Quantity1() {
            return Quantity1;
        }

        public double Quantity2() {
            return Quantity2;
        }

        public double Quantity3() {
            return Quantity3;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}