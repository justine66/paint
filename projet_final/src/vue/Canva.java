package vue;

import javafx.scene.canvas.Canvas;

public class Canva {
    private Canvas des;

    public final static int WIDTH = 800;
    public final static int HEIGHT = 500;

    /**
     * Constructeur 
     */
    public Canva() {
        this.des = new Canvas(Canva.WIDTH, Canva.HEIGHT);
    }
    /** Getter 
     * @return drawArea
     */
    public Canvas getdessin() {
        return des;
    }

    /** Setter
     * @param drawArea
     */
    public void setdessin(Canvas drawArea) {
        this.des = drawArea;
    }
}
