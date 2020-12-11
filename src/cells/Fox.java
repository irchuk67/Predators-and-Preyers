package cells;

public class Fox extends Predator {
    public Fox() {
        this.liveDuration = 14;
        this.reproductionTarget = 2;
        this.steps = 1;
    }

    private Fox(Fox fox) {
        this.steps = fox.steps;
        this.liveDuration = 14;
        this.isTracking = false;
        this.aim = null;
        this.reproductionTarget = fox.reproductionTarget;
        this.reproductionCounter = 0;
        this.i = fox.i;
        this.j = fox.j + 1;
    }

    @Override
    public Animal clone() {
        return new Fox(this);
    }
}
