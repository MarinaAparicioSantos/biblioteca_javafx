package negocio;

import java.util.ArrayList;

import negocio.model.Libro;

public interface BibliotecaService {
	
	boolean nuevo(Libro libro);
	
	boolean editar (Libro libro);
	
	boolean eliminar (Libro libro);
	
	//ArrayList <Libro> catalogo = new ArrayList <Libro>();

}
