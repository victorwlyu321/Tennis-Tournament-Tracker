package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Player;

// Referenced JsonTest in JsonSerializationDemo

public class JsonTest {
    protected void checkPlayer(String name, int wins, int losses, Player player) {
        assertEquals(name, player.getName());
        assertEquals(wins, player.getMatchWins());
        assertEquals(losses, player.getMatchLosses());
    }
}
