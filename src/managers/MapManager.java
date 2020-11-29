package managers;

import cells.Animal;
import cells.Cell;
import cells.Food;
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
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setInListGrass((Food) map.getMap()[indI][indJ]);
                    foodCounter++;
                    continue;
                }
                if (preyCounter < map.getMaxPrays()) {
                    map.getMap()[indI][indJ] = cellManager.createPray(9, 3);
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setCellInListAnimal((Animal) map.getMap()[indI][indJ]);
                    preyCounter++;
                    continue;
                }
                if (predatorCounter < map.getMaxPredators()) {
                    map.getMap()[indI][indJ] = cellManager.createPredator(12, 3);
                    map.getMap()[indI][indJ].setCoordinates(indI, indJ);
                    map.setCellInListAnimal((Animal) map.getMap()[indI][indJ]);
                    predatorCounter++;
                }
            } else {
                i--;
            }
        }
    }

    public void removeAnimalsFromList(Cell animal, Map map) {
        map.getAnimals().remove(animal);
    }

    public void removeFoodFromList(Cell food, Map map) {
        map.getGrass().remove(food);
    }

    public void changeMap(Map map) {
        int size = map.getMapSize();
        Cell[][] newCells = new Cell[size][size];
        for (int k = 0; k < map.getGrass().size(); k++) {
            Cell cell = map.getGrass().get(k);
            newCells[cell.getI()][cell.getJ()] = cell;
        }
        for (int k = 0; k < map.getAnimals().size(); k++) {
            Cell cell = map.getAnimals().get(k);
            newCells[cell.getI()][cell.getJ()] = cell;
        }
        map.setMap(newCells);
    }

}