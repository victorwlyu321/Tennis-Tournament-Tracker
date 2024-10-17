package persistence;

import java.io.IOException;

import org.json.*;

import model.Tournament;

// Referenced JsonReader in JsonSerializationDemo

// Represents a reader that reads tennis tournament player list from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs JSON reader to read from a source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads tournament from file and returns tournament
    //          if error occurs from reading the file, throws IOException
    public Tournament read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as a string and returns the string
    private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses Tournament from JSON object and returns Tournament
    private Tournament parseTournament(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: tn
    // EFFECTS: parses players from JSON obbject and adds them to tournament
    private void addPlayers(Tournament tn, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: tn
    // EFFECTS: parses player from JSON object and adds player to tournament
    private void addPlayer(Tournament tn, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: tn
    // EFFECTS: parses player from JSON object and adds player match win record to tournament
    private void addWin(Tournament tn, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: tn
    // EFFECTS: parses player from JSON object and adds player match win record to tournament
    private void addLoss(Tournament tn, JSONObject jsonObject) {
        // stub
    }
}
