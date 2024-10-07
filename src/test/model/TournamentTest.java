package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TournamentTest {
    private Tournament testTournament;

    @BeforeEach
    void runBefore() {
        testTournament = new Tournament();
    }

    @Test
    void testConstructor() {
        ArrayList<Player> players = testTournament.getPlayers();
        assertEquals(0, players.size());
    }

    @Test
    void testAddPlayer() {
        ArrayList<Player> players = testTournament.getPlayers();
        assertFalse(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
    }

    @Test
    void testAddPlayerMultiple() {
        ArrayList<Player> players = testTournament.getPlayers();
        assertFalse(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
        assertFalse(testTournament.addPlayer("Andy Murray"));
        assertEquals(2, players.size());
        assertEquals("Andy Murray", players.get(1).getName());
    }

    @Test
    void testAddPlayerSamePlayer() {
        ArrayList<Player> players = testTournament.getPlayers();
        assertFalse(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
        assertTrue(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
    }

    @Test
    void testFindPlayer() {
        testTournament.addPlayer("Rafael Nadal");
        assertEquals("Rafael Nadal", testTournament.findPlayer("Rafael Nadal").getName());
    }

    @Test
    void tesetFindPlayerDoesNotExist() {
        testTournament.addPlayer("Rafael Nadal");
        assertNull(testTournament.findPlayer("Roger Federer"));
    }
}
