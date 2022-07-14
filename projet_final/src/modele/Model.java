package modele;

import control.Control;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import vue.Vue;
import vue.Canva;


import java.util.ArrayList;

public class Model {
	Vue vue;
    Control ctrl;
    EnumFormesGeo Type;
    FormeGeo f;
    ArrayList<FormeGeo> formes;

    /** Constructeur
     * @param v
     */
    public Model(Vue v) {
        this.vue = v;
        this.formes = new ArrayList<>();
    }
    
    /** Ajout d'une nouvelle forme a la liste
     * @param f
     */
    public void addForme(FormeGeo f) {
        this.formes.add(f);
    }

    /** Reinitilisation de la liste de forme
     */
    public void clearFormes() {
        this.formes = new ArrayList<>();
        this.ctrl.getCvsCtrl().clear();
    }

    /** On commence a dessiner
     * @param e
     */
    public void startDrawPoint(MouseEvent e) {
        if(this.Type != null) {
            this.getf().setX(e.getX());
            this.getf().setY(e.getY());
        }
    }

    /** Affichage pendant le dessin
     * @param e
     */
    public void tempDraw(MouseEvent e) {
        if(this.Type != null) {
            this.getCtrl().getCvsCtrl().getCanva().getdessin().getGraphicsContext2D().clearRect(0, 0, Canva.WIDTH, Canva.HEIGHT);
            this.getCtrl().getCvsCtrl().draw();
            this.f.setx2(e.getX());
            this.f.sety2(e.getY());
            this.getf().draw(this.ctrl.getCvsCtrl().getGC());
        }
    }

    /** Fin du dessin
     * @param e
     */
    public void endDrawPoint(MouseEvent e) {
        if(this.Type != null) {
        	this.getf().setx2(e.getX());
            this.getf().sety2(e.getY());
            this.getFormes().add(this.f);
            this.getCtrl().getCvsCtrl().draw();
            this.newForme();
        }
    }

    /**
     *  Création d'un nouvelle forme
     */
    public void newForme() {
         if (this.Type == EnumFormesGeo.LIGNE){
                this.setf(new Ligne());}
         else if(this.Type == EnumFormesGeo.RECTANGLE){
                this.setf(new Rectangle());}   
         else if(this.Type == EnumFormesGeo.TRIANGLE) {
             	this.setf(new Triangle());}
         else if(this.Type == EnumFormesGeo.ELLIPSE){
                this.setf(new Ellipse());}
         
    }
    
    /**
     * Creation d'une nouvelle forme lors de l'ouverture du fichier sauvegarde
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void newForme2(double x, double y , double width , double height ) {
        if (this.Type == EnumFormesGeo.LIGNE){
             formes.add(new Ligne(x,y,width,height));}
        else if(this.Type == EnumFormesGeo.RECTANGLE){
        	formes.add(new Rectangle(x,y,width,height));}   
        else if(this.Type == EnumFormesGeo.TRIANGLE) {
        	formes.add(new Triangle(x,y,width,height));}
        else if(this.Type == EnumFormesGeo.ELLIPSE){
        	formes.add(new Ellipse(x,y,width,height));}
        
   }

    /** Fonction pour recuperer une forme de la liste
     * @param i
     * @return formes.get(i)
     */
	FormeGeo get(int i) {return formes.get(i);}

	/**
	 * Appel a la fonction qui recupere une forme dans la liste
	 * @param index
	 * @return
	 */
    public FormeGeo getForme(int index) {
        return this.formes.get(index);
    }

    /** Renvoit la taille de la liste 
     * @return formes.size()
     */
    public int getNbFormes() {
        return this.formes.size();
    }
    
    /**
     * Getter de la vue
     * @return vue
     */
    public Vue getView() {
        return vue;
    }

    /**
     * Setter de la vue
     * @param view
     */
    public void setView(Vue view) {
        this.vue = view;
    }

    /**
     * Getter du controlleur
     * @return
     */
    public Control getCtrl() {
        return ctrl;
    }

    /**
     * Setter du controlleur 
     * @param ctrl
     */
    public void setCtrl(Control ctrl) {
        this.ctrl = ctrl;
    }

    /** Getter du type
     * @return Type
     */
    public EnumFormesGeo getType() {
        return Type;
    }

    /** Setter du type
     * @param Type
     */
    public void setType(EnumFormesGeo Type) {
        this.Type = Type;
    }

    /** Getter de f
     * @return f
     */
    public FormeGeo getf() {
        return f;
    }

    /**
     * Setter de f
     * @param f
     */
    public void setf(FormeGeo f) {
        this.f = f;
    }

    /** Getter de formes
     * @return formes
     */
    public ArrayList<FormeGeo> getFormes() {
        return formes;
    }

    /**
     * Stter de la couleur 
     * @param c
     */
	public void Changercouleur(Color c) {
		f.Changercouleur(c);
	}
}
