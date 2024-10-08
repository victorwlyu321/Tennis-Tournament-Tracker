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
    // EFFECTS: returns true if a new tennis player has been created and added to the list of players in the tournament
    public boolean addPlayer(String name) {
        boolean playerAlreadyAdded = false;
        boolean newPlayerAdded = false;

        for (Player p : this.players) {
            if (p.getName().equals(name)) {
                playerAlreadyAdded = true;
                break;
            }
        }
        if (!playerAlreadyAdded) {
            Player newPlayer = new Player(name);
            this.players.add(newPlayer);
            newPlayerAdded = true;
        }
        return newPlayerAdded;
    }

    // EFFECTS: returns a tennis player with the given name if found
    public Player findPlayer(String name) {
        for (Player p : this.players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    // getter
    public ArrayList<Player> getPlayers() {
        return this.players;
    }
}
