package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import controller.DataAccessException;
import database.*;
import model.Shipment;
import model.ShipmentLine;

public class ShipmentDB implements ShipmentDBIF {

	private static String INSERT_SHIPMENT_TO_DATABASE_Q = "insert into SaleOrder(date, total, deliveryStatus, customer_id) values (GETDATE(), ?, ?, ?);";
	private PreparedStatement insertShipmentToDatabasePS;

	private static String INSERT_SHIPMENTLINE_TO_DATABASE_Q = "insert into SaleOrderLine(saleOrder_id, product_id, quantity, subTotal) values (?, ?, ?, ?);";
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
			insertShipmentToDatabasePS.setString(1, "lol"); // TODO
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
				insertShipmentLineToDatabasePS.setInt(1, id);
				//TODO
				
				insertShipmentLineToDatabasePS.executeQuery();
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}

}
