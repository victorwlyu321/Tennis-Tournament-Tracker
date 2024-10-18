package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
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
        assertTrue(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
    }

    @Test
    void testAddPlayerMultiple() {
        ArrayList<Player> players = testTournament.getPlayers();
        assertTrue(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
        assertTrue(testTournament.addPlayer("Andy Murray"));
        assertEquals(2, players.size());
        assertEquals("Andy Murray", players.get(1).getName());
    }

    @Test
    void testAddPlayerSamePlayer() {
        ArrayList<Player> players = testTournament.getPlayers();
        assertTrue(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
        assertFalse(testTournament.addPlayer("Rafael Nadal"));
        assertEquals(1, players.size());
        assertEquals("Rafael Nadal", players.get(0).getName());
    }

    @Test
    void testFindPlayer() {
        testTournament.addPlayer("Rafael Nadal");
        assertEquals("Rafael Nadal", testTournament.findPlayer("Rafael Nadal").getName());
    }

    @Test
    void testFindPlayerDoesNotExist() {
        testTournament.addPlayer("Rafael Nadal");
        assertNull(testTournament.findPlayer("Roger Federer"));
    }

    @Test
    void testToJson() {
        assertTrue(testTournament.addPlayer("Rafael Nadal"));
        assertTrue(testTournament.addPlayer("Andy Murray"));
        ArrayList<Player> players = testTournament.getPlayers();

        JSONObject testJsonObject = testTournament.toJson();
        JSONArray testJsonArray = testJsonObject.getJSONArray("players");

        assertEquals(players.get(0).getName(), testJsonArray.getJSONObject(0).getString("name"));
        assertEquals(players.get(0).getMatchWins(), testJsonArray.getJSONObject(0).getInt("wins"));
        assertEquals(players.get(0).getMatchLosses(), testJsonArray.getJSONObject(0).getInt("losses"));
        assertEquals(players.get(1).getName(), testJsonArray.getJSONObject(1).getString("name"));
        assertEquals(players.get(1).getMatchWins(), testJsonArray.getJSONObject(1).getInt("wins"));
        assertEquals(players.get(1).getMatchLosses(), testJsonArray.getJSONObject(1).getInt("losses"));
    }

    @Test
    void testPlayersToJson() {   
        assertTrue(testTournament.addPlayer("Rafael Nadal"));
        assertTrue(testTournament.addPlayer("Andy Murray"));
        ArrayList<Player> players = testTournament.getPlayers();

        JSONArray testJsonArray = testTournament.playersToJson();
        assertEquals(players.get(0).getName(), testJsonArray.getJSONObject(0).getString("name"));
        assertEquals(players.get(0).getMatchWins(), testJsonArray.getJSONObject(0).getInt("wins"));
        assertEquals(players.get(0).getMatchLosses(), testJsonArray.getJSONObject(0).getInt("losses"));
        assertEquals(players.get(1).getName(), testJsonArray.getJSONObject(1).getString("name"));
        assertEquals(players.get(1).getMatchWins(), testJsonArray.getJSONObject(1).getInt("wins"));
        assertEquals(players.get(1).getMatchLosses(), testJsonArray.getJSONObject(1).getInt("losses"));
    }
}
