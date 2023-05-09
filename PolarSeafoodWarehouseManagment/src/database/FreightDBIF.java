package database;

import controller.DataAccessException;
import model.Freight;

public interface FreightDBIF {
	public Freight findFreightByFreightNumber(String freightNumber) throws DataAccessException;
}
