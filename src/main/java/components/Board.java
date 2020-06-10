package components;
/* author = 4102770 */
public class Board {
    private Cell[][] internalBoard;
    private int dimension;
    private MersenneTwister random = new MersenneTwister();

    public Board(int dimension) {
        this.dimension = dimension;
    }

    public void initBoard() {
        internalBoard = new Cell[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                internalBoard[i][j] = new Cell(i, j, '_');
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= dimension || y >= dimension) return null;
        else return internalBoard[x][y];
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

    public void blackenAdjacentFields() {
        for (int y = 0; y < internalBoard.length; y++) {
            for (int x = 0; x < internalBoard[y].length; x++) {
                if (internalBoard[x][y].isNumber()) {
                    //System.out.println("-- Blackening: " + internalBoard[x][y]);
                    internalBoard[x][y].doBlackening(this);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "internalBoard=" + printBoard() +
                ", dimension=" + dimension +
                '}';
    }
}
