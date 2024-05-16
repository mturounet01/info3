package fr.insa.antoine.max.devibat;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author laelt
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
    
    //Setters
    public void setCouleurFond (Color couleur){this.CouleurFond = couleur;}
    public void setLargeur(double nouvelle_largeur){
        double x1 = this.getCoinDepart().getX();
        double x2 = this.getCoinFin().getX();
        if (x1<x2){
            this.getCoinFin().setX(x1+nouvelle_largeur);
            this.getCoin4().setX(x1+nouvelle_largeur);
        }else if (x1>x2){
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
    
    //AUTRES METHODES
    
    public void dessiner(GraphicsContext context){
        context.setFill(this.CouleurFond);
        double x1 = this.getCoinDepart().getX();
        double y1 = this.getCoinDepart().getY();
        double x3 = this.getCoinFin().getX();
        double y3 = this.getCoinFin().getY();
        if ((x1<x3)&&(y1<y3)){            
            context.fillRect(this.getCoinDepart().getX(), this.getCoinDepart().getY(), this.getLargeur(), this.getLongueur());
        }else if ((x1<x3)&&(y1>y3)){
            Coin CoinDepartRemplissage = this.Liste_Mur.get(1).getDebut();
            context.fillRect(CoinDepartRemplissage.getX(), CoinDepartRemplissage.getY(), this.getLargeur(), this.getLongueur());
        }else if ((x1>x3)&&(y1>y3)){
            Coin CoinDepartRemplissage = this.Liste_Mur.get(2).getDebut();
            context.fillRect(CoinDepartRemplissage.getX(), CoinDepartRemplissage.getY(), this.getLargeur(), this.getLongueur());
        }else if ((x1>x3)&&(y1<y3)){
            Coin CoinDepartRemplissage = this.Liste_Mur.get(3).getDebut();
            context.fillRect(CoinDepartRemplissage.getX(), CoinDepartRemplissage.getY(), this.getLargeur(), this.getLongueur());
        }
        
        for (Mur m : this.Liste_Mur){
            m.dessiner(context);
        }
    }
      
    public boolean ClickDansPiece(Coin c){
        double x1 = this.getCoinDepart().getX();
        double y1 = this.getCoinDepart().getY();
        double x2 = this.getCoinFin().getX();
        double y2 = this.getCoinFin().getY();
        double x3 = c.getX();
        double y3 = c.getY();
        
        if ((x1<x3 && x3<x2)||(x2<x3 && x3<x1)){
            if((y1<y3 && y3<y2)||(y2<y3 && y3<y1)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    
    public double Devis_Piece(){
        double prixMurs = 0;
        for (Mur m : Liste_Mur){
            prixMurs = prixMurs + m.Devis_Mur();
        }
        this.prix = prixMurs + this.sol.Devis_Sol() + this.plafond.Devis_Plafond();
        BigDecimal bd = new BigDecimal(this.prix);
        bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
        this.prix = bd.doubleValue();
        return this.getPrix();
    }
    
    public void Piece(Color CouleurFond) {
        this.color = new Couleur(this.CouleurFond.getRed(), this.CouleurFond.getGreen(), this.CouleurFond.getBlue(), this.CouleurFond.getOpacity());
    }
    
    public void Piece(Couleur color) {
        this.CouleurFond = this.color.toColor();
    }
}
