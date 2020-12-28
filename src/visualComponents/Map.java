package visualComponents;

import cells.Animal;
import cells.Cell;
import cells.Food;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private static Map instance;
    private List<Animal> animals = new ArrayList<>();
    private List<Food> grass = new ArrayList<>();
    private Cell[][] map;
    private int mapSize;
    private int maxFood;
    private int maxPrays;
    private int maxPredators;
    private int objectsAmount;

    private Map() {
    }

    public void setProperties(int mapSize) {
        this.mapSize = mapSize;
        map = new Cell[mapSize][mapSize];
        maxFood = (mapSize * mapSize) / 8;
        maxPrays = maxFood / 4;
        maxPredators = maxPrays / 2;
        objectsAmount = maxFood + maxPrays + maxPredators;
    }


    public void setCellInListAnimal(Animal animal) {
        animals.add(animal);
    }

    public void setInListGrass(Food food) {
        grass.add(food);
    }

    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
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

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Food> getGrass() {
        return grass;
    }

    public static Map getInstance() {
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }
}