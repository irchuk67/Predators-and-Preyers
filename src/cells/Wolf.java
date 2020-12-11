package cells;

import visualComponents.Map;

public class Wolf extends Predator {
    public Wolf() {
        this.liveDuration = 15;
        this.reproductionTarget = 4;
        this.steps = 2;
    }

    private Wolf(Wolf wolf) {
        this.steps = wolf.steps;
        this.liveDuration = 15;
        this.isTracking = false;
        this.aim = null;
        this.reproductionTarget = wolf.reproductionTarget;
        this.reproductionCounter = 0;
        this.i = wolf.i;
        this.j = wolf.j + 1;
    }

    @Override
    public void move(Cell cell) {
        for(int i = 0; i < 2; i++) {
            super.move(cell);
        }
    }

    @Override
    public Animal clone() {
        return new Wolf(this);
    }
}
