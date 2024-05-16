package fr.insa.antoine.max.devibat;

import java.io.Serializable;
import javafx.scene.paint.Color;

public class Couleur implements Serializable {
    private double red;
    private double green;
    private double blue;
    private double opacity;

    public Couleur(double red, double green, double blue, double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public Color toColor() {
        return new Color(red, green, blue, opacity);
    }
}