/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.scene.canvas.Canvas;

/**
 *
 * @author laelt
 */
public class Vu_Dessin_Bat extends MonCanvas {
    
    private Canvas c_Zone_Dessin;
    private Controleur controleur;
    
    public Vu_Dessin_Bat(MainPane main) {
        super(main);
        this.c_Zone_Dessin = this.getCanvas();
        this.getChildren().add(c_Zone_Dessin);
        this.controleur = this.getMain().getControleur();
        
        this.c_Zone_Dessin.setOnMousePressed((t)->{
            this.controleur.clickZoneDessin(t);
        });
        this.c_Zone_Dessin.setOnMouseDragged((t)->{
            this.controleur.traceRectangleConstruction(t);
        });
        this.c_Zone_Dessin.setOnMouseReleased((t)->{
            this.controleur.relacheZoneDessin(t);
        });
    }
    
    public Canvas getc_Zone_Dessin(){return c_Zone_Dessin;}
}
