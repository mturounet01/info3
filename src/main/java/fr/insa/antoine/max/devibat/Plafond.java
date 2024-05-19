/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author antoinez
 */
public class Plafond implements Serializable{
    
    private Revetplafond Revetement;
    private double Surface;
    private Piece piece;
    
    private double prix;
    
    //Constructeurs
    public Plafond (Piece piece){
        this.piece = piece;
        this.Surface = this.piece.getSurface();
    }
    
    //Getters
    public Revetplafond getRevetement(){return this.Revetement;}
    public double getsurface(){return this.piece.getSurface();}
    public double getPrix() {return this.prix;}
    
    //Setters
    public void setRevetement(Revetplafond revetement){
        this.Revetement = revetement;
    }
    
    //Methode devis du Plafond
    public double Devis_Plafond() {
    // Vérifier si un revêtement est défini pour le plafond
    if (this.Revetement == null) {
        // Si aucun revêtement n'est défini, le prix du plafond est de 0
        this.prix = 0;
    } else {
        // Calculer le prix du plafond en fonction de sa surface et du prix unitaire du revêtement
        double prixPlafond = (this.getsurface() / 100) * this.getRevetement().getPrixunit();
        
        // Arrondir le prix du plafond à deux décimales
        BigDecimal bd = BigDecimal.valueOf(prixPlafond).setScale(2, RoundingMode.DOWN);
        
        // Mettre à jour le prix du plafond avec le montant arrondi
        this.prix = bd.doubleValue();
    }
    
    // Retourner le prix du plafond
    return this.prix;
}
}