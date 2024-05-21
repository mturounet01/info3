/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.antoine.max.devibat;

import java.io.Serializable;

/**
 *
 * @author antoinez
 */

public class Coin implements Serializable{
    
    //Attributs
    private double x,y;
    
    //Constructeur
    public Coin(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //GETTERS
    public double getX() {return x;}
    public double getY() {return y;}
    
    //SETTERS
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
}
