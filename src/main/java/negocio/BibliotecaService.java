package negocio;

import java.util.ArrayList;
import java.util.List;

import negocio.model.Libro;

public interface BibliotecaService {
	
	boolean nuevo(Libro libro);
	
	boolean editar (Libro libro);
	
	boolean eliminar (Libro libro);
	
	//ArrayList <Libro> catalogo = new ArrayList <Libro>();

	List <Libro> getCatalogo();
	
	
	boolean guardarXML(String archivo);

    boolean cargarXML(String archivo);
}
