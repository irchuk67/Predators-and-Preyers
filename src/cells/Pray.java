package cells;

import visualComponents.Map;

public class Pray extends Animal {
    private static final int healthPoints = 3;

    public Pray(int liveDuration, int reproductionCounter) {
        super(liveDuration, reproductionCounter);
    }

    @Override

    public Cell searchFood(Map map) {
        Cell food = null;
        int minDistance = 0, currDistance;
        for(int i = 0; i < map.getMap().length; i++){
            for(int j = 0; j < map.getMap().length; j++){
                if(map.getMap()[i][j] instanceof Food){
                    currDistance = countDistance(i, j);
                    if(currDistance < minDistance){
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
    }

    @Override
    public void reproduction() {

    }

    public static int getHealthPoints() {
        return healthPoints;
    }
}
