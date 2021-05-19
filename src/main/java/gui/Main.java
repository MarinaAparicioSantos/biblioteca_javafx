package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Main extends Application {
 

	  @Override
	    public void start(Stage stage) throws IOException {

	    	  //Crea una rama principal que la asocia con MainStage.fxml donde se crea la ventana principal
	    	  Parent root = FXMLLoader.load(getClass().getResource("viewsandcontrollers/main/MainStage.fxml"));
	    	  //Crea un objeto escena que se asocia con la rama principal y representa el contenido fisico.
	          Scene scene = new Scene(root);
	          //Establece el scene y es como una ventana que aloja la escena
	          stage.setScene(scene);
	          //Muestra el contenido de scene.
	          stage.show();
	    }

	    public static void main(String[] args) {
	        launch();
	    }
}