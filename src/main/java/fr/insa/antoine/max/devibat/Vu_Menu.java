/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import fr.insa.antoine.max.devibat.Batiment;
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

/**
 *
 * @author laelt
 */
public class Vu_Menu extends BorderPane{
    private MainPane main;
    private Batiment Batiment;
    private Controleur controleur; 
    
    private Label l_titre = new Label("Etage n° :");
    private Spinner<Integer> sp_Nombre_Etage = new Spinner<>(0,10,0);
    private Label l_HSP = new Label("Hauteur sous plafond (m) :");
    private TextField tf_HSP = new TextField();
    private VBox VBox;
    private Label l_Erreur = new Label(" ");
    private Button b_Devis = new Button("Faire Devis");;
    private Button b_Sauvegarde = new Button("Sauvegarder");
    private Button b_Retour_Menu = new Button("Menu Principal");
    private VBox VBox2;
    
    public Vu_Menu (MainPane main){
        this.main = main;
        this.controleur = this.main.getControleur();     
        this.Batiment = this.main.getBatiment();

        this.sp_Nombre_Etage.setOnMouseClicked((t)->{
            this.controleur.spinner_change(this.sp_Nombre_Etage.getValue());
        });
        this.tf_HSP.setText(Double.toString(this.main.getEtage().getHauteur_sous_plafond()));
        this.b_Devis.setOnAction((t)->{
            this.controleur.Click_b_Devis();
        });
        this.b_Sauvegarde.setOnAction((t)->{
            this.controleur.Click_b_Sauvegarde();
        });
        
        this.b_Retour_Menu.setOnAction((t)->{
            this.controleur.Click_b_Retour_Menu();
        });
        
        this.VBox = new VBox(this.l_titre, this.sp_Nombre_Etage, this.l_HSP, this.tf_HSP);
        this.VBox2 = new VBox(this.l_Erreur,new HBox(this.b_Devis, this.b_Sauvegarde), this.b_Retour_Menu);
        this.VBox.setAlignment(Pos.CENTER);
        this.VBox2.setAlignment(Pos.CENTER);
        this.VBox2.setSpacing(10);
        
        this.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        this.setTop(this.VBox);
        this.setBottom(this.VBox2);
        
    }

    //Getters
    public Spinner getsp_Nombre_Etage(){return sp_Nombre_Etage;}
    public Label getl_Erreur(){return this.l_Erreur;}
    public TextField getTf_HSP(){return this.tf_HSP;}
    
    //Setters
    public void set_taille_spinner(int niveau){
        this.sp_Nombre_Etage.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, niveau, 0));
    }
}
