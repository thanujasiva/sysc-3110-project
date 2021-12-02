package Monopoly;

import Monopoly.Squares.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.*;
import java.util.HashMap;

public class VersionHandler extends DefaultHandler {
    private Boolean isName = false;
    private Boolean isPrice = false;
    private Boolean isColour = false;
    private String name;
    private int price;
    private ColourGroups colour;
    private HashMap<Integer, Square> squares;
    private int count = 0;

    public VersionHandler(){
         squares = new HashMap<>();
    }

    /**
     * Return the list of squares
     * @return squares hashmap
     */
    public HashMap<Integer, Square> updateSquares(){
        return squares;
    }

    /**
     * Check which attribute is being populated
     * @author Shrimei
     * @param uri
     * @param localName
     * @param qName
     * @param a
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes a) {
        switch (qName) {
            case "name":
                isName = true;
                break;
            case "price":
                isPrice = true;
                break;
            case "ColourGroups":
                isColour = true;
                break;
        }
    }

    /**
     * Create the appropriate square
     * @author Shrimei
     * @param uri
     * @param localName
     * @param qName
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "BlankSquare":
                BlankSquare blankSquare = new BlankSquare(name);
                this.squares.put(count, blankSquare);
                count += 1;
                break;
            case "Property":
                Property property = new Property(name, price, colour);
                this.squares.put(count, property);
                count += 1;
                break;
            case "Utility":
                Utility utility = new Utility(name);
                this.squares.put(count, utility);
                count += 1;
                break;
            case "Railroad":
                Railroad railroad = new Railroad(name);
                this.squares.put(count, railroad);
                count += 1;
                break;
            case "Jail":
                Jail jail = new Jail();
                this.squares.put(count, jail);
                count += 1;
                break;
        }
    }

    /**
     * Get attributes within each element
     * @author Shrimei
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        if (isName) {
            name = new String(ch, start, length);
            isName = false;
        } else if (isPrice) {
            price = Integer.parseInt(new String(ch, start, length));
            isPrice = false;
        } else if (isColour) {
            String value = new String(ch, start, length);
            colour =  ColourGroups.valueOf(value);
            Color.getColor(new String(ch, start, length));
            isColour = false;
        }
    }
}
