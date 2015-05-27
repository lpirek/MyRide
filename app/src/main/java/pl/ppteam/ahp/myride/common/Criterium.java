package pl.ppteam.ahp.myride.common;

/**
 * Created by Łukasz on 2015-05-22.
 */
public class Criterium extends Item {

    private String name;
    private boolean selected;
    public Criterium() {

    }

    public Criterium(String name) {
        this.name = name;
    }

    public Criterium(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
