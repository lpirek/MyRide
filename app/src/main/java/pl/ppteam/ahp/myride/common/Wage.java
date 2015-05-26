package pl.ppteam.ahp.myride.common;

/**
 * Created by £ukasz on 2015-05-26.
 */
public enum Wage {

    W9  (9, "jest ekstremalnie preferowane"),
    W7  (7, "jest bardzo silnie preferowane"),
    W5  (5, "jest silnie preferowane"),
    W3  (3, "jest s³aboo preferowane"),
    W1  (1, "jest równowa¿ne");


    Wage(int value, String description) {
        this.value = value;
        this.description = description;
    }

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
