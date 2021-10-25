# sysc-3110-project

## Usage:
Run the main method in `Board.java`.

Sample game output:
```
Welcome to the game of Monopoly!
How many players?
2

===============================================
Player 0
Position: GO
Money: $1500
Enter a command (roll, quit)
roll
Amount rolled is 6
You landed on Property: price=100, name='Vermont Avenue', colourGroup='grey', rent=10, rentWithColourSet=20
Would you like to buy this property? (yes or no)
yes
Congratulations! You now own Vermont Avenue

===============================================
Player 1
Position: GO
Money: $1500
Enter a command (roll, quit)
roll
Amount rolled is 8
You landed on BlankBox: name='Visiting Jail'

===============================================
Player 0
Position: Vermont Avenue
Money: $1400
Current properties you own: 
Property: price=100, name='Vermont Avenue', colourGroup='grey', rent=10, rentWithColourSet=20
Enter a command (roll, quit)
roll
Amount rolled is 9
You landed on Property: price=180, name='Tennessee Avenue', colourGroup='orange', rent=18, rentWithColourSet=36
Would you like to buy this property? (yes or no)
yes
Congratulations! You now own Tennessee Avenue

...
```

## Milestone 1 Changes:

Created initial design of a Monopoly style game.

Can play with 2-4 players. 

Players can either roll the dice or quit the game.

Players can purchase unowned properties, and will have to pay a rent of 10% on the property's price 
when they land on other player's properties. If a player owns all the properties of a colour group, 
the rents of those properties double.

A player goes bankrupt when they no longer have enough money to pay the rent. 
The bankrupt player loses all their properties and is removed from the game.

The game ends when there is only one player remaining in the game.
The remaining player is the winner.

## Known Issues:

When first starting the game, an integer must be entered for the number of players.

## Remaining Deliverables:

Milestone 2: GUI-based version and unit tests.

Milestone 3: Additional features (jail, "Go", railroad, utility) and "AI" players. 

Milestone 4: Save/load features and customizable games.

## Roadmap Ahead:

Improvements for future Milestones:
* Exception handling for `roll`, `quit`, and number of players
* Separate method for getting the number of players in Board.java
* Separate method for when a player goes bankrupt in Board.java
* printCurrentState should return String, so it's easier for GUI
* Add a command to switch turn instead of doing it automatically
    * Future milestones, players may also want to buy houses/hotels before their turn is over


## Contributors:
* Thanuja Sivaananthan
* Shrimei Chock
* Sabah Samwatin
* Maisha Abdullah
