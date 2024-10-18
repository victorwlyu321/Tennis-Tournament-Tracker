package persistence;

import org.json.JSONObject;

import java.io.*;

import model.Tournament;

// Referenced JsonWriter in JsonSerializationDemo

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs JSON writer to write to a destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens JSON writer
    //          if destination file cannot be opened for writing, throws FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of tournament to file
    public void write(Tournament tn) {
        JSONObject json = tn.toJson();
        saveToFile(json.toString(TAB));
        
    }

    // MODIFIES: this
    // EFFECTS: closes JSON writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes source file string to file
    private void saveToFile(String json) {
        writer.print(json);
    } 
}
