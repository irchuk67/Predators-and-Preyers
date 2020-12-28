package userInterface;

import cells.Deer;
import cells.Food;
import cells.Fox;
import cells.Hare;
import cells.Lynx;
import cells.Sheep;
import cells.Wolf;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import managers.Controller;
import managers.MapManager;
import visualComponents.Map;
import visualComponents.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class GUI extends Application {
    private static final Color red = Color.RED;
    private static final Color black = Color.BLACK;
    private static final Color green = Color.GREEN;
    private static final Color yellow = Color.YELLOW;
    private static final Color blue = Color.BLUE;
    private static final Color white = Color.WHITE;
    private static final Color purple = Color.PURPLE;
    private static final Color cyan = Color.CYAN;
    private static final Color grey = Color.GREY;
    private static List<Text> toRemove = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage startStage = intro(primaryStage);
        startStage.show();
        primaryStage.setTitle("Predators and Preys");
        startStage.setOnHidden(windowEvent -> {
            primaryStage.show();
        });
    }

    public Stage intro(Stage primaryStage) {
        Statistics statistics = Statistics.getInstance();
        Stage stageStart = new Stage();
        stageStart.setTitle("Predators and Preys");
        Text intro1 = new Text();
        intro1.setText("Hello! It`s our Predators and Preys program");
        intro1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
        Text inputDimension = new Text("Map dimension");
        TextField dimension = new TextField();
        Text inputPreyType = new Text("Preys type");
        ChoiceBox preyType = new ChoiceBox();
        preyType.getItems().addAll("Hare", "Sheep", "Deer");
        Text inputPredatorType = new Text("Predators type");
        ChoiceBox predatorType = new ChoiceBox();
        predatorType.getItems().addAll("Fox", "Lynx", "Wolf");
        Button submitButton = new Button("Submit");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(inputDimension, 0, 2);
        gridPane.add(dimension, 1, 2);
        gridPane.add(inputPreyType, 0, 3);
        gridPane.add(preyType, 1, 3);
        gridPane.add(inputPredatorType, 0, 4);
        gridPane.add(predatorType, 1, 4);
        gridPane.add(submitButton, 1, 5);
        submitButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        inputPreyType.setStyle("-fx-font:  normal 20px 'sans-serif' ");
        inputPredatorType.setStyle("-fx-font:  normal 20px 'sans-serif' ");
        inputDimension.setStyle("-fx-font:  normal 20px 'sans-serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");
        Scene scene = new Scene(gridPane, 500, 350);
        stageStart.setTitle("Predators and Preys");
        stageStart.setScene(scene);
        stageStart.initStyle(StageStyle.DECORATED);
        submitButton.setCursor(Cursor.HAND);
        submitButton.setOnAction(event -> {
            Map map = Map.getInstance();
            int mapSize = Integer.parseInt(dimension.getText());
            String typePrey = preyType.getValue().toString();
            String typePredator = predatorType.getValue().toString();
            Controller controller = Controller.getInstance();
            controller.startSimulation(mapSize, typePrey, typePredator);
            fillPrimaryStage(primaryStage);
            stageStart.close();
        });
        return stageStart;
    }

    public void fillPrimaryStage(Stage primaryStage) {
        Statistics statistics = Statistics.getInstance();
        Map map = Map.getInstance();
        int mapSize = map.getMapSize();
        Color[][] colorGrid = fillColorMap(map);
        GridPane mapPane = new GridPane();
        GridPane rightSide = new GridPane();
        BorderPane parentPane = new BorderPane();
        Scene scene = new Scene(parentPane, 1200, 750);
        parentPane.setStyle("-fx-background-color: " + toRGBCode(Color.WHITE));
        Button buttonStart = new Button();
        buttonStart.setText("Go simulation");
        buttonStart.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        rightSide.add(buttonStart, 4, 0);
        parentPane.setRight(rightSide);
        BorderPane.setAlignment(rightSide, Pos.CENTER_RIGHT);
        parentPane.setLeft(mapPane);

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Rectangle rect = new Rectangle(20, 20);
                rect.widthProperty()
                        .bind(Bindings
                                .when(scene.widthProperty().lessThanOrEqualTo(scene.heightProperty().subtract(25)))
                                .then(scene.widthProperty().divide(25))
                                .otherwise(scene.heightProperty().subtract(25).divide(20)));
                rect.heightProperty().bind(rect.widthProperty());
                rect.setStyle("-fx-fill: " + toRGBCode(colorGrid[i][j]));
                mapPane.add(rect, j, i);
            }
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle("Predators and Preys");
        buttonStart.setCursor(Cursor.HAND);
        AtomicInteger stepCounter = new AtomicInteger();
        buttonStart.setOnAction(event -> {
            MapManager mapManager = new MapManager();
            stepCounter.getAndIncrement();
            if(stepCounter.get() % 3 == 0 && stepCounter.get() != 0)
                mapManager.addNewGrass(map);
            Controller controller = Controller.getInstance();
            controller.startMoving(10, rightSide, mapPane);
        });
    }
    public static void rePrintMap(GridPane mapPane, Color[][] colorGrid) {
        Map map = Map.getInstance();
        int mapSize = map.getMapSize();
        ObservableList<Node> children = mapPane.getChildren();
        for(Node rect : children) {
            int row = mapPane.getRowIndex(rect);
            int col = mapPane.getColumnIndex(rect);
            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    if(row == i && col == j) {
                        rect.setStyle("-fx-fill: " + toRGBCode(colorGrid[i][j]));
                    }
                }
            }
        }

    }

    public static Color[][] fillColorMap(Map map){
        int mapSize = map.getMapSize();
        Color[][] colors = new Color[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map.getMap()[i][j] instanceof Food) {
                    colors[i][j] = green;
                } else if (map.getMap()[i][j] instanceof Deer) {
                    colors[i][j] = red;
                } else if (map.getMap()[i][j] instanceof Hare) {
                    colors[i][j] = blue;
                } else if (map.getMap()[i][j] instanceof Sheep) {
                    colors[i][j] = cyan;
                } else if (map.getMap()[i][j] instanceof Fox) {
                    colors[i][j] = yellow;
                } else if (map.getMap()[i][j] instanceof Lynx) {
                    colors[i][j] = purple;
                } else if (map.getMap()[i][j] instanceof Wolf) {
                    colors[i][j] = black;
                } else {
                    colors[i][j] = grey;
                }
            }
        }
        return colors;
    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}