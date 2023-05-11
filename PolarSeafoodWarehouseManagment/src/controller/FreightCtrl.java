package controller;

import database.FreightDB;
import database.FreightDBIF;
import model.Freight;

public class FreightCtrl { 
	public Freight findFreightByFreightNumber(String freightNumber) throws DataAccessException {
		FreightDBIF freightDBIF = new FreightDB();
		return freightDBIF.findFreightByFreightNumber(freightNumber);
	}

}
