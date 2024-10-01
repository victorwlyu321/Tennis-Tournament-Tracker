package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player testPlayer;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player("Roger Federer");
    }

    @Test
    void testConstructor() {
        assertEquals("Roger Federer", testPlayer.getName());
        assertEquals(0, testPlayer.getMatchWins());
        assertEquals(0, testPlayer.getMatchLosses());
    }

    @Test
    void testIncreaseMatchWin() {
        assertEquals(0, testPlayer.getMatchWins());
        testPlayer.increaseMatchWin();
        assertEquals(1, testPlayer.getMatchWins());
        assertEquals(0, testPlayer.getMatchLosses());
    }

    @Test
    void testIncreaseMatchWinMultiple() {
        assertEquals(0, testPlayer.getMatchWins());
        testPlayer.increaseMatchWin();
        assertEquals(1, testPlayer.getMatchWins());
        assertEquals(0, testPlayer.getMatchLosses());
        testPlayer.increaseMatchWin();
        assertEquals(2, testPlayer.getMatchWins());
        testPlayer.increaseMatchWin();
        assertEquals(3, testPlayer.getMatchWins());
        assertEquals(0, testPlayer.getMatchLosses());
    }

    @Test
    void testIncreaseMatchLoss() {
        assertEquals(0, testPlayer.getMatchLosses());
        testPlayer.increaseMatchLoss();
        assertEquals(1, testPlayer.getMatchLosses());
        assertEquals(0, testPlayer.getMatchWins());
    }

    @Test
    void testIncreaseMatchLossMultiple() {
        assertEquals(0, testPlayer.getMatchLosses());
        testPlayer.increaseMatchLoss();
        assertEquals(1, testPlayer.getMatchLosses());
        assertEquals(0, testPlayer.getMatchWins());
        testPlayer.increaseMatchLoss();
        assertEquals(2, testPlayer.getMatchLosses());
        testPlayer.increaseMatchLoss();
        assertEquals(3, testPlayer.getMatchLosses());
        assertEquals(0, testPlayer.getMatchWins());
    }
}
