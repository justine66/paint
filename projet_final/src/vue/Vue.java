package vue;

import control.ControlCan;
import control.Control;
import control.MonMenuControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Model;


public class Vue {
    private Model mdl;
    private Control ctrl;
    private MonMenuControl menu;
    private ControlCan dessin;
    private VBox Vue;
    public final static int WIDTH = 1000;
    public final static int HEIGHT = 750;

    public Vue() {
        this.mdl = new Model(this);
        this.ctrl = new Control(this.mdl, this);
        this.mdl.setCtrl(this.ctrl);
        this.menu = this.ctrl.getMenuCtrl();
        this.dessin = this.ctrl.getCvsCtrl();
        menu.MesActions();
        this.Vue = new VBox(this.menu.getMapp().getMenuBar(), new HBox(this.dessin.getCanva().getdessin()));
    }
    
    /** Getter
     * @return menu
     */
    public MonMenuControl getMenu() {
        return menu;
    }
    
    /** Setter
     * @param menu
     */
    public void setMenu(MonMenuControl menu) {
        this.menu = menu;
    }
    
    /** Getter
     * @return dessin
     */
    public ControlCan getDessin() {
        return dessin;
    }
    
    /** Setter
     * @param dessin
     */
    public void setDessin(ControlCan dessin) {
        this.dessin = dessin;
    }
    
   /** getter
    * @return Vue
    */
    public VBox getVue() {
        return Vue;
    }
}
