package cells;

import visualComponents.Map;

public abstract class Animal extends Cell {
    protected int reproductionTarget;
    protected int reproductionCounter;
    protected boolean isTracking;
    protected Cell aim;
    protected int steps;

    protected int countDistance(int i, int j) {
        return Math.abs(this.i - i) + Math.abs(this.j - j);
    }

    public abstract Cell searchFood(Map map);

    public abstract void eat();

    public void move(Cell cell) {
        if(this instanceof Pray){
            if(this.getAim() instanceof Food){
                step(cell);
            }
        } else if(this instanceof Predator){
            if(this.getAim() instanceof Pray){
                step(cell);
            }
        }
        if(cell == null) {
            i++;
        }
    }

    private void step(Cell cell) {
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

    public int getReproductionTarget() {
        return reproductionTarget;
    }

    public int getReproductionCounter() {
        return reproductionCounter;
    }

    public void setReproductionCounter(int reproductionCounter) {
        this.reproductionCounter = reproductionCounter;
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

    public int getSteps() {
        return steps;
    }

    public abstract Animal clone();
}