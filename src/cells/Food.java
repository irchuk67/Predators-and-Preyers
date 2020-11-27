package cells;

public class Food extends Cell {
    private static final int healthPoints = 1;

    public Food(int liveDuration ){
        super(liveDuration);
    }

    public static int getHealthPoints() {
        return healthPoints;
    }
}
