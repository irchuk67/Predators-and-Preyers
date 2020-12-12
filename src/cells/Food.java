package cells;

public class Food extends Cell {
    private static final int healthPoints = 1;

    public Food(){
        this.liveDuration=6;
    }
    private Food(Food food) {
        this.i = food.i;
        this.j = food.j;
        this.liveDuration = food.liveDuration;
    }

    public static int getHealthPoints() {
        return healthPoints;
    }

    public Cell clone() {
        return new Food(this);
    }

}
