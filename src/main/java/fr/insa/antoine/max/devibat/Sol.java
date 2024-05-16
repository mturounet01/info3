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
public class Sol implements Serializable{
    private Poursol Revetement;
    private double Surface;
    private Piece piece;
    
    private double prix;
    
    //Constructeurs
    public Sol (Piece piece){
        this.piece = piece;
        this.Surface = this.piece.getSurface();
    }
    
    //Getters
    public Poursol getRevetement(){return this.Revetement;}
    public double getsurface(){return this.piece.getSurface();}
    public double getPrix() {return this.prix;}
    
    //Setters
    public void setRevetement(Poursol revetement){
        this.Revetement = revetement;
    }
    
    //Methode devis du Sol
    public double Devis_Sol(){
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
