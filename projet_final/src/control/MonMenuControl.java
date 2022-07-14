package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import javax.imageio.ImageIO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import modele.EnumFormesGeo;
import modele.FormeGeo;
import vue.MonMenu;


public class MonMenuControl {
	Control ctrl;
	MonMenu menu_app;

	/** Constructuer 
	 * @param c
	 */
	public MonMenuControl(Control c) {
		this.ctrl = c;
		this.menu_app = new MonMenu();
	}

	/**
	 *  Toute les actions possibles dans les menues
	 */
	public void MesActions() {
		//Menu fichier
		ObservableList<MenuItem> fichier = menu_app.getMenuBar().getMenus().get(0).getItems();
		//Nouveau 
		fichier.get(0).setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Vous allez ouvrir un nouveau dessin sans sauvegarder!"+"\n" +"Etes vous sur?", 
					ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				this.ctrl.getModel().clearFormes();
			}
		});
		//Ouvrir
		fichier.get(1).setOnAction(e -> {
			try {
				FileReader fr = new FileReader("MonDessin.dessin");
				BufferedReader br = new BufferedReader(fr);
				this.ctrl.getModel().clearFormes();
				try {
					String line = br.readLine();
					while (line !=null) {
						String[] tab = line.split(" ");
						if (tab[0].equals("Rectangle") ) {
							this.ctrl.getModel().setType(EnumFormesGeo.RECTANGLE);
						}
						else if (tab[0].equals("Triangle") ) {
							this.ctrl.getModel().setType(EnumFormesGeo.TRIANGLE);
						}
						else if (tab[0].equals("Ligne") ) {
							this.ctrl.getModel().setType(EnumFormesGeo.LIGNE);
						}
						else if (tab[0].equals("Ellipse") ) {
							this.ctrl.getModel().setType(EnumFormesGeo.ELLIPSE);
						}
						double x 	  = Double.valueOf(tab[1]);
						double y 	  = Double.valueOf(tab[2]);
						double width  = Double.valueOf(tab[3]);
						double height = Double.valueOf(tab[4]);
						this.ctrl.getModel().newForme2(x, y, width, height);	
						FormeGeo f =this.ctrl.getModel().getFormes().get(this.ctrl.getModel().getFormes().size()-1);
						Color C = Color.valueOf(tab[5]);
						f.Changercouleur(C);
						line = br.readLine();
					}
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Un probleme est survenue lors de l'ouverture");
			}
			;});
		//Enregistrer
		fichier.get(2).setOnAction(e -> { 
			Canvas c = this.ctrl.getModel().getCtrl().getCvsCtrl().getCanva().getdessin();
			WritableImage image = new WritableImage ( (int)c.getWidth() , (int)c.getHeight() ) ;
			c.snapshot(null , image );
			File file = new File ( "image.png" ) ;
			try {
				ImageIO.write (SwingFXUtils.fromFXImage (image,null) ,"png", file ) ;
			} catch ( IOException ex ) {
				System.err.println( ex.getMessage() ) ;
			}
		});
		//Enregistrer
		fichier.get(3).setOnAction(e ->{
			File f = new File("MonDessin.dessin");
			try {
				FileWriter fw = new FileWriter(f);
				for (int i= 0 ; i <this.ctrl.getModel().getFormes().size();i++)  {
					FormeGeo forme = this.ctrl.getModel().getFormes().get(i);
					String str = forme.getClass().getSimpleName() + " " +
							forme.getX()     + " " + forme.getY()     + " " +
							forme.getx2() + " " + forme.gety2()+ " " +
							forme.getClr().toString()   + " " + "\n" ;
					fw.write(str);
				}
				fw.close();
			} catch ( IOException ex ) {
				System.out.println("Un probleme est survenue lors de l'enregistrer en .dessin");
				System.err.println( ex.getMessage() ) ;
			}

		});
		//Quitter
		fichier.get(4).setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Vous allez quitter sans sauvegarder!"+"\n" +"Etes vous sur?", 
					ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				Platform.exit();
			}
		});

		//Menu insertion
		ObservableList<MenuItem> insertion = menu_app.getMenuBar().getMenus().get(1).getItems();
		//insertion ligne
		insertion.get(0).setOnAction(e -> {ctrl.cvsCtrl.setDessins(true); 
		this.ctrl.getModel().setType(EnumFormesGeo.LIGNE);
		this.ctrl.getModel().newForme();
		});
		//insertion rectangle
		insertion.get(1).setOnAction(e -> {ctrl.cvsCtrl.setDessins(true); 
		this.ctrl.getModel().setType(EnumFormesGeo.RECTANGLE);
		this.ctrl.getModel().newForme();});
		//insertion Triangle
		insertion.get(2).setOnAction(e -> {	ctrl.cvsCtrl.setDessins(true);
		this.ctrl.getModel().setType(EnumFormesGeo.TRIANGLE);	
		this.ctrl.getModel().newForme();});
		//insertion Elispe
		insertion.get(3).setOnAction(e -> {ctrl.cvsCtrl.setDessins(true); 
		this.ctrl.getModel().setType(EnumFormesGeo.ELLIPSE);
		this.ctrl.getModel().newForme();});

		//Menu edition
		ObservableList<MenuItem> action = menu_app.getMenuBar().getMenus().get(2).getItems();
		action.get(0).setOnAction(e -> {ctrl.cvsCtrl.setDessins(false);
		ctrl.cvsCtrl.Redimension(false);});
		action.get(1).setOnAction(e -> {ctrl.cvsCtrl.setDessins(true);});

		//Menu couleur
		ObservableList<MenuItem> couleur = menu_app.getMenuBar().getMenus().get(3).getItems();
		couleur.get(0).setOnAction(e -> { if ( this.ctrl.getModel().getNbFormes()-1 >= 0){
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--)  {
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					this.ctrl.getModel().getForme(i).Changercouleur(Color.PINK); 
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}
			}
		}});

		couleur.get(1).setOnAction(e -> { if ( this.ctrl.getModel().getNbFormes()-1 >= 0){
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--)  {
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					this.ctrl.getModel().getForme(i).Changercouleur(Color.RED); 
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}
			}
		}});

		couleur.get(2).setOnAction(e -> { if ( this.ctrl.getModel().getNbFormes()-1 >= 0){
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--)  {
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					this.ctrl.getModel().getForme(i).Changercouleur(Color.BLUE); 
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}
			}
		}});

		couleur.get(3).setOnAction(e -> { if ( this.ctrl.getModel().getNbFormes()-1 >= 0){
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--)  {
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					this.ctrl.getModel().getForme(i).Changercouleur(Color.BLACK); 
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}
			}
		}});

		couleur.get(4).setOnAction(e -> { if ( this.ctrl.getModel().getNbFormes()-1 >= 0){
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--) {
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					this.ctrl.getModel().getForme(i).Changercouleur(Color.YELLOW); 
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}
			}
		}});

		couleur.get(5).setOnAction(e -> { if ( this.ctrl.getModel().getNbFormes()-1 >= 0){
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--) {
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					this.ctrl.getModel().getForme(i).Changercouleur(Color.GREEN); 
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}
			}
		}});

		//Menu modification
		ObservableList<MenuItem> modification = menu_app.getMenuBar().getMenus().get(4).getItems();
		//dernier plan 
		modification.get(0).setOnAction(e -> { 
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--){
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					FormeGeo F = this.ctrl.getModel().getFormes().get(i);
					this.ctrl.getModel().getFormes().remove(this.ctrl.getModel().getForme(i)); 
					Collections.reverse(this.ctrl.getModel().getFormes());
					this.ctrl.getModel().getFormes().add(F); 
					Collections.reverse(this.ctrl.getModel().getFormes());
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}}});

		//premier plan
		modification.get(1).setOnAction(e -> {ctrl.cvsCtrl.setDessins(false);
		for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--) {
			if ( this.ctrl.getModel().getFormes().get(i).isselect()){
				FormeGeo F = this.ctrl.getModel().getFormes().get(i);
				this.ctrl.getModel().getFormes().remove(this.ctrl.getModel().getForme(i)); 
				this.ctrl.getModel().getFormes().add(F); 
				this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
				break;
			}}});
		modification.get(2).setOnAction(e -> {
			ctrl.cvsCtrl.setDessins(false);
			for (int i=this.ctrl.getModel().getFormes().size()-1; i>=0;i--) {
				if ( this.ctrl.getModel().getFormes().get(i).isselect()){
					this.ctrl.getModel().getFormes().remove(this.ctrl.getModel().getForme(i)); 
					this.ctrl.getModel().getCtrl().getCvsCtrl().draw();
					break;
				}}});			

		modification.get(3).setOnAction( e -> {
			ctrl.cvsCtrl.setDessins(false);
			ctrl.cvsCtrl.Redimension(true);
		});
	}

	/** Getter Menu
	 * @return
	 */
	public MonMenu getMapp() {
		return menu_app;
	}
}
