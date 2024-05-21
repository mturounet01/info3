package fr.insa.antoine.max.devibat;

import java.io.Serializable;

public abstract class Revetements implements Serializable{
    
    protected int idRevetement;
    protected String designation;
    protected double prixunit;
    
    //GETTERS pour connaitre le prix et le nom associé a chaque revetement
    public double getPrixunit(){return this.prixunit;}
    public String getDesignation(){return this.designation;}
}

