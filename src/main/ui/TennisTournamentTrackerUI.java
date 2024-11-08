package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.Tournament;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced C3-LectureLabStarter IntersectionGUI & TrafficLightGui, SmartHomeUI, AlarmControllerUI
// Referenced https://www.youtube.com/watch?v=5o3fMLPY7qY Java GUI Tutorial - Make a GUI in 13 Minutes #99
// Referenced https://stackoverflow.com/questions/6077709/button-size-and-position
// Referenced https://www.geeksforgeeks.org/java-joptionpane/
// Referenced https://www.w3schools.com/java/ref_string_length.asp
// Referenced https://stackoverflow.com/questions/27687427/how-to-create-a-swing-application-with-multiple-pages
// Referenced https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
// Referenced https://stackoverflow.com/questions/66830786/java-gui-switching-between-panels-on-button-click

// Represents Tennis Tournament Tracker application's main window frame
public class TennisTournamentTrackerUI extends JFrame implements ActionListener {

    private Tournament tn;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel trackerPanel;
    private CardLayout cl;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_FILE = "./data/TennisTournamentTracker.json";
    
    // MODIFIES: this
    // EFFECTS: creates TennisTournamentTrackerUI, sets up button panel
    public TennisTournamentTrackerUI() {
        super("Tennis Tournament Tracker");
        cl = new CardLayout();
        mainPanel = new JPanel(cl);
        menuPanel = new JPanel();
        trackerPanel = new JPanel();

        mainPanel.add(menuPanel, "Main Menu");
        mainPanel.add(trackerPanel, "Tracker Page");
        
        JButton addPlayerButton = new JButton("Add Player");
        JButton enterTournamentButton = new JButton("Enter Tournament!");
        JButton recordWinnerLoserButton = new JButton("Record Winner & Loser");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton backButton = new JButton("Back");

        addPlayerButton.setActionCommand("addPlayer");
        addPlayerButton.addActionListener(this);
        enterTournamentButton.setActionCommand("enterTournament");
        enterTournamentButton.addActionListener(this);
        recordWinnerLoserButton.setActionCommand("recordPlayers");
        recordWinnerLoserButton.addActionListener(this);
        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);
        backButton.setActionCommand("backButton");
        backButton.addActionListener(this);

        menuPanel.add(enterTournamentButton);
        menuPanel.add(saveButton);
        menuPanel.add(loadButton);
        trackerPanel.add(addPlayerButton);
        trackerPanel.add(recordWinnerLoserButton);
        trackerPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);
        cl.show(mainPanel, "Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        pack();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        tn = new Tournament();
        jsonReader = new JsonReader(JSON_FILE);
        jsonWriter = new JsonWriter(JSON_FILE);
    }

    public static void main(String[] args) {
        new TennisTournamentTrackerUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enterTournament")) {
            cl.show(mainPanel, "Tracker Page");
        } else if (e.getActionCommand().equals("saveButton")) {
            try {
                jsonWriter.open();
                jsonWriter.write(tn);
                jsonWriter.close();
                System.out.println("Your Tennis Tournament Tracker has been saved to " + JSON_FILE + "!");
            } catch (FileNotFoundException f) {
                System.out.println("Saving Tennis Tournament Tracker to " + JSON_FILE + "was UNSUCCESSFUL.");
            }
        } else if (e.getActionCommand().equals("loadButton")) {
            try {
                tn = jsonReader.read(); 
                System.out.println("Your Tennis Tournament Tracker from " 
                        + JSON_FILE + " has been successfully loaded!");
            } catch (IOException i) {
                System.out.println("Loading Tennis Tournament Tracker from " + JSON_FILE + "was UNSUCCESSFULL.");
            }
        } else if (e.getActionCommand().equals("addPlayer")) {
            String newPlayerName = JOptionPane.showInputDialog(null,
            "Enter New Tennis Player Name",
            "Add Tennis Player",
            JOptionPane.QUESTION_MESSAGE);

            if (newPlayerName != null && newPlayerName.length() != 0) {
                if (tn.addPlayer(newPlayerName)) {
                    JOptionPane.showMessageDialog(null, newPlayerName + " has been successfully added to the tournament!");
                } else {
                    JOptionPane.showMessageDialog(null, "The player entered is already in the tournament.");
                }
            }
        } else if (e.getActionCommand().equals("recordPlayers")) {
            int tnSize = tn.getPlayers().size();
            if (tnSize < 2) {
                JOptionPane.showMessageDialog(null,
                "Not enough players for a match to be played!",
                "Record Winner & Loser",
                JOptionPane.WARNING_MESSAGE);
            } else {
                boolean playerNotFound = true;
                String winner = "";
                while (playerNotFound) {
                    winner = JOptionPane.showInputDialog(null,
                    "Enter Winner's Name",
                    "Record Winner & Loser",
                    JOptionPane.QUESTION_MESSAGE);
                    Player player = tn.findPlayer(winner);
                    if (player != null) {
                        player.increaseMatchWin();
                        playerNotFound = false;
                    } else {
                        JOptionPane.showMessageDialog(null,
                        "Sorry, the player you entered is not in the tournament.",
                        "Record Winner & Loser",
                        JOptionPane.WARNING_MESSAGE);
                    }
                }

                playerNotFound = true;

                while (playerNotFound) {
                    String loser = JOptionPane.showInputDialog(null,
                    "Enter Loser's Name",
                    "Record Winner & Loser",
                    JOptionPane.QUESTION_MESSAGE);
                    if (loser.equals(winner)) {
                        JOptionPane.showMessageDialog(null,
                        "The winner and loser of the match cannot be the same player.",
                        "Record Winner & Loser",
                        JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    Player player = tn.findPlayer(loser);
                    if (player != null) {
                        player.increaseMatchLoss();
                        playerNotFound = false;
                    } else {
                        JOptionPane.showMessageDialog(null,
                        "Sorry, the player you entered is not in the tournament.",
                        "Record Winner & Loser",
                        JOptionPane.WARNING_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(null,
                "The winner and loser of the match have been successfully recorded!",
                "Record Winner & Loser",
                JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getActionCommand().equals("backButton")) {
            cl.show(mainPanel, "Main Menu");
        }
    }
}
