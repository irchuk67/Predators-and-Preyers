package managers;

import userInterface.Console;
import visualComponents.Map;
import visualComponents.Statistics;

public class Controller {
    private CellManager cellManager = new CellManager();
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
        for(int step = 0; step < 10; step++) {
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            console.clean();
            simulation.moveMap(map);
            mapManager.changeMap(map);
            console.printMap(map);
            statistics.count(map);
            console.printStatistics();
        }
    }
}