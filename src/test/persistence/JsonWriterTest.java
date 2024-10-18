package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Player;
import model.Tournament;

// Referenced JsonWriterTest in JsonSerializationDemo
// Referenced https://stleary.github.io/JSON-java/index.html

public class JsonWriterTest extends JsonTest {


    @Test
    public void testWriterIllegalFile() {
        try {
            Tournament tn = new Tournament();
            JsonWriter writer = new JsonWriter("./data/illega\blFileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // does nothing, pass
        }
    }

    @Test
    public void testWriterEmptyTournament() {
        try {
            Tournament tn = new Tournament();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTournament.json");
            writer.open();
            writer.write(tn);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTournament.json");
            tn = reader.read();
            assertEquals(0, tn.getPlayers().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testWriterRegularTournament() {
        try {
            Tournament tn = new Tournament();
            assertTrue(tn.addPlayer("Roger Federer"));
            assertTrue(tn.addPlayer("Rafael Nadal"));
            Player p1 = tn.findPlayer("Roger Federer");
            Player p2 = tn.findPlayer("Rafael Nadal");
            p1.increaseMatchWin();
            p1.increaseMatchWin();
            p1.increaseMatchWin();
            p1.increaseMatchLoss();
            p2.increaseMatchWin();
            p2.increaseMatchWin();
            p2.increaseMatchLoss();
            p2.increaseMatchLoss();
            JsonWriter writer = new JsonWriter("./data/testWriterRegularTournament.json");
            writer.open();
            writer.write(tn);
            writer.close();
    
            JsonReader reader = new JsonReader("./data/testWriterRegularTournament.json");
            tn = reader.read();
            ArrayList<Player> players = tn.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Roger Federer", 3, 1, players.get(0));
            checkPlayer("Rafael Nadal", 2, 2, players.get(1));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }
}
