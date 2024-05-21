/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class CreerPiece {
    
    private Principal vue;
    private int etat;
    private double[] pos = new double[2];
    private ArrayList<Mur> Liste_Selection_Mur;
    private ArrayList<Piece> Liste_Selection_Piece;
    private static final String MESSAGE_ERREUR_ACTION_NON_VALIDE = "Validez votre action";
private static final String MESSAGE_DEVIS_CREE = "Le Devis a été créé";

    
    public CreerPiece(Principal vue){
        this.vue = vue;
        this.Liste_Selection_Mur = new ArrayList<Mur>();
        this.Liste_Selection_Piece = new ArrayList<Piece>();
    }
    
    public void changeEtat(int nouvelEtat){// modifie la couleur de la pièce si il y a un revetement
        this.etat = nouvelEtat;
        
        if (this.etat == 0){
            this.vue.Demarrage();
            
        }else if (this.etat == 10){
            for (Piece p : this.vue.getEtage().getListe_Piece()){
                if (p.getSol().getRevetement() != null && p.getPlafond().getRevetement() != null){
                    p.setCouleurFond(Color.AZURE);
                }else{
                    p.setCouleurFond(Color.TRANSPARENT);
                }
            }
            this.vue.redrawAll();
            this.vue.getFenEtage().getVb_Haut().setDisable(false);
            this.vue.getFenEtage().getVb_Bas().setDisable(true);
        }else if (this.etat == 11){
            this.vue.getFenEtage().getVb_Haut().setDisable(true);
        }else if (this.etat == 20){
            this.vue.getFenEtage().getVb_Haut().setDisable(true);
            this.vue.getFenEtage().getVb_Bas().setDisable(false);
            this.vue.getFenEtage().getGp_Piece().setDisable(true);
            this.vue.getFenEtage().getHb_Mur().setDisable(true);
            this.vue.getFenEtage().getHb_Action().setDisable(true);
            this.vue.getFenEtage().getHb_Porte().setDisable(true);
            this.vue.getFenEtage().getHb_Fenetre().setDisable(true);
            for (Piece p : this.vue.getEtage().getListe_Piece()){
                if (p.getSol().getRevetement() != null && p.getPlafond().getRevetement() != null){
                    p.setCouleurFond(Color.AZURE);
                }
            }
            this.vue.redrawAll();
        }
    }

        void clickZoneDessin(MouseEvent t) {// un évenement de click se produit sur le dessin
    if (this.etat == 10) {
        handleInitialState(t);
    } else if (this.etat == 20) {
        handleSelectionState(t);
    }
}

private void handleInitialState(MouseEvent t) {//enregistre les coordonnées du premier click
    this.pos[0] = t.getX();
    this.pos[1] = t.getY();
    this.changeEtat(11);
}

private void handleSelectionState(MouseEvent t) {
    Coin click = new Coin(t.getX(), t.getY());
    Mur murProche = this.vue.getEtage().MurPlusProche(click);
    Piece pieceClick = this.vue.getEtage().PieceClick(click);

    if (murProche != null) {
        updateSelection(murProche, t.isShiftDown(), t.isControlDown(), this.Liste_Selection_Mur);
        this.Liste_Selection_Piece.clear();
    } else if (pieceClick != null) {// dans le cas d'une pièce 
        updateSelection(pieceClick, t.isShiftDown(), t.isControlDown(), this.Liste_Selection_Piece);
        this.Liste_Selection_Mur.clear();
    } else {
        clearSelections();
        this.vue.ActuCouleur();
    }

    updateUI();
    updateColors();
    this.vue.redrawAll();
}

private <T> void updateSelection(T item, boolean isShiftDown, boolean isControlDown, List<T> selectionList) {
    if (isShiftDown) {
        selectionList.add(item);
    } else if (isControlDown) {
        if (selectionList.contains(item)) {
            selectionList.remove(item);
        } else {
            selectionList.add(item);
        }
    } else {
        selectionList.clear();
        selectionList.add(item);
    }
}

private void clearSelections() {
    this.Liste_Selection_Mur.clear();
    this.Liste_Selection_Piece.clear();
}

private void updateUI() {
    boolean noSelection = this.Liste_Selection_Mur.isEmpty() && this.Liste_Selection_Piece.isEmpty();
    boolean murSelected = !this.Liste_Selection_Mur.isEmpty();
    boolean pieceSelected = !this.Liste_Selection_Piece.isEmpty();

    if (noSelection) {
        disableAllControls();
    } else if (murSelected) {
        enableMurControls();
        disablePieceControls();
        updateMurDetails(this.Liste_Selection_Mur.get(0));
    } else if (pieceSelected) {
        enablePieceControls();
        disableMurControls();
        updatePieceDetails(this.Liste_Selection_Piece.get(0));
    }
}

private void disableAllControls() {
    this.vue.getFenEtage().getGp_Piece().setDisable(true);
    this.vue.getFenEtage().getHb_Mur().setDisable(true);
    this.vue.getFenEtage().getHb_Action().setDisable(true);
    this.vue.getFenEtage().getTf_Largeur().setText(" ");
    this.vue.getFenEtage().getTf_Longueur().setText(" ");
    this.vue.getFenEtage().getHb_Porte().setDisable(true);
    this.vue.getFenEtage().getHb_Fenetre().setDisable(true);
}

private void enableMurControls() {
    this.vue.getFenEtage().getHb_Mur().setDisable(false);
    this.vue.getFenEtage().getHb_Action().setDisable(false);
    this.vue.getFenEtage().getHb_Porte().setDisable(false);
    this.vue.getFenEtage().getHb_Fenetre().setDisable(false);
}

private void disablePieceControls() {
    this.vue.getFenEtage().getGp_Piece().setDisable(true);
}

private void enablePieceControls() {
    this.vue.getFenEtage().getGp_Piece().setDisable(false);
    this.vue.getFenEtage().getHb_Action().setDisable(false);
}

private void disableMurControls() {
    this.vue.getFenEtage().getHb_Mur().setDisable(true);
}

private void updateMurDetails(Mur mur) {
    this.vue.getFenEtage().getTf_Largeur().setText(" ");
    this.vue.getFenEtage().getTf_Longueur().setText(" ");
    this.vue.getFenEtage().getTf_Porte().setText(Integer.toString(mur.getNb_Porte()));
    this.vue.getFenEtage().getTf_Fenetre().setText(Integer.toString(mur.getNb_Fenetre()));

    String revetement = (mur.getRevetement() == null) ? " " : mur.getRevetement().getDesignation();
    this.vue.getFenEtage().getCb_Rev_Mur().setValue(revetement);
}

private void updatePieceDetails(Piece piece) {
    this.vue.getFenEtage().getTf_Largeur().setText(Double.toString(piece.getLargeur() / 10));
    this.vue.getFenEtage().getTf_Longueur().setText(Double.toString(piece.getLongueur() / 10));

    String solRevetement = (piece.getSol().getRevetement() == null) ? " " : piece.getSol().getRevetement().getDesignation();
    this.vue.getFenEtage().getCb_Rev_Sol().setValue(solRevetement);

    String plafondRevetement = (piece.getPlafond().getRevetement() == null) ? " " : piece.getPlafond().getRevetement().getDesignation();
    this.vue.getFenEtage().getCb_Rev_Plafond().setValue(plafondRevetement);

    this.vue.getFenEtage().getHb_Porte().setDisable(true);
    this.vue.getFenEtage().getHb_Fenetre().setDisable(true);
}

private void updateColors() {
    for (Mur mur : this.Liste_Selection_Mur) {
        mur.setColor(Color.ORANGE);
    }
    for (Piece piece : this.Liste_Selection_Piece) {
        piece.setCouleurFond(Color.PINK);
    }
}

    void traceRectangleConstruction(MouseEvent t) {
        if (this.etat == 11){
            this.vue.getEtage().add(new Piece(creationMurs(pos, t)));
            this.vue.redrawAll();
            this.vue.getEtage().remove(this.vue.getEtage().getListe_Piece().get(this.vue.getEtage().getListe_Piece().size()-1));
        }
    }
   
    void relacheZoneDessin(MouseEvent t) {
        if (this.etat == 11){
            if ((abs(pos[0]-t.getX())>15)&&(abs(pos[1]-t.getY())>15)){
                this.vue.getEtage().add(new Piece(creationMurs(pos, t)));
            }
            this.vue.redrawAll();
            this.changeEtat(10);
        }
    }

    void Click_b_Valider() {
        this.vue.getFenEtage().getRb_Dessiner().setSelected(true);
        this.changeEtat(10);
    }

    void Click_b_Supprimer() {
        if (this.etat == 20 && !this.Liste_Selection_Piece.isEmpty()){
            for (Piece p : this.Liste_Selection_Piece){
                this.vue.getEtage().getListe_Piece().remove(p);
            }
            this.vue.getFenEtage().getL_Message_Erreur().setText(" ");
            this.vue.redrawAll();
        }else{
            this.vue.getFenEtage().getL_Message_Erreur().setText("Veuilliez selectionner une piece");
        }
    }

    void Click_b_Appliquer() {
    if (!this.Liste_Selection_Piece.isEmpty()) {
        applyChangesToSelectedPieces();
    }
    if (!this.Liste_Selection_Mur.isEmpty()) {
        applyChangesToSelectedWalls();
    }
    this.vue.ActuCouleur();
    this.vue.redrawAll();
}

private void applyChangesToSelectedPieces() {
    double nouvelleLargeur = parseTextFieldToDouble(this.vue.getFenEtage().getTf_Largeur()) * 10;
    double nouvelleLongueur = parseTextFieldToDouble(this.vue.getFenEtage().getTf_Longueur()) * 10;
    String nvRevSol = this.vue.getFenEtage().getCb_Rev_Sol().getValue();
    String nvRevPlafond = this.vue.getFenEtage().getCb_Rev_Plafond().getValue();

    for (Piece p : this.Liste_Selection_Piece) {
        if (nouvelleLargeur != 0 && nouvelleLargeur != p.getLargeur()) {
            p.setLargeur(nouvelleLargeur);
        }
        if (nouvelleLongueur != 0 && nouvelleLongueur != p.getLongueur()) {
            p.setLongueur(nouvelleLongueur);
        }
        if (nvRevSol != null) {
            p.getSol().setRevetement(this.vue.getBatiment().getStringToPoursol().get(nvRevSol));
        }
        if (nvRevPlafond != null) {
            p.getPlafond().setRevetement(this.vue.getBatiment().getStringToPourplafond().get(nvRevPlafond));
        }   }
}

private void applyChangesToSelectedWalls() {
    String nouveauRevetement = this.vue.getFenEtage().getCb_Rev_Mur().getValue();
    int nbPorte = parseTextFieldToInt(this.vue.getFenEtage().getTf_Porte());
    int nbFenetre = parseTextFieldToInt(this.vue.getFenEtage().getTf_Fenetre());

    for (Mur m : this.Liste_Selection_Mur) {
        if (nouveauRevetement != null) {
            m.setRevetement(this.vue.getBatiment().getStringToPourmur().get(nouveauRevetement));
            m.setColor(Color.GREEN);
        }
        m.setNb_Porte(nbPorte);
        m.setNb_Fenetre(nbFenetre);
    }
}

private double parseTextFieldToDouble(TextField textField) {
    try {
        return Double.parseDouble(textField.getText());
    } catch (NumberFormatException e) {
        return 0;
    }
}

private int parseTextFieldToInt(TextField textField) {
    try {
        return Integer.parseInt(textField.getText());
    } catch (NumberFormatException e) {
        return 0;
    }
}


    void rb_Dessiner() {
        this.changeEtat(10);
    }

    void rb_Selectionner() {
        this.changeEtat(20);
    }

    void spinner_change(int num_etage) {
        this.vue.getEtage().setHauteur_sous_plafond(Double.parseDouble(this.vue.getFenMenu().getTf_HSP().getText()));
        this.vue.setEtage(this.vue.getBatiment().getListe_Etage().get(num_etage));
        this.vue.getFenMenu().getTf_HSP().setText(Double.toString(this.vue.getEtage().getHauteur_sous_plafond()));
        this.vue.redrawAll();
    }
    
    void Click_b_Valider_Bat() {
        this.vue.Creation_Bat();
        double largeur = 10*Double.parseDouble(this.vue.getFenDemarrage().getFenNouveau_Bat().getTf_Largeur().getText());
        double longueur = 10*Double.parseDouble(this.vue.getFenDemarrage().getFenNouveau_Bat().getTf_Longueur().getText());
        int nb_etage = Integer.parseInt(this.vue.getFenDemarrage().getFenNouveau_Bat().getTf_nb_etage().getText());
        String nom_bat = this.vue.getFenDemarrage().getFenNouveau_Bat().getTf_nom_bat().getText();
        this.vue.getBatiment().set_Largeur(largeur);
        this.vue.getBatiment().set_Longueur(longueur);
        this.vue.getBatiment().set_nb_niveau(nb_etage);
        this.vue.getBatiment().set_Nom_Batiment(nom_bat);
        this.vue.getChildren().clear();
        this.vue.Construction();
        this.changeEtat(10);
        this.vue.getFenMenu().setTailleSpinner(this.vue.getBatiment().getTailleListeEtage()-1);
    }
    
     public void Click_b_Devis() {
    // Vérifie si l'action doit être validée avant de générer un devis
    if (this.etat != 10) {
        // Affiche un message d'erreur dans l'interface utilisateur
        afficherMessageErreur(MESSAGE_ERREUR_ACTION_NON_VALIDE, Color.RED);
    } else {
        // Génère le devis pour le bâtiment
        this.vue.getBatiment().Devis_Batiment();
        // Écrit le devis dans un fichier
        Devis.ecrireDevis(this.vue.getBatiment());
        // Affiche un message de confirmation dans l'interface utilisateur
        afficherMessageErreur(MESSAGE_DEVIS_CREE, Color.GREEN);

        // Actualise l'interface utilisateur en affichant les éléments du bâtiment
        actualiserInterfaceUtilisateur();

        // Sauvegarde le bâtiment après la génération du devis
        sauvegarderBatiment();
    }
}

// Méthode pour afficher un message d'erreur dans l'interface utilisateur
private void afficherMessageErreur(String message, Color couleur) {
    this.vue.getFenMenu().getl_Erreur().setTextFill(couleur);
    this.vue.getFenMenu().getl_Erreur().setText(message);
}

// Méthode pour actualiser l'interface utilisateur en affichant les éléments du bâtiment
private void actualiserInterfaceUtilisateur() {
    for (Etage e : this.vue.getBatiment().getListe_Etage()) {
        for (Piece p : e.getListe_Piece()) {
            p.Piece(p.getCouleurFond());
            for (Mur m : p.getListe_Mur()) {
                m.Mur(m.getCouleur());
            }
        }
    }
}

// Méthode pour sauvegarder le bâtiment après la génération du devis
private void sauvegarderBatiment() {
    Sauvegarde save = new Sauvegarde(this.vue.getBatiment().getNom_Batiment() + " Sauvegarde");
    save.sauvegarder(this.vue.getBatiment());
}

   
public ArrayList<Mur> creationMurs(double[] pos, MouseEvent t) {
    final int POS_X = 0;
    final int POS_Y = 1;

    ArrayList<Coin> Liste_Coin = new ArrayList<>();
    Liste_Coin.add(new Coin(pos[POS_X], pos[POS_Y]));
    Liste_Coin.add(new Coin(pos[POS_X], t.getY()));
    Liste_Coin.add(new Coin(t.getX(), t.getY()));
    Liste_Coin.add(new Coin(t.getX(), pos[POS_Y]));

    ArrayList<Mur> Liste_Mur = new ArrayList<>();
    Etage etage = this.vue.getEtage();

    // Création des murs à partir des coins
    for (int i = 0; i < Liste_Coin.size(); i++) {
        Coin currentCoin = Liste_Coin.get(i);
        Coin nextCoin = Liste_Coin.get((i + 1) % Liste_Coin.size()); // Le prochain coin est le coin suivant dans la liste, sauf pour le dernier coin où le prochain est le premier

        Liste_Mur.add(new Mur(etage, currentCoin, nextCoin));
    }

    return Liste_Mur;
}


    public void Click_b_Charger() {// reconstruit l'interface en fonction du projet chargé
        String Nom_fichier = this.vue.getFenDemarrage().getFenCharger_Projet().gettf_nom_projet().getText()+" Sauvegarde";
        
        Sauvegarde save = new Sauvegarde(Nom_fichier);
        this.vue.setBatiment(save.charger(Nom_fichier));
        
        ArrayList<Etage> etages = this.vue.getBatiment().getListe_Etage();
        for (Etage e : etages) {
            ArrayList<Piece> pieces = e.getListe_Piece();
            
            for (Piece p : pieces) {
                p.Piece(p.getColor());
                
                ArrayList<Mur> murs = p.getListe_Mur();

                for (Mur m : murs) {
                    m.Mur(m.getColor());
                }
            }
        }
        
        this.vue.getChildren().clear();
        this.vue.ReConstruction();
        this.changeEtat(10);
        this.vue.getFenMenu().setTailleSpinner(this.vue.getBatiment().getTailleListeEtage()-1);
    }

    void Click_b_Charger1() {
        this.vue.getFenDemarrage().Vu_Charger_Projet();
    }
    void Click_b_Nouveau1() {
        this.vue.getFenDemarrage().Vu_Nouveau_Bat();
    }
    void Click_b_retour() {
        this.vue.getFenDemarrage().retour();
    }

    void Click_b_Sauvegarde() {
        Sauvegarde save = new Sauvegarde(this.vue.getBatiment().getNom_Batiment() + " Sauvegarde");
        save.sauvegarder(this.vue.getBatiment());
        this.vue.getFenMenu().getl_Erreur().setTextFill(Color.GREEN);
        this.vue.getFenMenu().getl_Erreur().setText("Sauvegarde Réussie");
    }

    void Click_b_Retour_Menu() {
        this.vue.getChildren().clear();
        this.changeEtat(0);
    }
}
