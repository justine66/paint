package control;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import modele.FormeGeo;
import modele.Model;
import vue.Vue;
import vue.Canva;

public class Control {
    Model model;
    Vue vue;
    ControlCan cvsCtrl;
    MonMenuControl menuCtrl;
    
    private int formeIdx; //Index de la forme qui est en train d'�tre d�plac�e
   	private boolean enDeplacement=false; //Si une forme est en train d'�tre d�plac�e
   	private double x_souris, y_souris; //Coordonn�es pr�c�dentes de la souris

    /** Constructeur 
     * @param model
     * @param view
     */
    public Control(Model model, Vue view) {
        this.model = model;
        this.vue = view;
        this.cvsCtrl = new ControlCan(this);
        this.menuCtrl = new MonMenuControl(this);
    }

    
	/**
	 * Quand l'utilisateur appuie sur la souris pour attraper une forme
	 * et la deplacer.
	 * @param e evenement souris
	 */
	void attrape(MouseEvent e) {
		for (int i=this.getModel().getFormes().size()-1; i>=0;i--) {
			FormeGeo f= this.getModel().getFormes().get(i);
			f.setselect(false);	
			if (f.estDedans(e.getX(), e.getY())) {
				formeIdx=i;
				enDeplacement=true;
				 x_souris = e.getX();
				 y_souris = e.getY();
				 f.setselect(true);
				break;
			}
		}
	}
	
	/**
	 * Quand l'utilisateur deplace une forme
	 * @param e evenement souris
	 */
	void deplace(MouseEvent e) {
		if (enDeplacement) {
			//System.out.println("deplaceL");
			double dx = e.getX() - x_souris;
			double dy = e.getY() - y_souris;
			
			double x_avant = this.getModel().getFormes().get(formeIdx).getX();
			double y_avant = this.getModel().getFormes().get(formeIdx).getY();
			double width   = this.getModel().getFormes().get(formeIdx).getx2();
			double height  = this.getModel().getFormes().get(formeIdx).gety2();
			
			this.getModel().getFormes().get(formeIdx).setX(x_avant+dx);
			this.getModel().getFormes().get(formeIdx).setY(y_avant+dy);
		
			this.getModel().getFormes().get(formeIdx).setx2(width+dx);
			this.getModel().getFormes().get(formeIdx).sety2(height+dy);
			x_souris = e.getX();
			y_souris = e.getY();
			this.draw();
			this.cvsCtrl.Redimension(false);
		}
	}
	
	
	/**
	 * Appel� quand l'utilisateur l�che le bouton de souris 
	 * pour arr�ter le d�placement
	 * @param e �v�nement souris
	 */
	void lache(MouseEvent e) {
		enDeplacement=false;
	}
	
	
	/** Redimensionne une forme
	 * @param e evenement souris
	 */
	void redimensionne(MouseEvent e) {
		if (enDeplacement) {
			//On calcule le d�placement de la souris par rapport � sa derni�re
			//position
			double dx = e.getX() ; //- x_souris;
			double dy = e.getY() ;//- y_souris;
			//On r�cup�re la position actuelle de la forme
			double x_avant = this.getModel().getFormes().get(formeIdx).getX();
			double y_avant = this.getModel().getFormes().get(formeIdx).getY();
			//On d�place la forme du m�me d�placement que la souris
			this.getModel().getFormes().get(formeIdx).setx2(x_avant+dx);
			this.getModel().getFormes().get(formeIdx).sety2(y_avant+dy);
			//On retient les coordonn�es de la souris pour calculer le prochain d�placement
			x_souris = e.getX();
			y_souris = e.getY();
			this.draw();
		}
	}
	
	/**
	 * La fonction qui efface le canvas et redessine tous les elements
	 */
	void draw() {
		cvsCtrl.getGC().setFill(Color.WHITE);
		cvsCtrl.getGC().fillRect(0,0,Canva.WIDTH, Canva.HEIGHT);
		cvsCtrl.getGC().setFill(Color.BLACK);
		for (int i=0; i<this.getModel().getFormes().size();i++) {
			FormeGeo f=this.getModel().getFormes().get(i);
			f.draw(cvsCtrl.getGC());
		}
	}
	
    /**Getter du model
     * @return model
     */
    public Model getModel() {
        return model;
    }

    /**Setter du Model 
     * @param model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**Getter de la vue
     * @return view
     */
    public Vue getVue() {
        return vue;
    }

    /** Setter de la vue
     * @param view
     */
    public void setVue(Vue vue) {
        this.vue = vue;
    }

    /** Getter controlleur du canvas
     * @return cvsCtrl
     */
    public ControlCan getCvsCtrl() {
        return cvsCtrl;
    }

    /** Setter controlleur du Canvas 
     * @param cvsCtrl
     */
    public void setCvsCtrl(ControlCan cvsCtrl) {
        this.cvsCtrl = cvsCtrl;
    }

    /** Getter du controlleur du menu
     * @return menuCtrl
     */
    public MonMenuControl getMenuCtrl() {
        return menuCtrl;
    }

    /** Setter du controlleur du menu 
     * @param menuCtrl
     */
    public void setMenuCtrl(MonMenuControl menuCtrl) {
        this.menuCtrl = menuCtrl;
    }
}
