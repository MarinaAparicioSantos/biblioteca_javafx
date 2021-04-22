package gui.viewsandcontrollers.main;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import negocio.model.Libro;

public class Tabla {
	
	 List<Libro> table = new ArrayList<Libro>();
	  ObservableList<Libro> observableList = FXCollections.observableList(table);
	  
	  
	  public void MainApp() {
			//		  List<Libro> table = new ArrayList<Libro>();
//			  ObservableList<Libro> observableList = FXCollections.observableList(table);
			observableList.add(new Libro("Hans", "Muster", null, null, null));
		}
		
		public ObservableList<Libro> getPersonData() {
			return observableList;
		}

}
