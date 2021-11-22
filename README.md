# sysc-3110-project

## Installation and Usage:

1. Download and execute jar file onto any IDE.
2. Run the game from GameView.main()
3. The game first asks for how many players the user wants to play with, followed by a window which asks for the number of AI players the user wants to play with.
    * If you fail to choose (i.e. hit cancel or close the JOptionPane) the game will terminate.
4. After choosing the number of players, all players will be initialized on position GO, as shown on the top right panel of the GUI.
5. Click the dice button on the centre of the board to roll and start the game.
6. Your player will move automatically on the board as shown by the player pieces.
   * The manual players are shown with the colour CYAN 
   * The AI players are shown with PINK.
7. From the second round, any player that passes GO (the starting position) collects $200.
8. The square the pieces land on will prompt the property card frame to pop up.
9. The card prompts the user to purchase if they land on an Ownable Square.                                                                                            * The manual players have to press the ‘YES’ button if they want to buy and the ‘NO’ button for not buying.
10. If the player lands on a light grey tax box, the Game does not do anything for now.
11. If a player chooses to purchase an ownable square (property/railroad/utilities), the owned square gets added to their list of properties on the bottom right panel and the price amount gets deducted from their total money.
12. The bottom right panel also shows the current player/AI player number along with the up to date money they have.
13. Manual players can click on their individual properties from the bottom right panel to check the property information and buy houses/hotels on them.
14. When a manual player owns all the properties of a colour set (for example, all properties of the green colour), they can buy houses on any of the properties of the colour set.
15. If the current player lands on another player's property, they have to pay rent. 
   * The rent for each property is displayed on each property card.
   * The rent increases as the player buys more houses or hotels.
   * The rent for utility is based on the dice rolled amount of the player who lands on it.
   * The rent for railroad increases with the number of railroad owned by a player.
16. When any player lands on an owned property, the property card appears to let the player know they have to pay the rent. 
   * After the ‘OK’ button is pressed on the card, the rent transaction will occur, and the money will be subtracted from their account and added to the owner's account.
17. Rolling doubles thrice or landing on the ‘Go to Jail’ square will send any player to Jail
   * For exiting jail, a manual player gets the option to pay a $50 fine or roll doubles in the first two rounds of staying in jail. If it is their third round, and they still do not roll doubles, they must pay a $50 fine to exit jail.
18. A player goes bankrupt and is automatically removed from the game if the player does not have enough money to pay the rent or the jail exit fee.
   * When a player goes bankrupt and is removed from the game, all their properties become available to purchase again.
19. The game ends when there is only one player left on the board or when a manual player closes out of the game.
20. In case any player wins, a window pops up congratulating the winner. 


## Milestone 3 Changes:

Added pieces for all the players to show movement on the board.
Added AI players and their implementations.
Implemented other ownable squares (Jail, GO, Utilities, Railroads)
Added the option for buying Houses and Hotels when all the properties of a single colour set are owned.


## Known Issues:


1. The two panels on the right side of the GUI (PlayerStatePanel and PlayersPanel) change their sizes automatically after every few roll calls. This should not occur.
2. Utility has an empty implementation of getRent(int).
3. Player should only be able to buy second house after all other properties of colour group have at least 1 house
4. Board cuts off property squares at top and bottom
5. The board shows only one (the most recent) player when both players land on the same property square.
6. There is code repetition in CardFrame when displaying card info.
7. All view classes should extend JFrame or JPanel (some classes contain a mainPanel or mainFrame, is not consistent).
8. Have AI functionality (checks for if they want to buy ownable squares/houses) in PlayerAI and not in view.


## Remaining Deliverables:

* Implement tax boxes
* Milestone 4: Save/load features and customizable games.
* Make dicePanel include the diceButton


## Roadmap Ahead:

Improvements for future Milestones:
 
* Make AI player that learns from previous turns
* AI players will have more random decisions instead of buying every property they can afford
* Tests for AI
* Making sure that player cannot buy houses on a property until all properties of the same colour group acquires at least one house
* More descriptive messages for not being able to buy house/hotel
* Make dicePanel include the diceButton
* Add a Monopoly logo on the centre of the Board.

## Contributors:

* Thanuja Sivaananthan
* Shrimei Chock
* Sabah Samwatin
* Maisha Abdullah
