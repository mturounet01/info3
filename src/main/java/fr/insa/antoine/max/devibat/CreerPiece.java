/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class CreerPiece {
    
    private Principal vue;
    private int etat;
    private double[] pos = new double[2];
    private ArrayList<Mur> Liste_Selection_Mur;
    private ArrayList<Piece> Liste_Selection_Piece;
    
    public CreerPiece(Principal vue){
        this.vue = vue;
        this.Liste_Selection_Mur = new ArrayList<Mur>();
        this.Liste_Selection_Piece = new ArrayList<Piece>();
    }
    
    public void changeEtat(int nouvelEtat){
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
            this.vue.getVu_Etage().getVb_Haut().setDisable(false);
            this.vue.getVu_Etage().getVb_Bas().setDisable(true);
        }else if (this.etat == 11){
            this.vue.getVu_Etage().getVb_Haut().setDisable(true);
        }else if (this.etat == 20){
            this.vue.getVu_Etage().getVb_Haut().setDisable(true);
            this.vue.getVu_Etage().getVb_Bas().setDisable(false);
            this.vue.getVu_Etage().getGp_Piece().setDisable(true);
            this.vue.getVu_Etage().getHb_Mur().setDisable(true);
            this.vue.getVu_Etage().getHb_Action().setDisable(true);
            this.vue.getVu_Etage().getHb_Porte().setDisable(true);
            this.vue.getVu_Etage().getHb_Fenetre().setDisable(true);
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
    this.vue.getVu_Etage().getGp_Piece().setDisable(true);
    this.vue.getVu_Etage().getHb_Mur().setDisable(true);
    this.vue.getVu_Etage().getHb_Action().setDisable(true);
    this.vue.getVu_Etage().getTf_Largeur().setText(" ");
    this.vue.getVu_Etage().getTf_Longueur().setText(" ");
    this.vue.getVu_Etage().getHb_Porte().setDisable(true);
    this.vue.getVu_Etage().getHb_Fenetre().setDisable(true);
}

private void enableMurControls() {
    this.vue.getVu_Etage().getHb_Mur().setDisable(false);
    this.vue.getVu_Etage().getHb_Action().setDisable(false);
    this.vue.getVu_Etage().getHb_Porte().setDisable(false);
    this.vue.getVu_Etage().getHb_Fenetre().setDisable(false);
}

private void disablePieceControls() {
    this.vue.getVu_Etage().getGp_Piece().setDisable(true);
}

private void enablePieceControls() {
    this.vue.getVu_Etage().getGp_Piece().setDisable(false);
    this.vue.getVu_Etage().getHb_Action().setDisable(false);
}

private void disableMurControls() {
    this.vue.getVu_Etage().getHb_Mur().setDisable(true);
}

private void updateMurDetails(Mur mur) {
    this.vue.getVu_Etage().getTf_Largeur().setText(" ");
    this.vue.getVu_Etage().getTf_Longueur().setText(" ");
    this.vue.getVu_Etage().getTf_Porte().setText(Integer.toString(mur.getNb_Porte()));
    this.vue.getVu_Etage().getTf_Fenetre().setText(Integer.toString(mur.getNb_Fenetre()));

    String revetement = (mur.getRevetement() == null) ? " " : mur.getRevetement().getDesignation();
    this.vue.getVu_Etage().getCb_Rev_Mur().setValue(revetement);
}

private void updatePieceDetails(Piece piece) {
    this.vue.getVu_Etage().getTf_Largeur().setText(Double.toString(piece.getLargeur() / 10));
    this.vue.getVu_Etage().getTf_Longueur().setText(Double.toString(piece.getLongueur() / 10));

    String solRevetement = (piece.getSol().getRevetement() == null) ? " " : piece.getSol().getRevetement().getDesignation();
    this.vue.getVu_Etage().getCb_Rev_Sol().setValue(solRevetement);

    String plafondRevetement = (piece.getPlafond().getRevetement() == null) ? " " : piece.getPlafond().getRevetement().getDesignation();
    this.vue.getVu_Etage().getCb_Rev_Plafond().setValue(plafondRevetement);

    this.vue.getVu_Etage().getHb_Porte().setDisable(true);
    this.vue.getVu_Etage().getHb_Fenetre().setDisable(true);
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
        this.vue.getVu_Etage().getRb_Dessiner().setSelected(true);
        this.changeEtat(10);
    }

    void Click_b_Supprimer() {
        if (this.etat == 20 && !this.Liste_Selection_Piece.isEmpty()){
            for (Piece p : this.Liste_Selection_Piece){
                this.vue.getEtage().getListe_Piece().remove(p);
            }
            this.vue.getVu_Etage().getL_Message_Erreur().setText(" ");
            this.vue.redrawAll();
        }else{
            this.vue.getVu_Etage().getL_Message_Erreur().setText("Veuilliez selectionner une piece");
        }
    }

    void Click_b_Appliquer() {
        if (!this.Liste_Selection_Piece.isEmpty()){
            double nouvelle_largeur = 10*Double.parseDouble(this.vue.getVu_Etage().getTf_Largeur().getText());
            double nouvelle_longueur = 10*Double.parseDouble(this.vue.getVu_Etage().getTf_Longueur().getText());
            String nv_rev_sol = this.vue.getVu_Etage().getCb_Rev_Sol().getValue();
            String nv_rev_plafond = this.vue.getVu_Etage().getCb_Rev_Plafond().getValue();
            if ((nouvelle_largeur != this.Liste_Selection_Piece.get(0).getLargeur())||((nouvelle_longueur != this.Liste_Selection_Piece.get(0).getLongueur()))){
                if (nouvelle_largeur !=0){
                    for (Piece p : this.Liste_Selection_Piece){
                        p.setLargeur(nouvelle_largeur);
                    }
                }
                if(nouvelle_longueur!=0){
                    for (Piece p : this.Liste_Selection_Piece){
                        p.setLongueur(nouvelle_longueur);
                    }
                }
            }
            if(nv_rev_sol != null){
                for (Piece p : this.Liste_Selection_Piece){
                    p.getSol().setRevetement(this.vue.getBatiment().getStringToPoursol().get(nv_rev_sol));
                }
            }
            if(nv_rev_plafond != null){
                for (Piece p : this.Liste_Selection_Piece){
                    p.getPlafond().setRevetement(this.vue.getBatiment().getStringToPourplafond().get(nv_rev_plafond));
                }
            }
            for (Piece p : this.Liste_Selection_Piece){
                if (p.getSol().getRevetement() != null && p.getPlafond().getRevetement() != null){
                }
            }
        }
        if (!this.Liste_Selection_Mur.isEmpty()){
            String nouveau_revetement = this.vue.getVu_Etage().getCb_Rev_Mur().getValue();
            if (nouveau_revetement != null){
                for (Mur m : this.Liste_Selection_Mur){
                    m.setRevetement(this.vue.getBatiment().getStringToPourmur().get(nouveau_revetement));
                    m.setColor(Color.GREEN);
                    m.setNb_Porte(Integer.parseInt(this.vue.getVu_Etage().getTf_Porte().getText()));
                    m.setNb_Fenetre(Integer.parseInt(this.vue.getVu_Etage().getTf_Fenetre().getText()));
                }
            }
            for (Mur m : this.Liste_Selection_Mur){
                m.setNb_Porte(Integer.parseInt(this.vue.getVu_Etage().getTf_Porte().getText()));
                m.setNb_Fenetre(Integer.parseInt(this.vue.getVu_Etage().getTf_Fenetre().getText()));
            }
        }
        this.vue.ActuCouleur();
        this.vue.redrawAll();
    }

    void rb_Dessiner_chosit() {
        this.changeEtat(10);
    }

    void rb_Selectionner_chosit() {
        this.changeEtat(20);
    }

    void spinner_change(int num_etage) {
        this.vue.getEtage().setHauteur_sous_plafond(Double.parseDouble(this.vue.getVu_Menu().getTf_HSP().getText()));
        this.vue.setEtage(this.vue.getBatiment().getListe_Etage().get(num_etage));
        this.vue.getVu_Menu().getTf_HSP().setText(Double.toString(this.vue.getEtage().getHauteur_sous_plafond()));
        this.vue.redrawAll();
    }
    
    void Click_b_Valider_Bat() {
        this.vue.Creation_Bat();
        double largeur = 10*Double.parseDouble(this.vue.getVu_Demarrage().getVu_Nouveau_Bat().getTf_Largeur().getText());
        double longueur = 10*Double.parseDouble(this.vue.getVu_Demarrage().getVu_Nouveau_Bat().getTf_Longueur().getText());
        int nb_etage = Integer.parseInt(this.vue.getVu_Demarrage().getVu_Nouveau_Bat().getTf_nb_etage().getText());
        String nom_bat = this.vue.getVu_Demarrage().getVu_Nouveau_Bat().getTf_nom_bat().getText();
        this.vue.getBatiment().set_Largeur(largeur);
        this.vue.getBatiment().set_Longueur(longueur);
        this.vue.getBatiment().set_nb_niveau(nb_etage);
        this.vue.getBatiment().set_Nom_Batiment(nom_bat);
        this.vue.getChildren().clear();
        this.vue.Construction();
        this.changeEtat(10);
        this.vue.getVu_Menu().setTailleSpinner(this.vue.getBatiment().getTailleListeEtage()-1);
    }
    
     void Click_b_Devis() {
         if (this.etat != 10){
             this.vue.getVu_Menu().getl_Erreur().setTextFill(Color.RED);
             this.vue.getVu_Menu().getl_Erreur().setText("Validez votre action");
         }else{
             this.vue.getBatiment().Devis_Batiment();
             Devis.ecrireDevis(this.vue.getBatiment());
             this.vue.getVu_Menu().getl_Erreur().setTextFill(Color.GREEN);
             this.vue.getVu_Menu().getl_Erreur().setText("Le Devis a été créé");
             
             ArrayList<Etage> etages = this.vue.getBatiment().getListe_Etage();
             for (Etage e : etages) {
             ArrayList<Piece> pieces = e.getListe_Piece();
            
                for (Piece p : pieces) {
                    p.Piece(p.getCouleurFond());
                
                    ArrayList<Mur> murs = p.getListe_Mur();

                        for (Mur m : murs) {
                            m.Mur(m.getCouleur());
                        }
                }
            }
            Sauvegarde save = new Sauvegarde(this.vue.getBatiment().getNom_Batiment() + " Sauvegarde");
            save.sauvegarder(this.vue.getBatiment());
         }
     }
   
public ArrayList<Mur> creationMurs (double[] pos, MouseEvent t){
        ArrayList<Coin> Liste_Coin = new ArrayList<Coin>();
        Liste_Coin.add(new Coin(pos[0],pos[1]));
        Liste_Coin.add(new Coin(pos[0],t.getY()));
        Liste_Coin.add(new Coin(t.getX(),t.getY()));
        Liste_Coin.add(new Coin(t.getX(),pos[1]));
        ArrayList<Mur> Liste_Mur = new ArrayList<Mur>();
        Etage etage = this.vue.getEtage();
        Liste_Mur.add(new Mur(etage,Liste_Coin.get(0), Liste_Coin.get(1)));
        Liste_Mur.add(new Mur(etage,Liste_Coin.get(1), Liste_Coin.get(2)));
        Liste_Mur.add(new Mur(etage,Liste_Coin.get(2), Liste_Coin.get(3)));
        Liste_Mur.add(new Mur(etage,Liste_Coin.get(3), Liste_Coin.get(0)));
        
        return Liste_Mur;
    }

    public void Click_b_Charger() {
        String Nom_fichier = this.vue.getVu_Demarrage().getVu_Charger_Projet().gettf_nom_projet().getText()+" Sauvegarde";
        
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
        this.vue.getVu_Menu().setTailleSpinner(this.vue.getBatiment().getTailleListeEtage()-1);
    }

    void Click_b_Charger1() {
        this.vue.getVu_Demarrage().Vu_Charger_Projet();
    }
    void Click_b_Nouveau1() {
        this.vue.getVu_Demarrage().Vu_Nouveau_Bat();
    }
    void Click_b_retour() {
        this.vue.getVu_Demarrage().retour();
    }

    void Click_b_Sauvegarde() {
        Sauvegarde save = new Sauvegarde(this.vue.getBatiment().getNom_Batiment() + " Sauvegarde");
        save.sauvegarder(this.vue.getBatiment());
        this.vue.getVu_Menu().getl_Erreur().setTextFill(Color.GREEN);
        this.vue.getVu_Menu().getl_Erreur().setText("Sauvegarde Réussie");
    }

    void Click_b_Retour_Menu() {
        this.vue.getChildren().clear();
        this.changeEtat(0);
    }
}
