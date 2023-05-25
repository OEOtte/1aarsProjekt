package database;

import controller.DataAccessException;
import model.Freight;

public interface FreightDBIF {
	
	public Freight findFreightByFreightNumber(String freightNo) throws DataAccessException;
}
