package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import controller.DataAccessException;
import model.Shipment;
import model.ShipmentLine;
import model.Staff;

public class ShipmentDB implements ShipmentDBIF {

	private static final String INSERT_SHIPMENT_TO_DATABASE_Q = "insert into Shipment(arrivalDate, arrivalLocation,dispatchDate, totalWeight, amountOfDifferentProduct, shipmentNo, freight_id) values(?,?,?,?,?,?,?);";
	private PreparedStatement insertShipmentToDatabasePS;

	private static final String INSERT_SHIPMENTLINE_TO_DATABASE_Q = "insert into ShipmentLine(quantity, shipment_id, product_id) values (?, ?, ?);";
	private PreparedStatement insertShipmentLineToDatabasePS;

	private static final String INSERT_SHIPMENT_AND_WAREHOUSE_TO_JOIN_TABLE = "Insert into ShipmentWarehouseTable(shipment_id,warehouse_id) values(?,?);";
	private PreparedStatement insertShipmentAndWarehouseToJoinTablePS;

	private static final String INSERT_STAFF_INTO_SHIPMENT_Q = "Insert into ShipmentStaffTable(shipment_id,staff_id) values(?,?);";
	private PreparedStatement insertStaffIntoShipmentPS;

	private static final String LATEST_SHIPMENT_NO_Q = "select shipmentNo from Shipment order by shipmentNo desc;";
	private PreparedStatement latestShipmentNoPS;

	public ShipmentDB() throws DataAccessException {
		init();
	}
	
	/**
	 * initialized ShipmentDB by creating a connection to the database and setting up the prepared statements
	 * @throws DataAccessException
	 */

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			insertShipmentToDatabasePS = connection.prepareStatement(INSERT_SHIPMENT_TO_DATABASE_Q,
					PreparedStatement.RETURN_GENERATED_KEYS);
			insertShipmentLineToDatabasePS = connection.prepareStatement(INSERT_SHIPMENTLINE_TO_DATABASE_Q);
			insertShipmentAndWarehouseToJoinTablePS = connection
					.prepareStatement(INSERT_SHIPMENT_AND_WAREHOUSE_TO_JOIN_TABLE);
			insertStaffIntoShipmentPS = connection.prepareStatement(INSERT_STAFF_INTO_SHIPMENT_Q);
			latestShipmentNoPS = connection.prepareStatement(LATEST_SHIPMENT_NO_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	/**
	 * persistShipment is used to persist the shipment within the database so that it might be found again later.
	 * It does this, using a prepared statement and a transaction.
	 * @param shipment an instance if Shipment that was put together previously.
	 */

	@Override
	public void persistShipment(Shipment shipment) throws DataAccessException {
		try {
			DBConnection.getInstance().startTransaction();

			int shipmentNo = getLatestShipmentNoAndIncreaseByOne();

			insertShipmentToDatabasePS.setDate(1, Date.valueOf(shipment.getArrivalDate()));
			insertShipmentToDatabasePS.setString(2, shipment.getArrivalLocation().getName());
			insertShipmentToDatabasePS.setDate(3, Date.valueOf(shipment.getDispatchDate()));
			insertShipmentToDatabasePS.setInt(4, shipment.getTotalWeight());
			insertShipmentToDatabasePS.setInt(5, shipment.getAmountOfDifferentProduct());
			insertShipmentToDatabasePS.setInt(6, shipmentNo);
			insertShipmentToDatabasePS.setInt(7, shipment.getFreight().getId());
			int id = DBConnection.getInstance().executeInsertWithIdentity(insertShipmentToDatabasePS);

			persistShipmentLine(shipment, id);
			persistShipmentInWarehouse(shipment, id);
			persistStaffOnShipment(id, shipment.getStaffOnShipment());

			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}
	
	/**
	 * getLatestShipmentNoAndIncreaseByOne is used when creating a new shipment, to ensure that the shipment number is always unique.
	 * @return int
	 */

	private int getLatestShipmentNoAndIncreaseByOne() throws DataAccessException {
		int foundShipmentNo = 1;
		try {
			ResultSet rs = latestShipmentNoPS.executeQuery();
			if (rs.next()) {
				foundShipmentNo += rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return foundShipmentNo;
	}
	
	/**
	 * getLatestShipmentNo simply finds the latest shipment number that was persisted within the database
	 * @return int
	 */

	@Override
	public int getLatestShipmentNo() throws DataAccessException {
		int foundShipmentNo = 0;
		try {
			ResultSet rs = latestShipmentNoPS.executeQuery();
			if (rs.next()) {
				foundShipmentNo = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return foundShipmentNo;
	}
	
	/**
	 * persistStaffOnShipment does exactly what it says, it persists the assigned staff to the shipment
	 * so that it might be found later.
	 * @param id
	 * @param staffOnShipment
	 * @throws DataAccessException
	 */

	private void persistStaffOnShipment(int id, List<Staff> staffOnShipment) throws DataAccessException {
		try {
			StaffDBIF staffDBIF = new StaffDB();
			for (Staff s : staffOnShipment) {
				insertStaffIntoShipmentPS.setInt(1, id);
				insertStaffIntoShipmentPS.setInt(2, staffDBIF.findStaffIdByNo(s.getStaffNo()));
				insertStaffIntoShipmentPS.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

	}
	
	/**
	 * persistShipmentInWarehouse is used to assign which warehouse a shipment has arrived at
	 * so that the shipment can be found through the warehouse, and the warehouse can be found through the shipment
	 * @param shipment
	 * @param id
	 * @throws DataAccessException
	 */

	private void persistShipmentInWarehouse(Shipment shipment, int id) throws DataAccessException {
		try {
			insertShipmentAndWarehouseToJoinTablePS.setInt(1, id);
			insertShipmentAndWarehouseToJoinTablePS.setInt(2, shipment.getArrivalLocation().getId());
			insertShipmentAndWarehouseToJoinTablePS.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}
	
	/**
	 * persistShipmentLine is used to persist the shipmentLine within the database.
	 * @param shipment
	 * @param id
	 * @throws DataAccessException
	 */

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
