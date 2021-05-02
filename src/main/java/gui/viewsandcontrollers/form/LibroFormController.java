package gui.viewsandcontrollers.form;


import java.net.URL;
import java.util.ResourceBundle;

import gui.viewsandcontrollers.form.viewmodel.LibroConverter;
import gui.viewsandcontrollers.form.viewmodel.LibroViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import negocio.model.Genero;
import negocio.model.Libro;

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
    private ChoiceBox<String> generoBox;
    

    @FXML
    private Button guardar;
    
    private LibroViewModel viewModel = new LibroViewModel();
    
    private String action;
    
    private static final String NUEVO = "new";
    private static final String EDITAR = "edit";
    
    
    public LibroFormController() {
    	
    	super();
    	this.action=NUEVO;
    }
    
    public LibroFormController(Libro libro) {
    	this.action=EDITAR;
    	viewModel = LibroConverter.toLibroVM(libro);
    }

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
    
    private String[] generos = {"NOVELA", "FICCION", "POESIA"};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		generoBox.getItems().addAll(generos);
		
	}

}
