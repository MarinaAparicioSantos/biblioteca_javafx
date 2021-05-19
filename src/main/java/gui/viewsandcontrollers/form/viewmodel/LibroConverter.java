package gui.viewsandcontrollers.form.viewmodel;

import negocio.model.Genero;
import negocio.model.Libro;

public class LibroConverter {

	
	//Crea un libro pasandolo desde LibroViewModel.
	public static Libro toLibro(LibroViewModel viewModel) {
		return new Libro(
				viewModel.getTitulo(), 
				viewModel.getIsbn(), 
				Genero.getGenero(viewModel.getGenero().getValue()),
				viewModel.getAutor(), 
				viewModel.getPaginas()
				);

	}
	
	//Crea un LibroViewModel pasandolo desde Libro.
	public static LibroViewModel toLibroVM(Libro libro) {
		return new LibroViewModel(
				libro.getTitulo(),
				libro.getIsbn(),
				libro.getGenero().toString(),
				libro.getAutor(),
				libro.getPaginas().intValue()
				);
	}
}