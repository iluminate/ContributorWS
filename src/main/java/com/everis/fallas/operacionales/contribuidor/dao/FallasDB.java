package com.everis.fallas.operacionales.contribuidor.dao;

import java.util.List;

import com.everis.fallas.operacionales.contribuidor.bean.Contributor;
import com.mongodb.MongoException;

public interface FallasDB {
	public List<Contributor> obtenerContribuidor (String name) throws MongoException;
}
