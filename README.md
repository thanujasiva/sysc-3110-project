# sysc-3110-project

## Installation and Usage:

1. Download and execute jar file onto any IDE.
2. Run the game from GameView.main()
3. The game first starts with a pop up window asking if the user wants to reload a previous game. 
    - If user presses `YES` an Input window comes up asking to input the name of the game that was saved.
    - If user presses `CANCEL` or `NO` the 'Choose Version' window pops up.
4. The 'Choose Version' window prompts the user to select a version of the Monopoly from a drop down menu.
    - User can choose from `Standard`, `Shuffled` and `International` options.
5. After selecting the version the game then asks for how many total players the user wants to play with, followed by a window which asks for the number of AI players the user wants.
   - If the user fails to choose (i.e. hits cancel or closes the JOptionPane) the game will terminate.
6. After choosing the number of players, all players will start on position GO of the board.
7. The user should click the dice button on the centre of the board to roll and start the game.
8. The player moves automatically on the board as shown by the player pieces.
   - The manual players are shown with the colour CYAN.
   - The AI players are shown with PINK and move on their own. The roll button will be disabled during and AI players' turn.
9. Any player that passes GO (the starting position) collects $200.
10. The Ownable Square on which the pieces land on will prompt the card frame to pop up.
11. The card prompts the user to purchase if they land on an Ownable Square.                                                  
   - The manual players have to press the `YES` button if they want to buy and the `NO` button for not buying.
12. If the player lands on a light grey tax box, the Game does not do anything.
13. If a player chooses to purchase an ownable square (property/railroad/utilities), the owned square gets added to their list of properties on the bottom right panel and the price amount gets deducted from their total money.
14. The bottom right panel also shows the current player/AI player number along with the up to date money they have.
15. Manual players can click on their individual properties from the bottom right panel to check the property information or to buy houses/hotels on them.
16. When a manual player owns all the properties of a colour set (for example, all properties of the green colour), they can buy houses on any of the properties of the colour set. 
17. Manual players must have equal number of houses on each property, of the same colour set, to buy the next house/hotel.
18. If the current player lands on another player's square, the Ownable Square card pops up showing the rent amount being paid on the top bar of the card frame.
   - After the `OK` button is pressed on the card, the rent transaction will occur, and the money will be subtracted from their account and added to the owner's  account.
   - The rent for each property is displayed on each property card.
   - The rent for property increases as the player buys more houses or hotels.
   - The rent for utility is based on the dice rolled amount of the player who lands on it.
   - The rent for railroad increases with the number of railroads owned by a player.
19. Rolling doubles thrice or landing on the `Go to Jail` square will send any player to Jail.
  - For exiting jail, a manual player gets the option to pay a $50 fine or roll doubles in the first two rounds of staying in jail. If it is their third round, and they still do not roll doubles, they must pay a $50 fine to exit jail.
20. If the user wants to `Save` the game and resume playing it later, at any point in the game, they can choose the `Save` option available from the menu bar of the Board Frame.
    - Clicking on `Save` prompts the user the input a name for the game to be saved.
    - The user can resume playing it by typing the same name whenever they want.
21. A player goes bankrupt and is automatically removed from the game if the player does not have enough money to pay the rent or the jail exit fee.
    - When a player goes bankrupt and is removed from the game, all their properties become available to purchase again.
22. The game ends when there is only one player left on the board or when a user closes out of the game.
23. In case any player wins, a window pops up congratulating the winner.


## Milestone 4 Changes:

Added pieces for all the players to show movement on the board.
Added AI players and their implementations.
Implemented other ownable squares (Jail, GO, Utilities, Railroads).
Added the option for buying Houses and Hotels when all the properties of a single colour set are owned.


## Known Issues:

1. Utility has an empty implementation of getRent(int).
2. Board cuts off property squares at top and bottom.
3. The board shows only one (the most recent) player when both players land on the same property square.
4. All view classes should extend JFrame or JPanel (some classes contain a mainPanel or mainFrame, and others extend a JPanel or JFrame which is not consistent).
5. Have AI functionality (checks for if they want to buy ownable squares/houses) in PlayerAI and not in view.


## Contributors:

* Thanuja Sivaananthan
* Shrimei Chock
* Sabah Samwatin
* Maisha Abdullah
