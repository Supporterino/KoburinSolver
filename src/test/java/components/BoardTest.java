package components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
/* author = 4102770 */
class BoardTest {
    private Board board = null;

    @BeforeEach
    void setup() {
        board = new Board(2);
    }

    @Test
    void initBoard() {
        board.initBoard();
        assertEquals(2, board.getDimension());
        assertEquals('_', board.getCell(1, 1).getValue());
    }

    @Test
    void getCell() {
        board.initBoard();
        assertNotNull(board.getCell(1, 1));
    }

    @Test
    void getInternalBoard() {
        board.initBoard();
        Cell[][] internalBoard = new Cell[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                internalBoard[i][j] = new Cell(i, j, '_');
            }
        }
        assertFalse(Arrays.deepEquals(board.getInternalBoard(), internalBoard));
    }

    @Test
    void setInternalBoard() {
        board.initBoard();
        Cell[][] internalBoard = new Cell[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                internalBoard[i][j] = new Cell(i, j, 'X');
            }
        }
        board.setInternalBoard(internalBoard);
        assertEquals('X', board.getCell(1,1).getValue());
    }

    @Test
    void getDimension() {
        assertEquals(2, board.getDimension());
    }

    @Test
    void setDimension() {
        board.setDimension(3);
        assertEquals(3, board.getDimension());

    }

    @Test
    void blackenAdjacentFields() {
        board.initBoard();
        Cell[][] internalBoard = new Cell[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                internalBoard[i][j] = new Cell(i, j, 'X');
            }
        }
        internalBoard[0][0].setValue('2');
        board.setInternalBoard(internalBoard);
        board.blackenAdjacentFields();
        assertTrue(board.getCell(0, 1).isBlackend());
        assertTrue(board.getCell(1, 0).isBlackend());
    }

    @Test
    void testToString() {
        board.initBoard();
        assertEquals("Board{internalBoard=\n" +
                "{ Cell{x=0, y=0, value=_}, Cell{x=0, y=1, value=_}, \n" +
                "{ Cell{x=1, y=0, value=_}, Cell{x=1, y=1, value=_}\n" +
                ", dimension=2}", board.toString());
    }
}