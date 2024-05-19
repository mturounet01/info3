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
    
    private Principal main;
    
    private Canvas canvas;
    
    
    
    public MonCanvas (Principal main){
        this.main = main;
        this.canvas = new Canvas();
        this.redrawAll();
    }
   //Getters 
    public Canvas getCanvas(){return this.canvas;}
    public Principal getMain(){return this.main;}
    public GraphicsContext getContext(){return this.canvas.getGraphicsContext2D();}
    //Setters
    public void setSize (double longueur, double largeur){
        this.canvas.setHeight(longueur);
        this.canvas.setWidth(largeur);
    }    
    public void redrawAll() {
    GraphicsContext context = this.canvas.getGraphicsContext2D(); // Obtient le contexte graphique pour dessiner
    context.setStroke(Color.BLACK); // Définit la couleur de la bordure comme noir

    // Efface tout le contenu actuel du canvas
    context.clearRect(0, 0, this.getWidth(), this.getHeight());

    // Dessine un rectangle représentant les dimensions du bâtiment
    context.strokeRect(0, 0, this.main.getBatiment().getLargeur(), this.main.getBatiment().getLongueur());

    // Obtient l'étage actuel et dessine son contenu sur le canvas
    Etage etage = this.main.getEtage();
    etage.dessiner(context);
}

}
