package cells;

import visualComponents.Map;

public class Predator extends Animal {

    public Predator() {
    }

    private Predator(Predator predator) {
        this.i = predator.i;
        this.j = predator.j + 1;
        this.liveDuration = predator.liveDuration;
        this.reproductionTarget = 0;
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
        reproductionCounter++;
    }

    @Override
    public Animal clone() {
        return new Predator(this);
    }

}
