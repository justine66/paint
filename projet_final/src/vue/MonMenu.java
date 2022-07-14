package vue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


public class MonMenu {
    private MenuBar menuBar;
    /**
     * Constructeur sans parametres pour faire le menu
     */
    public MonMenu() {
        this.menuBar = this.newMenu();
    }
    /**
     * Methode qui permet la creation du menu
     * @return menuBar cree
     */
    private MenuBar newMenu() {
        MenuBar res = new MenuBar();

        //Creation du menu fichier
        Menu fichier	    = new Menu("Fichier");
        MenuItem nouveau 	= new MenuItem("Nouveau");
        MenuItem ouvrir 	= new MenuItem("Ouvrir");
        MenuItem sauvegarder= new MenuItem("Enregistrer en .png");
        MenuItem sauvegarder2= new MenuItem("Enregistrer en .dessin");
        MenuItem quitter 	= new MenuItem("Quitter");
        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        fichier.getItems().add(nouveau);
        fichier.getItems().add(ouvrir);
        fichier.getItems().add(sauvegarder);
        fichier.getItems().add(sauvegarder2);
        fichier.getItems().add(quitter);

        // menu forme
        Menu formes = new Menu("Formes");
        MenuItem ligne 		= new MenuItem("ligne");
        MenuItem rectangle  = new MenuItem("rectangle");
        MenuItem triangle 	= new MenuItem("triangle");
        MenuItem ellipse 	= new MenuItem("ellipse");
        formes.getItems().add(ligne);
        formes.getItems().add(rectangle);
        formes.getItems().add(triangle);
        formes.getItems().add(ellipse);

        // menu action
        Menu action = new Menu("Actions");
        MenuItem deplace = new MenuItem("Deplacer");
        MenuItem dessine = new MenuItem("Dessiner");
        action.getItems().add(deplace);      
        action.getItems().add(dessine);
       
        
        //Creation du menu Couleur
        Menu couleur = new Menu("Changer la couleur");
        
        MenuItem rose = new MenuItem("Rose");
        MenuItem rouge = new MenuItem("Rouge");
        MenuItem bleu = new MenuItem("Bleu");
        MenuItem noir = new MenuItem("Noir");
        MenuItem jaune = new MenuItem("Jaune");
        MenuItem vert = new MenuItem("Vert");
        couleur.getItems().add(rose);
        couleur.getItems().add(rouge);
        couleur.getItems().add(bleu);
        couleur.getItems().add(noir);
        couleur.getItems().add(jaune);
        couleur.getItems().add(vert);
        
        //Creation du menu modification
        Menu modification = new Menu("Modification");
        MenuItem arrPlan = new MenuItem("Mettre au premier plan");
        MenuItem devPlan = new MenuItem("Mettre en arriere plan");
        MenuItem suppr = new MenuItem("Supprimer"); 
        MenuItem redimension = new MenuItem("Redimensionner"); 
        modification.getItems().add(devPlan);
        modification.getItems().add(arrPlan);
        modification.getItems().add(suppr);
        modification.getItems().add(redimension);
        
        
        //Ajout au MenuBar
        res.getMenus().add(fichier);
        res.getMenus().add(formes);
        res.getMenus().add(action);
        res.getMenus().add(couleur);
        res.getMenus().add(modification);
       
        return res;
    }
    /**
     * Getter pour le menu
     * @return menuBar
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

}
