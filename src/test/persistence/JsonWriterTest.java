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
            JsonWriter writer = new JsonWriter("./data/testWriterRegularTournament.json");
            writer.open();
            writer.write(tn);
            writer.close();
    
            JsonReader reader = new JsonReader("./data/testWriterRegularTournament.json");
            tn = reader.read();
            ArrayList<Player> players = tn.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Roger Federer", 0, 0, players.get(0));
            checkPlayer("Rafael Nadal", 0, 0, players.get(1));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }
}
