package pl.ppteam.ahp.myride.common;

import pl.ppteam.ahp.myride.R;

/**
 * Created by Łukasz on 2015-05-22.
 */
public enum MeansOfTransport {

    TRAIN (R.drawable.train, "Pociąg"),
    OUR_CAR (R.drawable.ourcar, "Własny samochód"),
    CAR (R.drawable.car, "Samochód"),
    BUS (R.drawable.bus, "Autobus"),
    PLANE (R.drawable.plane, "Samolot");

    MeansOfTransport(int image, String description) {
        this.image = image;
        this.description = description;
    }

    private int image;
    private String description;

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
