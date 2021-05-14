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

	@FXML
	private TextField ttitulo;

	@FXML
	private TextField tisbn;

	@FXML
	private TextField tautor;

	@FXML
	private TextField tpaginas;

	@FXML
	private ChoiceBox<Pair<String, String>> generoBox = new ChoiceBox<>();

	@FXML
	private Button guardar;

	@FXML
	private Button cancelar;

	private LibroViewModel viewModel = new LibroViewModel();

	private String action;

	private static final String NUEVO = "new";
	private static final String EDITAR = "edit";

	public LibroFormController() {

		super();
		this.action = NUEVO;
	}

	public LibroFormController(Libro libro) {
		super();
		this.action = EDITAR;
		viewModel = LibroConverter.toLibroVM(libro);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initcb();

		bindViewModel();
	}

	private void initcb() {
		List<Pair<String, String>> opcionesCombo = new ArrayList<>();
		opcionesCombo.add(new Pair(Genero.NOVELA.toString(), Genero.NOVELA.toString()));
		opcionesCombo.add(new Pair(Genero.FICCION.toString(), Genero.FICCION.toString()));
		opcionesCombo.add(new Pair(Genero.POESIA.toString(), Genero.POESIA.toString()));

		generoBox.setConverter(new StringConverter<Pair<String, String>>() {

			@Override
			public String toString(Pair<String, String> pair) {
				return pair.getKey();
			}

			@Override
			public Pair<String, String> fromString(String string) {
				return null;
			}
		});

		generoBox.getItems().addAll(opcionesCombo);

	}

	private void bindViewModel() {

		ttitulo.textProperty().bindBidirectional(viewModel.tituloProperty());
		tisbn.textProperty().bindBidirectional(viewModel.isbnProperty());
		tautor.textProperty().bindBidirectional(viewModel.autorProperty());
		generoBox.valueProperty().bindBidirectional(viewModel.generoProperty());
		Bindings.bindBidirectional(tpaginas.textProperty(), viewModel.paginasProperty(), new NumberStringConverter());

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

		boolean exito = false;
		switch (action) {
		case NUEVO:
			exito = viewModel.create();
			System.out.println("nuevo2");
			break;

		case EDITAR:
			exito = viewModel.update();
			System.out.println("editado");
			break;
		}
		if (exito) {
			((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
			Notifications.publish(Notifications.CATALOGO_UPDATED);
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

//    private String[] generos = {"NOVELA", "FICCION", "POESIA"};
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		generoBox.getItems().addAll(generos);
//		
//	}

}
