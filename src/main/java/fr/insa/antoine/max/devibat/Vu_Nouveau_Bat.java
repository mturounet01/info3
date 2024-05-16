/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author laelt
 */
public class Vu_Nouveau_Bat extends VBox{
    private GridPane gp_Acceuil;
    private Label l_Titre;
    private Label l_nom_bat;
    private Label l_Longueur;
    private Label l_Largeur;
    private Label l_nb_etage;
    private TextField tf_Longueur;
    private TextField tf_Largeur;
    private TextField tf_nb_etage;
    private TextField tf_nom_bat;
    private Button b_Valider_Bat;
    
    private Controleur controleur;
    private MainPane main;
    
    public Vu_Nouveau_Bat (MainPane main) {
        this.main = main;
        this.controleur = this.main.getControleur();     
        this.gp_Acceuil = new GridPane();
        this.l_Titre = new Label("NOUVEAU BATIMENT");
        this.l_Titre.setFont(new Font(30));
        this.l_Longueur = new Label("Longueur (m): ");
        this.l_Largeur = new Label("Largeur (m) : ");
        this.l_nb_etage = new Label("Nombre Etage : ");
        this.l_nom_bat = new Label("Nom du Batiment : ");
        this.l_Longueur.setMinSize(75, 50);
        this.l_Largeur.setMinSize(75, 50);
        this.l_nb_etage.setMinSize(75, 50);
        this.l_nom_bat.setMinSize(75, 50);
        this.tf_Longueur = new TextField();
        this.tf_Largeur = new TextField();
        this.tf_nb_etage = new TextField();
        this.tf_nom_bat = new TextField();
        this.gp_Acceuil.addRow(0, this.l_nom_bat, this.tf_nom_bat);
        this.gp_Acceuil.addRow(1,this.l_Longueur,this.tf_Longueur);
        this.gp_Acceuil.addRow(2, this.l_Largeur, this.tf_Largeur); 
        this.gp_Acceuil.addRow(3, this.l_nb_etage, this.tf_nb_etage);
        this.gp_Acceuil.setAlignment(Pos.CENTER);
        this.b_Valider_Bat = new Button("VALIDER");
        this.b_Valider_Bat.setOnAction((t)->{
            this.controleur.Click_b_Valider_Bat();
        });
                
        this.getChildren().addAll(this.l_Titre, this.gp_Acceuil,this.b_Valider_Bat);
        this.setAlignment(Pos.CENTER);
    }
    
    public TextField gettf_Longueur(){return tf_Longueur;}
    public TextField gettf_Largeur(){return tf_Largeur;}
    public TextField gettf_nb_etage(){return tf_nb_etage;}
    public TextField gettf_nom_bat(){return this.tf_nom_bat;}
    public Button getb_Valider_Bat() {return b_Valider_Bat;}
    
}
