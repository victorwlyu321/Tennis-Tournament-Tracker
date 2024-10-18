package persistence;

import java.io.FileNotFoundException;

import model.Tournament;

// Referenced JsonWriter in JsonSerializationDemo

public class JsonWriter {

    // EFFECTS: constructs JSON writer to write to a destination file
    public JsonWriter(String destination) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: opens JSON writer
    //          if destination file cannot be opened for writing, throws FileNotFoundException
    public void open() throws FileNotFoundException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of tournament to file
    public void write(Tournament tn) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes JSON writer
    public void close() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes source file string to file
    private void saveToFile(String json) {
        // stub
    } 
}
