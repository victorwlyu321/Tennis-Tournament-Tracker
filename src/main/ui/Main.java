package ui;

import java.io.FileNotFoundException;

// Referenced Main in JsonSerializationDemo

public class Main {
    public static void main(String[] args) {
        try {
            new TennisTournamentTracker();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run Tennis Tournament Tracker: File not found!");
        }
    }
}
