package managers;

import cells.Animal;
import cells.Cell;
import cells.Food;
import cells.Pray;
import cells.Predator;
import userInterface.Console;
import visualComponents.Map;
import visualComponents.Statistics;

import java.util.ArrayList;
import java.util.List;

public class MapManager {
    private final CellFactory cellFactory = new CellFactory();
    private final Console console = new Console();

    public void fillMap(Map map) {
        int typePray = console.prayOnMap();
        int typePredator = console.predatorOnMap();
        int foodCounter = 0, preyCounter = 0, predatorCounter = 0;
        for (int i = 0; i < map.getObjectsAmount(); i++) {
            int indI = (int) (Math.random() * map.getMapSize());
            int indJ = (int) (Math.random() * map.getMapSize());
            if (map.getMap()[indI][indJ] == null) {
                if (foodCounter < map.getMaxFood()) {
                    map.getMap()[indI][indJ] = cellFactory.createFood();
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setInListGrass((Food) map.getMap()[indI][indJ]);
                    foodCounter++;
                    continue;
                }
                if (preyCounter < map.getMaxPrays()) {
                    map.getMap()[indI][indJ] = cellFactory.createPray(typePray);
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setCellInListAnimal((Animal) map.getMap()[indI][indJ]);
                    preyCounter++;
                    continue;
                }
                if (predatorCounter < map.getMaxPredators()) {
                    map.getMap()[indI][indJ] = cellFactory.createPredator(typePredator);
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setCellInListAnimal((Animal) map.getMap()[indI][indJ]);
                    predatorCounter++;
                }
            } else {
                i--;
            }
        }
    }

    public void fillMap(Map map, String preyType, String predatorType) {//overloaded for GUI
        Statistics statistics = Statistics.getInstance();
        System.out.println("fill the map");
        int foodCounter = 0, preyCounter = 0, predatorCounter = 0;
        for (int i = 0; i < map.getObjectsAmount(); i++) {
            int indI = (int) (Math.random() * map.getMapSize());
            int indJ = (int) (Math.random() * map.getMapSize());
            if (map.getMap()[indI][indJ] == null) {
                if (foodCounter < map.getMaxFood()) {
                    map.getMap()[indI][indJ] = cellFactory.createFood();
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setInListGrass((Food) map.getMap()[indI][indJ]);
                    foodCounter++;
                    continue;
                }
                if (preyCounter < map.getMaxPrays()) {
                    map.getMap()[indI][indJ] = cellFactory.createPray(preyType);
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setCellInListAnimal((Animal) map.getMap()[indI][indJ]);
                    preyCounter++;
                    continue;
                }
                if (predatorCounter < map.getMaxPredators()) {
                    map.getMap()[indI][indJ] = cellFactory.createPredator(predatorType);
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setCellInListAnimal((Animal) map.getMap()[indI][indJ]);
                    predatorCounter++;
                }
            } else {
                i--;
            }
        }
    }

    public void removeFoodFromList(Cell food, Map map) {
        map.getGrass().remove(food);
    }
    public void cleanEmpty(Map map) {
        List<Food> toRemoveFood = new ArrayList<>();
        List<Animal> toRemoveAnimals = new ArrayList<>();
        Statistics statistics = Statistics.getInstance();
        for (int i = 0; i < map.getGrass().size(); i++) {
            if (map.getGrass().get(i).getLiveDuration() <= 0) {
                toRemoveFood.add(map.getGrass().get(i));
            }
        }
        for (int i = 0; i < map.getAnimals().size(); i++) {
            if (map.getAnimals().get(i).getLiveDuration() <= 0) {
                toRemoveAnimals.add(map.getAnimals().get(i));
                if(map.getAnimals().get(i) instanceof Pray){
                    statistics.addDeadPray(1);
                }
            }
        }
        map.getAnimals().removeAll(toRemoveAnimals);
        map.getGrass().removeAll(toRemoveFood);
        if(map.getAnimals().size() == 0) {
            return;
        }
    }

    public void changeMap(Map map) {
        int size = map.getMapSize();
        Cell[][] newCells = new Cell[size][size];
        for (int k = 0; k < map.getGrass().size(); k++) {
            Cell cell = map.getGrass().get(k);
            if(cell.getLiveDuration()>0){ newCells[cell.getI()][cell.getJ()] = cell;}
        }
        for (int k = 0; k < map.getAnimals().size(); k++) {
            Cell cell = map.getAnimals().get(k);
            if (cell.getI() > size) {
                cell.setCoordinates((cell.getI() - 2), cell.getJ());
            } else if (cell.getJ() > size) {
                cell.setCoordinates(cell.getI(), cell.getJ() - 2);
            }
            newCells[cell.getI()][cell.getJ()] = cell;
        }
        map.setMap(newCells);
    }

    public void addBorn(Map map){
        int length = map.getAnimals().size();
        for(int i = 0; i < length; i++) {
            if(map.getAnimals().get(i).getReproductionCounter() == map.getAnimals().get(i).getReproductionTarget()){
                Animal animal = map.getAnimals().get(i).clone();
                int indI = (int) (Math.random() * map.getMapSize());
                int indJ = (int) (Math.random() * map.getMapSize());
                if(map.getMap()[indI][indJ] == null){
                    animal.setCoordinates(indI, indJ);
                    map.getAnimals().get(i).setReproductionCounter(0);
                    map.setCellInListAnimal(animal);
                }
                else {
                    i--;
                }
            }
        }
    }

    public void addNewGrass(Map map) {
        int foodCounter = 0;
        for (int i = 0; i < map.getMaxFood()/2; i++) {
            int indI = (int) (Math.random() * map.getMapSize());
            int indJ = (int) (Math.random() * map.getMapSize());
            if (map.getMap()[indI][indJ] == null) {
                if (foodCounter < map.getMaxFood()) {
                    Food food = new Food();
                    food.setCoordinates(indI, indJ);
                    map.setInListGrass(food);
                }
            } else {
                i--;
            }
        }
    }

}