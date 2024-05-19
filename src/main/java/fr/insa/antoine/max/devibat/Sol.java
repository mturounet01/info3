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
public class Sol implements Serializable{
    private RevetSol Revetement;
    private double Surface;
    private Piece piece;
    
    private double prix;
    
    //Constructeurs
    public Sol (Piece piece){
        this.piece = piece;
        this.Surface = this.piece.getSurface();
    }
    
    //Getters
    public RevetSol getRevetement(){return this.Revetement;}
    public double getsurface(){return this.piece.getSurface();}
    public double getPrix() {return this.prix;}
    
    //Setters
    public void setRevetement(RevetSol revetement){
        this.Revetement = revetement;
    }
    
    //Methode qui permet de calculer le devis devis du Sol
    public double Devis_Sol() {
    // Vérifier si un revêtement est défini pour le sol
    if (this.Revetement == null) {
        // Si aucun revêtement n'est défini, le prix du sol est de 0
        this.prix = 0;
    } else {
        // Calculer le prix du sol en fonction de sa surface et du prix unitaire du revêtement
        double prixSol = (this.getsurface() / 100) * this.getRevetement().getPrixunit();
        
        // Arrondir le prix du sol à deux décimales
        BigDecimal bd = BigDecimal.valueOf(prixSol).setScale(2, RoundingMode.DOWN);
        
        // Mettre à jour le prix du sol avec le montant arrondi
        this.prix = bd.doubleValue();
    }
    
    // Retourner le prix du sol
    return this.prix;
}}
