package gui.viewsandcontrollers.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.Notifications;
import gui.viewsandcontrollers.form.LibroFormController;
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

	//Boton que crea un libro nuevo.
	@FXML
	private Button nuevo;

	//Boton que edita un libro.
	@FXML
	private Button editar;

	//Boton que elimina un libro.
	@FXML
	private Button eliminar;

	//Boton que crea un xml en el que se guarda la lista de libros
	@FXML
	private Button salvar;

	//Boton que carga un xml en el que hay libros guardados.
	@FXML
	private Button cargar;

	//Tabla de libros.
	@FXML
	private TableView <Libro> tabla;
	
	//Columna titulo de la tabla.
	@FXML
    private TableColumn<Libro, String> titulo;
	//Columna isbn de la tabla.
    @FXML
    private TableColumn<Libro, String> isbn;
    //Columna genero de la tabla.
    @FXML
    private TableColumn<Libro, Genero> genero;
    //Columna autor de la tabla.
    @FXML
    private TableColumn<Libro, String> autor;
    //Columna paginas de la tabla.
    @FXML
    private TableColumn<Libro, Integer> paginas;
    
    //private StringProperty texto = new SimpleStringProperty();
    
    //Instancia de BibliotecaImpl
    private BibliotecaImpl negocio = BibliotecaImpl.getInstance();
    
    //Creo un ObservableList
    ObservableList<Libro> lista;    
	

	public void initialize(URL location, ResourceBundle resources) {
		
		//Al observablelist lista le asocio el arraylist catalogo
		lista = FXCollections.observableArrayList(negocio.getCatalogo());

		
//		setValueFactory se establece para especificar cómo llenar 
//		todas las celdas dentro de una TableColumn. 
		
//		Creo un PropertyValueFactory predeterminado para extraer el valor de un 
//		elemento de fila de laTableView, utilizando el nombre de propiedad dado.
		this.titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		this.isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		this.genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		this.autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		this.paginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		
		//A la tabla le establezco las propiedades de la lista.
		tabla.setItems(lista);
		
		//Para no poder usar el boton editar ni eliminar si no hay nada seleccionado, 
		//defino el estado deshabilitado del nodo con disableProperty, y lo
		//uno a la tabla, que obtiene la propiedad de selection model, 
		//indico el elemento actualmente seleccionado en el modelo de selección
		//con selectedItemProperty y con isNull creo un BooleanBinding que es verdadero si la ObjectExpression es nula.
		editar.disableProperty().bind(tabla.getSelectionModel().selectedItemProperty().isNull());
		
		eliminar.disableProperty().bind(tabla.getSelectionModel().selectedItemProperty().isNull());
		
		//Llamo al metodo subscribe de la clase notifications para notificar que esta actualizado.
		Notifications.subscribe(Notifications.CATALOGO_UPDATED, this, this::actualizar);			
	}
		
		private void actualizar(String event) {
			//Asocio la lista con un observableArrayList, y le añado el catalogo.
			lista = FXCollections.observableArrayList(negocio.getCatalogo());
			//A la tabla le establezco las propiedades de la lista.
			
			tabla.setItems(lista);
			//Recreo y repoblo las celdas para actualizar la tabla.
			tabla.refresh();
		}

	@FXML
	void editarlistalibros(ActionEvent event) throws IOException {
		
		if(tabla.getSelectionModel().selectedIndexProperty().get()!=-1) {
			
			//El segundo parametro es el libro seleccionado
			abrirFormulario(event, tabla.getSelectionModel().getSelectedItem());
		}		
	}

	private void abrirFormulario(ActionEvent event, Libro libro) throws IOException {
		//Creo un nuevo escenario.
		Stage stage = new Stage();

		//Creo la instancia que lo asocia a LibroForm.fxml
        FXMLLoader loader = new FXMLLoader(LibroFormController.class.getResource("LibroForm.fxml"));

        //Creo LibroFormController y lo establezco a nulo.
        LibroFormController controller = null;

        //Si libro es distinto a nulo, creo un LibroFormController al que le añado un libro,
        // y si no, creo un LibroFormController vacio
        if (libro != null) 
            controller = new LibroFormController(libro);
        else
            controller = new LibroFormController();

        //Establezco el controller a LibroForm.fxml
        loader.setController(controller);
        //Le asocio LibroForm.fxml al padre.
        Parent root = loader.load();
        //Establezco una nueva escena.
        stage.setScene(new Scene(root));
        //Establezco el titutlo.
        stage.setTitle("Ventana Formulario");
        //Especifico la modalidad del escenario, que es una ventana modal.
        stage.initModality(Modality.WINDOW_MODAL);
        //Obtengo la fuente, el escenario y la ventana.
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        //Muestro la ventana y espero a que sea cerrada.
        stage.showAndWait();
    }
		
	
	@FXML
	void eliminarlibro(ActionEvent event) {
		
		//Creo una ventana de alerta
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		//Establezco el encabezamiento de la alerta.
		alerta.setHeaderText("Eliminar");
		//Establezco el titulo de la alerta
		alerta.setTitle("confirmacion");
		//Establezco el texto que se muestra en el área de contenido del cuadro de diálogo 
		//para preguntar si desea eliminar el libro.
		alerta.setContentText("¿Estas seguro de que quieres eliminar el libro?");
		//Muestra el dialogo y espera la respuesta del usuario.
		Optional<ButtonType> eliminar = alerta.showAndWait();
		
		//Si presiono el boton para eliminar
		if (eliminar.get() == ButtonType.OK) {
			//Llamo el metodo eliminar de BibliotecaImpl
			negocio.eliminar(tabla.getSelectionModel().getSelectedItem());
			//Lo actualizo
			actualizar("actualizacion");
		}
	}

	@FXML
	void libronuevo(ActionEvent event) throws IOException {
		//Llamo el metodo abrirFormulario y estableco el segundo parametro a nulo.
		abrirFormulario(event, null);
	}

	
	@FXML
	void cargar(ActionEvent event) {
		
		//Creo un textInput dialog que se muestra al usuario para preguntar si quiere cargar un xml
		TextInputDialog dialog = new TextInputDialog("backup.xml");
		//Establezco el encabezamiento del TextInputDialog.
		dialog.setHeaderText("Se va a cargar el catalogo de un fichero");
		//Establezco el titulo del TextInputDialog.
		dialog.setTitle("Cargar catalogo desde fichero");
		//Establezco el texto que se muestra en el área de contenido del cuadro de diálogo.
		dialog.setContentText("Introduce el nombre del fichero a cargar");
		//Muestra el dialogo y espera la respuesta del usuario.
		Optional<String> resultado = dialog.showAndWait();
		//Si el resultado es verdadero
		if(resultado.isPresent()) {
			//Se carga el xml que ha pedido el usuario llamando al metodo cargarXML
			//de BibliotecaImpl
			negocio.cargarXML(resultado.get());
			//Llamo al metodo subscribe de la clase notifications para notificar que esta actualizado.
            Notifications.publish(Notifications.CATALOGO_UPDATED);
            //Lo actualizo
			actualizar("Actualizacion");
		}
	}
	@FXML
	void salvar(ActionEvent event) {
		
		//Creo un textInput dialog que se muestra al usuario para preguntar si quiere guardar un xml
		TextInputDialog dialog = new TextInputDialog("backup.xml");
		//Establezco el encabezamiento del TextInputDialog.
		dialog.setHeaderText("Se va a guardar el catalogo en disco");
		//Establezco el titulo del TextInputDialog.
		dialog.setTitle("salvar a Fichero");
		//Establezco el texto que se muestra en el área de contenido del cuadro de diálogo.
		dialog.setContentText("Introduce el nombre del fichero a guardar");
		//Muestra el dialogo y espera la respuesta del usuario.
		Optional<String> resultado = dialog.showAndWait();
		//Si el resultado es verdadero
		if(resultado.isPresent()) {
			//Se guarda el xml que ha nombrado el usuario llamando al metodo guardarXML
			//de BibliotecaImpl
			negocio.guardarXML(resultado.get());
		}
	}	
}