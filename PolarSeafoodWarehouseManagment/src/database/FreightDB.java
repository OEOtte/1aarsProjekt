package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DataAccessException;
import model.Freight;

public class FreightDB implements FreightDBIF {
	private static final String FIND_BY_FREIGHTNUMBER_Q = "select * from Freight where freightNumber = ?;";
	private PreparedStatement findByFreightNumberPS;

	private static final String FIND_ALL_Q = "select * from Freight";
	private PreparedStatement findAllPS;

	public FreightDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findByFreightNumberPS = connection.prepareStatement(FIND_BY_FREIGHTNUMBER_Q);
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public Freight findFreightByFreightNumber(String freightNumber) throws DataAccessException {
		Freight foundFreight = null;
		try {
			findByFreightNumberPS.setString(1, freightNumber);
			ResultSet rs = findByFreightNumberPS.executeQuery();
			if (rs.next()) {
				foundFreight = buildObject(rs);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return foundFreight;
	}

	private Freight buildObject(ResultSet rs) throws DataAccessException {
		Freight res = null;
		try {
			res = new Freight(rs.getString("name"), 
					rs.getString("nameOfCourier"), 
					rs.getString("email"), 
					rs.getString("phoneNo"), 
					rs.getString("address"), 
					rs.getString("freightNumber"));
		
		} catch (Exception e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return res;
	}
}
