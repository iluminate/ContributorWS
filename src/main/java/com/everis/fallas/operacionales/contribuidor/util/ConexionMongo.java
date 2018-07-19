package com.everis.fallas.operacionales.contribuidor.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
	private MongoClient mongoClient;

	public MongoDatabase conectar() throws MongoException{
		mongoClient = new MongoClient( "192.168.100.169" , 27017 );
		MongoDatabase database = mongoClient.getDatabase("fallas");
		return database;
	}
}
