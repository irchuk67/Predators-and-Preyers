package managers;

import cells.*;
import visualComponents.Map;

public class CellFactory {

    public Pray createPray() {
        return new Pray();
    }

    public Predator createPredator() {
        return new Predator();
    }

    public Food createFood() {
        return new Food();
    }

    public void makeEmptyCell(Cell cell) throws Exception {
        if (cell instanceof Animal || cell instanceof Food) {
            cell = null;
        } else {
            throw new Exception("Not an object!");
        }
    }

    public void makeZeroLiveDurationNull(Map map) {
        for (int i = 0; i < map.getGrass().size(); i++) {
            if (map.getGrass().get(i).getLiveDuration() <= 0) {
                try {
                    makeEmptyCell(map.getGrass().get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        for (int i = 0; i < map.getAnimals().size(); i++) {
            if (map.getAnimals().get(i).getLiveDuration() <= 0) {
                try {
                    makeEmptyCell(map.getAnimals().get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}