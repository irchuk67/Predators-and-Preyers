package cells;

import visualComponents.Map;

public class Lynx extends Predator {
    public Lynx() {
        this.liveDuration = 13;
        this.reproductionTarget = 3;
        this.steps = 2;
    }

    private Lynx(Lynx lynx) {
        this.steps = lynx.steps;
        this.liveDuration = 13;
        this.isTracking = false;
        this.aim = null;
        this.reproductionTarget = lynx.reproductionTarget;
        this.reproductionCounter = 0;
        this.i = lynx.i;
        this.j = lynx.j + 1;
    }

    @Override
    public void move(Cell cell) {
        for(int i = 0; i < 2; i++) {
            super.move(cell);
        }
    }

    @Override
    public Animal clone() {
        return new Lynx(this);
    }
}
