package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import controller.DataAccessException;
import database.*;
import model.Shipment;
import model.ShipmentLine;

public class ShipmentDB implements ShipmentDBIF {

	private static final String INSERT_SHIPMENT_TO_DATABASE_Q = "insert into Shipment(arrivalDate, arrivalLocation,dispatchDate, totalWeight, amountOfDifferentProduct, shipmentNo, freight_id) values(GETDATE(),?,?,?,?,?,?);";
	private PreparedStatement insertShipmentToDatabasePS;

	private static final String INSERT_SHIPMENTLINE_TO_DATABASE_Q = "insert into ShipmentLine(quantity, shipment_id, product_id) values (?, ?, ?, ?);";
	private PreparedStatement insertShipmentLineToDatabasePS;

	public ShipmentDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			insertShipmentToDatabasePS = connection.prepareStatement(INSERT_SHIPMENT_TO_DATABASE_Q,
					PreparedStatement.RETURN_GENERATED_KEYS);
			insertShipmentLineToDatabasePS = connection.prepareStatement(INSERT_SHIPMENTLINE_TO_DATABASE_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public void persistShipment(Shipment shipment) throws DataAccessException {
		int id = -1;
		try {
			insertShipmentToDatabasePS.setInt(1, shipment.getArrivalLocation().getId());
			insertShipmentToDatabasePS.setDate(2, Date.valueOf(shipment.getDisbatchDate()));
			insertShipmentToDatabasePS.setInt(3, shipment.getTotalWeight());
			insertShipmentToDatabasePS.setInt(4, shipment.getAmountOfDifferentProduct());
			insertShipmentToDatabasePS.setString(5, shipment.getShipmentNo());
			insertShipmentToDatabasePS.setInt(6, shipment.getFreight().getId());

			id = DBConnection.getInstance().executeInsertWithIdentity(insertShipmentToDatabasePS);

			persistShipmentLine(shipment, id);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}

	private void persistShipmentLine(Shipment shipment, int id) throws DataAccessException {
		List<ShipmentLine> shipmentLines = shipment.getShipmentLines();

		try {
			for (ShipmentLine sl : shipmentLines) {
				insertShipmentLineToDatabasePS.setInt(1, sl.getQuantity());
				insertShipmentLineToDatabasePS.setInt(2, id);
				insertShipmentLineToDatabasePS.setInt(3, sl.getProduct().getId()); 
				
				insertShipmentLineToDatabasePS.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}

}
