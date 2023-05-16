package controller;

import java.util.ArrayList;
import java.util.List;

import database.StaffDB;
import database.StaffDBIF;
import model.Staff;

public class StaffCtrl {
	private List<Staff> staffList;

	public List<Staff> findStaffById(List<String> staffNos) throws DataAccessException {
		StaffDBIF staffDBIF = new StaffDB();
		List<Staff> res = null;
		if (!staffNos.isEmpty()) {
			res = staffDBIF.findStaffByNos(staffNos);
			staffList = res;
		}
		return res;

	}
}
