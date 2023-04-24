import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
 
public class SpicePage extends Application {
    private final TableView<spiceInfo> table = new TableView<>();
    TableView<spiceInfo> selectedSpiceTable = new TableView<>();
    private final ObservableList<spiceInfo> data = FXCollections.observableArrayList( // populate table
        new spiceInfo("Salt", 1, 0.0),
        new spiceInfo("Pepper", 2, 0.0),
        new spiceInfo("Garlic Powder", 3, 0.0),
        new spiceInfo("Garlic", 4, 0.0),
        new spiceInfo("Cinnamon", 5, 0.0),
        new spiceInfo("Paprika", 6, 0.0),
        new spiceInfo("Curry Powder", 7, 0.0),
        new spiceInfo("Turmeric", 8, 0.0)
    );
    ArrayList<spiceInfo> rows = new ArrayList<>();

    
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

        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #1c1f21;");
        anchorPane.getChildren().add(borderPane);

        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Slider measurementSlider = new Slider(0, 10, 0);
        measurementSlider.setShowTickLabels(true);
        measurementSlider.setShowTickMarks(true);
        measurementSlider.setStyle("-fx-control-inner-background: #1c1f21; -fx-tick-label-fill: white; -fx-tick-mark-fill: white;");
        AnchorPane.setBottomAnchor(measurementSlider, 200.0); 
        AnchorPane.setLeftAnchor(measurementSlider, 140.0); 
        AnchorPane.setRightAnchor(measurementSlider, 140.0); 

        anchorPane.getChildren().add(measurementSlider);

        Label measurementDisplay = new Label();
        measurementDisplay.setText("Quantity: __ tsp");
        measurementDisplay.setTextFill(Color.WHITE);
        measurementDisplay.setFont(new Font("System Bold", 24.0));
        measurementDisplay.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setBottomAnchor(measurementDisplay, 150.0);
        AnchorPane.setLeftAnchor(measurementDisplay, 0.0);
        AnchorPane.setRightAnchor(measurementDisplay, 0.0);
        measurementDisplay.setAlignment(Pos.CENTER);
        measurementDisplay.setPrefWidth(358.0);
        measurementDisplay.setPrefHeight(40.0);

        anchorPane.getChildren().add(measurementDisplay);

        ArrayList<TableColumn<spiceInfo, String>> spiceColumn = new ArrayList<>();
        ArrayList<TableColumn<spiceInfo, Integer>> mapColumn = new ArrayList<>();
        ArrayList<TableColumn<spiceInfo, Double>> quantityColumn = new ArrayList<>();
        
        TableColumn<spiceInfo, String> spiceColFinal = new TableColumn<>("Spice");
        spiceColFinal.setStyle("-fx-alignment: CENTER");
        spiceColFinal.setCellValueFactory(new PropertyValueFactory<>("Spice"));

        TableColumn<spiceInfo, Integer> mapCol = new TableColumn<>("Mapping");
        mapCol.setStyle("-fx-alignment: CENTER");
        mapCol.setCellValueFactory(new PropertyValueFactory<>("Mapping"));

        TableColumn<spiceInfo, Double> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setStyle("-fx-alignment: CENTER");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        spiceColumn.add(spiceColFinal);
        mapColumn.add(mapCol);
        quantityColumn.add(quantityCol);

        selectedSpiceTable.getColumns().addAll(spiceColumn);
        selectedSpiceTable.getColumns().addAll(mapColumn);
        selectedSpiceTable.getColumns().addAll(quantityColumn);

        //selectedSpiceTable.setItems(data);
        selectedSpiceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        selectedSpiceTable.setPrefWidth(300);
        selectedSpiceTable.setPrefHeight(400);

        Label spiceInstruction = new Label();
        spiceInstruction.setText("Choose spices below to dispense and measure the quantities for each in tsp.");
        spiceInstruction.setTextFill(Color.WHITE);
        spiceInstruction.setFont(new Font("System Bold", 24.0));
        spiceInstruction.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(spiceInstruction, 0.0);
        AnchorPane.setRightAnchor(spiceInstruction, 0.0);
        spiceInstruction.setAlignment(Pos.CENTER);
        spiceInstruction.setPrefWidth(358.0);
        spiceInstruction.setPrefHeight(80.0);

        anchorPane.getChildren().add(spiceInstruction);
        
