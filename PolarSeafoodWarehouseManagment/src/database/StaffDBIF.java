package database;
import java.util.List;

import controller.DataAccessException;
import model.*;

public interface StaffDBIF {

	public List<Staff> findStaffByNos(List<String> staffNos) throws DataAccessException;
	public int findStaffIdByNo(String staffNo) throws DataAccessException;
}
