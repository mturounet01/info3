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
public class Principal extends BorderPane{
    private Batiment Batiment;
    private Etage etage;
    private CreerPiece controleur;
    
    private FenDemarrage Start;
    private FenEtage fenetage;
    private FenMenu fenmenu;
    private FenDessin fendessin;

    public Principal (){
        this.controleur = new CreerPiece(this);
        
        this.Start = new FenDemarrage(this);
        
        this.controleur.changeEtat(0);
    }
    
    public void Demarrage(){
       this.Start = new FenDemarrage(this);
       this.setCenter(this.Start);
    }
    
    public void Nouveau_Batiment(){
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
        this.fendessin = new FenDessin(this);
        this.fenetage = new FenEtage(this);
        this.fenmenu = new FenMenu(this);
//ajuste la taille du batiment selon les valeur entrées précedemment
        this.getFenEtage().getcZoneDessin().getCanvas().setHeight(this.Batiment.getLongueur());
        this.getFenEtage().getcZoneDessin().getCanvas().setWidth(this.Batiment.getLargeur());
        
       this.setLeft(this.fendessin);
       //organisation des pièces

BorderPane rightPane = new BorderPane();
rightPane.setTop(this.fenmenu);
rightPane.setCenter(this.fenetage);
this.setRight(rightPane);

        
        this.fenetage.getcZoneDessin().redrawAll();
    }
   // lorsqu'on change de niveau on actualise les champs 
    public void ReConstruction(){
        this.etage = this.Batiment.getListe_Etage().get(0);
        this.fendessin = new FenDessin(this);
        this.fenetage = new FenEtage(this);
        this.fenmenu = new FenMenu(this);
//ajuste la taille du batiment selon les valeur entrées précedemment
        this.getFenEtage().getcZoneDessin().getCanvas().setHeight(this.Batiment.getLongueur());
        this.getFenEtage().getcZoneDessin().getCanvas().setWidth(this.Batiment.getLargeur());
        
       
        GridPane GP = new GridPane();
        GP.add(this.fendessin, 0, 0);
        GP.setAlignment(Pos.CENTER);
        this.setCenter(GP);
        BorderPane rightPane = new BorderPane();
rightPane.setTop(this.fenmenu);
rightPane.setCenter(this.fenetage);
this.setRight(rightPane);
        
        
        this.fenetage.getcZoneDessin().redrawAll();
    }
    public void redrawAll(){
        this.fenetage.getcZoneDessin().redrawAll();
    }
    
    //GETTERS
    public FenEtage getFenEtage() {return fenetage;}
    public FenMenu getFenMenu() {return fenmenu;}
    public FenDessin getFenDessin_Bat(){return fendessin;}
    public FenDemarrage getFenDemarrage() {return Start;}
    public Etage getEtage() {return etage;}
    public CreerPiece getCreerPiece() {return controleur;}
    public Batiment getBatiment() {return Batiment;}
    
    public void setEtage (Etage nv_etage){
        this.etage = nv_etage;
    }
    //met à jour la couleur des pieces lorsqu'il y a un revetement
    public void ActuCouleur(){
        for (Piece p : this.getEtage().getListe_Piece()){//parcours les pièces de cet étage
            for (Mur m : p.getListe_Mur()){//parcours les murs de cette pièce
                if (m.getRevetement()!=null){
                    m.setColor(Color.ORANGE);
                }else{
                    m.setColor(Color.BLACK);
                }
            }
                    if (p.getSol().getRevetement() != null && p.getPlafond().getRevetement() != null){
                        p.setCouleurFond(Color.MAGENTA);
                    }else{
                        p.setCouleurFond(Color.TRANSPARENT);
                    }
                }
        
    }

 }
