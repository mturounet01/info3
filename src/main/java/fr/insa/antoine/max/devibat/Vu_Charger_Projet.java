/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author laelt
 */
public class Vu_Charger_Projet extends VBox {
    private Label l_Titre = new Label("CHARGER UN PROJET");
    private Label l_Titre2 = new Label("Entrez le nom du projet à charger : ");
    private TextField tf_nom_projet = new TextField();
    private Button b_Charger;
    
    private Controleur controleur;
    private MainPane main;
    
    public Vu_Charger_Projet(MainPane main) {
        this.main = main;
        this.controleur = this.main.getControleur();  
        
        this.l_Titre.setFont(new Font(30));
        this.tf_nom_projet.setMaxSize(200, USE_PREF_SIZE);
        this.b_Charger = new Button("Charger");
        this.b_Charger.setOnAction((t)->{
            this.controleur.Click_b_Charger();
        });
        
        this.setSpacing(20);
        this.getChildren().addAll(this.l_Titre,  this.l_Titre2,this.tf_nom_projet, this.b_Charger);
        this.setAlignment(Pos.CENTER);
    }
    
    //Getters
    public TextField gettf_nom_projet(){return this.tf_nom_projet;}
}
