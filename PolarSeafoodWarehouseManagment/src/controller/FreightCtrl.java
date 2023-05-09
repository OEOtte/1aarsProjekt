package controller;

import database.FreightDB;
import database.FreightDBIF;
import model.Freight;

public class FreightCtrl {
	
	public Freight findFreightByFreightNumber(String freightNumber) {
		FreightDBIF freightDBIF = new FreightDB();
		Freight res = freightDBIF.findFreightByFreightNumber(freightNumber);
		return res;
	}

}
