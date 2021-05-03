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
	
	private final StringProperty titulo = new SimpleStringProperty();
	private final StringProperty isbn = new SimpleStringProperty();
	private final StringProperty autor = new SimpleStringProperty();
	private final ObjectProperty<Pair<String, String>> genero = new SimpleObjectProperty<Pair<String, String>>();
	private final IntegerProperty paginas = new SimpleIntegerProperty();
	
	private final BibliotecaService negocio = BibliotecaImpl.getInstance();
	
	public LibroViewModel() {
		genero.setValue(new Pair<String,String>(Genero.NOVELA.toString(), Genero.NOVELA.toString() ));
	}
	
	public LibroViewModel(String titulo, String isbn, String genero, String autor, int paginas) {
		setAutor(autor);
		setTitulo(titulo);
		setIsbn(isbn);
		setGenero(new Pair<String,String>(genero, genero));
		setPaginas(paginas);
	}

	public String getTitulo() {
		return titulo.get();
	}

	public StringProperty tituloProperty() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo.set(titulo);
	}

	public String getIsbn() {
		return isbn.get();
	}

	public StringProperty isbnProperty() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	
	public String getAutor() {
		return autor.get();
	}

	public StringProperty autorProperty() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor.set(autor);
	}
	
	
	
	
	public Pair<String, String> getGenero() {
		return genero.get();
	}

	public ObjectProperty<Pair<String,String>> generoProperty() {
		return genero;
	}

	public void setGenero(Pair<String, String> genero) {
		this.genero.set(genero);
	}

	
//	public Pair<String, String> getGenero() {
//        return genero.get();
//    }
//
//    public void setGenero(Pair<String, String> pair) {
//
//    }
//
//    public ObjectProperty<Pair<String, String>> generoProperty() {
//        return genero;
//    }
	

    
    public Integer getPaginas() {
		return paginas.get();
	}

	public IntegerProperty paginasProperty() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas.set(paginas);
	}

}
