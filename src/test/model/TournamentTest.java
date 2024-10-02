package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TournamentTest {
    private Tournament testTournament;
    private Player p1;
    private Player p2;

    @BeforeEach
    void runBefore() {
        testTournament = new Tournament();
        p1 = new Player("Rafael Nadal");
        p2 = new Player("Andy Murray");
    }

    @Test
    void testConstructor() {
        ArrayList<Player> players = testTournament.getPlayers();
        assertEquals(0, players.size());
    }

    @Test
    void testAddPlayer() {
        ArrayList<Player> players = testTournament.getPlayers();
        testTournament.addPlayer(p1);
        assertEquals(1, players.size());
        assertEquals(p1, players.get(0));
    }

    @Test
    void testAddPlayerMultiple() {
        ArrayList<Player> players = testTournament.getPlayers();
        testTournament.addPlayer(p1);
        assertEquals(1, players.size());
        assertEquals(p1, players.get(0));
        testTournament.addPlayer(p2);
        assertEquals(2, players.size());
        assertEquals(p2, players.get(1));
    }

    @Test
    void testAddPlayerSamePlayer() {
        ArrayList<Player> players = testTournament.getPlayers();
        testTournament.addPlayer(p1);
        assertEquals(1, players.size());
        assertEquals(p1, players.get(0));
        testTournament.addPlayer(p1);
        assertEquals(1, players.size());
        assertEquals(p1, players.get(0));
    }

    @Test
    void testFindPlayer() {
        testTournament.addPlayer(p1);
        assertEquals(p1, testTournament.findPlayer("Rafael Nadal"));
    }

    @Test
    void tesetFindPlayerDoesNotExist() {
        testTournament.addPlayer(p1);
        assertNull(testTournament.findPlayer("Rafael Nadal"));
    }
}
