/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author laelt
 */
public class Plafond implements Serializable{
    
    private Pourplafond Revetement;
    private double Surface;
    private Piece piece;
    
    private double prix;
    
    //Constructeurs
    public Plafond (Piece piece){
        this.piece = piece;
        this.Surface = this.piece.getSurface();
    }
    
    //Getters
    public Pourplafond getRevetement(){return this.Revetement;}
    public double getsurface(){return this.piece.getSurface();}
    public double getPrix() {return this.prix;}
    
    //Setters
    public void setRevetement(Pourplafond revetement){
        this.Revetement = revetement;
    }
    
    //Methode devis du Plafond
    public double Devis_Plafond(){
        if (this.Revetement == null){
            this.prix = 0;
        }else{
            this.prix = (this.getsurface()/100) * this.getRevetement().getPrixunit();
        }
        BigDecimal bd = new BigDecimal(this.prix);
        bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
        this.prix = bd.doubleValue();
        return this.prix;
    }
}
