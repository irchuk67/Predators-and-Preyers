package cells;

import visualComponents.Map;

public class Hare extends Pray {
    public Hare() {
        this.liveDuration = 8;
        this.reproductionTarget = 2;
        this.steps = 1;
    }

    private Hare(Hare hare) {
        this.steps = hare.steps;
        this.liveDuration = 8;
        this.isTracking = false;
        this.aim = null;
        this.reproductionTarget = hare.reproductionTarget;
        this.reproductionCounter = 0;
        this.i = hare.i;
        this.j = hare.j + 1;
    }

    @Override
    public Animal clone() {
        return new Hare(this);
    }
}
