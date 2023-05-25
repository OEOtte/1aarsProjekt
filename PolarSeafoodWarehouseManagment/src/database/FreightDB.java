package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DataAccessException;
import model.Freight;

public class FreightDB implements FreightDBIF {
	private static final String FIND_BY_FREIGHTNUMBER_Q = "select * from Freight where freightNo = ?";
	private PreparedStatement findByFreightNumberPS;

	private static final String FIND_ALL_Q = "select * from Freight";
	private PreparedStatement findAllPS;

	public FreightDB() throws DataAccessException {
		init();
	}
	
	/**
	 * initializes the FreightDB object by setting up a database connection and preparing statements
	 * @throws DataAccessException
	 */

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findByFreightNumberPS = connection.prepareStatement(FIND_BY_FREIGHTNUMBER_Q);
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	/**
	 * findFreightByFreightNumber uses a prepared statement to construct a result set, with which we construct the desired objects
	 * @param freightNumber the number used to identify various freights
	 * @return foundFreight a list of freights matching the input parameters.
	 * 
	 */

	@Override
	public Freight findFreightByFreightNumber(String freightNumber) throws DataAccessException {
		Freight foundFreight = null;
		try {
			findByFreightNumberPS.setString(1, freightNumber);
			ResultSet rs = findByFreightNumberPS.executeQuery();

			if (rs.next()) {
				foundFreight = buildFreight(rs);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return foundFreight;
	}
	
	/**
	 * buildObject contstructs objects with data from the resultset found in findFreightByFreightNumber
	 * @param rs
	 * @return res an instance of Freight
	 * @throws DataAccessException
	 */

	private Freight buildFreight(ResultSet rs) throws DataAccessException {
		Freight res = null;
		try {
			StorageDBIF storageDBIF = new StorageDB();

			res = new Freight(rs.getInt("id"), rs.getString("name"), rs.getString("nameOfCourier"),
					rs.getString("email"), rs.getString("phoneNo"), storageDBIF.findAddress(rs.getInt("address_id")),
					rs.getString("freightNo"));

		} catch (Exception e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}
}
