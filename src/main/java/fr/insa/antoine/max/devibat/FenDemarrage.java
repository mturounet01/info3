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
 * @author maxt
 */
public class FenDemarrage extends BorderPane {

    private CreerPiece creerPiece;
    private Principal main;
    private FenCharger_Projet FenCharger_Projet;
    private FenNouveau_Bat FenNouveau_Bat;
    private Button b_retour;
    private Label l_GrandTitre = new Label("DEVIBAT");
    private Label l_Titre = new Label("BIENVENUE");
    private Label l_Titre2 = new Label("Choisissez une action");
    private Button b_Charger = new Button("OUVRIR");
    private Button b_Nouveau = new Button("NOUVEAU");
    private VBox VB = new VBox();

    public FenDemarrage(Principal main) {
        this.main = main;
        this.creerPiece = this.main.getCreerPiece();
        this.FenCharger_Projet = new FenCharger_Projet(this.main);
        this.FenNouveau_Bat = new FenNouveau_Bat(this.main);
        this.l_GrandTitre.setFont(new Font(100));

        this.l_Titre.setFont(new Font(40));
        this.l_Titre2.setFont(new Font(20));
        this.b_Charger.setOnAction((t) -> {
            this.creerPiece.Click_b_Charger1();
        });
        this.b_Nouveau.setOnAction((t) -> {
            this.creerPiece.Click_b_Nouveau1();
        });

        this.b_retour = new Button("RETOUR");
        this.b_retour.setOnAction((t) -> {
            this.creerPiece.Click_b_retour();
        });

        HBox HB = new HBox(this.b_Charger, this.b_Nouveau);
        HB.setAlignment(Pos.CENTER);
        HB.setSpacing(20);
        this.VB.getChildren().addAll(this.l_GrandTitre,this.l_Titre, this.l_Titre2, HB);
        this.VB.setSpacing(40);
        this.VB.setAlignment(Pos.CENTER);
        this.setCenter(VB);

    }

    //Getters
    public FenCharger_Projet getFenCharger_Projet() {
        return this.FenCharger_Projet;
    }

    public FenNouveau_Bat getFenNouveau_Bat() {
        return this.FenNouveau_Bat;
    }

    public void Vu_Nouveau_Bat() {
        this.getChildren().clear();
        this.setLeft(this.b_retour);
        this.setCenter(this.FenNouveau_Bat);
    }

    public void Vu_Charger_Projet() {
        this.getChildren().clear();
        this.setLeft(this.b_retour);
        this.setCenter(this.FenCharger_Projet);
    }

    void retour() {
        this.getChildren().clear();
        this.setCenter(this.VB);
    }
}
