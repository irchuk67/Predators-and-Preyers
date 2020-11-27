package visualComponents;

import cells.Cell;

public class Map {
    private Cell[][] map;
    private int mapSize;
    private int maxFood;
    private int maxPrays;
    private int maxPredators;
    private int objectsAmount;

    public Map(int mapSize){
        this.mapSize = mapSize;
        map = new Cell[mapSize][mapSize];
        maxFood = (mapSize * mapSize) / 5;
        maxPrays = maxFood / 4;
        maxPredators = maxPrays / 4;
        objectsAmount = maxFood + maxPrays + maxPredators;
    }

    public Cell[][] getMap() {
        return map;
    }

    public int getMapSize() {
        return mapSize;
    }

    public int getMaxFood() {
        return maxFood;
    }

    public int getMaxPrays() {
        return maxPrays;
    }

    public int getMaxPredators() {
        return maxPredators;
    }

    public int getObjectsAmount() {
        return objectsAmount;
    }
}
