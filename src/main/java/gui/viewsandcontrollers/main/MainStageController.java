package gui.viewsandcontrollers.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class MainStageController implements Initializable {

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
	private TableView <Libro> tabla;
	
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
    
    
	

	public void initialize(URL location, ResourceBundle resources) {
		
		this.titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		this.isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		this.genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		this.autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		this.paginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		
		tabla.setItems(libros);
	}
	
			ObservableList<Libro> libros= FXCollections.observableArrayList(
    		new Libro("El Principito", "43554", Genero.NOVELA, "alguien", 100),
    		new Libro("bajo la misma estrella", "jijiui", Genero.FICCION, "alguien2", 200)
    		);

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
	
}
