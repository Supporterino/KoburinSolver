package gui;

import components.Board;
import components.Cell;
import components.Solver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private Pane pane;
    private Board board;
    private Solver solver;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button run = new Button("Run");
        run.setOnMouseClicked(mouseEvent -> solver.run());

        Button step = new Button("Step");
        step.setOnMouseClicked(mouseEvent -> solver.step());

        Label label = new Label("Drag a file to me.");
        Label dropped = new Label("");
        VBox dragTarget = new VBox();
        dragTarget.getChildren().addAll(label, dropped);

        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.add(run, 0, 0);
        root.add(step, 1, 0);
        root.add(dragTarget, 2, 0);

        pane = new Pane();
        root.add(pane, 0, 1, 3, 2);

        Scene scene = new Scene(root);

        scene.setOnDragOver(event -> {
            if (event.getGestureSource() != dragTarget
                    && event.getDragboard().hasFiles()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        scene.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                dropped.setText(db.getFiles().get(0).toPath().toString());
                try {
                    // clear previous board (place rectangle over)
                    System.out.println("Dragged");
                    //solver.setFilepath(db.getFiles().get(0).toPath().toString());
                    solver = new Solver(new Board(0), this, db.getFiles().get(0).toPath().toString());
                    solver.init();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Koburin Solver");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        board = new Board(0);
        solver = new Solver(board, this, "/Users/larsroth/Documents/Repos/KoburinSolver/src/main/resources/4x4_simple.json");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void update(Board board){
        this.board = board;
        this.showBoard();
    }

    public void showBoard() {
        //Clear GUI of old Grids
        pane.getChildren().clear();

        int i = 0;
        for (int x = 0; x < this.board.getInternalBoard().length; x++) {
            for (int y = 0; y < this.board.getInternalBoard()[x].length; y++) {

                Cell cell = this.board.getCell(x, y);

                // Rectangles for white and black cells
                javafx.scene.shape.Rectangle rectangle = new Rectangle(x * 50, y * 50, 50, 50);
                rectangle.setFill(cell.isBlackend() ? javafx.scene.paint.Paint.valueOf("#111111") : javafx.scene.paint.Paint.valueOf("#ffffff00"));
                rectangle.setStroke(Paint.valueOf("#666666"));
                pane.getChildren().add(rectangle);

                // add numbers to the grid
                if (cell.isNumber()) {
                    Text text = new Text(x * 50 + 20, y * 50 + 25, String.valueOf(cell.getNumber()));
                    pane.getChildren().addAll(text);
                }

                // draw line of solution
                Cell next = cell.getNext();
                if (next != null) {
                    Line line = new Line(x * 50 + 25, y * 50 + 25, next.x * 50 + 25, next.y * 50 + 25);
                    pane.getChildren().add(line);
                }

            }
        }
    }
}
