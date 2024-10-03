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

    // EFFECTS: runs the tennis tournament application
    public TennisTournamentTracker() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: initializes tennis tournament with starting values
    public void init() {
        tournament = new Tournament();
        input = new Scanner(System.in);
    }

    // EFFECTS: processes user inputs from the main menu
    private void handleMenu() {
        // stub
    }

    // EFFECTS: displays menu options to user
    private void displayMenu() {
        // stub
    }

    // EFFECTS: processes user's input
    private void processCommand(String input) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: creates a new tennis player and 
    //          adds the player to the list of players in the tournament
    private void addNewPlayer(String name) {
        Player newPlayer = new Player(name);
        tournament.addPlayer(newPlayer);
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

    // EFFECTS: prompts user to select a tennis player from list of players
    private Player selectPlayer(String player) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: selects the winner of a match and increases the number of match wins of the player
    private void specifyWinner(String winner) {
        Player player = tournament.findPlayer(winner);
        if (player != null) {
            player.increaseMatchWin();
        }
    }

    // MODIFIES: this
    // EFFECTS: selects the loser of a match and increases the number of match losses of the player
    private void specifyLoser(String loser) {
        Player player = tournament.findPlayer(loser);
        if (player != null) {
            player.increaseMatchLoss();
        }
    }

    // EFFECTS: returns a player's win-loss record
    private void displayPlayerRecord() {
        displayPlayers();
        System.out.println("Please select a player from the list");
        String answer = this.input.nextLine();
        Player player = tournament.findPlayer(answer);
        System.out.println("W-L: " + player.getMatchWins() + "-" + player.getMatchLosses());
    }
}
