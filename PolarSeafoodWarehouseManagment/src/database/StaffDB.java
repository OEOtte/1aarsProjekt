package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import database.DBMessages;
import model.Staff;

public class StaffDB implements StaffDBIF {

	private static final String FIND_STAFF_BY_NO_Q = "select * from Staff where staffNo = ?;";
	private PreparedStatement findStaffByNoPS;

	private static final String FIND_STAFF_BY_NO_AND_RETURN_ID_Q = "select id from Staff where staffNo = ?;";
	private PreparedStatement findStaffByNoAndReturnIdPS;

	public StaffDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findStaffByNoPS = connection.prepareStatement(FIND_STAFF_BY_NO_Q);
			findStaffByNoAndReturnIdPS = connection.prepareStatement(FIND_STAFF_BY_NO_AND_RETURN_ID_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public List<Staff> findStaffByNos(List<String> staffNos) throws DataAccessException {
		List<Staff> foundStaff = null;

		for (int i = 0; i < staffNos.size(); i++) {
			try {
				findStaffByNoPS.setString(1, staffNos.get(i));
				ResultSet rs = findStaffByNoPS.executeQuery();
				if (rs.next()) {
					if (foundStaff == null) {
						foundStaff = new ArrayList<>();
					}
					foundStaff.add(buildObject(rs));
				}
			} catch (SQLException e) {
				throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
			}
		}

		return foundStaff;
	}

	private Staff buildObject(ResultSet rs) throws DataAccessException {
		Staff res = null;

		try {

			res = new Staff(rs.getString("name"), rs.getString("phoneNo"), rs.getBoolean("admin"),
					rs.getString("staffNo"));

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}

	@Override
	public int findStaffIdByNo(String staffNo) throws DataAccessException {
		int foundStaffId = -1;
		try {
			findStaffByNoAndReturnIdPS.setString(1, staffNo);
			ResultSet rs = findStaffByNoAndReturnIdPS.executeQuery();
			if (rs.next()) {
				foundStaffId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return foundStaffId;
	}

	private int getId(ResultSet rs) throws DataAccessException {
		int res = -1;
		try {
			res = rs.getInt("id");
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}

}
