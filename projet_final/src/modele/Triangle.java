package modele;



	import javafx.scene.canvas.GraphicsContext;
	import javafx.scene.paint.Color;
	import javafx.scene.paint.Paint;

	public class Triangle extends FormeGeo {
		
		double x;
		double y;
		double x1;
		double y1;
		private double x2;
	    private double y2;
	    private Color clr = Color.BLUE;
	    private boolean selectionne;
	   
	    

		public Triangle() {
	        super();
	    }


	    public Triangle(double x, double y, double x2, double y2) {
	        super(x, y, x2, y2);
	    }
	    
	    public Triangle(double x, double y, double x2, double y2, double x1, double y1) {
	    	this.x = x;
	        this.y = y;
	        this.x1 = x1;
	        this.y1 = y1;
	        this.x2 = x2;
	        this.y2 = y2;
	        this.selectionne = false;
	        
	    }

		//Methodes
	    @Override
	    public boolean estDedans(double x, double y) {
	    	return (x)<=this.getx2()  && (x-this.getX())>=0 &&
	     		   (y)<=this.gety2() && (y-this.getY())>=0;
	    }

	    @Override
	    public void draw(GraphicsContext gc) {
	        Paint save = gc.getFill();
	        gc.setFill(this.getClr());
	        gc.fillPolygon( new double[] {this.getX(), this.getx2(), (this.getX())},
	        				new double[] {this.getY(), this.gety2(), this.gety2()}, 3);
	        gc.setFill(save);
	    }
	    
	    public double getX1() {
	        return x1;
	    }

	    public void setX1(double x) {
	        this.x1 = x;
	    }

	    public double getY1() {
	        return y1;
	    }

	    public void setY1(double y) {
	        this.y1 = y;
	    }
	}