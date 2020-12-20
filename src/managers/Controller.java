package managers;

import userInterface.Console;
import visualComponents.Map;
import visualComponents.Statistics;

public class Controller {
    private CellFactory cellFactory = new CellFactory();
    private MapManager mapManager = new MapManager();
    private Console console = new Console();
    private Simulation simulation = new Simulation();
    private Statistics statistics = Statistics.getInstance();

    public void startSimulation(){
        console.startText();
        Map map = mapManager.createMap(console.mapSizeEnter());
        mapManager.fillMap(map);
        console.printMap(map);
        console.printStatus(map);
        startMoving(map);
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
            if(statistics.getPray() == 0) {
                console.endSimulation();
                System.exit(0);
            }
        }
    }
}