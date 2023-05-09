package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.Freight;

public class FreightDB implements FreightDBIF{
	private static String FIND_BY_FREIGHTNUMBER_Q = "select * from Freight where freightNumber = ?;";
	private PreparedStatement findByFreightNumberPS;
	
	private static final String FIND_ALL_Q = "select * from Freight";
	private PreparedStatement findAllPS;
	
	private void init() {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
			findByFreightNumberPS = connection.prepareStatement(FIND_BY_FREIGHTNUMBER_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
		}
	}
	
	@Override
	public Freight findFreightByFreightNumber(String freightNumber) {
		Freight foundFreight = null;
		try {
			findByFreightNumberPS.setString(1, freightNumber);
			ResultSet rs = findByFreightNumberPS.executeQuery();
			if(rs.next()) {
				foundFreight = buildObject(rs);
			} 
			
			
			} catch (SQLException e) {
		}
		return foundFreight;
	}

	private Freight buildObject(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
}
