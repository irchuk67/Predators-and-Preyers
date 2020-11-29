package cells;

import visualComponents.Map;

public class Predator extends Animal {

    public Predator(int liveDuration, int reproductionCounter) {
        super(liveDuration, reproductionCounter);
    }

    private Predator(Predator predator) {
        super(predator.liveDuration, predator.reproductionCounter);
        this.i = predator.i;
        this.j = predator.j;
        this.liveDuration = predator.liveDuration;
        this.reproductionCounter = predator.reproductionCounter;
    }

    @Override
    public Cell searchFood(Map map) {
        Cell food = null;
        int minDistance = map.getMapSize() * 2;
        int currDistance = 0;
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap().length; j++) {
                if (map.getMap()[i][j] instanceof Pray) {
                    currDistance = countDistance(i, j);
                    if (currDistance < minDistance) {
                        minDistance = currDistance;
                        food = map.getMap()[i][j];
                    }
                }
            }
        }
        return food;
    }

    @Override
    public void eat() {
        liveDuration += Pray.getHealthPoints();
    }

    @Override
    public void reproduction() {

    }

    @Override
    public Cell clone() {
        return new Predator(this);
    }

    @Override
    public String toString() {
        return "Predator{" +
                "reproductionCounter=" + reproductionCounter +
                ", i=" + i +
                ", j=" + j +
                ", liveDuration=" + liveDuration +
                '}';
    }
}
