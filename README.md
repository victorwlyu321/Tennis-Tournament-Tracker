# Tennis Tournament Tracker

## Project Description
**Tennis Tournament Tracker** is a Java application designed to help manage and track tennis player data in a tournament. It allows users to enter tennis players' names, view a list of participating players, and record the winner and loser of a match. The Tennis Tournament Tracker also provides each player's win-loss record as matches are being played, enabling users to follow players' performances over the tournament. This project includes unit testing for all ```model``` and ```persistence``` classes.

**Why this project:** This tracker is designed for tennis fans, coaches, players, as well as tennis tournament organizers who wish to keep track of players and players' performance in a tournament. As an avid tennis player and fan myself, I find tennis tournament data and players statistics to be a fascinating aspect of the sport. This project allows me to combine my passion for tennis with software development, creating a tool that makes tournament data and player performance monitoring more accessible, convenient, and engaging.

## Features
### Main Menu
Welcome page for users to enter tracker, save current Tennis Tournament Tracker data to file, and load existing tracker data from file, which would appear on the Tracking Page.
<div align="center">
    <img src="README images/Main%20Menu.png" alt="Main Menu" width="700"/>
</div>

### Tracking Page
Tracking page for users to add tennis players to the tracker (if not already in the tracker), view a list of players in the tournament, specify the winner and the loser of a match, and view players' win-loss records.

The list view updates in real time as users add players and update player records.
<div align="center">
    <img src="README images/Tracking%20Page.png" alt="Tracking Page" width="700"/>
</div>

<div align="center" style="display: flex; justify-content: center; gap: 10px;">
    <img src="README images/Add%20Tennis%20Player.png" alt="Add Tennis Player" width="250"/>
    <img src="README images/Enter%20Winner.png" alt="Enter Winner" width="250"/>
    <img src="README images/Enter%20Loser.png" alt="Enter Loser" width="250"/>
</div>

### Event Tracking & History
Every action performed that relates to a player and their match records is logged in an event log after the user quits/closes the Tennis Tournament Tracker application.

_Event Log Sample:_

Mon Nov 25 00:40:59 PST 2024 - Roger Federer has been successfully added to the tournament!<br/>
Mon Nov 25 00:41:11 PST 2024 - Rafael Nadal has been successfully added to the tournament!<br/>
Mon Nov 25 00:41:16 PST 2024 - Roger Federer's match wins have increased by 1!<br/>
Mon Nov 25 00:41:19 PST 2024 - Rafael Nadal's match losses have increased by 1!<br/>
Mon Nov 25 00:41:24 PST 2024 - Rafael Nadal's match wins have increased by 1!<br/>
Mon Nov 25 00:41:27 PST 2024 - Roger Federer's match losses have increased by 1!<br/>
Mon Nov 25 00:41:34 PST 2024 - Novak Djokovic has been successfully added to the tournament!<br/>

## Unit Testing
The project includes unit tests for key components using JUnit, ensuring that the ```Player```, ```Tournament```, ```JsonReader```, and ```JsonWriter``` classes work as expected with 100% code coverage.

**Tested Classes:**
1. Player (in ```model``` package)
   - **Tests:** Constructor, match win/loss increment, and JSON serialization
2. Tournament (in ```model``` package)
   - **Tests:** Adding players, finding players, and JSON serialization
3. JsonReader (in ```persistence``` package)
   - **Tests:** Reading from a non-existent file, empty tournament, and regular tournament with players
4. JsonWriter (in ```persistence``` package)
   - **Tests:** Writing to an invalid file, empty tournament, and regular tournament with players

## How It's Built
**Tech used:** Java, JUnit, Swing

### <ins>Architecture Overview<ins> 
The project structure implements 3 layers, each with their own responsibilities: 

- **Model:** Core application logic
- **Persistence:** Data storage and retrieval
- **UI:** Both console-based and Java Swing GUI

### <ins>Object-Oriented Design<ins>
The application follows several OOP principles:

**Encapsulation**
- Each class has well-defined responsibilities
- The ```Player``` class encapsulates player statistics and match records
- The ```Tournament``` class manages the collection of players and tournament operations

**Inheritance & Polymorphism**
- The ```Writable``` interface enables polymorphic JSON serialization
- UI components extend from Swing classes (e.g., ```TennisTournamentTrackerUI extends JFrame```)
- Custom ```BackgroundImagePanel``` extends ```JPanel``` for specialized rendering
- ```WindowListener``` interface implementation for handling window events

### <ins>Data Persistence<ins>
JSON-based data persistence implementation:

**Data Writing**
- ```JsonWriter``` class handles saving tournament state
- Implements the file I/O operations with error handling
- Uses the ```org.json``` library for JSON formatting

**Data Reading**
- ```JsonReader``` class manages loading tournament state
- Parses JSON structure back into application objects

### <ins>Event Tracking System<ins>
The application implements a singleton-based event logging system:

**Singleton Pattern**
- ```EventLog``` class ensures a single system-wide event log
- Static ```getInstance()``` method provides global access point
- Private constructor prevents multiple instances

**Event Tracking**
- All significant actions are logged (player additions, match results)
- Events include timestamps and descriptions
- Log is automatically printed to console on application close

### <ins>User Interface<ins>
The application offers two interface options:

**Graphical Interface (Java Swing)**
- Window-based interface using JFrame
- Card layout for multiple views/panels
- Custom background images using ```BackgroundImagePanel``` class
- Interactive components:
  - JButtons for actions
  - JList for player display
  - JScrollPane for list scrolling
  - Dialog boxes for user input and messages

**Console Interface**
- Text-based menu system
- Command-line input
- Formatted output display

## How to Run Locally
1. Clone the repo
```
git clone https://github.com/victorwlyu321/Tennis-Tournament-Tracker.git
```
2. Locate and run the ```TennisTournamentTrackerUI.java``` file in the ```ui``` folder in the following directory:

```\Tennis-Tournament-Tracker\src\main\ui```

## Some Technical Challenges and Achievements
1. Utilized OOP principles to set up different classes, each with single responsibility, to enhance scalibility and maintainability
2. Data persistence allowing users to save and reload tournament data as JSON files
3. Singleton-based event logging system tracking event history with detailed logs
4. 100% unit test coverage for all ```model``` and ```persistence``` classes
5. Created a dynamic, user-friendly GUI using Java Swing, featuring interactive components like buttons and tables for inputting player data and viewing statistics, and utilizing layout managers to maintain a clean, organized interface

## To-Do's & Potential Improvements:
1. Refactor the ```TennisTournamentTrackerUI``` class by breaking it into smaller, separate classes to improve readability and maintainability.
2. Create a ```Menu``` class and a ```Tracker``` class for managing the two different pages (panels) in the app, adhering to the Single Responsibility Principle.
3. Refactor the ```specifyWinner``` and ```specifyLoser``` methods in ```TennisTournamentTracker``` and ```TennisTournamentTrackerUI``` to reduce code duplication and improve readability.
4. Extract common logic from the ```specifyWinner``` and ```specifyLoser``` methods into separate helper methods to enhance code reusability and reduce method length.
5. Improve method coupling by abstracting duplicated code into new methods, making it easier to understand and maintain.
