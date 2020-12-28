package userInterface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import managers.Controller;
import visualComponents.Map;
import visualComponents.Statistics;

public class GUI extends Application {

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

    private void fillPrimaryStage(Stage primaryStage) {
    }
}