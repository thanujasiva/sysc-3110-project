package Monopoly;

public interface MonopolyInterfaceView {

    void handleBoardPlayersUpdate();

    void handleRoll();

    void handlePlayerState();

    void handleEndOfTurn();

    void handleJailEntered(String message);

    void handleJailExited(String message);

    void handleBankruptcy();

    void handlePassedGo();
}
