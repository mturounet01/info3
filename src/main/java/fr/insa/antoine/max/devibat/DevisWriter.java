package fr.insa.antoine.max.devibat;

import fr.insa.antoine.max.devibat.Batiment;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; 

public class DevisWriter {
    public static void ecrireDevis(Batiment batiment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(batiment.getNom_Batiment()+" Devis"))) {
            ArrayList<Etage> etages = batiment.getListe_Etage();
            writer.write("Devis Batiment : "+batiment.getNom_Batiment()+"\n");
            writer.write("\n");
            writer.write("Longueur : "+batiment.getLongueur()+" m\n");
            writer.write("Largeur : "+batiment.getLargeur()+" m\n");
            double hauteur = 0;
            for (Etage e : etages) {
                hauteur = hauteur + e.getHauteur_sous_plafond();
            }
            writer.write("Hauteur : "+hauteur+" m\n");
            writer.write("\n");
            
            int eID = -1;
            
            for (Etage e : etages) {
                int pID = 0;
                eID = eID + 1;
                
                writer.write("Etage " + eID +" (HSP : "+e.getHauteur_sous_plafond()+" m) :\n");
                ArrayList<Piece> pieces = e.getListe_Piece();
                for (Piece p : pieces) {
                    int mID = 0;
                    pID = pID + 1;
                    
                    writer.write("  Piece " + pID + ":\n");
                    ArrayList<Mur> murs = p.getListe_Mur();

                    for (Mur m : murs) {
                        mID = mID + 1;
                        if (m.getRevetement()==null){
                            writer.write("    Mur " + mID +" (Aucun) : " + m.getPrix() + "€\n");
                        }else{
                            writer.write("    Mur " + mID +" ("+ m.getRevetement().getDesignation()+ ") : " + m.getPrix() + "€\n");
                        }
                        if (m.getNb_Porte()!=0){
                            writer.write("        Nombre de porte : "+m.getNb_Porte()+"\n");
                        }
                        if (m.getNb_Fenetre()!=0){
                            writer.write("        Nombre de fenetre : "+m.getNb_Fenetre()+"\n");
                        }
                                            
                    }
                    if (p.getSol().getRevetement()==null){
                        writer.write("    Sol (Aucun) : " + p.getSol().getPrix() + "€\n");
                    }else {
                        writer.write("    Sol ("+p.getSol().getRevetement().getDesignation()+") : " + p.getSol().getPrix() + "€\n");
                    }
                    if (p.getPlafond().getRevetement()==null){
                        writer.write("    Plafond (Aucun) : " + p.getPlafond().getPrix() + "€\n");
                    }else {
                        writer.write("    Plafond ("+p.getPlafond().getRevetement().getDesignation()+") : " + p.getPlafond().getPrix() + "€\n");
                    }
                    writer.write("\n");
                }
                writer.write("\n");
            }
            writer.write("\n");
            writer.write("Prix total du bâtiment : " + batiment.getPrix() + "€\n");
            System.out.println("Le devis a été écrit dans le fichier : " + batiment.getNom_Batiment() + " Devis");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
}
