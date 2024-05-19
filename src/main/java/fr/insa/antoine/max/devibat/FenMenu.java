/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FenMenu extends BorderPane {
    private Principal main;
    private Batiment batiment;
    private CreerPiece creerPiece; 
    
    private Label l_titre = new Label("Etage n° :");
    private Spinner<Integer> sp_Nombre_Etage = new Spinner<>(0, 10, 0);
    private Label l_HSP = new Label("Hauteur sous plafond (m) :");
    private TextField tf_HSP = new TextField();
    private VBox vbox;
    private Label l_Erreur = new Label(" ");
    private Button b_Devis = new Button("Faire Devis");
    private Button b_Sauvegarde = new Button("Sauvegarder");
    private Button b_Retour_Menu = new Button("Menu Principal");
    private VBox vbox2;

    // Ajout du grand titre
    private Label l_GrandTitre = new Label("DeviBat");

    public FenMenu(Principal main) {
        this.main = main;
        this.creerPiece = this.main.getCreerPiece();     
        this.batiment = this.main.getBatiment();

        // Initialisation des composants
        initializeComponents();

        // Configuration de la mise en page
        configureLayout();

        // Ajout des composants au BorderPane
        VBox topContainer = new VBox(l_GrandTitre, vbox);
        topContainer.setAlignment(Pos.CENTER);
        topContainer.setSpacing(10);

        this.setTop(topContainer);
        this.setBottom(vbox2);

        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    private void initializeComponents() {
        l_GrandTitre.setFont(new Font("Arial", 40));
        l_GrandTitre.setAlignment(Pos.CENTER);

        sp_Nombre_Etage.setOnMouseClicked((t) -> {
            creerPiece.spinner_change(sp_Nombre_Etage.getValue());
        });

        tf_HSP.setText(Double.toString(main.getEtage().getHauteur_sous_plafond()));

        b_Devis.setOnAction((t) -> {
            creerPiece.Click_b_Devis();
        });

        b_Sauvegarde.setOnAction((t) -> {
            creerPiece.Click_b_Sauvegarde();
        });

        b_Retour_Menu.setOnAction((t) -> {
            creerPiece.Click_b_Retour_Menu();
        });
    }

    private void configureLayout() {
        vbox = new VBox(l_titre, sp_Nombre_Etage, l_HSP, tf_HSP);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        HBox buttonBox = new HBox(b_Devis, b_Sauvegarde);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        vbox2 = new VBox(l_Erreur, buttonBox, b_Retour_Menu);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(10);
    }

    // Getters
    public Spinner<Integer> getSp_Nombre_Etage() {
        return sp_Nombre_Etage;
    }

    public Label getl_Erreur() {
        return l_Erreur;
    }

    public TextField getTf_HSP() {
        return tf_HSP;
    }

    // Setters
    public void setTailleSpinner(int niveau) {
        sp_Nombre_Etage.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, niveau, 0));
    }
}
