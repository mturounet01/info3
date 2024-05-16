/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import fr.insa.antoine.max.devibat.Batiment;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 *
 * @author maxt
 */
public class MainPane extends BorderPane{
    private Batiment Batiment;
    private Etage etage;
    private Controleur controleur;
    
    private Vu_Demarrage Vu_Demarrage;
    private Vu_Etage Vu_Etage;
    private Vu_Menu Vu_Menu;
    private Vu_Dessin_Bat Vu_Dessin_Bat;

    public MainPane (){
        this.controleur = new Controleur(this);
        
        this.Vu_Demarrage = new Vu_Demarrage(this);
        
        this.controleur.changeEtat(0);
    }
    
    public void Demarrage(){
       this.Vu_Demarrage = new Vu_Demarrage(this);
       this.setCenter(this.Vu_Demarrage);
    }
    
    public void Nouveau_Batiement(){
        //this.setCenter();
    }
    
    public void Creation_Bat(){
        this.Batiment = new Batiment();
    }
    public void setBatiment(Batiment bat){
        this.Batiment = bat;
    }
    public void Construction(){
        for (int i = 0; i<this.Batiment.getNiveau(); i++){
            this.Batiment.getListe_Etage().add(new Etage());
        }
        this.etage = this.Batiment.getListe_Etage().get(0);
        this.Vu_Dessin_Bat = new Vu_Dessin_Bat(this);
        this.Vu_Etage = new Vu_Etage(this);
        this.Vu_Menu = new Vu_Menu(this);

        this.getVu_Etage().getcZoneDessin().getCanvas().setHeight(this.Batiment.getLongueur());
        this.getVu_Etage().getcZoneDessin().getCanvas().setWidth(this.Batiment.getLargeur());
        
        this.setLeft(this.Vu_Menu);
        GridPane GP = new GridPane();
        GP.add(this.Vu_Dessin_Bat, 0, 0);
        GP.setAlignment(Pos.CENTER);
        this.setCenter(GP);
        this.setRight(this.Vu_Etage);
        
        this.Vu_Etage.getcZoneDessin().redrawAll();
    }
    public void ReConstruction(){
        this.etage = this.Batiment.getListe_Etage().get(0);
        this.Vu_Dessin_Bat = new Vu_Dessin_Bat(this);
        this.Vu_Etage = new Vu_Etage(this);
        this.Vu_Menu = new Vu_Menu(this);

        this.getVu_Etage().getcZoneDessin().getCanvas().setHeight(this.Batiment.getLongueur());
        this.getVu_Etage().getcZoneDessin().getCanvas().setWidth(this.Batiment.getLargeur());
        
        this.setLeft(this.Vu_Menu);
        GridPane GP = new GridPane();
        GP.add(this.Vu_Dessin_Bat, 0, 0);
        GP.setAlignment(Pos.CENTER);
        this.setCenter(GP);
        this.setRight(this.Vu_Etage);
        
        this.Vu_Etage.getcZoneDessin().redrawAll();
    }
    public void redrawAll(){
        this.Vu_Etage.getcZoneDessin().redrawAll();
    }
    
    //GETTERS
    public Vu_Etage getVu_Etage() {return Vu_Etage;}
    public Vu_Menu getVu_Menu() {return Vu_Menu;}
    public Vu_Dessin_Bat getVu_Dessin_Bat(){return Vu_Dessin_Bat;}
    public Vu_Demarrage getVu_Demarrage() {return Vu_Demarrage;}
    public Etage getEtage() {return etage;}
    public Controleur getControleur() {return controleur;}
    public Batiment getBatiment() {return Batiment;}
    
    public void setEtage (Etage nv_etage){
        this.etage = nv_etage;
    }
    
    public void ResetCouleur(){
        for (Piece p : this.getEtage().getListe_Piece()){
            for (Mur m : p.getListe_Mur()){
                if (m.getRevetement()!=null){
                    m.setColor(Color.GREEN);
                }else{
                    m.setColor(Color.BLACK);
                }
            }
                    if (p.getSol().getRevetement() != null && p.getPlafond().getRevetement() != null){
                        p.setCouleurFond(Color.AZURE);
                    }else{
                        p.setCouleurFond(Color.TRANSPARENT);
                    }
                }
        
    }

 }
