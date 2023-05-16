package database;

import controller.DataAccessException;
import model.Shipment;

public interface ShipmentDBIF {

	public void persistShipment(Shipment shipment) throws DataAccessException;

}
