import components.Board;
import components.Loader;
import components.Solver;

public class Application {
    public static void main(String... args) {
        Board board = new Board(0);
        Loader loader = new Loader(board);
        loader.initConfig("/Users/larsroth/Documents/Repos/KoburinSolver/src/main/resources/demoConfig.json");
        loader.loadConfig();
        Solver solver = new Solver(board);
        System.out.println("Loading Config...");
        System.out.println(board.toString());
        System.out.println("Starting Solving...");
        solver.run();
        System.out.println(board.toString());
    }
}
