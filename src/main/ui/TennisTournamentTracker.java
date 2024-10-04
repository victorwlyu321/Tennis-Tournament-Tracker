package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Player;
import model.Tournament;

// A Tennis Tournament Application that allows users to add tennis players to a tournament, 
// view the list of players, view players' win-loss records, and specify the winner and loser of a match
public class TennisTournamentTracker {
    private Tournament tournament;
    private Scanner input;
    private boolean isRunning;

    // EFFECTS: runs the tennis tournament application
    public TennisTournamentTracker() {
        init();

        System.out.println("Welcome to the Tennis Tournament Tracker!");

        while (this.isRunning) {
            displayMenu();
            String command = this.input.nextLine();
            command = command.toLowerCase();

            processCommands(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes tennis tournament with starting values
    public void init() {
        this.tournament = new Tournament();
        this.input = new Scanner(System.in);
        this.isRunning = true;
    }

    // EFFECTS: displays menu options to user
    private void displayMenu() {
        System.out.println("Please select from the following options:\n");
        System.out.println("a: Add a new tennis player to the tournament");
        System.out.println("v: View all players in the tournament");
        System.out.println("s: Specify the winner and loser of a match");
        System.out.println("r: View players' win-loss records");
        System.out.println("q: Exit the application");
    }

    // EFFECTS: processes user's input
    private void processCommands(String input) {
        switch (input) {
            case "a":
                addNewPlayer();
                break;
            case "v":
                displayPlayers();
                break;

            case "q":
                quitTracker();
                break;
            default:
                System.out.println("Sorry, please choose a valid option from the menu");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for a tennis player name, 
    //          creates a new tennis player with given name and 
    //          adds the player to the list of players in the tournament
    private void addNewPlayer() {
        System.out.println("Please enter the tennis player's name");
        String newPlayerName = this.input.nextLine();
        tournament.addPlayer(newPlayerName);
        System.out.println(newPlayerName + " has been successfully added to the tournament!");
    }

    // EFFECTS: returns a list of players in the tournament
    private void displayPlayers() {
        ArrayList<Player> players = tournament.getPlayers();
        if (players.isEmpty()) {
            System.out.println("There are no players in the tournament!");
        } else {
            for (Player p : players) {
                System.out.println(p.getName());
            }
        }
    }

    // EFFECTS: displays list of players and 
    // prompts user to enter the names of the winner and loser of a tennis match
    private void specifyPlayer() {
        displayPlayers();
        System.out.println("Please enter the name of the winning player");
        String winner = this.input.nextLine();
        specifyWinner(winner);
        specifyLoser();
    }

    // MODIFIES: this
    // EFFECTS: prompts user for the winner and increases the number of match wins for that player
    private void specifyWinner(String winner) {
        Player player = tournament.findPlayer(winner);
        if (player != null) {
            player.increaseMatchWin();
        } else {
            System.out.println("Sorry, the player you entered is not in the tournament");
            specifyPlayer();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for the loser and increases the number of match losses for that player
    private void specifyLoser() {
        boolean playerExist = false;

        while (!playerExist) {
            System.out.println("Please enter the name of the losing player");
            String loser = this.input.nextLine();
            Player player = tournament.findPlayer(loser);
            if (player != null) {
                player.increaseMatchLoss();
                playerExist = true;
            } else {
                System.out.println("Sorry, the player you entered is not in the tournament");
                displayPlayers();
            }

        }

    }

    // EFFECTS: returns a player's win-loss record
    private void displayPlayerRecord() {
        displayPlayers();
        System.out.println("Please select a player from the list");
        String selectedPlayer = this.input.nextLine();
        Player player = tournament.findPlayer(selectedPlayer);
        System.out.println("W-L: " + player.getMatchWins() + "-" + player.getMatchLosses());
    }

    // MODIFIES: this
    // EFFECTS: returns program quitting messages and sets application to not running
    private void quitTracker() {
        System.out.println("Game, set, match!");
        System.out.println("Thank you for using the Tennis Tournament Tracker!");
        this.isRunning = false;
    }
}
