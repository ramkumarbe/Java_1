# TicTacToe Game

## Introduction
This is a simple implementation of the TicTacToe game in Java. The game allows a player to play against the computer.

## How to Play
1. Run the `TicTocToe.java` file to start the game.
2. Choose who plays first: Computer or Player.
3. The game board is displayed, and the player can make their move by selecting a position on the board (1-9).
4. The computer will then make its move.
5. The game continues until there is a winner or the game ends in a tie.

## Rules
- The game is played on a 3x3 grid.
- The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.
- If all 9 squares are filled and no player has three in a row, the game is a tie.

## Implementation Details
- The game logic is implemented in the `TicTocToe.java` file.
- The game uses a simple minimax algorithm to determine the computer's moves.
- The game board is displayed in the console, and players can interact with it using numeric inputs.
- The game announces the winner or declares a tie at the end of each game.

## Future Enhancements
- Add a graphical user interface (GUI) for a more interactive playing experience.
- Implement different difficulty levels for the computer player.
- Allow players to choose their marks (X or O) at the start of the game.
