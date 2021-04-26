package gui.viewsandcontrollers.form;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import negocio.model.Genero;

public class LibroFormController implements Initializable{

    @FXML
    private TextField titulo;

    @FXML
    private TextField isbn;

    @FXML
    private TextField autor;

    @FXML
    private TextField paginas;

    @FXML
    private ChoiceBox<Genero> generoBox;
    
    Genero genero = (Genero) new Object();

    @FXML
    private Button guardar;

    @FXML
    void añadirTitulo(ActionEvent event) {

    }

    @FXML
    void añadirautor(ActionEvent event) {

    }

    @FXML
    void añadirisbn(ActionEvent event) {

    }

    @FXML
    void añadirpaginas(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		generoBox.getItems().getClass();
		
	}

}
