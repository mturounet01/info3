/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author maxt
 */
public class MonCanvas extends Pane {
    
    private MainPane main;
    
    private Canvas canvas;
    
    
    
    public MonCanvas (MainPane main){
        this.main = main;
        this.canvas = new Canvas();
        this.redrawAll();
    }
    
    public Canvas getCanvas(){return this.canvas;}
    public MainPane getMain(){return this.main;}
    public GraphicsContext getContext(){return this.canvas.getGraphicsContext2D();}
    
    public void setSize (double longueur, double largeur){
        this.canvas.setHeight(longueur);
        this.canvas.setWidth(largeur);
    }    
    public void redrawAll(){
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        context.setStroke(Color.BLACK);
        context.clearRect(0, 0, this.getWidth(), this.getHeight());
        context.strokeRect(0, 0, this.main.getBatiment().getLargeur(), this.main.getBatiment().getLongueur());
        Etage etage = this.main.getEtage();
        etage.dessiner(context);
    }
}
