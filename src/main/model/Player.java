package model;

// Represents a tennis player having a name, number of match wins, 
// and number of match losses
public class Player {

    // REQUIRES: name has a non-zero length
    // EFFECTS: create a tennis player with the given name,
    //          zero match wins, and zero match losses
    public Player(String name) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: increment player's number of match wins by 1
    public void increaseMatchWin() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: increment player's number of match losses by 1
    public void increaseMatchLoss() {
        // stub
    }

    // getter
    public String getName() {
        return null;
    }

    // getter
    public int getMatchWins() {
        return 0;
    }

    // getter
    public int getMatchLosses() {
        return 0;
    }
}
