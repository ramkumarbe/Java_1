# Snake and Ladder Game

## Introduction
This is a simple implementation of the classic Snake and Ladder game in Java. The game allows multiple players to take turns rolling a dice and moving their pieces across a board consisting of cells with snakes and ladders.

## How to Play
1. Run the `Game.java` file to start the game.
2. Enter the number of rows for the game board.
3. Enter the number of snakes on the board, followed by their head and tail positions.
4. Enter the number of ladders on the board, followed by their bottom and top positions.
5. Enter the number of players, followed by their names.
6. Roll the dice to determine the number of moves each player can make.
7. Players take turns moving their pieces across the board according to the number rolled.
8. If a player lands on a cell with a snake, they move down to the tail position.
9. If a player lands on a cell with a ladder, they move up to the top position.
10. The first player to reach the last cell wins the game.

## Implementation Details
- The game logic is implemented in the `Game.java` file.
- The game view model is implemented in the `GameViewModel.java` file.
- The repository for storing game data is implemented in the `Repository.java` file.
- The game board is represented as a 2D array of cells, with snakes and ladders marked on specific cells.
- Players are represented as objects containing their name and current cell position.
- Players take turns rolling a dice and moving their pieces according to the number rolled.
- Snakes and ladders affect player movement by moving them to specific cell positions on the board.

## Future Enhancements
- Implement a graphical user interface (GUI) for a more interactive playing experience.
- Add support for customizable game board layouts and configurations.
- Implement additional game features, such as power-ups, obstacles, or special cells.
- Enhance player interaction with customizable player avatars, names, or colors.
- Add support for multiplayer gameplay over a network or online.
