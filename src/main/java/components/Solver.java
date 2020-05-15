package components;

import java.util.ArrayList;
import java.util.HashSet;

import gui.Main;

public class Solver implements Runnable {

    private int counter;
    private Main controller;
    private Loader loader;
    private String filepath;

    private final Board board;

    HashSet<String> historicBoards = new HashSet<>();

    private boolean solutionFound = false;

    public Solver(Board board, Main contr, String path) {
        this.board = board;
        this.controller = contr;
        this.loader = new Loader(board);
        this.filepath = path;
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean getSolutionState() {
        return solutionFound;
    }

    public void setSolutionState(boolean solutionFound) {
        this.solutionFound = solutionFound;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void addCheckedBoard(String board) {
        this.historicBoards.add(board);
    }

    public boolean alreadyChecked(String board) {
        return historicBoards.contains(board);
    }


    public boolean finalValidation() {
        for (Cell[] row : board.getInternalBoard()) {
            for (Cell field : row) {
                if (!field.isUsed()) {
                    return false;
                }
            }
        }
        System.out.println("Lösung gefunden!");
        this.solutionFound = true;
        this.counter = 0;
        return true;
    }

    public boolean solve(int x, int y) {
        if (this.historicBoards.contains(this.board.toString())) {
            System.out.println("Already checked this board possibility!");
            return false;
        }
        historicBoards.add(this.board.toString());
        return solve(board.getCell(x, y));
    }

    public void run() {
        System.out.println("Start Solving");
        reset();
        loader.initConfig(filepath);
        loader.loadConfig();
        System.out.println("Initial Board");
        System.out.println(board.toString());

        board.blackenAdjacentFields();
        System.out.println("Blackend Board");
        System.out.println(board.toString());

        setCounter(0);
        solve(0, 0);
        System.out.println("Possible Solution found:");
        System.out.println(solutionFound);
        //System.out.println(board.toString());
        controller.update(board);
        /*do {

            setCounter(0);
            solve(0, 0);
            System.out.println(board.toString());
        } while (!solutionFound);*/

        System.out.println("Finished Solving");
    }


    public boolean solve(Cell currentCell) {
        /*if (counter % 20 == 0) {
            Platform.runLater(() -> controller.update(board));
        }*/
        if (currentCell.isStart()) {
            return finalValidation();
        }

        if (counter == 0) {
            currentCell.setStart(true);
        }

        currentCell.enter();
        ArrayList<Cell> possibleCells = currentCell.possibleValues(board);
        for (Cell c : possibleCells) {
            currentCell.setNext(c);
            counter++;
            if (solve(c)) {
                return true;
            }
        }
        currentCell.leave();
        return false;
    }

    public void reset() {
        this.historicBoards = new HashSet<>();
        this.counter = 0;
    }
}

