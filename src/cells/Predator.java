package cells;

import visualComponents.Map;

public class Predator extends Animal {

    public Predator(int liveDuration, int reproductionCounter) {
        super(liveDuration, reproductionCounter);
    }

    @Override
    public Cell searchFood(Map map) {
        Cell food = null;
        int minDistance = 0, currDistance;
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
}
