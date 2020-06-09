package components;

import java.util.ArrayList;
import java.util.HashSet;

import gui.Main;
import javafx.application.Platform;

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

    public void init() {
        initBoard();
        controller.update(board);
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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

    private void initBoard() {
        System.out.println("Start Solving");
        reset();
        loader.initConfig(filepath);
        loader.loadConfig();
        //System.out.println("Initial Board");
        //System.out.println(board.toString());
    }

    private void blackening() {
        board.blackenAdjacentFields();
        //System.out.println("Blackend Board");
        //System.out.println(board.toString());
    }

    private void oneStep() {
        setCounter(0);
        solve(0, 0);
        //System.out.println("Possible Solution found:");
        //System.out.println(solutionFound);
        controller.update(board);
    }

    public void step() {
        initBoard();
        blackening();
        oneStep();
        System.out.println("Step done!");
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            if (!solutionFound) {
                initBoard();
                blackening();
                oneStep();
            }
            else break;
        }
        controller.update(board);
        System.out.println("Finished Solving");
    }


    public boolean solve(Cell currentCell) {
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
        this.solutionFound = false;
    }
}

