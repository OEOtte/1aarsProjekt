package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DataAccessException;
import database.*;
import model.Lot;
import model.LotLine;
import model.Product;
import model.Warehouse;

public class StorageDB implements StorageDBIF {

	private static final String FIND_ALL_Q = "select * from Lot";
	private PreparedStatement findAllPS;

	private static final String FIND_AVAILABLE_LOT_BY_PRIORITY_ASCENDING_Q = "select * from Lot where priority = ?";
	private PreparedStatement findAvailableLotByPriorityAscendingPS;

	private static final String FIND_AVAILABLE_LOT_BY_PRIORITY_DECENDING_Q = "select * from Lot where priority = ?";
	private PreparedStatement findAvailableLotByPriorityDecendingPS;

	private static final String FIND_WAREHOUSE_BY_NAME_Q = "select * from Lot where priority = ?";
	private PreparedStatement findWarehouseByNamePS;
	
	private static final String INSERT_PRODUCT_ON_LOT_TO_DATABASE_Q = "";
	private PreparedStatement insertProductOnLotToDatabasePS;

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
			findWarehouseByNamePS = connection.prepareStatement(FIND_WAREHOUSE_BY_NAME_Q);
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
			res = new Lot(rs.getString("lotNumber"), new Warehouse(rs.getString("warehouse_name")));

			if (fullAssociation) {
				Warehouse warehouse = findWarehouseByName(res.getWarehouse().getName());
				res.setWarehouse(warehouse);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}

	private Warehouse findWarehouseByName(String name) throws DataAccessException {
		Warehouse foundWarehouse = null;
		try {
			findWarehouseByNamePS.setString(1, name);
			ResultSet rs = findWarehouseByNamePS.executeQuery();

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
			warehouse = new Warehouse(rs.getString("name"), rs.getString("address"));
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return warehouse;
	}

	@Override
	public boolean persistProductOnLot(Product product, LotLine lotLine) throws DataAccessException {
		try {
			insertProductOnLotToDatabasePS.setString(1,product.getBarcode());
			
			
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return false;
	}
}
