/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author laelt
 */
public class Vu_Demarrage extends BorderPane {
       
    private Controleur controleur;
    private MainPane main;
    private Vu_Charger_Projet Vu_Charger_Projet;
    private Vu_Nouveau_Bat Vu_Nouveau_Bat;
    private Button b_retour;
    
    private Label l_Titre = new Label("BIENVENUE");
    private Label l_Titre2 = new Label("Choisissez une action");
    private Button b_Charger = new Button("OUVRIR");
    private Button b_Nouveau = new Button("NOUVEAU");
    private VBox VB = new VBox();
    
    public Vu_Demarrage (MainPane main) {
        this.main = main;
        this.controleur = this.main.getControleur();
        this.Vu_Charger_Projet = new Vu_Charger_Projet(this.main);
        this.Vu_Nouveau_Bat = new Vu_Nouveau_Bat(this.main);
        
        this.l_Titre.setFont(new Font(50));
        this.l_Titre2.setFont(new Font(20));
        this.b_Charger.setOnAction((t)->{
            this.controleur.Click_b_Charger1();
        });
        this.b_Nouveau.setOnAction((t)->{
            this.controleur.Click_b_Nouveau1();
        });
        
        this.b_retour = new Button("RETOUR");
        this.b_retour.setOnAction((t)->{
            this.controleur.Click_b_retour();
        });
        
        HBox HB = new HBox(this.b_Charger, this.b_Nouveau);
        HB.setAlignment(Pos.CENTER);
        HB.setSpacing(10);
        this.VB.getChildren().addAll(this.l_Titre, this.l_Titre2, HB);
        this.VB.setSpacing(20);
        this.VB.setAlignment(Pos.CENTER);
        this.setCenter(VB);
        
    }
    
    //Getters
    public Vu_Charger_Projet getVu_Charger_Projet(){return this.Vu_Charger_Projet;}
    public Vu_Nouveau_Bat getVu_Nouveau_Bat(){return this.Vu_Nouveau_Bat;}
    
    public void Vu_Nouveau_Bat(){
        this.getChildren().clear();
        this.setLeft(this.b_retour);
        this.setCenter(this.Vu_Nouveau_Bat);
    }
    public void Vu_Charger_Projet(){
        this.getChildren().clear();
        this.setLeft(this.b_retour);
        this.setCenter(this.Vu_Charger_Projet);
    }

    void retour() {
        this.getChildren().clear();
        this.setCenter(this.VB);
    }
}
