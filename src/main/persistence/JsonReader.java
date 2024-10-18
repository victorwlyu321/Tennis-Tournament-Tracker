package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Player;
import model.Tournament;

// Referenced JsonReader in JsonSerializationDemo
// Referenced https://stleary.github.io/JSON-java/index.html

// Represents a reader that reads tennis tournament from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs JSON reader to read from a source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tournament from file and returns tournament
    //          if error occurs from reading the file, throws IOException
    public Tournament read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTournament(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns the string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses Tournament from JSON object and returns Tournament
    private Tournament parseTournament(JSONObject jsonObject) {
        Tournament tn = new Tournament();
        addPlayers(tn, jsonObject);
        return tn;
    }

    // MODIFIES: tn
    // EFFECTS: parses players from JSON obbject and adds them to tournament
    private void addPlayers(Tournament tn, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(tn, nextPlayer);
        }
    }

    // MODIFIES: tn
    // EFFECTS: parses player from JSON object and adds player and their win-loss record to tournament
    private void addPlayer(Tournament tn, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int wins = jsonObject.getInt("wins");
        int losses = jsonObject.getInt("losses");
        if (tn.addPlayer(name)) {
            Player player = tn.findPlayer(name);

            for (int num = 0; num < wins; num++) {
                player.increaseMatchWin();
            }
            for (int num = 0; num < losses; num++) {
                player.increaseMatchLoss();
            }
        }
    }
}
