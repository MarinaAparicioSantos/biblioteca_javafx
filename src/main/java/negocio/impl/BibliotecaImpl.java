package negocio.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Xml.XMLService;
import negocio.BibliotecaService;
import negocio.model.Genero;
import negocio.model.Libro;

public class BibliotecaImpl implements BibliotecaService {
	
	private static BibliotecaImpl program = null;
	
	private static List <Libro> catalogo;
	
	private BibliotecaImpl() {
		catalogo = new ArrayList <Libro>();
		
	
	    	Libro primero = new Libro("Bajo la misma estrella", "123456789", Genero.NOVELA, "John Grenn", 283);
	    	Libro segundo = new Libro("Un mundo feliz", "987654321", Genero.FICCION, "Aldous Huxley", 178);
	    	Libro tercero = new Libro("Divina comedia ", "123234345", Genero.POESIA, "Dante Alighieri", 322);
	    		
	    	catalogo.add(primero);
	    	catalogo.add(segundo);
	    	catalogo.add(tercero);
	}

	public static BibliotecaImpl getInstance() {
		
		if(program==null) {
			program = new BibliotecaImpl();
		}
		
		return program;
	}
	
	@Override
	public boolean nuevo(Libro libro) {
		return catalogo.add(libro);
	}

	@Override
	public boolean editar(Libro libro) {
//		boolean editado = false;
//		
//		for (Libro l : catalogo) {
//			int pos = catalogo.indexOf(l);
//			catalogo.set(pos, libro);
//			editado = true;
//			
//			break;
//			
//		}
//		return editado;
		
		catalogo.remove(libro);
		return catalogo.add(libro);
		
		
	
	}

	@Override
	public boolean eliminar(Libro libro) {
		return catalogo.remove(libro);
	}

	@Override
	public List<Libro> getCatalogo() {
		return catalogo;
	}

	
	public boolean guardarXML(String archivo) {
        boolean retorno = false;
        XMLService.guardarXML(archivo);
        retorno = true;
        return retorno;
    }
	
	@Override
    public boolean cargarXML(String archivo) {
        boolean retorno = false;
        XMLService.cargarXML(archivo);
        retorno = true;
        return retorno;
    }

}
