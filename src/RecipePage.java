import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
 
public class RecipePage extends Application {
    private final TableView<recipeInfo> table = new TableView<>();
    private final ObservableList<recipeInfo> data = FXCollections.observableArrayList( // populate table
        new recipeInfo("Garlic Butter Chicken", "Salt", "Pepper", "Garlic", 1, 2, 4, 0.50, 0.75, 0.25),
        new recipeInfo("Honey Garlic Pork", "Pepper", "Garlic Powder", "Garlic", 2, 3, 4, 0.80, 0.30, 0.40)
    );
    TableView<spicesQuantities> selectedRecipeTable = new TableView<>();
    ArrayList<spicesQuantities> rows = new ArrayList<>();

    //ArrayList<String> spices;
    //HashMap<Integer, Double> hashData = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
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

        ObservableList<spicesQuantities> selectedRecipeData = FXCollections.observableArrayList();

        ArrayList<TableColumn<spicesQuantities, String>> spiceColumns = new ArrayList<>();
        //ArrayList<TableColumn<spicesQuantities, Integer>> mapColumns = new ArrayList<>();
        ArrayList<TableColumn<spicesQuantities, Double>> quantityColumns = new ArrayList<>();

        TableColumn<spicesQuantities, String> spiceCol = new TableColumn<>("Spice");
        spiceCol.setStyle("-fx-alignment: CENTER");
        spiceCol.setCellValueFactory(new PropertyValueFactory<>("spice"));

        TableColumn<spicesQuantities, Integer> mapCol = new TableColumn<>("Mapping");
        mapCol.setStyle("-fx-alignment: CENTER");
        mapCol.setCellValueFactory(new PropertyValueFactory<>("mapping"));

        TableColumn<spicesQuantities, Double> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setStyle("-fx-alignment: CENTER");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        spiceColumns.add(spiceCol);
        //mapColumns.add(mapCol);
        quantityColumns.add(quantityCol);

        selectedRecipeTable.getColumns().addAll(spiceColumns);
        //selectedRecipeTable.getColumns().addAll(mapColumns);
        selectedRecipeTable.getColumns().addAll(quantityColumns);

        //selectedRecipeTable.getItems().add(selectedRecipe);

        selectedRecipeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        selectedRecipeTable.setPrefWidth(300);
        selectedRecipeTable.setPrefHeight(400);

