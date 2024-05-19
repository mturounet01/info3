package fr.insa.antoine.max.devibat;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author antoinez
 */
public class Piece implements Serializable{
    
    private ArrayList<Mur> Liste_Mur;
    private double longueur;
    private double largeur;
    private Sol sol;
    private Plafond plafond;
    private double Surface;
    
    private transient Color CouleurFond;
    private Couleur color;
    
    private double prix;
    
    //Constructeurs
    public Piece(ArrayList<Mur> Liste_Mur) {
        this.Liste_Mur = Liste_Mur;
        this.longueur = this.Liste_Mur.get(0).getLongueur();
        this.largeur = this.Liste_Mur.get(1).getLongueur();
        this.Surface = this.largeur*this.longueur;
        this.CouleurFond = Color.TRANSPARENT;
        this.plafond = new Plafond(this);
        this.sol = new Sol(this);
        this.color = new Couleur(this.CouleurFond.getRed(), this.CouleurFond.getGreen(), this.CouleurFond.getBlue(), this.CouleurFond.getOpacity());
    }
    
    //Getters
    public ArrayList<Mur> getListe_Mur(){return this.Liste_Mur;}
    public double getLongueur(){return this.Liste_Mur.get(0).getLongueur();}
    public double getLargeur(){return this.Liste_Mur.get(1).getLongueur();}
    public double getSurface(){return this.getLongueur()*this.getLargeur();}
    public Coin getCoinDepart(){return this.Liste_Mur.get(0).getDebut();}
    public Coin getCoin2(){return this.Liste_Mur.get(1).getDebut();}
    public Coin getCoinFin(){return this.Liste_Mur.get(2).getDebut();}
    public Coin getCoin4(){return this.Liste_Mur.get(3).getDebut();}
    public Sol getSol(){return this.sol;}
    public Plafond getPlafond(){return this.plafond;}
    public double getPrix() {return this.prix;}
    
    public Color getCouleurFond() {return this.CouleurFond;}
    public Couleur getColor() {return this.color;}
    
    //Setters qui décrivent la création d'une pièce
    public void setCouleurFond (Color couleur){this.CouleurFond = couleur;}
    public void setLargeur(double nouvelle_largeur){
        double x1 = this.getCoinDepart().getX();
        double x2 = this.getCoinFin().getX();
        if (x1<x2){//cas ou le coin de fin est plus grand que celui d'arrivée
            this.getCoinFin().setX(x1+nouvelle_largeur);
            this.getCoin4().setX(x1+nouvelle_largeur);
        }else if (x1>x2){//cas inverse
            this.getCoinFin().setX(x1-nouvelle_largeur);
            this.getCoin4().setX(x1-nouvelle_largeur);
        }
    }
    public void setLongueur(double nouvelle_longueur){
        double y1 = this.getCoinDepart().getY();
        double y2 = this.getCoinFin().getY();
        if (y1<y2){
            this.getCoinFin().setY(y1+nouvelle_longueur);
            this.getCoin2().setY(y1+nouvelle_longueur);
        }else if (y1>y2){   
            this.getCoinFin().setY(y1-nouvelle_longueur);
            this.getCoin2().setY(y1-nouvelle_longueur);
        }
    }
    
    //
    
    public void dessiner(GraphicsContext context) {
    context.setFill(this.CouleurFond);

    // Récupérer les coins de départ et de fin
    Coin coinDepart = this.getCoinDepart();
    Coin coinFin = this.getCoinFin();

    // Calculer les dimensions du rectangle
    double largeur = Math.abs(coinDepart.getX() - coinFin.getX());
    double longueur = Math.abs(coinDepart.getY() - coinFin.getY());
    
    // Déterminer le coin de départ pour le remplissage du rectangle
    double x = Math.min(coinDepart.getX(), coinFin.getX());
    double y = Math.min(coinDepart.getY(), coinFin.getY());

    // Dessiner le rectangle
    context.fillRect(x, y, largeur, longueur);

    // Dessiner les murs
    for (Mur m : this.Liste_Mur) {
        m.dessiner(context);
    }
}

      
    public boolean ClickDansPiece(Coin c) {
    // Récupérer les coordonnées des coins de départ et de fin de la pièce
    double x1 = this.getCoinDepart().getX();
    double y1 = this.getCoinDepart().getY();
    double x2 = this.getCoinFin().getX();
    double y2 = this.getCoinFin().getY();
    double x3 = c.getX();
    double y3 = c.getY();
    
    // Vérifier si le point se trouve entre les coordonnées x des coins de départ et de fin
    boolean xInRange = (x1 < x3 && x3 < x2) || (x2 < x3 && x3 < x1);
    
    // Vérifier si le point se trouve entre les coordonnées y des coins de départ et de fin
    boolean yInRange = (y1 < y3 && y3 < y2) || (y2 < y3 && y3 < y1);
    
    // Le point est à l'intérieur de la pièce si ses coordonnées x et y sont dans les plages définies
    return xInRange && yInRange;
}

    
   public double Devis_Piece() {
    // Calculer le prix des murs,stream permet d'ajouter la somme des murs
    double prixMurs = Liste_Mur.stream().mapToDouble(Mur::Devis_Mur).sum();
    
    // Calculer le prix du sol et du plafond
    double prixSol = sol.Devis_Sol();
    double prixPlafond = plafond.Devis_Plafond();
    
    // Calculer le prix total de la pièce
    double prixTotal = prixMurs + prixSol + prixPlafond;
    
    // Arrondir le prix total à deux décimales
    BigDecimal bd = BigDecimal.valueOf(prixTotal).setScale(2, RoundingMode.DOWN);
    
    // Mettre à jour le prix de la pièce avec le nouveau montant arrondi
    this.prix = bd.doubleValue();
    
    // Retourner le prix arrondi
    return this.getPrix();
}

    
    public void Piece(Color CouleurFond) {
        this.color = new Couleur(this.CouleurFond.getRed(), this.CouleurFond.getGreen(), this.CouleurFond.getBlue(), this.CouleurFond.getOpacity());
    }
    
    public void Piece(Couleur color) {
        this.CouleurFond = this.color.toColor();
    }
}
