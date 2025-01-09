# Tennis Tournament Tracker

## Project Description
**Tennis Tournament Tracker** is an application designed to help manage and track tennis player data in a tournament. It allows users to enter tennis players' names, view a list of participating players, and record the winner and loser of a match. The Tennis Tournament Tracker also provides each player's win-loss record as matches are being played, enabling users to follow players' performances over the tournament.

This tracker is designed for tennis fans, coaches, players, as well as tennis tournament organizers who wish to keep track of players and players' performance in a tournament. As an avid tennis player and fan myself, I find tennis tournament data and players statistics to be a fascinating aspect of the sport. This project allows me to combine my passion for tennis with software development, creating a tool that makes tournament data and player performance monitoring more accessible, convenient, and engaging.

 ## User Stories
 - I want to be able to add a tennis player to the tracker *(tennis player is X)*
 - I want to be able to view a list of players in the tournament *(tournament is Y)*
 - I want to be able to specify the winner and the loser of a match that has been played outside of this app
 - I want to be able to view a player's win-loss record
 - As a user, I want to be able to save my Tennis Tournament Tracker if I like
 - As a user, I want to be able to load a Tennis Tournament Tracker from file if I like

 ## Instructions for End User

 - You can generate the first required action related to the user story "adding multiple Xs to a Y" by:
    1) clicking the "Enter Tournament!" button in the Main Menu
    2) clicking the "Add Player" button
    3) entering the tennis player's name
    4) clicking the "OK" button
 - You can generate the second required action related to the user story "adding multiple Xs to a Y" by...
    1) clicking the "Enter Tournament!" button in the Main Menu
    2) adding at least 2 tennis players into the tournament
    3) clicking the "Record Winner & Loser" button
    4) entering the winner's name and click "OK"
    5) entering the loser's name and click "OK"
 - You can locate my visual component in the Main Menu and on the page after clicking "Enter Tournament!" 
 - You can save the state of my application by clicking the "Save" button in the Main Menu
 - You can reload the state of my application by clicking the "Load" button in the Main Menu


## Phase 4: Task 2 - Event Log Sample

Mon Nov 25 00:40:59 PST 2024 - Roger Federer has been successfully added to the tournament!<br/>
Mon Nov 25 00:41:11 PST 2024 - Rafael Nadal has been successfully added to the tournament!<br/>
Mon Nov 25 00:41:16 PST 2024 - Roger Federer's match wins have increased by 1!<br/>
Mon Nov 25 00:41:19 PST 2024 - Rafael Nadal's match losses have increased by 1!<br/>
Mon Nov 25 00:41:24 PST 2024 - Rafael Nadal's match wins have increased by 1!<br/>
Mon Nov 25 00:41:27 PST 2024 - Roger Federer's match losses have increased by 1!<br/>
Mon Nov 25 00:41:34 PST 2024 - Novak Djokovic has been successfully added to the tournament!<br/>

## Phase 4: Task 3

I would refactor my TennisTournamentTrackerUI class to break it into smaller, separate classes so that all the functionality related to the UI does not exist in a single class and file, which makes finding code and debugging more difficult and reduces readability. I can possibly create a menu class and a tracker class for the 2 different pages (panels) in my app. This will help improve the single responsibility principle and increase readability. Another area suitable for refactoring is the specifyWinner and specifyLoser methods in both TennisTournamentTracker and TennisTournamentTrackerUI. They have very similar code and parts of them can be extracted into their own methods, rather than duplicating code. This will also help with increasing code readibility as their method sizes are reduced and improve coupling by abstracting duplicated code into methods. The specifyWinner and specifyLoser methods in TennisTournamentTrackerUI specifically are fairly long which makes it more difficult to understand its function, so they can benefit from having methods extracted to capture the behaviour of the original methods.
