package cells;

public abstract class Cell {
    protected int i;
    protected int j;
    protected int liveDuration;

    public Cell(int liveDuration) {
        this.liveDuration = liveDuration;
    }

    public int getLiveDuration() {
        return liveDuration;
    }

    public void setLiveDuration(int liveDuration) {
        this.liveDuration = liveDuration;
    }

    public int getI() {
        return i;
    }

    public void setCoordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getJ() {
        return j;
    }

    public abstract Cell clone();
}
