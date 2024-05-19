/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import fr.insa.antoine.max.devibat.Etage;
import fr.insa.antoine.max.devibat.Revetmur;
import fr.insa.antoine.max.devibat.Revetplafond;
import fr.insa.antoine.max.devibat.RevetSol;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author laelt
 */
public class Batiment implements Serializable{
    
    private double largeur, longueur;
    private int niveau;
    private String Nom_Batiment;
    
    private double prix;
    
    private ArrayList<Etage> Liste_Etage;
    
    private ArrayList<String> liste_mur = new ArrayList<String>();
    private ArrayList<String> liste_plafond = new ArrayList<String>();
    private ArrayList<String> liste_sol = new ArrayList<String>();
    private HashMap<String,Revetmur> StringToPourmur= new HashMap<String,Revetmur>();
    private HashMap<String,Revetplafond> StringToPourplafond= new HashMap<String,Revetplafond>();
    private HashMap<String,RevetSol> StringToPoursol= new HashMap<String,RevetSol>();
    
    //Constructeurs
    public Batiment(){
        this.Liste_Etage = new ArrayList<Etage>();
        this.liste_mur.add(" ");
        this.liste_sol.add(" ");
        this.liste_plafond.add(" ");
        this.StringToPourmur.put(" ", null);
        this.StringToPoursol.put(" ", null);
        this.StringToPourplafond.put(" ", null);
        this.Creation_Liste_Revetement();
    }
    
    //GETTERS
    public int getNiveau () {return this.niveau;}
    public int getTailleListeEtage () {return this.Liste_Etage.size();}
    public double getLongueur () {return this.longueur;}
    public double getLargeur () {return this.largeur;}
    public String getNom_Batiment() {return this.Nom_Batiment;}
    public ArrayList<Etage> getListe_Etage(){return this.Liste_Etage;}
    public ArrayList<String> getListe_mur() {return liste_mur;}
    public ArrayList<String> getListe_plafond() {return liste_plafond;}
    public ArrayList<String> getListe_sol() {return liste_sol;}
    public HashMap<String,Revetmur> getStringToPourmur (){return this.StringToPourmur;}
    public HashMap<String,RevetSol> getStringToPoursol (){return this.StringToPoursol;}
    public HashMap<String,Revetplafond> getStringToPourplafond (){return this.StringToPourplafond;}
    
    public double getPrix() {return this.prix;}
    
    
    //SETTERS
    public void set_Longueur(double nb){this.longueur = nb;}
    public void set_Largeur(double nb){this.largeur = nb;}
    public void set_nb_niveau(int nb){this.niveau = nb;}
    public void set_Nom_Batiment(String nom){this.Nom_Batiment = nom;}
    
    public void Creation_Liste_Revetement(){
        String designation;
        int idRevetement, mur, sol, plafond;
        double prixunit;
      
        try{
            BufferedReader revet = new BufferedReader(new FileReader("revetements.txt"));
            String lignelue;
            lignelue = revet.readLine(); // enlever la premiere ligne car elle contient la légende
            while ((lignelue = revet.readLine()) != null){
                StringTokenizer mot=new StringTokenizer(lignelue, ";"); 
                idRevetement=Integer.parseInt(mot.nextToken());
                designation=mot.nextToken();
                mur=Integer.parseInt(mot.nextToken());
                sol=Integer.parseInt(mot.nextToken());
                plafond=Integer.parseInt(mot.nextToken());
                prixunit=Double.parseDouble(mot.nextToken());//récupérer les différentes infos
    
                if(test_binaire(mur)){
                    Revetmur objmur = new Revetmur(idRevetement,designation,prixunit);
                   this.liste_mur.add(objmur.getDesignation());
                    this.StringToPourmur.put(objmur.getDesignation(),objmur);// mettre dans les listes les objets
                }
                if(test_binaire(sol)){
                    RevetSol objsol = new RevetSol(idRevetement,designation,prixunit);
                    this.liste_sol.add(objsol.getDesignation());
                    this.StringToPoursol.put(objsol.getDesignation(),objsol);
                }
                if(test_binaire(plafond)){
                    Revetplafond objplafond = new Revetplafond(idRevetement,designation,prixunit);
                    this.liste_plafond.add(objplafond.getDesignation());
                    this.StringToPourplafond.put(objplafond.getDesignation(),objplafond);
                }
            }
        revet.close();
        }
        
        catch(FileNotFoundException err){
            System.out.println( "Erreur :le fichier n’existe pas!\n "+err);
        }
        catch (IOException err){
            System.out.println(" Erreur :\n "+err);
        }
    }
    static boolean test_binaire(int binaire){ //test si 1 vrai si 0 faux
        if (binaire == 1){
            return true;
        }
        return false;
        }
    
    public void Devis_Batiment(){
        double prixEtages = 0;
        for (Etage e : Liste_Etage){
            prixEtages = prixEtages + e.Devis_Etage();
        }
        this.prix = prixEtages;
        BigDecimal bd = new BigDecimal(this.prix);
        bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
        this.prix = bd.doubleValue();
    }

}