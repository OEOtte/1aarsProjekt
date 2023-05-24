package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import database.*;
import model.BoxedProduct;
import model.Lot;
import model.LotLine;
import model.Product;
import model.Supplier;
import model.Warehouse;

public class StorageDB implements StorageDBIF {

	private static final String FIND_ALL_Q = "select * from Lot";
	private PreparedStatement findAllPS;

	private static final String FIND_AVAILABLE_LOT_BY_PRIORITY_ASCENDING_Q = "select * from Lot where available = 1 and warehouse_id = ? order by lotNo asc;";
	private PreparedStatement findAvailableLotByPriorityAscendingPS;

	private static final String FIND_AVAILABLE_LOT_BY_PRIORITY_DECENDING_Q = "select * from Lot where available = 1 and warehouse_id = ? order by lotNo desc;";
	private PreparedStatement findAvailableLotByPriorityDecendingPS;

	private static final String FIND_LOT_BY_ID_AND_UPDATE_TO_UNAVAILABLE_Q = "update lot set available = 0 where id = ?;";
	private PreparedStatement findLotByIdAndUpdateToUnavailablePS;

	private static final String FIND_LOT_BY_ID_AND_UPDATE_TO_AVAILABLE_Q = "update lot set available = 1 where id = ?;";
	private PreparedStatement findLotByIdAndUpdateToAvailablePS;

	private static final String FIND_WAREHOUSE_BY_ID_Q = "select * from Warehouse where id = '?';";
	private PreparedStatement findWarehouseByIdPS;

	private static final String FIND_WAREHOUSE_BY_NAME_Q = "select * from Warehouse where name = ?;";
	private PreparedStatement findWarehouseByNamePS;

	private static final String INSERT_PRODUCT_ON_LOT_TO_DATABASE_Q = "insert into LotLine(quantity, expirationDate, lot_id, product_id) values (?,?,?,?);";
	private PreparedStatement insertProductOnLotToDatabasePS;

	private static final String FIND_ADDRESS_WITH_FULL_ASSOSIATION_FROM_ADDRESS_Q = "Select * from Address_view where id = ?";
	private PreparedStatement findAddressWithFullAssosiationFromAddressPS;

	private static final String FIND_PRODUCTS_IN_WAREHOUSE_BY_ID_Q = "SELECT LotLine.* FROM LotLine INNER JOIN Lot ON LotLine.lot_id = Lot.id WHERE LotLine.product_id = ? AND Lot.warehouse_id = ? ORDER BY LotLine.expirationDate DESC; ";
	private PreparedStatement findProductsInWarehouseByIdPS;

	private static final String FIND_LOT_FROM_ID_Q = "Select * from Lot where id = ?";
	private PreparedStatement findLotFromIdPS;

	private static final String REMOVE_LOT_LINE_FROM_DATBASE_WITH_IDS_Q = "Delete from LotLine where product_id = ? and lot_id = ?;";
	private PreparedStatement removeLotLineFromDatabaseWithIdsPS;

	private static final String UPDATE_QUANTITY_ON_LOTLINE_Q = "Update LotLine set quantity = ? where product_id = ? and lot_id = ?;";
	private PreparedStatement updateQuantityOnLotLinePS;

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
			findWarehouseByNamePS = connection.prepareStatement(FIND_WAREHOUSE_BY_NAME_Q);
			findLotByIdAndUpdateToUnavailablePS = connection
					.prepareStatement(FIND_LOT_BY_ID_AND_UPDATE_TO_UNAVAILABLE_Q);
			findLotByIdAndUpdateToAvailablePS = connection.prepareStatement(FIND_LOT_BY_ID_AND_UPDATE_TO_AVAILABLE_Q);
			insertProductOnLotToDatabasePS = connection.prepareStatement(INSERT_PRODUCT_ON_LOT_TO_DATABASE_Q);
			findAddressWithFullAssosiationFromAddressPS = connection
					.prepareStatement(FIND_ADDRESS_WITH_FULL_ASSOSIATION_FROM_ADDRESS_Q);
			findProductsInWarehouseByIdPS = connection.prepareStatement(FIND_PRODUCTS_IN_WAREHOUSE_BY_ID_Q);
			removeLotLineFromDatabaseWithIdsPS = connection.prepareStatement(REMOVE_LOT_LINE_FROM_DATBASE_WITH_IDS_Q);
			findLotFromIdPS = connection.prepareStatement(FIND_LOT_FROM_ID_Q);
			updateQuantityOnLotLinePS = connection.prepareStatement(UPDATE_QUANTITY_ON_LOTLINE_Q);

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	/**
	 * This method is used to find the first available lot according to priority of
	 * the product
	 * 
	 * @param priority
	 * @param warehouse
	 * @return Lot
	 */

