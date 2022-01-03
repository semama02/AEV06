package es.florida.AEV06;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.event.ClusterListenerAdapter;

import static com.mongodb.client.model.Filters.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.DimensionUIResource;


public class AEV {
	
	//	Métode: recuperarTots
	//	Descripció: posar en una llista tots els llibres de la base de dades
	//	Parametres d'entrada: MongoCollection<Document> coleccion
	//	Parametres d'eixida: els llibres
	public static ArrayList<Llibre> recuperTots(MongoCollection<Document> coleccion) throws JSONException{
		//CRUD operations
		ArrayList <Llibre>listaLlibres = new ArrayList<Llibre>();
		JSONObject obj = new JSONObject();
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			obj = new JSONObject(cursor.next().toJson());
			Llibre llib = new Llibre(obj.getString("Id"), obj.getString("Titol"), obj.getString("Autor"), obj.getString("Any_naixement"), obj.getString("Any_publicacio"), obj.getString("Editorial"), obj.getString("Nombre_pagines"));
			listaLlibres.add(llib);
		}
		
		return listaLlibres;		
	}
	
	//	Métode: recuperarLlibre
	//	Descripció: mos torna un objecte llibre
	//	Parametres d'entrada: int identificador, MongoCollection<Document> coleccion
	//	Parametres d'eixida: un llibre nou
	public static Llibre recuperarLlibre(int identificador,MongoCollection<Document> coleccion) throws JSONException{
		//CRUD operations
		Llibre llibreNou = null;
		ArrayList <Llibre>listaLlibres = recuperTots(coleccion);
		for (int i = 0; i < listaLlibres.size(); i++) {
			String pepe = (listaLlibres.get(i).getIdentificador());
			if (identificador == Integer.parseInt(pepe)) {
				llibreNou = listaLlibres.get(i);
			}
		}
		
		return llibreNou;		
	}
	
	//	Métode: crearLlibre
	//	Descripció: a partir de la llista creada anyadim un llibre nou
	//	Parametres d'entrada: un llibre, MongoCollection<Document> coleccion
	//	Parametres d'eixida: el identificador del llibre
	public static String crearLlibre(Llibre llibr, MongoCollection<Document> coleccion) {
		
		Document doc = new Document();
		doc.append("Id", llibr.getIdentificador());
		doc.append("Titol", llibr.getTitol());
		doc.append("Autor", llibr.getAutor());
		doc.append("Any_naixement", llibr.getAny_naixement());
		doc.append("Any_publicacio", llibr.getAny_publicacio());
		doc.append("Editorial", llibr.getEditorial());
		doc.append("Nombre_pagines", llibr.getNum_pags());
		
		coleccion.insertOne(doc);
		return (String) llibr.getIdentificador();
	}

	//	Métode: actualitzaLlibre
	//	Descripció: a partir de la llista modifiquem un llibre
	//	Parametres d'entrada: identificador, MongoCollection<Document> coleccion
	//	Parametres d'eixida: ningún
	public static void actualitzaLlibre(int identificador, MongoCollection<Document> coleccion) throws JSONException {
				
		Scanner in = new Scanner(System.in);
				
		System.out.println("Dime el titol: ");
		String titol = in.nextLine();
		if (titol == "") {
			titol = recuperarLlibre(identificador, coleccion).getTitol();
		}
		coleccion.updateOne(eq("Titol", recuperarLlibre(identificador, coleccion).getTitol()), new Document("$set", new Document("Titol", titol)));
		
		System.out.println("Dime el autor: ");
		String autor = in.nextLine();
		if (autor == "") {
			autor = recuperarLlibre(identificador, coleccion).getAutor();
		}
		coleccion.updateOne(eq("Autor", recuperarLlibre(identificador, coleccion).getAutor()), new Document("$set", new Document("Autor", autor)));
		
		System.out.println("Dime el any_naixement: ");
		String any_naixement = in.nextLine();
		if (any_naixement == "") {
			any_naixement = recuperarLlibre(identificador, coleccion).getAny_naixement();
		}
		coleccion.updateOne(eq("Any_naixement", recuperarLlibre(identificador, coleccion).getAny_naixement()), new Document("$set", new Document("Any_naixement", any_naixement)));
		
		System.out.println("Dime el any_publicacio: ");
		String any_publicacio = in.nextLine();
		if (any_publicacio == "") {
			any_publicacio = recuperarLlibre(identificador, coleccion).getAny_publicacio();
		}
		coleccion.updateOne(eq("Any_publicacio", recuperarLlibre(identificador, coleccion).getAny_publicacio()), new Document("$set", new Document("Any_publicacio", any_publicacio)));
		
		System.out.println("Dime el editorial: ");
		String editorial = in.nextLine();
		if (editorial == "") {
			editorial = recuperarLlibre(identificador, coleccion).getEditorial();
		}
		coleccion.updateOne(eq("Editorial", recuperarLlibre(identificador, coleccion).getEditorial()), new Document("$set", new Document("Editorial", editorial)));
		
		System.out.println("Dime el nombre_pagines: ");
		String nombre_pagines = in.nextLine();
		if (nombre_pagines == "") {
			nombre_pagines = recuperarLlibre(identificador, coleccion).getNum_pags();
		}
		coleccion.updateOne(eq("Nombre_pagines", recuperarLlibre(identificador, coleccion).getNum_pags()), new Document("$set", new Document("Nombre_pagines", nombre_pagines)));
	}

	//	Métode: borrarLlibre
	//	Descripció: a partir de la llista borrem un llibre
	//	Parametres d'entrada: identificador, MongoCollection<Document> coleccion
	//	Parametres d'eixida: ningún
	public static void borrarLlibre(int identificador, MongoCollection<Document> coleccion) throws JSONException {
		
		String id = recuperarLlibre(identificador, coleccion).getIdentificador();
		coleccion.deleteOne(eq("Id", id));
		
	}
	
	//	Métode: main
	//	Descripció: menu per a vore que vol fer l'usuari
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: ningún
	public static void main(String[] args) throws JSONException{ 
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Llibres");

		System.out.println("1. Mostrar tots els títols de la biblioteca");
		System.out.println("2. Mostrar informació detallada d'un llibre");
		System.out.println("3. Crear nou llibre");
		System.out.println("4. Actualitzar llibre");
		System.out.println("5. Borrar llibre");
		System.out.println("6. Tanca la biblioteca");
		Scanner in = new Scanner(System.in);
		String opcion = in.nextLine();
		
		switch(opcion) {
		case "1":
			for (int i = 0; i < recuperTots(database.getCollection("Llibres")).size(); i++) {
				System.out.println("Identificador: " + recuperTots(database.getCollection("Llibres")).get(i).getIdentificador() + " --- Titol: " + recuperTots(database.getCollection("Llibres")).get(i).getTitol() );
			}
			mongoClient.close();
			System.exit(0);
			
		case "2":
			in = new Scanner(System.in);
			System.out.print("ID del llibre: ");
			String opcion2 = in.nextLine();
			int opcion3 = Integer.parseInt(opcion2); 
			if (recuperarLlibre(opcion3, database.getCollection("Llibres")) == null) {
				System.out.println("ERROR");
			}else {
				System.out.println("|---------------------------------|");
				System.out.println("  Títol: "+ recuperarLlibre(opcion3, database.getCollection("Llibres")).getTitol());
				System.out.println("  Autor: "+ recuperarLlibre(opcion3, database.getCollection("Llibres")).getAutor());
				System.out.println("  Any de naixement: "+ recuperarLlibre(opcion3, database.getCollection("Llibres")).getAny_naixement());
				System.out.println("  Any de publicacio: "+ recuperarLlibre(opcion3, database.getCollection("Llibres")).getAny_publicacio());
				System.out.println("  Editorial: "+ recuperarLlibre(opcion3, database.getCollection("Llibres")).getEditorial());
				System.out.println("  Número de pàgines: "+ recuperarLlibre(opcion3, database.getCollection("Llibres")).getNum_pags());
			}
			System.exit(0);
			mongoClient.close();
			
		case "3":
			in = new Scanner(System.in);
			System.out.println("Dime el identificador: ");
			String identificador = in.nextLine();
			System.out.println("Dime el titol: ");
			String titol = in.nextLine();
			System.out.println("Dime el autor: ");
			String autor = in.nextLine();
			System.out.println("Dime el any de naixement: ");
			String any_publicacio = in.nextLine();
			System.out.println("Dime el any de publicacio: ");
			String any_naixement= in.nextLine();
			System.out.println("Dime la editorial: ");
			String editorial = in.nextLine();
			System.out.println("Dime el numero de pagines: ");
			String pagines = in.nextLine();
			
			Llibre nouLlibre = new Llibre(identificador, titol, autor, any_naixement, any_publicacio, editorial, pagines);
			crearLlibre(nouLlibre, database.getCollection("Llibres"));
			mongoClient.close();
			System.exit(0);
			
		case "4":
			System.out.println("Dime el ID del llibre a canviar: ");
			Integer idCanviar = in.nextInt();
			actualitzaLlibre(idCanviar, database.getCollection("Llibres")); 
			mongoClient.close();
			System.exit(0);
			
		case "5":
			System.out.println("Dime el ID del llibre a borrar: ");
			Integer idBorrar = in.nextInt();
			borrarLlibre(idBorrar, database.getCollection("Llibres"));
			mongoClient.close();
			System.exit(0);
			
		case "6":
			System.exit(0);
	}
	}

}
