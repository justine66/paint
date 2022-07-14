package Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vue.Vue;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    	Vue view = new Vue();
        Scene scene = new Scene(view.getVue());
        primaryStage.setTitle("Dessin Ensemble");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
