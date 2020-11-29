package cells;

public class Food extends Cell {
    private static final int healthPoints = 1;

    public Food(int liveDuration) {
        super(liveDuration);
    }

    private Food(Food food) {
        super(food.liveDuration);
        this.i = food.i;
        this.j = food.j;
        this.liveDuration = food.liveDuration;
    }

    public static int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public Cell clone() {
        return new Food(this);
    }

    @Override
    public String toString() {
        return "Food{" +
                "i=" + i +
                ", j=" + j +
                ", liveDuration=" + liveDuration +
                '}';
    }
}
