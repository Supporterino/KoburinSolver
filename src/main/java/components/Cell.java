package components;

import java.util.ArrayList;

public class Cell {
    private int x;
    private int y;
    private boolean isNumeric = false;
    private int value;
    private boolean canBeBlack = true;
    private boolean blackend = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ArrayList<Cell> getSurroundingCells(Board board) {
        ArrayList<Cell> surroundingCells = new ArrayList<Cell>();

        Cell above = board.getCell(this.x, this.y - 1);
        Cell below = board.getCell(this.x, this.y + 1);
        Cell left = board.getCell(this.x - 1, this.y);
        Cell right = board.getCell(this.x + 1, this.y);

        if (above != null) surroundingCells.add(above);
        if (below != null) surroundingCells.add(below);
        if (left != null) surroundingCells.add(left);
        if (right != null) surroundingCells.add(right);

        return surroundingCells;
    }

    public boolean isNumeric() {
        return isNumeric;
    }

    public void setNumeric(boolean numeric, int val) {
        canBeBlack = false;
        value = val;
        isNumeric = numeric;
    }

    public boolean isCanBeBlack() {
        return canBeBlack;
    }

    public void setCanBeBlack(boolean canBeBlack) {
        this.canBeBlack = canBeBlack;
    }

    public boolean isBlackend() {
        return blackend;
    }

    public void setBlackend(boolean blackend) {
        if (blackend) canBeBlack = false;
        this.blackend = blackend;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", isNumeric=" + isNumeric +
                ", value=" + value +
                ", canBeBlack=" + canBeBlack +
                ", blackend=" + blackend +
                '}';
    }
}
