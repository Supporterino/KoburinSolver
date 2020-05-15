package components;

import java.util.Arrays;

public class Board {
    private Cell[][] internalBoard;
    private int dimension;

    public Board(int dimension) {
        this.dimension = dimension;
    }

    public void initBoard() {
        internalBoard = new Cell[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                internalBoard[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= dimension || y >= dimension) return null;
        else return internalBoard[x][y];
    }

    public boolean blackCell(int x, int y) {
        if (x >= 0 && y >= 0 && x <= dimension && y <= dimension && internalBoard[x][y].isCanBeBlack()) {
            internalBoard[x][y].setBlackend(true);
            return true;
        }
        return false;
    }

    public Cell[][] getInternalBoard() {
        return internalBoard;
    }

    public void setInternalBoard(Cell[][] internalBoard) {
        this.internalBoard = internalBoard;
    }

    private String printBoard() {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        for (int i = 0; i < dimension; i++) {
            str.append("{ ");
            Cell[] line = internalBoard[i];
            for (int j = 0; j < dimension; j++) {
                str.append(line[j].toString());
                if (dimension - j > 1) str.append(", ");
            }
            if (dimension - i > 1) str.append(", ");
            str.append("\n");
        }
        return str.toString();
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "Board{" +
                "internalBoard=" + printBoard() +
                ", dimension=" + dimension +
                '}';
    }
}
