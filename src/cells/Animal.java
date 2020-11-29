package cells;

import visualComponents.Map;

public abstract class Animal extends Cell {
    protected int reproductionCounter;
    protected boolean isTracking;
    protected Cell aim;

    public Animal(int liveDuration, int reproductionCounter) {
        super(liveDuration);
        this.reproductionCounter = reproductionCounter;
    }

    protected int countDistance(int i, int j) {
        return Math.abs(this.i - i) + Math.abs(this.j - j);
    }

    public abstract Cell searchFood(Map map);

    public abstract void eat();

    public abstract void reproduction();

    public void move(Cell cell) {  //TODO: Exceptions in the next lab
        if (i < cell.getI()) {
            i++;
        } else if (i > cell.getI()) {
            i--;
        } else if (j < cell.getJ()) {
            j++;
        } else if (j > cell.getJ()) {
            j--;
        }
    }

    public int getReproductionCounter() {
        return reproductionCounter;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }

    public Cell getAim() {
        return aim;
    }

    public void setAim(Cell aim) {
        this.aim = aim;
    }
}
