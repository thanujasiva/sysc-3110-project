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


## Milestone 2 Changes:

Implemented a GUI based version of the Monopoly game following the MVC format.
Created test classes for each Model to ensure functionality.


## Known Issues:

1. Current players can press the dice button without closing the property pop-up, resulting in them rolling again. Therefore, the limitation is the current player must close the CardFrame before rolling again.
2. The two panels on the right side of the GUI (PlayerStatePanel and PlayersPanel) change their sizes automatically after every few roll calls. This should not occur.
3. Don’t skip to the next player right away, player might want to buy houses after rolling. (Milestone 3)
4. PieceComponent not yet implemented. The player pieces do not show up on the board yet. For now, PlayersPanel on the top right of the GUI displays the position of each player. 
5. From the current player's list of properties, the player can have multiple cards open at once, even duplicates of the same card.
6. Reduce coupling between the components of the MVC. Reduce the workload of CardFrame, should just display the property information.
7. Reduce responsibility of player. Move rent methods to game class so that player can just act as a model.

## Remaining Deliverables:

Milestone 3: Additional features (jail, "Go", railroad, utility) and "AI" players. 

Milestone 4: Save/load features and customizable games.

## Roadmap Ahead:

Improvements for future Milestones:

* ColourGroup of properties needs to be displayed on bottom right panel so that the player does not have to manually view the properties they have, to check if they own a colour group. 
* Add pop-up for when player gets a colour group.
* Need to add a Monopoly title on the centre of the Board.
* 3 doubles will send a player to jail (Milestone 3)
* BoardPanel will take a Board object directly instead of a Game object.

## Contributors:
* Thanuja Sivaananthan
* Shrimei Chock
* Sabah Samwatin
* Maisha Abdullah
