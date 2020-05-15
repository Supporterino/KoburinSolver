package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Solver {
    private Board board;
    private ArrayList<String> history;
    private Cell[][] initialBoard;

    public Solver(Board board) {
        this.board = board;
        history = new ArrayList<String>();
    }

    public void run() {
        blackenCells();
        System.out.println(history.size());
    }

    private void blackenCells() {
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                Cell activeCell = board.getCell(i, j);
                if (activeCell.isNumeric()) {
                    ArrayList<Cell> surroundings = activeCell.getSurroundingCells(board);
                    surroundings.removeIf(cell -> !cell.isCanBeBlack());
                    if (activeCell.getValue() == 0) {
                        surroundings.forEach(item -> item.setCanBeBlack(false));
                    }
                    if (activeCell.getValue() == 1) {
                        if (surroundings.size() == 1) board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                        if (surroundings.size() == 2) {
                            int choice = ThreadLocalRandom.current().nextInt(2);
                            switch (choice) {
                                case 0:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    break;
                                case 1:
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    break;
                            }
                        }
                        if (surroundings.size() == 3) {
                            int choice = ThreadLocalRandom.current().nextInt(3);
                            switch (choice) {
                                case 0:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    break;
                                case 1:
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    break;
                                case 2:
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    break;
                            }
                        }
                        if (surroundings.size() == 4) {
                            int choice = ThreadLocalRandom.current().nextInt(4);
                            switch (choice) {
                                case 0:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    break;
                                case 1:
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    break;
                                case 2:
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    break;
                                case 3:
                                    board.blackCell(surroundings.get(3).getX(), surroundings.get(3).getY());
                                    break;
                            }
                        }
                    }
                    if (activeCell.getValue() == 2) {
                        if (surroundings.size() <= 2) surroundings.forEach(item -> board.blackCell(item.getX(), item.getY()));
                        if (surroundings.size() == 3) {
                            int choice = ThreadLocalRandom.current().nextInt(3);
                            switch (choice) {
                                case 0:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    break;
                                case 1:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    break;
                                case 2:
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    break;
                            }
                        }
                        if (surroundings.size() == 4) {
                            int choice = ThreadLocalRandom.current().nextInt(6);
                            switch (choice) {
                                case 0:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    break;
                                case 1:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    break;
                                case 2:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(3).getX(), surroundings.get(3).getY());
                                    break;
                                case 3:
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    break;
                                case 4:
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    board.blackCell(surroundings.get(3).getX(), surroundings.get(3).getY());
                                    break;
                                case 5:
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    board.blackCell(surroundings.get(3).getX(), surroundings.get(3).getY());
                                    break;
                            }
                        }
                    }
                    if (activeCell.getValue() == 3) {
                        if (surroundings.size() <= 3) surroundings.forEach(item -> board.blackCell(item.getX(), item.getY()));
                        else {
                            int choice = ThreadLocalRandom.current().nextInt(4);
                            switch (choice) {
                                case 0:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    break;
                                case 1:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    board.blackCell(surroundings.get(3).getX(), surroundings.get(3).getY());
                                    break;
                                case 2:
                                    board.blackCell(surroundings.get(0).getX(), surroundings.get(0).getY());
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    board.blackCell(surroundings.get(3).getX(), surroundings.get(3).getY());
                                    break;
                                case 3:
                                    board.blackCell(surroundings.get(1).getX(), surroundings.get(1).getY());
                                    board.blackCell(surroundings.get(2).getX(), surroundings.get(2).getY());
                                    board.blackCell(surroundings.get(3).getX(), surroundings.get(3).getY());
                                    break;
                            }
                        }
                    }
                    else surroundings.forEach(item -> board.blackCell(item.getX(), item.getY()));
                }
            }
        }
    }

}
