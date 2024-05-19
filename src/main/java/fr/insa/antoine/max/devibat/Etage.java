/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author antoinez
 */
public class Etage implements Serializable{
    
    private ArrayList<Piece> Liste_Piece;
    private double hauteur_sous_plafond;
    
    private double prix;
    
    //Constructeurs
    public Etage (ArrayList<Piece> Liste_Piece){
        this.Liste_Piece = Liste_Piece;
        this.hauteur_sous_plafond = 2;
    }
    public Etage(){
        this(new ArrayList<Piece>());
    }
    
    //Getters
    public ArrayList<Piece> getListe_Piece(){return Liste_Piece;} 
    public double getPrix() {return this.prix;}
    public double getHauteur_sous_plafond() {return this.hauteur_sous_plafond;}
    
    //Setters
    public void setHauteur_sous_plafond(double hauteur){
        this.hauteur_sous_plafond = hauteur;
    }
    
    //méthode qui permettent d'ajouter ou de supprimer des pièce dnas une liste
    public void add(Piece piece){
        if (!this.Liste_Piece.contains(piece)){
            this.Liste_Piece.add(piece);
        }else{
            throw new Error("Le piece existe deja");
        }
    }
    public void remove(Piece piece){
        if (this.Liste_Piece.contains(piece)){
            this.Liste_Piece.remove(piece);
        }else{
            throw new Error("Le piece n'existe pas");
        }
    }
    public void dessiner(GraphicsContext context){
        for (Piece p : this.Liste_Piece){
            p.dessiner(context);
        }
        
    }  
    public Mur MurPlusProche (Coin click){
        Mur Mproche = this.Liste_Piece.get(0).getListe_Mur().get(0);
        double dmin = 10000;
        for (Piece p : this.Liste_Piece){
            for (Mur m : p.getListe_Mur()){//parcous les murs de chaque pièces si la distance d'un murs 
                double dtest = m.DistanceClick(click);
                if (dtest<dmin){
                    dmin = dtest;
                    Mproche = m;
                }
            }
        }
        if (dmin>5){
            return null;
        }else{
            return Mproche;
        }
    }
    public Piece PieceClick (Coin c){
        for (Piece p : Liste_Piece){
            if (p.ClickDansPiece(c)){
                return p;
            }
        }
        return null;
    }
    
   

public double Devis_Etage() {
    BigDecimal total = BigDecimal.ZERO;

    // Parcourir toutes les pièces de l'étage
    for (Piece piece : Liste_Piece) {
        // Calculer le devis de la pièce et l'ajouter au devis total
        double prixPiece = piece.Devis_Piece();
        total = total.add(BigDecimal.valueOf(prixPiece));
    }

    // Arrondir le devis total à deux décimales
    total = total.setScale(2, RoundingMode.DOWN);

    // Mettre à jour le prix total de l'étage avec le devis arrondi
    this.prix = total.doubleValue();

    // Retourner le devis total de l'étage
    return this.prix;
}}
