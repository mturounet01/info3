/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.scene.canvas.Canvas;

/**
 *
 * @author maxt
 */
public class FenDessin extends MonCanvas {
    
    private Canvas c_Zone_Dessin;
    private CreerPiece creerPiece;
    
    public FenDessin(Principal main) {
        super(main);
        this.c_Zone_Dessin = this.getCanvas();
        this.getChildren().add(c_Zone_Dessin);
        this.creerPiece = this.getMain().getCreerPiece();
        
        this.c_Zone_Dessin.setOnMousePressed((t)->{
            this.creerPiece.clickZoneDessin(t);
        });
        this.c_Zone_Dessin.setOnMouseDragged((t)->{
            this.creerPiece.traceRectangleConstruction(t);
        });
        this.c_Zone_Dessin.setOnMouseReleased((t)->{
            this.creerPiece.relacheZoneDessin(t);
        });
    }
    
    public Canvas getc_Zone_Dessin(){return c_Zone_Dessin;}
}