        AnchorPane.setTopAnchor(borderPane, 160.0);
        AnchorPane.setLeftAnchor(borderPane, 160.0);
        AnchorPane.setRightAnchor(borderPane, 160.0);
        AnchorPane.setBottomAnchor(borderPane, 300.0);

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
            homeListener();
        });
        anchorPane.getChildren().add(backButton);

        anchorPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            backButton.setLayoutX(10);
        });
        
        anchorPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            backButton.setLayoutY(newHeight.doubleValue() - backButton.getPrefHeight() - 10);
        });

        Button viewSpicesButton = new Button("→"); // back button to go back to landing page
        viewSpicesButton.setLayoutX(anchorPane.getWidth() - viewSpicesButton.getPrefWidth() - 10);
        viewSpicesButton.setLayoutY(anchorPane.getHeight() - viewSpicesButton.getPrefHeight() - 10);
        viewSpicesButton.setPrefWidth(100.0);
        viewSpicesButton.setPrefHeight(100.0);
        viewSpicesButton.setTextFill(Color.WHITE);
        viewSpicesButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        viewSpicesButton.setFont(new Font("System Bold", 44.0));
        viewSpicesButton.setOnAction(event -> {
            primaryStage.close();
            viewSpices();
        });
        anchorPane.getChildren().add(viewSpicesButton);

        anchorPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            viewSpicesButton.setLayoutX(anchorPane.getWidth() - viewSpicesButton.getPrefWidth() - 10);
        });
        
        anchorPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            viewSpicesButton.setLayoutY(newHeight.doubleValue() - viewSpicesButton.getPrefHeight() - 10);
        });

        ObservableList<spiceInfo> selectedSpicesList = FXCollections.observableArrayList();

        Button addSpice = new Button("Add Spice"); // add spice
        addSpice.setPrefWidth(50.0);
        addSpice.setPrefHeight(50.0);
        addSpice.setStyle("-fx-cursor: hand;");
        addSpice.setFont(new Font("System Bold", 33.0));
        addSpice.setOnAction(event -> {
            spiceInfo selectedSpice = table.getSelectionModel().getSelectedItem();
            if (selectedSpice != null) {
                //double measurement = measurementSlider.getValue();
                boolean exists = false;
                for (spiceInfo spice : selectedSpicesList) {
                    if (spice.getSpice().equals(selectedSpice.getSpice())) {
                        spice.setQuantity(measurementSlider.getValue());
                        //spice.setQuantity(measurement);
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    String spiceName = selectedSpice.getSpice();
                    int mapValue = selectedSpice.getMapping();
                    double measurement = Math.floor(measurementSlider.getValue() * 100) / 100;
                    selectedSpicesList.add(new spiceInfo(spiceName, mapValue, measurement)); // obtaining user's selected spices
                    //System.out.println()
                }
            }

            //ArrayList<spiceInfo> rows = new ArrayList<>();

            ObservableList<spiceInfo> items = selectedSpiceTable.getItems();

            for (spiceInfo item : items) {
                rows.add(item);
                System.out.println("Added item: " + item);
            }

            Collections.sort(rows);

            System.out.println("Selected spice data: ");
            for (spiceInfo row : rows) {
                System.out.println(row.toString());
            }

            System.out.println("");

            List<Integer> mappingValues = new ArrayList<>();

            for (spiceInfo item : items) {
                int mappingValue = item.getMapping();
                mappingValues.add(mappingValue);
            }

            Collections.sort(mappingValues);

            System.out.println("Sorted mapping values: " + mappingValues);

            System.out.println("");
        });

        selectedSpiceTable.setItems(selectedSpicesList); // add selected data to the table
        
        AnchorPane.setBottomAnchor(addSpice, 10.0);
        AnchorPane.setLeftAnchor(addSpice, 400.0);
        AnchorPane.setRightAnchor(addSpice, 400.0);
        anchorPane.getChildren().add(addSpice);

        measurementSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            measurementDisplay.setText("Quantity: " + String.format("%.2f", newValue.doubleValue()) + " tsp");
        });

        Scene scene = new Scene(anchorPane, 1024, 600);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class spiceInfo implements Comparable<spiceInfo> { // class to hold all necessary spice data
        private String spice;
        private int mapping;
        private double quantity;

        public spiceInfo(String spice, int mapping, double quantity) {
            this.spice = spice;
            this.mapping = mapping;
            this.quantity = quantity;
        }

        public String getSpice() {
            return spice;
        }

        public double getQuantity() {
            return quantity;
        }

        public int getMapping() {
            return mapping;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "Spice: " + spice + ", Mapping: " + mapping + ", Quantity: " + quantity;
        }

        @Override
        public int compareTo(spiceInfo other) {
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

        Button backButton = new Button("←"); // back button to go back to landing page
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

        ArrayList<spiceInfo> rows = new ArrayList<>();

        ObservableList<spiceInfo> items = selectedSpiceTable.getItems();

        for (spiceInfo item : items) {
            rows.add(item);
            System.out.println("Added item: " + item);
        }

        Collections.sort(rows);

        System.out.println("ACTUAL TABLE: \n");

        System.out.println("Selected spice data: ");
        for (spiceInfo row : rows) {
            System.out.println(row.toString());
        }

        System.out.println("");

        borderPane.setCenter(selectedSpiceTable);

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
            ObservableList<spiceInfo> data = selectedSpiceTable.getItems();
            data.clear(); // clear data
        });

        optionBox.getChildren().addAll(yes, no);

        anchorPane.getChildren().add(optionBox);
        AnchorPane.setBottomAnchor(optionBox, 50.0);
        AnchorPane.setLeftAnchor(optionBox, 150.0);
        AnchorPane.setRightAnchor(optionBox, 150.0);

        Scene scene = new Scene(anchorPane, 1024, 600);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();
    }

    private void backListener() { // go back to spice page
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        SpicePage spicePage = new SpicePage();
        spicePage.start(stage);
    }

    private void homeListener() { // go back to landing page
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