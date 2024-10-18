package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Referenced WorkRoom in JsonSerializationDemo
// Referenced https://stleary.github.io/JSON-java/index.html

// Represents a tennis tournament having a list of participating tennis players
public class Tournament implements Writable {
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

    // EFFECTS: puts player JSON array into JSON object and returns JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns players in tournament as a JSON array
    public JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : this.players) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
