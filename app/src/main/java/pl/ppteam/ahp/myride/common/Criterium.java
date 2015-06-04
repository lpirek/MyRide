package pl.ppteam.ahp.myride.common;

/**
 * Created by Łukasz on 2015-05-22.
 */
public class Criterium extends Item {

    private static final String priceSymbol = "PRICE";
    private static final String timeSymbol = "TIME";
    private static final String startSymbol = "START";
    private static final String arriveSymbol = "ARRIVE";
    private static final String comfortSymbol = "COMFORT";
    private static final String luggageSymbol = "LUGGAGE";
    private static final String toiletSymbol = "TOILET";

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
