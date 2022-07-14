package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Ellipse extends FormeGeo {
    public Ellipse() {
    }

    public Ellipse(double x, double y) {
        super(x, y);
    }

    public Ellipse(double x, double y, double x2, double y2) {
        super(x, y, x2, y2);
    }

    @Override
    public boolean estDedans(double x, double y) {
    	return (x)<=this.getx2()  && (x-this.getX())>=0 &&
     		   (y)<=this.gety2() && (y-this.getY())>=0;
    }
    

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());
        gc.fillOval(this.getX(), this.getY(), (this.getx2()-this.getX()), this.gety2()-this.getY());
        gc.strokeOval(this.getX(), this.getY(), (this.getx2()-this.getX()), this.gety2()-this.getY());
        gc.setFill(save);
    }
    
}
