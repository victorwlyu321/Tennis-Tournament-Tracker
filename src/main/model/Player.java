package model;

// Represents a tennis player having a name, number of match wins, 
// and number of match losses
public class Player {
    private String name;
    private int wins;
    private int losses;

    // REQUIRES: name has a non-zero length
    // EFFECTS: create a tennis player with the given name,
    //          zero match wins, and zero match losses
    public Player(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
    }

    // MODIFIES: this
    // EFFECTS: increment player's number of match wins
    public void increaseMatchWin() {
        this.wins += 1;
    }

    // MODIFIES: this
    // EFFECTS: increment player's number of match losses
    public void increaseMatchLoss() {
        this.losses += 1;
    }

    // getter
    public String getName() {
        return this.name;
    }

    // getter
    public int getMatchWins() {
        return this.wins;
    }

    // getter
    public int getMatchLosses() {
        return this.losses;
    }
}
