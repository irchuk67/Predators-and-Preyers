package managers;

import userInterface.Console;
import visualComponents.Map;

public class Controller {
    private CellManager cellManager = new CellManager();
    private MapManager mapManager = new MapManager();
    private Console console = new Console();

    public void startSimulation(){
        console.startText();
        Map map = mapManager.createMap(console.mapSizeEnter());
        mapManager.fillMap(map);
        console.printMap(map);
        console.printStatus(map);
    }
}