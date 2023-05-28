package controller;

import java.util.ArrayList;
import java.util.List;

import database.StaffDB;
import database.StaffDBIF;
import model.Staff;

public class StaffCtrl {

	/**
	 * This method find staffs by IDs.
	 * 
	 * @param staffNos the list of ID strings that we use to search in the database
	 *                 with.
	 * @return returns a list of staff IDs and null if the staff IDs were not found.
	 * @throws DataAccessException if there is an error accessing the data.
	 */

	public List<Staff> findStaffById(List<String> staffNos) throws DataAccessException {
		StaffDBIF staffDBIF = new StaffDB();
		List<Staff> res = null;
		if (!staffNos.isEmpty()) {
			res = staffDBIF.findStaffByNos(staffNos);
		}
		return res;

	}
}
