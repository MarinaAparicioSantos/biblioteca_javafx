package gui.viewsandcontrollers.form;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.Notifications;
import gui.viewsandcontrollers.form.viewmodel.LibroConverter;
import gui.viewsandcontrollers.form.viewmodel.LibroViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import negocio.model.Genero;
import negocio.model.Libro;

public class LibroFormController implements Initializable {

	//Campo de texto donde se pone el titulo
	@FXML
	private TextField ttitulo;

	//Campo de texto donde se pone el isbn
	@FXML
	private TextField tisbn;
	
	//Campo de texto donde se pone el autor
	@FXML
	private TextField tautor;

	//Campo de texto donde se ponen las paginas
	@FXML
	private TextField tpaginas;

	
	//Objeto ChoiceBox en el que se elige una de las opciones
	//Pair, es una clase para manejar la asociación de clave a valor 
	//y es para devolver dos valores de un método. A la izquierda se representa la clave 
	//y a la derecha el valor.
	@FXML
	private ChoiceBox<Pair<String, String>> generoBox = new ChoiceBox<>();

	//Boton que guarda los cambios hechos en el formulario.
	@FXML
	private Button guardar;

	//Boton cancelar que hace que se cierre el formulario sin guardar ningun cambio.
	@FXML
	private Button cancelar;

	//Instancia que llama la clase LibroViewModel del paquete viewmodel.
	private LibroViewModel viewModel = new LibroViewModel();

	//String action al que luego se le asocio NUEVO y EDITAR.
	private String action;

	//String NUEVO y string EDITAR
	private static final String NUEVO = "new";
	private static final String EDITAR = "edit";

	
	//Constructor de LibroFormController sin parametros en el que action se le asocia NUEVO.
	public LibroFormController() {

		super();
		this.action = NUEVO;
	}
	//Constructor de LibroFormController con el parametro Libro libro en el que action se le asocia EDITAR y 
	//a viewModel se le asignael metodo toLibroVM de la clase LibroConverter.
	public LibroFormController(Libro libro) {
		super();
		this.action = EDITAR;
		viewModel = LibroConverter.toLibroVM(libro);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Hago que tisbn no se pueda editar estableciendo que setEditable sea falso.
		tisbn.setEditable(false);
		//Llama los metodos initcb y bindViewModel
        initchoiceBox();
        bindViewModel();

        //Si el valor del texto de tautor es nulo, el tisbn si se puede editar.
        if (tautor.getText() == null)
            tisbn.setEditable(true);
    }
	private void initchoiceBox() {
		//Arraylist que contiene un Pair, a la izquierda esta la clave y a la derecha el valor.
		List<Pair<String, String>> opciones = new ArrayList<>();
		
		//Al arraylist opciones se le añade un nuevo pair de Genero con el valor novela.
		opciones.add(new Pair(Genero.NOVELA.toString(), Genero.NOVELA.toString()));
		
		//Al arraylist opciones se le añade un nuevo pair de Genero con el valor ficcion.
		opciones.add(new Pair(Genero.FICCION.toString(), Genero.FICCION.toString()));
		
		//Al arraylist opciones se le añade un nuevo pair de Genero con el valor poesia.
		opciones.add(new Pair(Genero.POESIA.toString(), Genero.POESIA.toString()));

		
		
		//Converter que define el comportamiento de conversión entre cadenas y objetos.
		generoBox.setConverter(new StringConverter<Pair<String, String>>() {

			//Devuelve la clave de pair.
			@Override
			public String toString(Pair<String, String> pair) {
				return pair.getKey();
			}

			//No se usa
			@Override
			public Pair<String, String> fromString(String string) {
				return null;
			}
		});

		//Obtiene las propiedades del choicebox y añade todos los elementos del arrayList opciones.
		generoBox.getItems().addAll(opciones);

	}

	private void bindViewModel() {

		//Sincroniza el valor de las dos propiedades para que cada vez que una
		//de las propiedades cambie, el valor de la otra propiedad se actualice automáticamente
		ttitulo.textProperty().bindBidirectional(viewModel.tituloProperty());
		tisbn.textProperty().bindBidirectional(viewModel.isbnProperty());
		tautor.textProperty().bindBidirectional(viewModel.autorProperty());
		generoBox.valueProperty().bindBidirectional(viewModel.generoProperty());
		
		//Como paginas es de tipo Integer, convierte de numero a string.
		Bindings.bindBidirectional(tpaginas.textProperty(), viewModel.paginasProperty(), new NumberStringConverter());

	}


	//Accion del boton guardar
	@FXML
	void guardar(ActionEvent event) {

		
		boolean exito = false;
		switch (action) {
		//Si se selecciona nuevo, se llama al metodo create de la clase libroViewModel 
		//y se crea un nuevo libro y es asignado por exito.
		case NUEVO:
			exito = viewModel.create();
			break;

		case EDITAR:
		//Si se selecciona editar, se llama al metodo update de la clase libroViewModel 
		//y se actualiza un libroy es asignado por exito.
			exito = viewModel.update();
			break;
		}
		//Si exito es verdadero, se llama a la clase notifications y se cierra el 
		//formulario y se notifica que el catalogo ha sido actualizado.
		if (exito) {
			((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
			Notifications.publish(Notifications.CATALOGO_UPDATED);
		}
	}
	//Accion del boton cancelar.
	@FXML
	void cancelar(ActionEvent event) {
		//Se obtiene la fuente.
		Node source = (Node) event.getSource();
		//Se obtiene el escenario y la ventana.
		Stage stage = (Stage) source.getScene().getWindow();
		//El escenario se cierra.
		stage.close();

	}
}