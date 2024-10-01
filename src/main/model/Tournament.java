package model;

import java.util.ArrayList;

// Represents a tennis tournament having a list of participating tennis players
public class Tournament {
    private ArrayList<Player> players;

    // EFFECTS: create a tournament with an empty list of tennis players
    public Tournament() {
        this.players = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a tennis player to the list of players in the tournament
    public void addPlayer(Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }

    // getter
    public ArrayList<Player> getPlayers() {
        return this.players;
    }
}
