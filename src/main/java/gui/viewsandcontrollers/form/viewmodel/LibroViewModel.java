package gui.viewsandcontrollers.form.viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Pair;
import negocio.BibliotecaService;
import negocio.impl.BibliotecaImpl;
import negocio.model.Genero;

public class LibroViewModel {
	
//	Al usar propiedades, si el valor de un atributo de propiedad en 
//	el modelo de datos cambia, la vista del elemento en TableView se actualiza 
//	automáticamente para que coincida con el valor del modelo de datos actualizado.
	
	//Convierte de String, Integer etc a SimpleStringProperty, SimpleIntegerProperty...
	
	private final StringProperty titulo = new SimpleStringProperty();
	private final StringProperty isbn = new SimpleStringProperty();
	private final StringProperty autor = new SimpleStringProperty();
	private final ObjectProperty<Pair<String, String>> genero = new SimpleObjectProperty<Pair<String, String>>();
	private final IntegerProperty paginas = new SimpleIntegerProperty();
	
	//Instancia de BibliotecaImpl
	private final BibliotecaService negocio = BibliotecaImpl.getInstance();
	
	//Constructor de LibroViewModel que establece los valores de genero.
	public LibroViewModel() {
		genero.setValue(new Pair<String,String>(Genero.NOVELA.toString(), Genero.NOVELA.toString() ));
	}
	
	//Constructor de LibroViewModel
	public LibroViewModel(String titulo, String isbn, String genero, String autor, int paginas) {
		setAutor(autor);
		setTitulo(titulo);
		setIsbn(isbn);
		setGenero(new Pair<String,String>(genero, genero));
		setPaginas(paginas);
	}

	//Getter de titulo
	public String getTitulo() {
		return titulo.get();
	}

	//StringProperty de titulo
	public StringProperty tituloProperty() {
		return titulo;
	}

	//Setter de titulo
	public void setTitulo(String titulo) {
		this.titulo.set(titulo);
	}
	
	//Getter de isbn
	public String getIsbn() {
		return isbn.get();
	}
	
	//StringProperty de isbn
	public StringProperty isbnProperty() {
		return isbn;
	}

	//Setter de isbn
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	
	//Getter de autor
	public String getAutor() {
		return autor.get();
	}
	
	//StringProperty de autor
	public StringProperty autorProperty() {
		return autor;
	}

	//Setter de autor
	public void setAutor(String autor) {
		this.autor.set(autor);
	}
	
	//Getter de genero
	public Pair<String, String> getGenero() {
		return genero.get();
	}
	
	//ObjectProperty de genero
	public ObjectProperty<Pair<String,String>> generoProperty() {
		return genero;
	}

	//Setter de genero
	public void setGenero(Pair<String, String> genero) {
		this.genero.set(genero);
	}
	
	//Getter de paginas
    public Integer getPaginas() {
		return paginas.get();
	}

    //IntegerProperty de paginas
	public IntegerProperty paginasProperty() {
		return paginas;
	}

	//Setter de paginas
	public void setPaginas(Integer paginas) {
		this.paginas.set(paginas);
	}
	
	//Devuelve el metodo nuevo de la clase BibliotecaImpl, convierte el libro de LibroViewModel a Libro.
	public boolean create() {
        return negocio.nuevo(LibroConverter.toLibro(this));
    }

	//Devuelve el metodo editar de la clase BibliotecaImpl, convierte el libro de LibroViewModel a Libro.
    public boolean update() {
        return negocio.editar(LibroConverter.toLibro(this));
    }
}