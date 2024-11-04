package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.swing.*;

import model.Tournament;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



// Referenced C3-LectureLabStarter IntersectionGUI & TrafficLightGui, SmartHomeUI, AlarmControllerUI
// Referenced https://www.youtube.com/watch?v=5o3fMLPY7qY Java GUI Tutorial - Make a GUI in 13 Minutes #99
// Referenced https://stackoverflow.com/questions/6077709/button-size-and-position

// Represents Tennis Tournament Tracker application's main window frame
public class TennisTournamentTrackerUI extends JFrame implements ActionListener {

    private Tournament tn;
    private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
    private JPanel trackerPanel;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_FILE = "./data/TennisTournamentTracker.json";
    
    // MODIFIES: this
    // EFFECTS: creates TennisTournamentTrackerUI, sets up button panel
    public TennisTournamentTrackerUI() {
        super("Tennis Tournament Tracker");
        tn = new Tournament();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        trackerPanel = new JPanel();
        setLayout(new GridLayout(0, 1));
        add(trackerPanel, BorderLayout.CENTER);
        //setTitle("Tennis Tournament Tracker");

        JButton addPlayerButton = new JButton("Add Player");
        JButton viewPlayerButton = new JButton("View Players & Stats");
        JButton recordWinnerLoserButton = new JButton("Record Winner & Loser");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);

        add(addPlayerButton);
        add(viewPlayerButton);
        add(recordWinnerLoserButton);
        add(saveButton);
        add(loadButton);



        pack();
		setVisible(true);

        jsonReader = new JsonReader(JSON_FILE);
        jsonWriter = new JsonWriter(JSON_FILE);


    }



    public static void main(String[] args) {
        new TennisTournamentTrackerUI();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("saveButton")) {
            try {
                jsonWriter.open();
                jsonWriter.write(tn);
                jsonWriter.close();
                System.out.println("Your Tennis Tournament Tracker has been saved to " + JSON_FILE + "!");
            } catch (FileNotFoundException f) {
                System.out.println("Saving Tennis Tournament Tracker to " + JSON_FILE + "was UNSUCCESSFUL.");
            }
        }
        if (e.getActionCommand().equals("loadButton")) {
            try {
                tn = jsonReader.read(); 
                System.out.println("Your Tennis Tournament Tracker from " + JSON_FILE + "has been successfully loaded!");
            } catch (IOException i) {
                System.out.println("Loading Tennis Tournament Tracker from " + JSON_FILE + "was UNSUCCESSFULL.");
            }
        }
    }
}
