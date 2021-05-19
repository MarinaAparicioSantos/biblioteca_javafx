package negocio;

import java.util.List;

import negocio.model.Libro;

public interface BibliotecaService {
	
	/**
	 * nuevo()
	 * @param libro
	 * @return boolean
	 */
	boolean nuevo(Libro libro);
	
	/**
	 * editar()
	 * @param libro
	 * @return boolran
	 */
	boolean editar (Libro libro);
	
	/**
	 * eliminar()
	 * @param libro
	 * @return boolean
	 */
	boolean eliminar (Libro libro);
	
	/**
	 * getCatalogo()
	 * @return List
	 */
	List <Libro> getCatalogo();
	
	/**
	 * guardarXML()
	 * @param archivo
	 * @return boolean
	 */
	boolean guardarXML(String archivo);

	
	/**
	 * cargarXML()
	 * @param archivo
	 * @return boolean
	 */
    boolean cargarXML(String archivo);
}