/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import static java.lang.Math.abs;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 *
 * @author antoinez
 */
public class Mur implements Serializable{

    private Coin debut, fin;
    private double longueur;
    private double surface;
    private Revetmur Revetement;
    private transient Color couleur;
    private Couleur color;
    private Etage etage;
    
    private double prix;
    private int nbporte;
    private int nbfenetre;
    double Surface_porte = 1.89;
    double Surface_fenetre = 1.44;
    
    //Constructeurs
    public Mur(Etage etage, Coin debut, Coin fin) {
        this.debut = debut;
        this.fin = fin;
        this.couleur = Color.BLACK;
        this.etage = etage;
        this.surface = (this.getLongueur()/10)*this.etage.getHauteur_sous_plafond();
        this.nbporte = 0;
        this.nbfenetre = 0;
        this.color = new Couleur(this.couleur.getRed(), this.couleur.getGreen(), this.couleur.getBlue(), this.couleur.getOpacity());
    }
    
    //GETTERS permet de recup les valeurs des attributs
    public Coin getDebut() {return this.debut;}
    public Coin getFin() {return this.fin;}
    public double getLongueur(){return Math.sqrt(Math.pow(this.fin.getX()-this.debut.getX(), 2)+Math.pow(this.fin.getY()-this.debut.getY(), 2));}
    public double getSurface(){return this.getLongueur()*this.etage.getHauteur_sous_plafond();}
    public Revetmur getRevetement(){return this.Revetement;}
    public double getPrix() {return this.prix;}
    public int getNb_Porte(){return this.nbporte;}
    public int getNb_Fenetre(){return this.nbfenetre;}
    
    public Color getCouleur() {return this.couleur;}
    public Couleur getColor() {return this.color;}
    
    //SETTERS 
    public void setDebut(Coin debut) {this.debut = debut;}
    public void setFin(Coin fin) {this.fin = fin;}
    public void setColor (Color couleur) {this.couleur = couleur;}
    public void setRevetement (Revetmur revetement){this.Revetement = revetement;}
    public void setNb_Porte(int nb){this.nbporte = nb;}
    public void setNb_Fenetre(int nb){this.nbfenetre = nb;}
    //AUTRES METHODES
    
    public void dessiner (GraphicsContext context){
        context.setStroke(this.couleur);
        context.strokeLine(this.getDebut().getX(), this.getDebut().getY(), this.getFin().getX(), this.getFin().getY());
    }
    
    public double DistanceClick (Coin c){
        double x1 = this.debut.getX();
        double y1 = this.debut.getY();
        double x2 = this.fin.getX();
        double y2 = this.fin.getY();
        double x3 = c.getX();
        double y3 = c.getY();
        double up = 10000;
        if (x1==x2){ //Si mur vertical
            if ((y1<y3 && y3<y2)||(y2<y3 && y3<y1)){
                up = abs(x1-x3);
            }
        } else if(y1==y2) { //Si mur horizontal
            if ((x1<x3 && x3<x2)||(x2<x3 && x3<x1)){
                up = abs(y1-y3);
            }
        }
        return up;
    }
    
    public double Devis_Mur() {
    // Vérifier si un revêtement est défini pour le mur
    if (this.Revetement == null) {
        // Si aucun revêtement n'est défini, le prix du mur est de 0
        this.prix = 0;
    } else {
        // Calculer la surface du mur en excluant les portes et les fenêtres
        double surfaceMur = (this.getSurface() / 10) - (Surface_porte * this.nbporte) - (Surface_fenetre * this.nbfenetre);
        
        // Calculer le prix du mur en fonction de sa surface et du prix unitaire du revêtement
        double prixMur = surfaceMur * this.Revetement.getPrixunit();
        
        // Arrondir le prix du mur à deux décimales
        BigDecimal bd = BigDecimal.valueOf(prixMur).setScale(2, RoundingMode.DOWN);
        
        // Mettre à jour le prix du mur avec le montant arrondi
        this.prix = bd.doubleValue();
    }
    
    // Retourner le prix du mur
    return this.prix;
}

    
    public void Mur(Color couleur) {
        this.color = new Couleur(this.couleur.getRed(), this.couleur.getGreen(), this.couleur.getBlue(), this.couleur.getOpacity());
    }
    
    public void Mur(Couleur color) {
        this.couleur = this.color.toColor();
    }
}
