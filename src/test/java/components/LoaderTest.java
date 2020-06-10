package components;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/* author = 4102770 */
class LoaderTest {
    Loader loader = null;

    @Test
    void loadConfig() {
        Board board = new Board(0);
        loader = new Loader(board);
        String rawData = "{    \"dimension\": 4,    \"numbers\": [        {            \"value\": 2,            \"x\": 1,            \"y\": 1        },        {            \"value\": 0,            \"x\": 3,            \"y\": 3        }    ]}";
        JSONObject temp = new JSONObject(rawData);
        loader.setConfig(temp);
        loader.loadConfig();
        assertEquals(4, board.getDimension());
    }
}