package fr.insa.antoine.max.devibat;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
        
    @Override
    public void start(Stage stage) {
        Scene scene1 = new Scene(new Principal(), 1200, 600);
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}