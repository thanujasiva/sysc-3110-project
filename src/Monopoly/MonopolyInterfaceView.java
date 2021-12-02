package Monopoly;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface MonopolyInterfaceView {

    void handleBoardPlayersUpdate() throws ParserConfigurationException, IOException, SAXException;

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
