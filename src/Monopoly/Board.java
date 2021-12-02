package Monopoly;

import Monopoly.Squares.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class Board extends DefaultHandler implements Serializable {
    private HashMap<Integer, Square> squares;

    /**
     * Constructor for board
     * @author Maisha
     */
    /*
    public Board(){
        this.squares = new HashMap<>();
        this.setSquares(); //replace with import from XML
    }*/

    /**
     * Build board according to specified version
     * @author Shrimei
     * @param version
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Board(String version) throws ParserConfigurationException, IOException, SAXException { //FIXME
        super();
        this.importFromXMLFile(version);
    }

    /**
     * @author Shrimei
     * @author Sabah
     * @author Thanuja
     * Sets the properties with their descriptions
     */
    /*
    private void setSquares(){

        BlankSquare GO = new BlankSquare("GO");

        Property Mediterranean = new Property("Mediterranean Avenue", 60, ColourGroups.BROWN);
        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);

        BlankSquare IncomeTax = new BlankSquare("Income Tax");

        Railroad ReadingRail = new Railroad("Reading Railroad");

        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);
        Property Vermont = new Property("Vermont Avenue", 100, ColourGroups.GREY);
        Property Connecticut = new Property("Connecticut Avenue", 120, ColourGroups.GREY);

        Jail VisitingJail = new Jail();

        Property StCharles  = new Property("St. Charles Place", 140, ColourGroups.PINK);

        Utility Electric  = new Utility("Electric Company");

        Property States  = new Property("States Avenue", 140, ColourGroups.PINK);
        Property Virginia  = new Property("Virginia Avenue", 160, ColourGroups.PINK);

        Railroad PennsylvaniaRail = new Railroad("Pennsylvania Railroad");

        Property StJames  = new Property("St. James Place", 180, ColourGroups.ORANGE);
        Property Tennessee  = new Property("Tennessee Avenue", 180, ColourGroups.ORANGE);
        Property NewYork  = new Property("New York Avenue", 200, ColourGroups.ORANGE);

        BlankSquare FreeParking = new BlankSquare("Free Parking");

        Property Kentucky  = new Property("Kentucky Avenue", 220, ColourGroups.RED);
        Property Indiana  = new Property("Indiana Avenue", 220, ColourGroups.RED);
        Property Illinois  = new Property("Illinois Avenue", 240, ColourGroups.RED);

        Railroad BO = new Railroad("B. and O. Railroad");

        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);
        Property Ventnor  = new Property("Ventnor Avenue", 260, ColourGroups.YELLOW);

        Utility WaterWorks = new Utility("Water Works");

        Property Marvin = new Property("Marvin Gardens", 280, ColourGroups.YELLOW);

        BlankSquare GotoJail = new BlankSquare("Go To Jail");

        Property Pacific  = new Property("Pacific Avenue", 300, ColourGroups.GREEN);
        Property NorthCarolina  = new Property("North Carolina Avenue", 300,  ColourGroups.GREEN);
        Property Pennsylvania  = new Property("Pennsylvania Avenue", 320,  ColourGroups.GREEN);

        Railroad ShortLine = new Railroad("Short Line Railroad");

        Property ParkPlace = new Property("Park Place", 350,  ColourGroups.BLUE);

        BlankSquare LuxuryTax = new BlankSquare("Luxury Tax");

        Property Boardwalk  = new Property("Boardwalk", 400, ColourGroups.BLUE);

        this.squares.put(0,GO);
        this.squares.put(1,Mediterranean);
        this.squares.put(2,Baltic);
        this.squares.put(3,IncomeTax);
        this.squares.put(4,ReadingRail);
        this.squares.put(5,Oriental);
        this.squares.put(6,Vermont);
        this.squares.put(7,Connecticut);
        this.squares.put(8,VisitingJail);
        this.squares.put(9,StCharles);
        this.squares.put(10,Electric);
        this.squares.put(11,States);
        this.squares.put(12,Virginia);
        this.squares.put(13,PennsylvaniaRail);
        this.squares.put(14,StJames);
        this.squares.put(15,Tennessee);
        this.squares.put(16,NewYork);
        this.squares.put(17,FreeParking);
        this.squares.put(18,Kentucky);
        this.squares.put(19,Indiana);
        this.squares.put(20,Illinois);
        this.squares.put(21,BO);
        this.squares.put(22,Atlantic);
        this.squares.put(23,Ventnor);
        this.squares.put(24,WaterWorks);
        this.squares.put(25,Marvin);
        this.squares.put(26,GotoJail);
        this.squares.put(27,Pacific);
        this.squares.put(28,NorthCarolina);
        this.squares.put(29,Pennsylvania);
        this.squares.put(30,ShortLine);
        this.squares.put(31,ParkPlace);
        this.squares.put(32,LuxuryTax);
        this.squares.put(33,Boardwalk);
    }*/

    /**
     * Return the list of squares on the board
     * @author Thanuja
     * @return box hashmap
     */
    public HashMap<Integer, Square> getSquares() {
        return squares;
    }

    /**
     * Return go to jail position
     * @author Thanuja
     * @return  position of Go To Jail
     */
    public int getGoToJailPosition(){
        return 26;
    }

    /**
     * Return casted version of jail square
     * @author Thanuja
     * @return Jail square
     */
    public Jail getJailSquare(){
        return (Jail) squares.get(8);
    }

    /**
     * Return jail position
     * @author Thanuja
     * @return int, position of jail
     */
    public int getJailPosition(){
        return 8;
    }

    /**
     * Return go position
     * @author Sabah
     * @return int, position of go
     */
    public int getGoPosition() {
        return 0;
    }

    public void importFromXMLFile(String filename) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(filename);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();

        VersionHandler vh = new VersionHandler();
        s.parse(file, vh);
        this.squares = vh.updateSquares();
    }

}
