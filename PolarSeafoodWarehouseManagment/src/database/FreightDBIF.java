package database;

import model.Freight;

public interface FreightDBIF {
	public Freight findFreightByFreightNumber(String freightNumber);
}
