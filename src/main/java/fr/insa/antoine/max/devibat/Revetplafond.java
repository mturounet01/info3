/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

/**
 *
 * @author maxt
 */
public class Revetplafond extends Revetements {//classe fille de Revetements

    public Revetplafond(int idRevetement, String designation, double prixunit) {
        this.idRevetement = idRevetement;
        this.designation = designation+" "+prixunit+"€/m²";
        this.prixunit = prixunit;
    } 
}