	@Override
	public Lot findAvailableLotByPriorityInArrivalWarehouse(boolean priority, Warehouse warehouse)
			throws DataAccessException {
		Lot foundLot = null;
		ResultSet rs = null;
		try {
			if (priority) {
				findAvailableLotByPriorityAscendingPS.setInt(1, warehouse.getId());
				rs = findAvailableLotByPriorityAscendingPS.executeQuery();
			} else {
				findAvailableLotByPriorityDecendingPS.setInt(1, warehouse.getId());
				rs = findAvailableLotByPriorityDecendingPS.executeQuery();
			}
			if (rs.next()) {
				foundLot = buildLot(rs, warehouse);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

		return foundLot;
	}

	/**
	 * A method used to construct a lot from database information.
	 * 
	 * @param rs
	 * @param warehouse
	 * @return Lot
	 * @throws DataAccessException
	 */

	private Lot buildLot(ResultSet rs, Warehouse warehouse) throws DataAccessException {
		Lot res = null;
		try {
			res = new Lot(rs.getInt("id"), rs.getString("lotNo"), rs.getBoolean("available"), warehouse);

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}

	/*
	 * Not a used method
	 */
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

	/**
	 * A method similar to buildLot, it is instead used to create a warehouse,
	 * within which Lots are found.
	 * 
	 * @param rs
	 * @return Warehouse
	 * @throws DataAccessException
	 */

	private Warehouse buildWarehouse(ResultSet rs) throws DataAccessException {
		Warehouse warehouse = null;
		try {
//			int addressId = rs.getInt("address_id");
//			String address = ;

			warehouse = new Warehouse(rs.getInt("id"), rs.getString("name"), findAddress(rs.getInt("address_id")));
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return warehouse;
	}

	/**
	 * A method used for finding the address of a specific warehouse, using a
	 * warehouse ID and a prepared statement
	 */

	public String findAddress(int addressId) throws DataAccessException {
		String address = null;
		try {
			findAddressWithFullAssosiationFromAddressPS.setInt(1, addressId);
			ResultSet rs = findAddressWithFullAssosiationFromAddressPS.executeQuery();
			if (rs.next()) {
				address = buildAddress(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return address;
	}

	/**
	 * A method used for creating an address from database information
	 * 
	 * @param rs
	 * @return String
	 * @throws DataAccessException
	 */

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

	/**
	 * A method used for persisting the product on the lot within the database
	 * 
	 * @param product
	 * @param lot
	 * @param quantity
	 * @param date
	 * @return boolean
	 */
	@Override
	public boolean persistProductOnLot(Product product, Lot lot, int quantity, LocalDate date)
			throws DataAccessException {
		try {
			DBConnection.getInstance().startTransaction();

			insertProductOnLotToDatabasePS.setInt(1, quantity);
			insertProductOnLotToDatabasePS.setDate(2, Date.valueOf(date));
			insertProductOnLotToDatabasePS.setInt(3, lot.getId());
			insertProductOnLotToDatabasePS.setInt(4, product.getId());
			insertProductOnLotToDatabasePS.executeUpdate();

			setLotToUnavailable(lot);

			DBConnection.getInstance().commitTransaction();

		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		// TODO return correct boolean
		return false;
	}

	/**
	 * A method used for marking lots as unavailable, so that new products may not
	 * be placed on them
	 * 
	 * @param lot
	 * @throws DataAccessException
	 */

	private void setLotToUnavailable(Lot lot) throws DataAccessException {
		try {
			findLotByIdAndUpdateToUnavailablePS.setInt(1, lot.getId());
			findLotByIdAndUpdateToUnavailablePS.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

	}

	private void setLotToAvailable(Lot lot) throws DataAccessException {
		try {
			findLotByIdAndUpdateToAvailablePS.setInt(1, lot.getId());
			findLotByIdAndUpdateToAvailablePS.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

	}

	/**
	 * A method used for finding a warehouse using its name
	 * 
	 * @param warehouseName
	 * @return Warehouse
	 */
	@Override
	public Warehouse findWarehouseByName(String warehouseName) throws DataAccessException {
		Warehouse warehouse = null;
		try {
			findWarehouseByNamePS.setString(1, warehouseName);
			ResultSet rs = findWarehouseByNamePS.executeQuery();

			if (rs.next()) {
				warehouse = buildWarehouse(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

		return warehouse;
	}

	/**
	 * A method used when removing from the physical warehouse for finding the
	 * product in the database and preparing it for removal from the database
	 * 
	 * @param product
	 * @param quantity
	 * @param warehouseName
	 * @return List<LotLine>
	 */
	@Override
	public List<LotLine> findAvailableProductsInWarehouseAndPrepareToRemove(Product product, int quantity,
			String warehouseName) throws DataAccessException {
		List<LotLine> res = new ArrayList<LotLine>();
		Warehouse currentWarehouse = findWarehouseByName(warehouseName);
		LotLine foundLotLine = null;

		try {
			findProductsInWarehouseByIdPS.setInt(1, product.getId());
			findProductsInWarehouseByIdPS.setInt(2, currentWarehouse.getId());
			ResultSet rs = findProductsInWarehouseByIdPS.executeQuery();

			while (rs.next() && quantity != 0) {
				foundLotLine = buildLotLine(rs, product, currentWarehouse);
				if (quantity >= foundLotLine.getQuantity()) {
					foundLotLine.setRemovedQty(foundLotLine.getQuantity());
					quantity -= foundLotLine.getQuantity();
				} else if (quantity < foundLotLine.getQuantity()) {
					foundLotLine.setRemovedQty(quantity);
					quantity = 0;
				} // TODO make else statement that allows the method to work if no lotlines are
					// available
				res.add(foundLotLine);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	/**
	 * A method used for constructing LotLine objects from the database
	 * 
	 * @param rs
	 * @param product
	 * @param warehouse
	 * @return LotLine
	 * @throws DataAccessException
	 */
	private LotLine buildLotLine(ResultSet rs, Product product, Warehouse warehouse) throws DataAccessException {

		LotLine res = null;
		try {
			res = new LotLine(product, rs.getInt("quantity"), rs.getDate("expirationDate").toLocalDate(),
					findLotFromID(rs.getInt("lot_id"), warehouse));
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	/**
	 * A method used for finding Lots by their IDs
	 * 
	 * @param id
	 * @param warehouse
	 * @return Lot
	 * @throws DataAccessException
	 */
	private Lot findLotFromID(int id, Warehouse warehouse) throws DataAccessException {
		Lot res = null;

		try {
			findLotFromIdPS.setInt(1, id);
			ResultSet rs = findLotFromIdPS.executeQuery();

			if (rs.next()) {
				res = buildLot(rs, warehouse);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	/**
	 * A method used for removing a product that has previously been persisted on a
	 * lot, within the database
	 * 
	 * @param lotLines
	 * @return boolean
	 */
	@Override
	public boolean removalOfProductInWarehouse(List<LotLine> lotLines) throws DataAccessException {
		boolean res = true;
		try {
			DBConnection.getInstance().startTransaction();

			for (LotLine ll : lotLines) {
				if (ll.getQuantity() == ll.getRemovedQty()) {
					removeLotLineFromDatabaseWithIdsPS.setInt(1, ll.getProduct().getId());
					removeLotLineFromDatabaseWithIdsPS.setInt(2, ll.getLot().getId());
					removeLotLineFromDatabaseWithIdsPS.executeUpdate();
					setLotToAvailable(ll.getLot());

				} else {
					int leftQty = ll.getQuantity() - ll.getRemovedQty();
					updateQuantityOnLotLinePS.setInt(1, leftQty);
					updateQuantityOnLotLinePS.setInt(2, ll.getProduct().getId());
					updateQuantityOnLotLinePS.setInt(3, ll.getLot().getId());
					updateQuantityOnLotLinePS.executeUpdate();
				}
			}
			DBConnection.getInstance().commitTransaction();

		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			res = false;
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

}
