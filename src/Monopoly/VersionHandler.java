package Monopoly;

import Monopoly.Squares.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class VersionHandler extends DefaultHandler {
    private Boolean isName = false;
    private Boolean isPrice = false;
    private Boolean isColour = false;
    private String name;
    private int price;
    private ColourGroups colour;

    private int count = 0;
    private HashMap<Integer, Square> squares = new HashMap<>();

    public HashMap<Integer, Square> updateSquares(){
        return squares;
    }

    public void startDocument() {
        //???
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes a) {
        switch (qName) {
            case "name" -> isName = true;
            case "price" -> isPrice = true;
            case "colour" -> isColour = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "BlankSquare" -> {
                BlankSquare blankSquare = new BlankSquare(name);
                this.squares.put(count, blankSquare);
                System.out.println(blankSquare.getName());
                count += 1;
            }
            case "Property" -> {
                Property property = new Property(name, price, colour);
                this.squares.put(count, property);
                System.out.println(property.getName());
                count += 1;
            }
            case "Utility" -> {
                Utility utility = new Utility(name);
                this.squares.put(count, utility);
                System.out.println(utility.getName());
                count += 1;
            }
            case "Railroad" -> {
                Railroad railroad = new Railroad(name);
                this.squares.put(count, railroad);
                System.out.println(railroad.getName());
                count += 1;
            }
            case "Jail" -> {
                Jail jail = new Jail();
                this.squares.put(count, jail);
                System.out.println(jail.getName());
                count += 1;
            }
        }
        System.out.println(squares.size());
    }

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
            colour = ColourGroups.valueOf(value);
            isColour = false;
        }
    }
}
