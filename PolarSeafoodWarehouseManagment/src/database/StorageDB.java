package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import controller.DataAccessException;
import database.*;
import model.Lot;
import model.LotLine;
import model.Product;
import model.Warehouse;

public class StorageDB implements StorageDBIF {

	private static final String FIND_ALL_Q = "select * from Lot";
	private PreparedStatement findAllPS;

	private static final String FIND_AVAILABLE_LOT_BY_PRIORITY_ASCENDING_Q = "select * from Lot where available = 1 order by lotNo asc;";
	private PreparedStatement findAvailableLotByPriorityAscendingPS;

	private static final String FIND_AVAILABLE_LOT_BY_PRIORITY_DECENDING_Q = "select * from Lot where available = 1 order by lotNo desc;";
	private PreparedStatement findAvailableLotByPriorityDecendingPS;

	private static final String FIND_LOT_BY_ID_AND_UPDATE_TO_UNAVAILABLE_Q = "update lot set available = 0 where id = ?;";
	private PreparedStatement findLotByIdAndUpdateToUnavailablePS;

	private static final String FIND_WAREHOUSE_BY_ID_Q = "select * from Warehouse where id = '?';";
	private PreparedStatement findWarehouseByIdPS;

	private static final String INSERT_PRODUCT_ON_LOT_TO_DATABASE_Q = "insert into LotLine(quantity, expirationDate, lot_id, product_id) values (?,?,?,?);";
	private PreparedStatement insertProductOnLotToDatabasePS;

	private static final String FIND_ADDRESS_WITH_FULL_ASSOSIATION_FROM_ID_Q = "SELECT a.address, c.zipcode, c.name AS city_name, co.name AS country_name\r\n"
			+ "FROM Address a\r\n" + "INNER JOIN City c ON a.city_id = c.id\r\n"
			+ "INNER JOIN Country co ON c.country_id = co.id\r\n" + "WHERE a.id = ?;";
	private PreparedStatement findAddressWithFullAssosiationFromIdPS;

	public StorageDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
			findAvailableLotByPriorityAscendingPS = connection
					.prepareStatement(FIND_AVAILABLE_LOT_BY_PRIORITY_ASCENDING_Q);
			findAvailableLotByPriorityDecendingPS = connection
					.prepareStatement(FIND_AVAILABLE_LOT_BY_PRIORITY_DECENDING_Q);
			findWarehouseByIdPS = connection.prepareStatement(FIND_WAREHOUSE_BY_ID_Q);
			findLotByIdAndUpdateToUnavailablePS = connection
					.prepareStatement(FIND_LOT_BY_ID_AND_UPDATE_TO_UNAVAILABLE_Q);
			insertProductOnLotToDatabasePS = connection.prepareStatement(INSERT_PRODUCT_ON_LOT_TO_DATABASE_Q);

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public Lot findAvailableLotByPriority(boolean priority, boolean fullAssociation) throws DataAccessException {
		Lot foundLot = null;
		ResultSet rs = null;
		try {
			if (priority) {
				rs = findAvailableLotByPriorityAscendingPS.executeQuery();
			} else {
				rs = findAvailableLotByPriorityDecendingPS.executeQuery();
			}
			if (rs.next()) {
				foundLot = buildObject(rs, fullAssociation);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

		return foundLot;
	}

	private Lot buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Lot res = null;
		try {
			res = new Lot(rs.getInt("id"), rs.getString("lotNo"), rs.getBoolean("available"),
					new Warehouse(rs.getInt("warehouse_id")));

			if (fullAssociation) {
				Warehouse warehouse = findWarehouseById(res.getWarehouse().getId());
				res.setWarehouse(warehouse);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}

	private Warehouse findWarehouseById(int id) throws DataAccessException {
		Warehouse foundWarehouse = null;
		try {
			findWarehouseByIdPS.setInt(1, id);
			ResultSet rs = findWarehouseByIdPS.executeQuery();

			if (rs.next()) {
				foundWarehouse = buildWarehouse(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return foundWarehouse;
	}

	private Warehouse buildWarehouse(ResultSet rs) throws DataAccessException {
		Warehouse warehouse = null;
		try {
			int addressId = rs.getInt("address_id");
			String address = findAddress(addressId);

			warehouse = new Warehouse(rs.getInt("id"), rs.getString("name"), address);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return warehouse;
	}

	private String findAddress(int addressId) throws DataAccessException {
		String address = null;
		try {
			findAddressWithFullAssosiationFromIdPS.setInt(1, addressId);
			ResultSet rs = findAddressWithFullAssosiationFromIdPS.executeQuery();

			address = buildAddress(rs);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return address;
	}

	private String buildAddress(ResultSet rs) throws DataAccessException {
		String res = null;
		try {
			res = rs.getString("address") + ", " + rs.getString("zipcode") + " " + rs.getString("city_name") + ", "
					+ rs.getString("country_name");
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	@Override
	public boolean persistProductOnLot(Product product, Lot lot, int quantity, LocalDate date)
			throws DataAccessException {
		try {
			insertProductOnLotToDatabasePS.setInt(1, quantity);
			insertProductOnLotToDatabasePS.setDate(2, Date.valueOf(date));
			insertProductOnLotToDatabasePS.setInt(3, lot.getId());
			insertProductOnLotToDatabasePS.setInt(4, product.getId());

			insertProductOnLotToDatabasePS.executeUpdate();

			setLotToUnavailable(lot);

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		// TODO return correct boolean
		return false;
	}

	private void setLotToUnavailable(Lot lot) throws DataAccessException {
		try {
			findLotByIdAndUpdateToUnavailablePS.setInt(1, lot.getId());
			findLotByIdAndUpdateToUnavailablePS.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

	}
}
