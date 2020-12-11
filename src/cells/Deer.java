package cells;

public class Deer extends Pray {

    public Deer() {
        this.liveDuration = 12;
        this.reproductionTarget = 4;
        this.steps = 2;
    }

    private Deer(Deer deer) {
        this.steps = deer.steps;
        this.liveDuration = 12;
        this.isTracking = false;
        this.aim = null;
        this.reproductionTarget = deer.reproductionTarget;
        this.reproductionCounter = 0;
        this.i = deer.i;
        this.j = deer.j + 1;
    }

    @Override
    public void move(Cell cell) {
        for(int i = 0; i < 2; i++) {
            super.move(cell);
        }
    }

    @Override
    public Animal clone() {
        return new Deer(this);
    }
}
