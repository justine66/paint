package control;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import modele.EnumFormesGeo;
import modele.FormeGeo;
import vue.Canva;

public class ControlCan {
   
private Control control;
    private Canva Canva;
    private GraphicsContext gc;
    private boolean redimension = false;
	private boolean dessins = true;
	
    /** Constructeur 
     * @param c
     */
    public ControlCan(Control c) {
        this.control = c;
        this.Canva = new Canva();
        this.gc = this.Canva.getdessin().getGraphicsContext2D();
        this.MesActions();
    }

    /** On dessine toute les formes 
     */
    public void draw() {
        gc.clearRect(0, 0, Canva.WIDTH, Canva.HEIGHT);
        for (int i= 0 ; i < this.control.getModel().getFormes().size(); i++)  {
			this.control.getModel().getFormes().get(i).draw(this.gc);
		}
    }

    /** Clear de la zone de dessin 
     */
    public void clear() {
        gc.clearRect(0, 0, Canva.WIDTH, Canva.HEIGHT);
    }
    
    /** Les actions possible sur le canvas
     */
    public void MesActions() {
    	Canvas cnvs = this.Canva.getdessin();

    	cnvs.setOnMousePressed(e -> {if (isDessins() == false){ this.control.attrape(e);}
    	else this.control.getModel().startDrawPoint(e);});

    	cnvs.setOnMouseReleased(e ->{if (isDessins() == false){ this.control.lache(e);}
    	else this.control.getModel().endDrawPoint(e);});

    	cnvs.setOnMouseDragged(e ->{if (isDessins() == false){ 
    		if (redimension == false ) {
    			this.control.deplace(e);
    		}
    		else this.control.redimensionne(e);}
    	else  this.control.getModel().tempDraw(e);});		
    }

    /** Getter de la zone de dessin 
     * @return Canva
     */
    public Canva getCanva() {
        return Canva;
    }

    /** Getter de gc
     * @return gc
     */
    public GraphicsContext getGC() {
        return gc;
    }
    
    /** Setter de gc
     * @param gc
     */
    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }

    /** Getter de dessin
     * @return dessins
     */
	public boolean isDessins() {
		return dessins;
	}

	/** Setter de dessin
	 * @param dessins
	 */
	public void setDessins(boolean dessins) {
		this.dessins = dessins;
	}

	/** Setter de redimension
	 * @param b
	 */
	public void Redimension(boolean b) {
		this.redimension = b;
		
	}
}
