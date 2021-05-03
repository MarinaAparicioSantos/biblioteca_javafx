package negocio.impl;

import java.util.ArrayList;
import java.util.List;

import negocio.BibliotecaService;
import negocio.model.Libro;

public class BibliotecaImpl implements BibliotecaService {
	
	private static BibliotecaImpl program = null;
	
	private List <Libro> catalogo;
	
	private BibliotecaImpl() {
		catalogo = new ArrayList <Libro>();
	}

	public static BibliotecaImpl getInstance() {
		
		if(program==null) {
			program = new BibliotecaImpl();
		}
		
		return program;
	}
	
	@Override
	public boolean nuevo(Libro libro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(Libro libro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Libro libro) {
		// TODO Auto-generated method stub
		return false;
	}


}
