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

/**
 *
 * @author laelt
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
    public void setHauteur_sous_plafond(double hauteur){this.hauteur_sous_plafond = hauteur;}
    
    //AUTRES METHODES
    
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
            for (Mur m : p.getListe_Mur()){
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
    
    public double Devis_Etage(){ 
        double prixPieces = 0;
        for (Piece p : Liste_Piece){
            prixPieces = prixPieces + p.Devis_Piece();
        }
        this.prix = prixPieces;
        BigDecimal bd = new BigDecimal(this.prix);
        bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
        this.prix = bd.doubleValue();
        return this.getPrix();
    }
}
