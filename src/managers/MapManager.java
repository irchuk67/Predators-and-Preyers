package managers;

import visualComponents.Map;

public class MapManager {
    private final CellManager cellManager = new CellManager();

    public Map createMap(int mapSize) {
        return new Map(mapSize);
    }

    public void fillMap(Map map) {
        int foodCounter = 0, preyCounter = 0, predatorCounter = 0;
        for (int i = 0; i < map.getObjectsAmount(); i++) {
            int indI = (int) (Math.random() * map.getMapSize());
            int indJ = (int) (Math.random() * map.getMapSize());
            if (map.getMap()[indI][indJ] == null) {
                if (foodCounter < map.getMaxFood()) {
                    map.getMap()[indI][indJ] = cellManager.createFood(6);
                    foodCounter++;
                    continue;
                }
                if (preyCounter < map.getMaxPrays()) {
                    map.getMap()[indI][indJ] = cellManager.createPray(9, 3);
                    preyCounter++;
                    continue;
                }
                if (predatorCounter < map.getMaxPredators()) {
                    map.getMap()[indI][indJ] = cellManager.createPredator(12, 3);
                    predatorCounter++;
                }
            } else {
                i--;
            }
        }
    }
}