package Xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import negocio.BibliotecaService;
import negocio.impl.BibliotecaImpl;
import negocio.model.Genero;
import negocio.model.Libro;

public class XMLService {

	//Instancia de BibliotecaImpl
	private static BibliotecaService programa = BibliotecaImpl.getInstance();

	//Lista  listadeLibros
	private static List<Libro> listaDeLibros;

	public static void guardarXML(String nombre_archivo) {
		//Obtengo el catalogo de BibliotecaImpl
		listaDeLibros = programa.getCatalogo();

		//Creo los arraylist de cada una de las propiedades.
		ArrayList<String> titulo = new ArrayList<>();
		ArrayList<String> isbn = new ArrayList<>();
		ArrayList<String> genero = new ArrayList<>();
		ArrayList<String> autor = new ArrayList<>();
		ArrayList<String> paginas = new ArrayList<>();

		//Recorro el arraylist y obtengo las propiedades.
		for (int i = 0; i < listaDeLibros.size(); i++) {
			titulo.add(listaDeLibros.get(i).getTitulo());
			isbn.add(listaDeLibros.get(i).getIsbn());
			genero.add(listaDeLibros.get(i).getGenero().toString());
			autor.add(listaDeLibros.get(i).getAutor());
			//Convierte de Integer a String
			paginas.add(Integer.toString(listaDeLibros.get(i).getPaginas()));
		}
		try {
			//Llama al metodo generate
			generate(nombre_archivo, titulo, isbn, genero, autor, paginas);

		} catch (Exception e) {

		}
	}

	public static void cargarXML(String nombre_archivo) {
		try {
			//Instancia de documentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//Creo un nuevo creador de documento.
			DocumentBuilder builder = factory.newDocumentBuilder();

			//Obtengo el documento a partir del nombre del xml
			Document documento = builder.parse(new File(nombre_archivo + ".xml"));

			//Creo una NodeList de todos los elementos en el orden del 
			//documento con un nombre de etiqueta dado y están contenidos en el documento.
			NodeList cargaXML = documento.getElementsByTagName("libro");

			//Recorro la NodeList
			for (int i = 0; i < cargaXML.getLength(); i++) {

				//Obtengo el nodo de cada una de las propiedades.
				Node nodo = cargaXML.item(i);

				//Si el nodo es un elemento
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					//Transformo el nodo en elemento.
					Element element = (Element) nodo;

					//Pongo las etiquetas a las diferentes propiedades.
					String titulo = element.getElementsByTagName("titulo").item(0).getTextContent();
					
					String isbn = element.getElementsByTagName("isbn").item(0).getTextContent();

					Genero genero = Genero.getGenero(element.getElementsByTagName("genero").item(0).getTextContent());

					String autor = element.getElementsByTagName("autor").item(0).getTextContent();

					int paginas = Integer.parseInt(element.getElementsByTagName("paginas").item(0).getTextContent());

					//Creo un nuevo libro
					Libro libro = new Libro(titulo, isbn, genero, autor, paginas);
					//Invoco el metodo nuevo de la clase BibliotecaImpl para añadir un nuevo libro.
					programa.nuevo(libro);
				}
			}
			//Excepciones.
		} catch (ParserConfigurationException | SAXException | IOException ex) {
			System.out.println("error");
		}
	}
	
	private static void generate(String nombre_archivo, ArrayList<String> titulo, ArrayList<String> isbn,
			ArrayList<String> genero, ArrayList<String> autor, ArrayList<String> paginas) throws Exception {

		
		//Si todo esta vacio, hay error en el arraylist.
		if (nombre_archivo.isEmpty() || titulo.isEmpty() || isbn.isEmpty() || genero.size() != autor.size()) {
			System.out.println("Error en el arraylist.");
			return;
		}
		//Si no
		else {

			//Creo una nueva instancia de DocumentBuilderFactory.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//Creo un nuevo creador de documento.
			DocumentBuilder builder = factory.newDocumentBuilder();
			//Obtengo una instancia de DOMImplementation.
			DOMImplementation implementation = builder.getDOMImplementation();
			//Creo el documendo y le pongo el nombre que le haya dado el usuario.
			Document document = implementation.createDocument(null, nombre_archivo, null);
			//Establezco la version XML.
			document.setXmlVersion("1.0");

			//Obtento el documento y lo asocio con la raiz.
			Element raiz = document.getDocumentElement();
			//Recorro titulo
			for (int i = 0; i < titulo.size(); i++) {
				//Creo el elemento libro
				Element itemLibro = document.createElement("libro");

				//Creo el elemento titulo
				Element tituloNode = document.createElement("titulo");
				//Creo un nodo de texto con el texto de titulo.
				Text nodeTituloValue = document.createTextNode(titulo.get(i));
				//Agrego un nodo hijo al nodo existente.
				tituloNode.appendChild(nodeTituloValue);

				//Creo el elemento isbn
				Element isbnNode = document.createElement("isbn");
				//Creo un nodo de texto con el texto de isbn.
				Text nodeIsbnValue = document.createTextNode(isbn.get(i));
				//Agrego un nodo hijo al nodo existente.
				isbnNode.appendChild(nodeIsbnValue);

				//Creo el elemento genero
				Element generoNode = document.createElement("genero");
				//Creo un nodo de texto con el texto de genero.
				Text nodeGeneroValue = document.createTextNode(genero.get(i));
				//Agrego un nodo hijo al nodo existente.
				generoNode.appendChild(nodeGeneroValue);

				//Creo el elemento autor
				Element autorNode = document.createElement("autor");
				//Creo un nodo de texto con el texto de autor.
				Text nodeAutorValue = document.createTextNode(autor.get(i));
				//Agrego un nodo hijo al nodo existente.
				autorNode.appendChild(nodeAutorValue);

				//Creo el elemento paginas
				Element paginasNode = document.createElement("paginas");
				//Creo un nodo de texto con el texto de paginas.
				Text nodePaginasValue = document.createTextNode(paginas.get(i));
				//Agrego un nodo hijo al nodo existente.
				paginasNode.appendChild(nodePaginasValue);

				//Agrego los nodos hijos a las propiedades del libro.
				itemLibro.appendChild(tituloNode);
				itemLibro.appendChild(isbnNode);
				itemLibro.appendChild(generoNode);
				itemLibro.appendChild(autorNode);
				itemLibro.appendChild(paginasNode);

				//Agrego el nodo hijo a la raiz.
				raiz.appendChild(itemLibro);

				//Genero el documento XML.
				Source source = new DOMSource(document);
				//Indico donde lo quiero almacenar
				Result result = new StreamResult(new java.io.File(nombre_archivo + ".xml"));
				//Creo una instancia de Transformer
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				//Transformo el origen XML en un resultado
				transformer.transform(source, result);
			}
		}
	}


}