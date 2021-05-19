package negocio.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Xml.XMLService;
import negocio.BibliotecaService;
import negocio.model.Genero;
import negocio.model.Libro;

public class BibliotecaImpl implements BibliotecaService {
	
	//Instancia de BibliotecaImpl
	private static BibliotecaImpl program = null;
	
	//Lista catalogo
	private static List <Libro> catalogo;
	
	
	private BibliotecaImpl() {
		
		
		//Crea un arraylist de catalogo
		catalogo = new ArrayList <Libro>();
		
		//Crea nuevos libros añadiendole sus propiedades
	    	Libro primero = new Libro("Bajo la misma estrella", "123456789", Genero.NOVELA, "John Grenn", 283);
	    	Libro segundo = new Libro("Un mundo feliz", "987654321", Genero.FICCION, "Aldous Huxley", 178);
	    	Libro tercero = new Libro("Divina comedia ", "123234345", Genero.POESIA, "Dante Alighieri", 322);
	    	
	    	//Añade los libros al catalogo
	    	catalogo.add(primero);
	    	catalogo.add(segundo);
	    	catalogo.add(tercero);
	}

	//Singleton para que haya una unica instancia
	public static BibliotecaImpl getInstance() {
		
		//Si BibliotecaImpl es nulo, crea un nuevo BibliotecaImpl
		if(program==null) {
			program = new BibliotecaImpl();
		}
		
		return program;
	}

	//Devuelve añadir un libro al catalogo.
	@Override
	public boolean nuevo(Libro libro) {
		return catalogo.add(libro);
	}

	//Elimina y vuelve a añadir un libro al catalogo, de esta manera lo edita
	@Override
	public boolean editar(Libro libro) {

		catalogo.remove(libro);
		return catalogo.add(libro);	
	}

	//Devuelve eliminar un libro del catalogo.
	@Override
	public boolean eliminar(Libro libro) {
		return catalogo.remove(libro);
	}

	//Obtiene el catalogo.
	@Override
	public List<Libro> getCatalogo() {
		return catalogo;
	}

	
	public boolean guardarXML(String archivo) {
        boolean retorno = false;
        //Llamo al metodo guardarXML de la clase XMLService.
        XMLService.guardarXML(archivo);
        //Si se guarda el xml, retorna a true.
        retorno = true;
        return retorno;
    }
	
	@Override
    public boolean cargarXML(String archivo) {
        boolean retorno = false;
      //Llamo al metodo cargarXML de la clase XMLService.
        XMLService.cargarXML(archivo);
      //Si se carga el xml, retorna a true.
        retorno = true;
        return retorno;
    }

}