package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Player;
import model.Tournament;

// Referenced JsonReaderTest in JsonSerializationDemo
// Referenced https://stleary.github.io/JSON-java/index.html

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/fileDoesNotExist.json");

        try {
            Tournament tn = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // does nothing, pass
        }
    }

    @Test
    public void testReaderEmptyTournament() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTournament.json");

        try {
            Tournament tn = reader.read();
            assertEquals(0, tn.getPlayers().size());
        } catch (IOException e) {
            fail("Error reading file");
        }
    }

    @Test
    public void testReaderRegularTournament() {
        JsonReader reader = new JsonReader("./data/testReaderRegularTournament.json");

        try {
            Tournament tn = reader.read();
            ArrayList<Player> players = tn.getPlayers();
            assertEquals(3, players.size());
            checkPlayer("Roger Federer", 5, 2, players.get(0));
            checkPlayer("Rafael Nadal", 4, 3, players.get(1));
            checkPlayer("Novak Djokovic", 2, 5, players.get(2));
        } catch (IOException e) {
            fail("Error reading file");
        }
    }
}
