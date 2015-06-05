package pl.ppteam.ahp.myride.common;

/**
 * Created by Łukasz on 2015-05-22.
 */
public class Criterium extends Item {

    public static final String priceSymbol = "PRICE";
    public static final String timeSymbol = "TIME";
    public static final String startSymbol = "START";
    public static final String arriveSymbol = "ARRIVE";
    public static final String comfortSymbol = "COMFORT";
    public static final String luggageSymbol = "LUGGAGE";
    public static final String toiletSymbol = "TOILET";

    private String name;
    private String symbol;

    //Zmienne dla projektu
    private boolean selected;

    public Criterium() {

    }

    public Criterium(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Criterium(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
