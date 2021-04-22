package gui.viewsandcontrollers.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocio.model.Genero;
import negocio.model.Libro;

public class MainStageController {

	@FXML
	private Button nuevo;

	@FXML
	private Button editar;

	@FXML
	private Button eliminar;

	@FXML
	private Button salvar;

	@FXML
	private Button cargar;

	@FXML
	private TableView tabla;
	
	@FXML
    private TableColumn<Libro, String> titulo;
    @FXML
    private TableColumn<Libro, String> isbn;
    @FXML
    private TableColumn<Libro, Genero> genero;
    @FXML
    private TableColumn<Libro, String> autor;
    @FXML
    private TableColumn<Libro, Integer> paginas;
	

	public void initialize() {
	
		titulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("El principito"));
		isbn.setCellValueFactory(new PropertyValueFactory<Libro, String>("fgrsd"));
		//genero.setCellValueFactory(new PropertyValueFactory<Libro, String>("El principito"));
		autor.setCellValueFactory(new PropertyValueFactory<Libro, String>("alguien"));
		//paginas.setCellFactory(new PropertyValueFactory<Libro, String>("El principito"));

	}

	@FXML
	void cargar(ActionEvent event) {

	}

	@FXML
	void editarlistalibros(ActionEvent event) {

	}

	@FXML
	void eliminarlibro(ActionEvent event) {

	}

	@FXML
	void libronuevo(ActionEvent event) throws IOException {

		Stage dialog = new Stage();
		Node source = (Node) event.getSource();
		Stage parent = (Stage) source.getScene().getWindow();

		dialog.initOwner(parent);
		dialog.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("../form/LibroForm.fxml"));
		Scene scene = new Scene(root);
		dialog.setScene(scene);
		dialog.show();

	}

	@FXML
	void salvar(ActionEvent event) {

	}
	
	@FXML
	void anadirElementos(ActionEvent event) {
		

		

	}
}
