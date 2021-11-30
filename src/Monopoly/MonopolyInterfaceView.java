package Monopoly;

public interface MonopolyInterfaceView {

    void handleBoardPlayersUpdate();

    void handleRoll();

    void handlePlayerState();

    void handleEndOfTurn();

    void handleJailEntered(String message);

    void handleJailExited(String message);

    void handleBankruptcy(Player player);

    void handlePassedGo();

    boolean askIfJailExit();

    void handleWinner();

    String getFilenameToSaveGame(String extension);

    void handleGameSaving(boolean savedSuccesfully, String fileName);
}
