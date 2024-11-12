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
// Referenced https://docs.oracle.com/javase/6/docs/api/javax/swing/JList.html
// Referenced https://docs.oracle.com/javase/1.5.0/docs/api/javax/swing/JList.html
// Referenced https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
// Referenced https://docs.oracle.com/javase/tutorial/uiswing/components/list.html#mutable
// Referenced https://docs.oracle.com/javase/8/docs/api/javax/swing/JList.html
// Referenced https://docs.oracle.com/javase/8/docs/api/javax/swing/DefaultListModel.html
// Referenced https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
// Referenced https://docs.oracle.com/javase/7/docs/api/javax/swing/JScrollPane.html
// Referenced https://www.w3schools.com/java/ref_string_startswith.asp

// Represents Tennis Tournament Tracker application's main window frame
public class TennisTournamentTrackerUI extends JFrame implements ActionListener {

    private Tournament tn;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel trackerPanel;
    private CardLayout cardLayout;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private DefaultListModel<String> listModel;
    private JList<String> playerList;
    private static final String JSON_FILE = "./data/TennisTournamentTracker.json";

    // MODIFIES: this
    // EFFECTS: creates TennisTournamentTrackerUI, sets up JFrame, menu and tracker
    // panels, sets up player list, 
    // sets up buttons, and initializes tournament
    public TennisTournamentTrackerUI() {
        super("Tennis Tournament Tracker");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        menuPanel = new JPanel();
        trackerPanel = new JPanel();

        mainPanel.add(menuPanel, "Main Menu");
        mainPanel.add(trackerPanel, "Tracker Page");

        setupPlayerList();
        setupButtons();

        add(mainPanel, BorderLayout.CENTER);
        cardLayout.show(mainPanel, "Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        pack();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        initializeTournament();
    }

    // MODIFIES: this
    // EFFECTS: sets up buttons and adds buttons to the menu and tracker panels
    private void setupButtons() {
        addButtonToMenuPanel("Enter Tournament!", "enterTournament");
        addButtonToMenuPanel("Save", "saveButton");
        addButtonToMenuPanel("Load", "loadButton");
        addButtonToTrackerPanel("Add Player", "addPlayer");
        addButtonToTrackerPanel("Record Winner & Loser", "recordPlayers");
        addButtonToTrackerPanel("Back", "backButton");
    }

    // MODIFIES: this
    // EFFECTS: creates button and adds button to the menu panel
    private void addButtonToMenuPanel(String buttonName, String actionCommand) {
        JButton button = new JButton(buttonName);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        menuPanel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: creates button and adds button to the tracker panel
    private void addButtonToTrackerPanel(String buttonName, String actionCommand) {
        JButton button = new JButton(buttonName);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        trackerPanel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: initializes tournament, JSONReader and JSONWriter
    private void initializeTournament() {
        tn = new Tournament();
        jsonReader = new JsonReader(JSON_FILE);
        jsonWriter = new JsonWriter(JSON_FILE);
    }

    // MODIFIES: this
    // EFFECTS: handles button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enterTournament")) {
            showMenuPanel("Tracker Page");
        } else if (e.getActionCommand().equals("saveButton")) {
            saveTournament();
        } else if (e.getActionCommand().equals("loadButton")) {
            loadTournament();
        } else if (e.getActionCommand().equals("addPlayer")) {
            addNewPlayer();
        } else if (e.getActionCommand().equals("recordPlayers")) {
            int tnSize = tn.getPlayers().size();
            if (tnSize < 2) {
                displayMessageDialogue("Not enough players in the tournament for a match to be played!",
                        "Record Winner & Loser",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                String previousWinner = specifyWinner();
                if (previousWinner != null) {
                    specifyLoser(previousWinner);
                }
            }
        } else if (e.getActionCommand().equals("backButton")) {
            showTrackerPanel("Main Menu");
        }
    }

    // MODIFIES: this
    // EFFECTS: show menu page in application
    private void showMenuPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    // MODIFIES: this
    // EFFECTS: show tracker page in application
    private void showTrackerPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    // EFFECTS: saves the tournament to file
    private void saveTournament() {
        try {
            jsonWriter.open();
            jsonWriter.write(tn);
            jsonWriter.close();
            System.out.println("Your Tennis Tournament Tracker has been saved to " + JSON_FILE + "!");
        } catch (FileNotFoundException f) {
            System.out.println("Saving Tennis Tournament Tracker to " + JSON_FILE + "was UNSUCCESSFUL.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tournament from file
    private void loadTournament() {
        try {
            tn = jsonReader.read();
            addPlayerInfoToPlayerListUponLoad(tn);
            System.out.println("Your Tennis Tournament Tracker from "
                    + JSON_FILE + " has been successfully loaded!");
        } catch (IOException i) {
            System.out.println("Loading Tennis Tournament Tracker from " + JSON_FILE + "was UNSUCCESSFULL.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and adds a tennis player to the list of players in the
    // tournament
    // if the player is not already in the tournament
    private void addNewPlayer() {
        String newPlayerName = displayInputDialogue("Enter New Tennis Player Name",
                "Add Tennis Player",
                JOptionPane.QUESTION_MESSAGE);

        if (newPlayerName != null && newPlayerName.length() != 0) {
            if (tn.addPlayer(newPlayerName)) {
                Player player = tn.findPlayer(newPlayerName);
                addPlayerInfoToPlayerList(player);
                displayMessageDialogue(newPlayerName + " has been successfully added to the tournament!",
                        "Add Tennis Player", JOptionPane.INFORMATION_MESSAGE);
            } else {
                displayMessageDialogue("The player entered is already in the tournament.",
                        "Add Tennis Player",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: specifies the winner, increases the number of match wins for that
    // player and returns the winner
    // if the winner entered is in the tournament
    // otherwise displays message dialog
    private String specifyWinner() {
        boolean playerNotFound = true;
        boolean playerNotNull = true;
        String winner = "";

        while (playerNotFound && playerNotNull) {
            winner = displayInputDialogue("Enter Winner's Name", "Record Winner & Loser",
                    JOptionPane.QUESTION_MESSAGE);

            if (winner != null && winner.length() != 0) {
                Player player = tn.findPlayer(winner);
                if (player != null) {
                    player.increaseMatchWin();
                    updatePlayerListInfo(player);
                    playerNotFound = false;
                } else {
                    displayMessageDialogue("The player you entered is not in the tournament.",
                            "Record Winner & Loser",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                return null;
            }
        }
        return winner;
    }

    // REQUIRES: a non-zero length string from user's previous winner input
    // MODIFIES: this
    // EFFECTS: specifies the loser and increases the number of match losses for
    // that player
    // if the winner from previous input and loser are not the same player and
    // if the loser entered is in the tournament
    private void specifyLoser(String prevWinner) {
        while (true) {
            String loser = displayInputDialogue("Enter Loser's Name", "Record Winner & Loser",
                    JOptionPane.QUESTION_MESSAGE);
            if (loser == null || loser.length() == 0) {
                break;
            }
            if (loser.equals(prevWinner)) {
                displayMessageDialogue("The winner and loser of the match cannot be the same player.",
                        "Record Winner & Loser", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            Player player = tn.findPlayer(loser);
            if (player != null) {
                player.increaseMatchLoss();
                updatePlayerListInfo(player);
                displayMessageDialogue("The winner and loser of the match have been successfully recorded.",
                        "Record Winner & Loser", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                displayMessageDialogue("The player you entered is not in the tournament.",
                        "Record Winner & Loser", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // EFFECTS: displays input dialogues to user
    private String displayInputDialogue(String message, String title, int messageType) {
        return JOptionPane.showInputDialog(null,
                message,
                title,
                messageType);
    }

    // EFFECTS: displays message dialogues to user
    private void displayMessageDialogue(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                messageType);
    }

    // MODIFIES: this
    // EFFECTS: sets up player list view and adds to trackerPanel
    private void setupPlayerList() {
        listModel = new DefaultListModel<>();

        playerList = new JList<>(listModel);
        playerList.setFixedCellWidth(500);
        JScrollPane listScrollPane = new JScrollPane(playerList);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        trackerPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds player name and win-loss record to the player list
    private void addPlayerInfoToPlayerList(Player player) {
        String playerInfo = player.getName() + "  " + player.getMatchWins() + "-" + player.getMatchLosses();

        listModel.addElement(playerInfo);
    }

    // MODIFIES: this
    // EFFECTS: adds player name and win-loss record to the player list upon loading JSON file
    private void addPlayerInfoToPlayerListUponLoad(Tournament tournament) {
        listModel.removeAllElements();
        for (Player p : tournament.getPlayers()) {
            String playerInfo = p.getName() + "  " + p.getMatchWins() + "-" + p.getMatchLosses();
            listModel.addElement(playerInfo);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates player's win/loss record in player list
    private void updatePlayerListInfo(Player player) {
        String playerNewInfo = player.getName() + "  " + player.getMatchWins() + "-" + player.getMatchLosses();

        for (int num = 0; num < listModel.size(); num++) {
            String playerOldInfo = listModel.getElementAt(num);
            if (playerOldInfo.startsWith(player.getName())) {
                listModel.set(num, playerNewInfo);
                break;
            }
        }
    }

    public static void main(String[] args) {
        new TennisTournamentTrackerUI();
    }
}
