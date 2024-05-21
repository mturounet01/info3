package fr.insa.antoine.max.devibat;

import fr.insa.antoine.max.devibat.Batiment;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; 

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
/**
 *
 * @author maxt
 */

public class Devis {
    
    private static final String DEVIS_EXTENSION = ".txt";
    private static final String NO_REVETEMENT = "Aucun";
    private static final String EURO_SYMBOL = "€";
    //méthode qui écris le devis dans un fichier texte
    public static void ecrireDevis(Batiment batiment) {
        String fileName = batiment.getNom_Batiment() + " Devis" + DEVIS_EXTENSION;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("DEVIBAT"+"\n\n");
            writer.write("Devis Batiment : " + batiment.getNom_Batiment() + "\n\n");
            writer.write("Longueur : " + batiment.getLongueur() + " m\n");
            writer.write("Largeur : " + batiment.getLargeur() + " m\n\n");
            writer.write("Recapitulatif du devis : "+"\n\n");
            
            int etageID = 0;
            for (Etage etage : batiment.getListe_Etage()) {
                writer.write("Etage " + etageID++ + " (HSP : " + etage.getHauteur_sous_plafond() + " m) :\n");
                ecrireEtage(writer, etage);
                writer.write("\n");
            }
            
            writer.write("\nPrix total du bâtiment : " + batiment.getPrix() + EURO_SYMBOL + "\n");
            System.out.println("Le devis a été écrit dans le fichier : " + fileName);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
    
    private static double calculerHauteur(ArrayList<Etage> etages) {
        double hauteur = 0;
        for (Etage etage : etages) {
            hauteur += etage.getHauteur_sous_plafond();
        }
        return hauteur;
    }
    
    private static void ecrireEtage(BufferedWriter writer, Etage etage) throws IOException {
        int pieceID = 0;
        for (Piece piece : etage.getListe_Piece()) {
            writer.write("  Piece " + pieceID++ + ":\n");
            ecrirePiece(writer, piece);
            writer.write("\n");
        }
    }
    
    private static void ecrirePiece(BufferedWriter writer, Piece piece) throws IOException {
        int murID = 0;
        for (Mur mur : piece.getListe_Mur()) {
            String revetement = (mur.getRevetement() == null) ? NO_REVETEMENT : mur.getRevetement().getDesignation();
            writer.write("    Mur " + murID++ + " (" + revetement + ") : " + mur.getPrix() + EURO_SYMBOL + "\n");
            if (mur.getNb_Porte() != 0) {
                writer.write("        Nombre de porte : " + mur.getNb_Porte() + "\n");
            }
            if (mur.getNb_Fenetre() != 0) {
                writer.write("        Nombre de fenetre : " + mur.getNb_Fenetre() + "\n");
            }
        }
        
        String solRevetement = (piece.getSol().getRevetement() == null) ? NO_REVETEMENT : piece.getSol().getRevetement().getDesignation();
        String plafondRevetement = (piece.getPlafond().getRevetement() == null) ? NO_REVETEMENT : piece.getPlafond().getRevetement().getDesignation();
        
        writer.write("    Sol (" + solRevetement + ") : " + piece.getSol().getPrix() + EURO_SYMBOL + "\n");
        writer.write("    Plafond (" + plafondRevetement + ") : " + piece.getPlafond().getPrix() + EURO_SYMBOL + "\n");
    }
}
