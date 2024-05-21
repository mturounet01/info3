/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 *
 * @author maxt
 */
public class FenNouveau_Bat extends VBox {
    private GridPane gp_Acceuil;
    private Label l_Titre;
    private Label l_GrandTitre ;
    private Label l_nom_bat;
    private Label l_Longueur;
    private Label l_Largeur;
    private Label l_nb_etage;
    private TextField tf_Longueur;
    private TextField tf_Largeur;
    private TextField tf_nb_etage;
    private TextField tf_nom_bat;
    private Button b_Valider_Bat;

    private CreerPiece controleur;
    private Principal main;

    public FenNouveau_Bat(Principal main) {
        this.main = main;
        this.controleur = this.main.getCreerPiece();
        
        // Initialisation des composants
        initializeComponents();
        
        // Configuration de la mise en page
        configureLayout();

        // Ajout des composants à la VBox principale
        this.getChildren().addAll(l_GrandTitre,l_Titre, gp_Acceuil, b_Valider_Bat);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);  // Ajout d'un espacement entre les éléments
        this.setPadding(new Insets(20));  // Ajout de marges autour de la VBox
    }

    private void initializeComponents() {
        l_GrandTitre = new Label ("DEVIBAT");
        l_GrandTitre.setFont(new Font("Arial", 50));
        l_Titre = new Label("NOUVEAU BATIMENT");
        l_Titre.setFont(new Font(25));

        l_Longueur = new Label("Longueur (m): ");
        l_Largeur = new Label("Largeur (m) : ");
        l_nb_etage = new Label("Nombre Etage : ");
        l_nom_bat = new Label("Nom du Batiment : ");

        // Définir la taille minimale des labels
        setLabelMinSize(l_Longueur, l_Largeur, l_nb_etage, l_nom_bat);

        tf_Longueur = new TextField();
        tf_Largeur = new TextField();
        tf_nb_etage = new TextField();
        tf_nom_bat = new TextField();

        b_Valider_Bat = new Button("VALIDER");
        b_Valider_Bat.setOnAction(event -> this.controleur.Click_b_Valider_Bat());
    }

    private void setLabelMinSize(Label... labels) {
        for (Label label : labels) {
            label.setMinSize(75, 50);
        }
    }

    private void configureLayout() {
        gp_Acceuil = new GridPane();
        gp_Acceuil.addRow(0, l_nom_bat, tf_nom_bat);
        gp_Acceuil.addRow(1, l_Longueur, tf_Longueur);
        gp_Acceuil.addRow(2, l_Largeur, tf_Largeur);
        gp_Acceuil.addRow(3, l_nb_etage, tf_nb_etage);
        gp_Acceuil.setAlignment(Pos.CENTER);
        gp_Acceuil.setHgap(10);  // Ajout d'un espacement horizontal entre les colonnes
        gp_Acceuil.setVgap(10);  // Ajout d'un espacement vertical entre les lignes
        gp_Acceuil.setPadding(new Insets(20));  // Ajout de marges autour du GridPane
    }

    // Méthodes d'accès pour les champs de texte et le bouton
    public TextField getTf_Longueur() {
        return tf_Longueur;
    }

    public TextField getTf_Largeur() {
        return tf_Largeur;
    }

    public TextField getTf_nb_etage() {
        return tf_nb_etage;
    }

    public TextField getTf_nom_bat() {
        return tf_nom_bat;
    }

    public Button getB_Valider_Bat() {
        return b_Valider_Bat;
    }
}
