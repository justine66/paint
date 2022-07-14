package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class FormeGeo {
    //Attributs
    private double x;
    private double y;
    private double x2;
    private double y2;
    private Color clr = Color.BLUE;
    private boolean selectionne;
 
    /**
     * Constructeur de la forme sans params
     */
    public FormeGeo() {
        this.x = 0;
        this.y = 0;
        this.x2 = 0;
        this.y2 = 0;
        this.selectionne = true;
    }

    /**
     * Constructeur de la forme avec tout les params
     * @param x
     * @param y
     * @param x2
     * @param y2
     */
    public FormeGeo(double x, double y, double x2, double y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.selectionne = false;
    }

    /**
     * Constructeur de la forme avec la position
     * @param x
     * @param y
     */
    public FormeGeo(double x, double y) {
        this.x = x;
        this.y = y;
        this.x2 = 0;
        this.y2 = 0;
        this.selectionne = true;
    }

    /**
     * Verifie qu'un point est dans la forme
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return vrai si le point est dans la forme
     */
    public abstract boolean estDedans(double x, double y);

    /**
     * Dessine la forme
     * @param gc GraphicsContext du canvas de la vue
     */
    public abstract void draw(GraphicsContext gc);
    

    //Getters et Setters

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getx2() {
        return x2;
    }

    public void setx2(double x2) {
        this.x2 = x2;
    }

    public double gety2() {
        return y2;
    }

    public void sety2(double y2) {
        this.y2 = y2;
    }

    public Color getClr() {
        return clr;
    }

    public void Changercouleur(Color clr) {
        this.clr = clr;
    }

    public boolean isselect() {
        return selectionne;
    }

    public void setselect(boolean selectionne) {
        this.selectionne = selectionne;
    }
			
}
