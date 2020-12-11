package cells;

import visualComponents.Map;

public class Pray extends Animal {
    private static final int healthPoints = 3;

    public Pray() {
    }

    private Pray(Pray pray) {
        this.steps = pray.steps;
        this.liveDuration = 0;
        this.isTracking = false;
        this.aim = null;
        this.reproductionTarget = pray.reproductionTarget;
        this.reproductionCounter = 0;
        this.i = pray.i;
        this.j = pray.j + 1;
    }

    @Override
    public Cell searchFood(Map map) {
        Cell food = null;
        int minDistance = map.getMapSize() * 2, currDistance;
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap().length; j++) {
                if (map.getMap()[i][j] instanceof Food) {
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
        liveDuration += Food.getHealthPoints();
        reproductionCounter++;
    }

    public static int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public Animal clone() {
        return new Pray(this);
    }

}
