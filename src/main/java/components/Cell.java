package components;

public class Cell {
    private int x;
    private int y;
    private boolean isNumeric;
    private int value;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isNumeric() {
        return isNumeric;
    }

    public void setNumeric(boolean numeric, int val) {
        value = val;
        isNumeric = numeric;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", isNumeric=" + isNumeric +
                ", value=" + value +
                '}';
    }
}