        table.setRowFactory(event -> { // to fetch spice and quantity data for selected recipe
            TableRow<recipeInfo> row = new TableRow<>();
            row.setOnMouseClicked(click -> {
                if (click.getClickCount() == 1 && !row.isEmpty()) {
                    recipeInfo selectedRecipe = table.getSelectionModel().getSelectedItem();
                    /*for(MapPage.mapInfo map : MapPage.data) { // go through each mapping in data
                        hashData.put(map.getMapping(), 0.0);
                    }*/
                    if (selectedRecipe != null) {
                        //insertRecipe(selectedRecipe);
                        List<spicesQuantities> spicesQuantitiesList = selectedRecipe.getSpicesQuantities();
                        selectedRecipeData.setAll(spicesQuantitiesList);
                    }
                    try {
                        primaryStage.close();
                        viewSpices();
                        //confirmListener();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        selectedRecipeTable.setItems(selectedRecipeData);

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
            homeListener();
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
        RecipePage recipePage = new RecipePage();
        recipePage.start(stage);
    }

    public class recipeInfo { // class to hold all necessary recipe data
        private String RecipeName;
        private String spice1;
        private String spice2;
        private String spice3;
        private int mapping1;
        private int mapping2;
        private int mapping3;
        private double quantity1;
        private double quantity2;
        private double quantity3;
        private List<spicesQuantities> spicesQuantitiesList;

        public recipeInfo(String RecipeName, String spice1, String spice2, String spice3, int mapping1, int mapping2, int mapping3, double quantity1, double quantity2, double quantity3) {
            this.RecipeName = RecipeName;
            this.spice1 = spice1;
            this.spice2 = spice2;
            this.spice3 = spice3;
            this.mapping1 = mapping1;
            this.mapping2 = mapping2;
            this.mapping3 = mapping3;
            this.quantity1 = quantity1;
            this.quantity2 = quantity2;
            this.quantity3 = quantity3;
            this.spicesQuantitiesList = new ArrayList<>();
            this.spicesQuantitiesList.add(new spicesQuantities(spice1, mapping1, quantity1));
            this.spicesQuantitiesList.add(new spicesQuantities(spice2, mapping2, quantity2));
            this.spicesQuantitiesList.add(new spicesQuantities(spice3, mapping3, quantity3));
        }

        public String getRecipeName() {
            return RecipeName;
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

        public int getMapping1() {
            return mapping1;
        }

        public int getMapping2() {
            return mapping2;
        }

        public int getMapping3() {
            return mapping3;
        }

        public List<spicesQuantities> getSpicesQuantities() {
            return spicesQuantitiesList;
        }
    }

    public class spicesQuantities implements Comparable<spicesQuantities> {
        private String spice;
        private int mapping;
        private double quantity;

        public spicesQuantities(String spice, int mapping, double quantity) {
            this.spice = spice;
            this.mapping = mapping;
            this.quantity = quantity;
        }

        public String getSpice() {
            return spice;
        }

        public Integer getMapping() {
            return mapping;
        }

        public double getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Spice: " + spice + ", Mapping: " + mapping + ", Quantity: " + quantity;
        }

        @Override
        public int compareTo(spicesQuantities other) {
            // Compare by mapping value
            return Integer.compare(this.mapping, other.mapping);
        }
    }

    private void viewSpices() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);

        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");
        anchorPane.getChildren().add(borderPane);

        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Button backButton = new Button("←"); // back button to go back to recipe page
        backButton.setLayoutX(10);
        backButton.setLayoutY(anchorPane.getHeight() - backButton.getPrefHeight() - 10);
        backButton.setPrefWidth(100.0);
        backButton.setPrefHeight(100.0);
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        backButton.setFont(new Font("System Bold", 44.0));
        backButton.setOnAction(event -> {
            stage.close();
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

        borderPane.setCenter(selectedRecipeTable);

        HBox optionBox = new HBox();
        optionBox.setAlignment(Pos.CENTER);
        optionBox.setSpacing(300);

        Button yes = new Button("Yes"); // dispense spices
        yes.setOnAction(event -> {
            //SerialCommunication.testComm();
            stage.close();
            homeListener();
        });

        Button no = new Button("No"); // clear tableview data
        no.setOnAction(event -> {
            ObservableList<spicesQuantities> data = selectedRecipeTable.getItems();
            data.clear(); // clear data
        });

        optionBox.getChildren().addAll(yes, no);

        anchorPane.getChildren().add(optionBox);
        AnchorPane.setBottomAnchor(optionBox, 50.0);
        AnchorPane.setLeftAnchor(optionBox, 150.0);
        AnchorPane.setRightAnchor(optionBox, 150.0);

        ObservableList<spicesQuantities> items = selectedRecipeTable.getItems();

        for (spicesQuantities item : items) {
            rows.add(item);
            System.out.println("Added item: " + item);
        }

        Collections.sort(rows);

        System.out.println("Selected spice data: ");
        for (spicesQuantities row : rows) {
            System.out.println(row.toString());
        }

        System.out.println("");

        List<Integer> mappingValues = new ArrayList<>();

        for (spicesQuantities item : items) {
            int mappingValue = item.getMapping();
            mappingValues.add(mappingValue);
        }

        Collections.sort(mappingValues);

        System.out.println("Sorted mapping values: " + mappingValues);

        System.out.println("");

        Scene scene = new Scene(anchorPane, 1024, 600);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();
    }

    private void homeListener() {
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