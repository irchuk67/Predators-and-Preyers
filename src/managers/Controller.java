package managers;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import userInterface.Console;
import userInterface.GUI;
import visualComponents.Map;
import visualComponents.Statistics;

public class Controller {
    private static Controller instance;
    private CellFactory cellFactory = new CellFactory();
    private MapManager mapManager = new MapManager();
    private Console console = new Console();
    private Simulation simulation = new Simulation();
    private Statistics statistics = Statistics.getInstance();

    public void startSimulation(){
        console.startText();
        Map map = Map.getInstance();
        map.setProperties(console.mapSizeEnter());
        mapManager.fillMap(map);
        console.printMap(map);
        console.printStatus(map);
        startMoving(map);
    }

    public void startSimulation(int mapSize, String prayType, String predatorType){//overloaded for GUI
        Map map = Map.getInstance();
        map.setProperties(mapSize);
        mapManager.fillMap(map, prayType, predatorType);
        System.out.println("start simulation");
        statistics.setAmountInList(map.getAnimals());
    }

    public void startMoving(Map map){
        for(int step = 0; step < 50; step++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            console.clean();
            if(step % 3 == 0 && step != 0)
                mapManager.addNewGrass(map);
            simulation.moveMap(map);
            simulation.reduceLiveDurationMap(map);
            mapManager.cleanEmpty(map);
            mapManager.addBorn(map);
            mapManager.changeMap(map);
            console.printMap(map);
            statistics.count(map);
            console.printStatistics();
            if(statistics.getPreys() == 0) {
                console.endSimulation();
                System.exit(0);
            }
        }
    }

    public void startMoving(int pause, GridPane rightPane, GridPane mapPane){
        Map map = Map.getInstance();
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simulation.moveMap(map);
        simulation.reduceLiveDurationMap(map);
        mapManager.cleanEmpty(map);
        mapManager.addBorn(map);
        mapManager.changeMap(map);
        Color[][] colors = GUI.fillColorMap(map);
        GUI.fullStatistics(rightPane);
        GUI.rePrintMap(mapPane, colors);
        statistics.setAmountInList(map.getAnimals());
        if(statistics.getPreys() == 0) {
            return;
        }
    }

    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }
}

