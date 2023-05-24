package controller;

import database.FreightDB;
import database.FreightDBIF;
import model.Freight;
/**
 * 
 * @author Gruppe 3
 */

/**
* This method finds a freight by its freight number.
*
* @param (freightNo) the freightNo of the freight.
* @return Returns the freight found by the freight number.
* @throws DataAccessException if there is an error accessing the data.
*/

public class FreightCtrl { 
	public Freight findFreightByFreightNumber(String freightNo) throws DataAccessException {
		FreightDBIF freightDBIF = new FreightDB();
		return freightDBIF.findFreightByFreightNumber(freightNo);
	}

}
