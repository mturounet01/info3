/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author laelt
 */
public class FenEtage extends Pane {
    private CreerPiece creerPiece; 
    private Principal principal;
    private Etage etage_a_dessiner;
    
    private RadioButton rb_Dessiner = new RadioButton();
    private RadioButton rb_Selectionner = new RadioButton();
    
    private Label l_MessageErreur = new Label();
    private Label l_longueur = new Label("Longueur (m):");
    private Label l_largeur  = new Label("Largeur (m):");
    private Label l_Revetement_Mur = new Label("Revetement Mur :");
    private Label l_Revetement_Sol = new Label("Revetement Sol :");
    private Label l_Revetement_Plafond = new Label("Revetement Plafond :");
    private Label l_Porte = new Label("Porte : ");
    private Label l_Fenetre = new Label("Fenêtre : ");
    
    private Button b_Valider = new Button ("Valider");
    private Button b_Supprimer = new Button ("Supprimer");
    private Button b_Appliquer = new Button ("Appliquer");

    private TextField tf_Longueur = new TextField();
    private TextField tf_Largeur = new TextField();
    
    private ChoiceBox<String> cb_Rev_Mur;
    private ChoiceBox<String> cb_Rev_Sol;
    private ChoiceBox<String> cb_Rev_Plafond;
    
    private TextField tf_Porte = new TextField(" ");
    private TextField tf_Fenetre = new TextField();

    private MonCanvas cZoneDessin;
    
    private VBox VB = new VBox();
    private VBox vb_Haut = new VBox();
    private VBox vb_Bas = new VBox();
    private GridPane gp_Piece = new GridPane();
    private HBox hb_Action = new HBox();
    private HBox hb_Mur = new HBox();
    private HBox hb_Porte = new HBox();
    private HBox hb_Fenetre = new HBox();

    
    public FenEtage (Principal main){
        
        this.principal = main;
        this.creerPiece = this.principal.getCreerPiece();
        //liste des revêtements
        ObservableList<String> ListePourMur = FXCollections.observableArrayList(this.principal.getBatiment().getListe_mur());
        this.cb_Rev_Mur = new ChoiceBox<String>(ListePourMur);
        ObservableList<String> ListePourSol = FXCollections.observableArrayList(this.principal.getBatiment().getListe_sol());
        this.cb_Rev_Sol = new ChoiceBox<String>(ListePourSol);
        ObservableList<String> ListePourPlafond = FXCollections.observableArrayList(this.principal.getBatiment().getListe_plafond());
        this.cb_Rev_Plafond = new ChoiceBox<String>(ListePourPlafond);
        
        this.l_MessageErreur.setTextFill(Color.RED);
        
        this.tf_Porte.setMaxWidth(40);
        this.tf_Fenetre.setMaxWidth(40);

        this.b_Valider.setOnAction((t)->{
            this.creerPiece.Click_b_Valider();
        });
        this.b_Supprimer.setOnAction((t)->{
            this.creerPiece.Click_b_Supprimer();
        });
        this.b_Appliquer.setOnAction((t)->{
            this.creerPiece.Click_b_Appliquer();
        });
  //bouton qui permet de dessiner la pièce 
        this.rb_Dessiner.setText("Dessiner");
        this.rb_Dessiner.setOnAction((t)->{
            this.creerPiece.rb_Dessiner_chosit();
        });
        //bouton qui permet de mettre des revetements
        this.rb_Selectionner.setText("Selectionner");
        this.rb_Selectionner.setOnAction((t)->{
            this.creerPiece.rb_Selectionner_chosit();
        });
        ToggleGroup Group_rb = new ToggleGroup();
        this.rb_Dessiner.setToggleGroup(Group_rb);
        this.rb_Selectionner.setToggleGroup(Group_rb);
        this.rb_Dessiner.setSelected(true);
        
        this.cZoneDessin = this.principal.getVu_Dessin_Bat();
        
        this.vb_Bas.setAlignment(Pos.CENTER);
        
        this.gp_Piece.setAlignment(Pos.CENTER);
        this.gp_Piece.addRow(1, this.l_largeur, this.tf_Largeur);
        this.gp_Piece.addRow(2, this.l_longueur, this.tf_Longueur);
        this.gp_Piece.addRow(3, this.l_Revetement_Plafond, this.cb_Rev_Plafond);
        this.gp_Piece.addRow(4, this.l_Revetement_Sol, this.cb_Rev_Sol);
        this.hb_Action.getChildren().addAll(this.b_Appliquer,this.b_Supprimer);
        this.hb_Action.setAlignment(Pos.CENTER);
        this.hb_Mur.getChildren().addAll(this.l_Revetement_Mur,this.cb_Rev_Mur);
        this.hb_Porte.getChildren().addAll(this.l_Porte, this.tf_Porte);
        this.hb_Porte.setAlignment(Pos.CENTER);
        this.hb_Fenetre.getChildren().addAll(this.l_Fenetre, this.tf_Fenetre);
        this.hb_Fenetre.setAlignment(Pos.CENTER);
        this.vb_Bas.setAlignment(Pos.CENTER);
        this.vb_Bas.getChildren().addAll(this.l_MessageErreur,this.gp_Piece, this.hb_Mur,this.hb_Porte,this.hb_Fenetre,this.hb_Action, this.b_Valider);
        this.vb_Bas.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        this.vb_Haut.getChildren().addAll(this.getRb_Dessiner(), this.getRb_Selectionner());
        this.vb_Haut.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        this.VB.getChildren().addAll(this.vb_Haut, this.vb_Bas);
        
        this.getChildren().add(this.VB);
    }
    
    //Getters
    public Button getB_Valider() {return this.b_Valider;}
    public Button getB_Supprimer() {return this.b_Supprimer;}
    public Button getB_Appliquer() {return this.b_Appliquer;}
    public TextField getTf_Longueur() {return this.tf_Longueur;}
    public TextField getTf_Largeur() {return this.tf_Largeur;}
    public RadioButton getRb_Dessiner() {return this.rb_Dessiner;}
    public RadioButton getRb_Selectionner() {return this.rb_Selectionner;}
    public MonCanvas getcZoneDessin() {return this.cZoneDessin;}
    public GridPane getGp_Piece() {return this.gp_Piece;}
    public HBox getHb_Mur() {return this.hb_Mur;}
    public HBox getHb_Porte(){return this.hb_Porte;}
    public HBox getHb_Fenetre(){return this.hb_Fenetre;}
    public HBox getHb_Action() {return this.hb_Action;}
    public VBox getVb_Haut() {return this.vb_Haut;}
    public VBox getVb_Bas() {return this.vb_Bas;}
    public Label getL_Message_Erreur(){return this.l_MessageErreur;}
    public ChoiceBox<String> getCb_Rev_Mur(){return this.cb_Rev_Mur;}
    public ChoiceBox<String> getCb_Rev_Sol(){return this.cb_Rev_Sol;}
    public ChoiceBox<String> getCb_Rev_Plafond(){return this.cb_Rev_Plafond;}
    public TextField getTf_Porte(){return this.tf_Porte;}
    public TextField getTf_Fenetre(){return this.tf_Fenetre;}
}
