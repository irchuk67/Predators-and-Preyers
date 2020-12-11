package cells;

import visualComponents.Map;

public class Sheep extends Pray {
    public Sheep() {
        this.liveDuration = 11;
        this.reproductionTarget = 3;
        this.steps = 1;
    }

    private Sheep(Sheep sheep) {
        this.steps = sheep.steps;
        this.liveDuration = 11;
        this.isTracking = false;
        this.aim = null;
        this.reproductionTarget = sheep.reproductionTarget;
        this.reproductionCounter = 0;
        this.i = sheep.i;
        this.j = sheep.j + 1;
    }

    @Override
    public Animal clone() {
        return new Sheep(this);
    }
}
