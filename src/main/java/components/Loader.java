package components;
import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/* author = 4102770 */
public class Loader {
    private JSONObject config;
    private Board board;

    public Loader(Board board) {
        this.board = board;
    }

    public void loadConfig() {
        int dim = config.getInt("dimension");
        board.setDimension(dim);
        board.initBoard();

        JSONArray numberCells = config.getJSONArray("numbers");
        for (int i = 0; i < numberCells.length(); i++) {
            int x = numberCells.getJSONObject(i).getInt("x");
            int y = numberCells.getJSONObject(i).getInt("y");
            int value = numberCells.getJSONObject(i).getInt("value");
            Cell[][] tempBoard = board.getInternalBoard();
            tempBoard[y][x].setValue((char) (value + '0'));
            board.setInternalBoard(tempBoard);
        }
    }

    public void initConfig(String filePath) {
        try {
            String rawFile = readFile(filePath);
            config = new JSONObject(rawFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConfig(JSONObject config) {
        this.config = config;
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String         line;
        StringBuilder  stringBuilder = new StringBuilder();

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
