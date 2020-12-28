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
            statistics.setPrey(typePrey);
            String typePredator = predatorType.getValue().toString();
            statistics.setPredator(typePredator);
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
        constructStatistics(rightSide);
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

        Button statisticsButton = new Button("Show graphs");
        statisticsButton.setCursor(Cursor.HAND);
        statisticsButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        rightSide.add(statisticsButton, 0, 6);
        statisticsButton.setOnAction(event -> {
            Stage graphStage = graphStage();
            graphStage.show();
        });
    }

    public static Stage graphStage() {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        Statistics statistics = Statistics.getInstance();
        Scene scene = new Scene(root, 700, 550);


        List<Integer> animalsNumber = statistics.getAmountAnimalList();
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of animals");
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < animalsNumber.size(); i++) {
            series.getData().add(new XYChart.Data((i + 1), animalsNumber.get(i)));
        }
        lineChart.getData().add(series);


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Food", statistics.getFood()),
                        new PieChart.Data("Preys", statistics.getPreys()),
                        new PieChart.Data("Predators", statistics.getPredators()));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Current objects on Map");

        root.add(lineChart, 0, 0);
        root.add(chart, 1, 0);
        stage.setScene(scene);
        stage.setTitle("Graphs");
        return stage;
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

    public static void constructStatistics(GridPane pane) {
        Text currentDataText = new Text("Current data: ");
        Text foodName = new Text("Food");
        Text preysName = new Text("Preys");
        Text predatorsName = new Text("Predators");

        Text startText = new Text("Start  ");
        Text deadText = new Text("Dead  ");
        Text bornText = new Text("Born  ");

        currentDataText.setStyle("-fx-font: normal bold 24px 'sans-serif' ");
        foodName.setStyle("-fx-font: normal 20px 'sans-serif' ");
        preysName.setStyle("-fx-font: normal 20px 'sans-serif' ");
        predatorsName.setStyle("-fx-font: normal 20px 'sans-serif' ");
        startText.setStyle("-fx-font: normal 20px 'sans-serif' ");
        deadText.setStyle("-fx-font: normal 20px 'sans-serif' ");
        bornText.setStyle("-fx-font: normal 20px 'sans-serif' ");

        pane.add(currentDataText, 0, 0);
        pane.add(foodName, 0, 2);
        pane.add(preysName, 0, 3);
        pane.add(predatorsName, 0, 4);

        pane.add(startText, 1,1);
        pane.add(deadText, 2,1);
        pane.add(bornText, 3,1);
        fullStatistics(pane);

    }

    public static void fullStatistics(GridPane pane) {
        pane.getChildren().removeAll(toRemove);
        toRemove.clear();
        Map map = Map.getInstance();
        Statistics statistics = Statistics.getInstance();
        statistics.count(map);
        Text startFood = new Text(statistics.getStartFood().toString());
        Text startPreys = new Text(statistics.getStartPreys().toString());
        Text startPredators = new Text(statistics.getStartPredators().toString());
        Text deadFood = new Text(statistics.getEatenFood().toString());
        Text deadPreys = new Text(statistics.getDeadPray().toString());
        Text deadPredators = new Text(statistics.getDeadPredators().toString());
        Text bornFood = new Text(statistics.getBornFood().toString());
        Text bornPreys = new Text(statistics.getBornPreys().toString());
        Text bornPredators = new Text(statistics.getBornPredators().toString());
        toRemove.add(startFood);
        toRemove.add(startPreys);
        toRemove.add(startPredators);
        toRemove.add(deadFood);
        toRemove.add(deadPreys);
        toRemove.add(deadPredators);
        toRemove.add(bornFood);
        toRemove.add(bornPreys);
        toRemove.add(bornPredators);
        pane.add(startFood, 1,2);
        pane.add(startPreys, 1,3);
        pane.add(startPredators, 1,4);

        pane.add(deadFood, 2,2);
        pane.add(deadPreys, 2,3);
        pane.add(deadPredators, 2,4);

        pane.add(bornFood, 3,2);
        pane.add(bornPreys, 3,3);
        pane.add(bornPredators, 3,4);
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

