package gui.viewsandcontrollers.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocio.impl.BibliotecaImpl;
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
    
    private StringProperty texto = new SimpleStringProperty();
    
    private BibliotecaImpl programa = BibliotecaImpl.getInstance();
    
    ObservableList<Libro> lista;
	

	public void initialize(URL location, ResourceBundle resources) {
		
//		libros.getItems().addAll(programa.getLibros());
		
		this.titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		this.isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		this.genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		this.autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		this.paginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		
		tabla.setItems(libros);
		
//		editar.disabledProperty().bind(
//				tabla.getSelectionModel().selectedItemProperty().isNull()
//			);
//		
//		eliminar.disabledProperty().bind(
//				tabla.getSelectionModel().selectedItemProperty().isNull());
	}
	
			ObservableList<Libro> libros= FXCollections.observableArrayList(
    		new Libro("El Principito", "43554", Genero.NOVELA, "alguien", 100),
    		new Libro("bajo la misma estrella", "jijiui", Genero.FICCION, "alguien2", 200),
    		new Libro("Los inmortales", "jifergfgjiui", Genero.POESIA, "alguien3", 4324)
    		);
			
			
		private void actualizar(String event) {
			lista = FXCollections.observableArrayList(negocio.getLibros());
			tabla.setItems(lista);
			tabla.refresh();
		}

	@FXML
	void cargar(ActionEvent event) {
		
		TextInputDialog dialog = new TextInputDialog("backup.xml");
		dialog.setHeaderText("Se va a cargar el catalogo de un fichero");
		dialog.setTitle("Cargar catalogo desde fichero");
		dialog.setContentText("Introduce el nombre del fichero a cargar");
		Optional<String> resultado = dialog.showAndWait();
		if(resultado.isPresent()) {
			negocio.cargar(resultado.get());
			actualizar("Actualizacion manual");
		}
	}


	@FXML
	void editarlistalibros(ActionEvent event) throws IOException {
		
		if(tabla.getSelectionModel().selectedIndexProperty().get()!=-1) {
			
			abrirFormulario(event, tabla.getSelectionModel().getSelectedItem());
		}

	}

	
	private void abrirFormulario(ActionEvent event, Libro libro) throws IOException {
		

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
	void eliminarlibro(ActionEvent event) {
		
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setHeaderText("Eliminar");
		alerta.setTitle("confirmacion");
		alerta.setContentText("¿Estas seguro de que quieres eliminar el libro?");
		Optional<ButtonType> eliminar = alerta.showAndWait();
		
		if (eliminar.get() == ButtonType.OK) {
			negocio.eliminar(tabla.getSelectionModel().getSelectedItem());
			
			actualizar("actualizacion manual");
		}else {
			System.out.println("operacion cancelada por el usuario");
		}
			

	}

	@FXML
	void libronuevo(ActionEvent event) throws IOException {
		
		abrirFormulario(event, null);

	}

	@FXML
	void salvar(ActionEvent event) {
		
		TextInputDialog dialog = new TextInputDialog("backup.xml");
		dialog.setHeaderText("Se va a guardar el catalogo en disco");
		dialog.setTitle("salvar a Fichero");
		dialog.setContentText("Introduce el nombre del fichero a guardar");
		Optional<String> resultado = dialog.showAndWait();
		if(resultado.isPresent()) {
			System.out.println(negocio.salvar(resultado.get()));
		}
	}
	
}
