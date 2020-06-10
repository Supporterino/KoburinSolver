package components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/* author = 4102770 */
class CellTest {
    private Cell cell = null;

    @BeforeEach
    void setup() {
        cell = new Cell(0, 0, '_');
    }

    @Test
    void isBlackend() {
        assertFalse(cell.isBlackend());
    }

    @Test
    void isUsed() {
        assertFalse(cell.isUsed());
    }

    @Test
    void getNumber() {
        cell.setValue('5');
        assertEquals(5, cell.getNumber());
    }

    @Test
    void isStart() {
        assertFalse(cell.isStart());
    }

    @Test
    void setStart() {
        cell.setStart(true);
        assertTrue(cell.isStart());
    }

    @Test
    void isEmpty() {
        assertTrue(cell.isEmpty());
    }

    @Test
    void setBlack() {
        cell.setBlack();
        assertEquals('x', cell.getValue());
    }

    @Test
    void isVisitable() {
        Cell cell2 = new Cell(0,1,'_');
        assertTrue(cell.isVisitable(cell2));
    }

    @Test
    void getValue() {
        assertEquals('_', cell.getValue());
    }

    @Test
    void setValue() {
        cell.setValue('c');
        assertEquals('c', cell.getValue());
    }

    @Test
    void getNext() {
        assertNull(cell.getNext());
    }

    @Test
    void setNext() {
        Cell cell2 = new Cell(0,1,'_');
        cell.setNext(cell2);
        assertEquals(cell2, cell.getNext());
    }

    @Test
    void getSurroundingCells() {
        Board board = new Board(2);
        board.initBoard();
        assertEquals(2, board.getCell(0,0).getSurroundingCells(board.getCell(0,0), board).size());
    }

    @Test
    void possibleValues() {
        Board board = new Board(2);
        board.initBoard();
        assertEquals(2, cell.possibleValues(board).size());
    }

    @Test
    void enter() {
        cell.enter();
        assertTrue(true);
    }

    @Test
    void leave() {
        cell.leave();
        assertNull(cell.getNext());
    }

    @Test
    void addToBlackSurroundings() {
        Cell cell2 = new Cell(0,1,'_');
        cell.setValue('1');
        cell.addToBlackSurroundings(cell2);
        assertTrue(cell.isStatisfied());
    }

    @Test
    void doBlackening() {
        Board board = new Board(2);
        board.initBoard();
        Cell[][] internalBoard = new Cell[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                internalBoard[i][j] = new Cell(i, j, 'X');
            }
        }
        internalBoard[0][0].setValue('2');
        board.setInternalBoard(internalBoard);
        board.getCell(0,0).doBlackening(board);
        assertTrue(board.getCell(0,0).isStatisfied());
    }

    @Test
    void isBlackable() {
        Board board = new Board(2);
        board.initBoard();
        Cell[][] internalBoard = new Cell[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                internalBoard[i][j] = new Cell(i, j, 'X');
            }
        }
        internalBoard[0][0].setValue('2');
        board.setInternalBoard(internalBoard);
        assertTrue(board.getCell(0,1).isBlackable(board));
    }

    @Test
    void inNeedOf() {
        cell.setValue('1');
        assertEquals(1, cell.inNeedOf());
    }

    @Test
    void isStatisfied() {
        cell.setValue('1');
        assertFalse(cell.isStatisfied());
    }

    @Test
    void isNumber() {
        assertFalse(cell.isNumber());
    }

    @Test
    void testToString() {
        assertEquals("Cell{x=0, y=0, value=_}", cell.toString());
    }
}