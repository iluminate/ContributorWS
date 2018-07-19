package com.everis.fallas.operacionales.contribuidor.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.everis.fallas.operacionales.contribuidor.bean.Contributor;
import com.everis.fallas.operacionales.contribuidor.util.ConexionMongo;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FallasDBImpl implements FallasDB {
	
	public List<Contributor> obtenerContribuidor(String name) throws MongoException{		
		List<Contributor> contribuidores = new ArrayList<Contributor>();
		ConexionMongo mongo = new ConexionMongo();
		MongoDatabase database = mongo.conectar();
		MongoCollection<Document> collection = database.getCollection("listacontribuidor");
		System.out.println("[FF] Parametro de busqueda: " + name);
		FindIterable<Document> docs = collection.find(Document.parse("{$or:[{nombre:/" + name + "/i},{codigo:\"" + name + "\"}]}"));
		for (Document doc : docs) {
			Contributor contribuidor = new Contributor();
			System.out.println("[FF] codigo >> " + doc.get("codigo").toString());
			System.out.println("[FF] nombre >> " + doc.get("nombre").toString());			
			contribuidor.setCodigo(doc.get("codigo").toString());
			contribuidor.setNombre(doc.get("nombre").toString());
			contribuidores.add(contribuidor);
		}
		return contribuidores;
	}
}
