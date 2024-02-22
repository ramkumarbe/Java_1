# Cricket Scoreboard Console Application
This Java console application simulates a cricket scoreboard, allowing users to manage and track the progress of a cricket match. The application provides functionality to record ball-by-ball updates, manage players, and generate live and innings scoreboards.
## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)
## Features
- **Ball-by-Ball Updates**: Record ball-by-ball updates including runs scored, wides, no-balls, wickets, etc.
- **Player Management**: Manage players including batsmen, bowlers, and all-rounders.
- **Innings Tracking**: Track innings progress, including score, wickets, run rate, and projected score.
- **Live Scoreboard**: Generate a live scoreboard displaying the current status of the match.
- **Innings Scoreboard**: Generate a detailed scoreboard for each innings including batsmen and bowler statistics.
## Prerequisites
- Java Development Kit (JDK) installed on your system.
- IDE (Integrated Development Environment) such as IntelliJ IDEA or Eclipse.
## Installation

1. Clone the repository to your local machine:

    ```
    git clone https://github.com/ramkumarbe/Java/new/main/eclipse-workspace/Java/src/com/ramkumarbe/consoleApplication/cricketscoreboard
    ```

2. Open the project in your preferred IDE.
## Usage
1. Run the `Main` class to start the application.
2. Follow the prompts to set up the match, record ball-by-ball updates, and generate scoreboards.
## Documentation
The project structure consists of several packages and classes:
- `com.ramkumarbe.consoleApplication.cricketscoreboard.dto`: Contains Data Transfer Objects (DTOs) representing various entities such as players, teams, overs, and balls.
- `com.ramkumarbe.consoleApplication.cricketscoreboard.repository`: Contains the `ScoreCardRepository` class responsible for managing data related to teams and players.
- `com.ramkumarbe.consoleApplication.cricketscoreboard.scorecardmanager`: Contains the `ScoreCardManagerViewModel` class, which serves as the core logic for managing the cricket scoreboard.
- `com.ramkumarbe.consoleApplication.cricketscoreboard.view`: Contains view classes for interacting with the user interface.

For detailed documentation of each class and its functionalities, refer to the source code comments.

```
                 +-----------------------+
                 |      Main Class       |
                 +-----------------------+
                          |
                 +-----------------------+
                 |  ScoreCardManagerView |
                 +-----------------------+
                          |
           +--------------+--------------+
           |                             |
+-------------------+       +------------------------+
| ScoreCardManager  |       | ScoreCardRepository    |
+-------------------+       +------------------------+
| - repository      |       | - firstInnings: List<> |
| - view            |       | - secondInnings: List<>|
| - firstInnings    |       | - india: Team          |
| - secondInnings   |       | - australia: Team      |
| - totalOvers      |       +------------------------+
| - ballOfTheOver   |
| - isInningsOver   |
| - isFirstInnings  |
| - batsmen         |
| - bowlers         |
| - striker         |
| - nonStriker      |
| - bowler          |
| - previousBowler  |
| - currentOver     |
+-------------------+
      |      |
+------------------------+
| Innings                |
+------------------------+
| - battingTeam: Team    |
| - bowlingTeam: Team    |
| - oversOfTheInnings:   |
|     List<Over>         |
+------------------------+
      |      |
+------------------------+
| Over                   |
+------------------------+
| - numberOfOver: int    |
| - balls: List<Ball>    |
+------------------------+
      |      |
+------------------------+
| Team                   |
+------------------------+
| - name: String         |
| - score: int           |
| - wickets: int         |
| - projectedScore: int  |
| - extras: int          |
| - runRate: float       |
| - players: Player[]    |
+------------------------+
      |      |
+------------------------+
| Player                 |
+------------------------+
| - name: String         |
| - role: Role           |
| - runs: int            |
| - wickets: int         |
| - ballsFaced: int      |
| - ballsBowled: int     |
| - fours: int           |
| - sixes: int           |
| - conceededRuns: int   |
| - strikeRate: float    |
| - economy: float       |
| - isBatted: boolean    |
| - canBowl: boolean     |
+------------------------+
      |      |
+------------------------+
| Ball                   |
+------------------------+
| - batsman: Player      |
| - bowler: Player       |
| - run: int             |
| - over: Over           |
| - ballOfTheOver: int   |
+------------------------+
```
