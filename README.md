# sysc-3110-project

## Installation and Usage:

1. Download and execute jar file onto any IDE.
2. Run the game from GameView.main()
3. When the game opens, first choose how many players you wish to play with from the drop down menu. 
    * If you fail to choose (i.e. hit cancel or close the JOptionPane) the game will terminate.
4. After choosing the number of players, all players will be initialized on position GO, as shown on the top right panel of the GUI.
5. Click the dice button on the centre of the board to roll and start the game.
6. Your position will move automatically, and the square you land on will prompt the property card frame to pop up.
7. If the player lands on a utilility or railroad (shown as light gray on the board), nothing will happen during their turn.
8. When the player closes the property card, they will be given the option to purchase the property. The player must close the pop up to change turns.
10. If a player chooses to purchase, the property will be added onto their list of owned properties on the bottom right panel, wich also displays their money. The price paid for the property will be subtracted from their money.
12. Player can click on their individual properties from the bottom right panel to check the property information.
13. If the current player lands on another player's property, they have to pay rent. The player is notified via a pop up message. 
      * After the pop up message is closed, the rent transaction will occur and the money will be subtracted from their account and added to the owner's account.
14. A player is automatically removed from the game if the player does not have enough money to pay rent.
      * When a player goes bankrupt and is removed from the game, all their properties become available to purchase again.
16. The game continues until the player closes out of the game, or when there is only one player left on the board.

## Milestone 2 Changes:

Implemented a GUI based version of the Monopoly game following the MVC format.
Created test classes for each Model to ensure functionality.


## Known Issues:

1. Current players can press the dice button without closing the property pop-up, resulting in them rolling again. Therefore, the limitation is the current player must close the CardFrame before rolling again.
2. The two panels on the right side of the GUI (PlayerStatePanel and PlayersPanel) change their sizes automatically after every few roll calls. This should not occur.
3. Don’t skip to the next player right away, player might want to buy houses after rolling. (Milestone 3)
4. PieceComponent not yet implemented. The player pieces do not show up on the board yet. For now, PlayersPanel on the top right of the GUI displays the position of each player. 
5. From the current player's list of properties, the player can have multiple cards open at once, even duplicates of the same card.
6. Reduce coupling between the components of the MVC. Reduce workload of CardFrame, should just display the property information.
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
