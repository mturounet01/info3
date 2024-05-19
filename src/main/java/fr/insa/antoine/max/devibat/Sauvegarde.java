package fr.insa.antoine.max.devibat;

import fr.insa.antoine.max.devibat.Batiment;
import java.io.*;


//constructeur
public class Sauvegarde {
    private String fichierSauvegarde;

    public Sauvegarde(String fichierSauvegarde) {
        this.fichierSauvegarde = fichierSauvegarde;
    }

    public void sauvegarder(Batiment batiment) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fichierSauvegarde))) {
            outputStream.writeObject(batiment);
            System.out.println("Sauvegarde réussie !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public Batiment charger(String fichierSauvegarde) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fichierSauvegarde))) {
            Batiment batiment = (Batiment) inputStream.readObject();
            System.out.println("Chargement réussi !");
            return batiment;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
            return null;
        }
    }
}
