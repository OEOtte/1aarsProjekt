package database;
import java.util.List;

import controller.DataAccessException;
import model.*;

public interface StaffDBIF {

	public List<Staff> findStaffByNo(List<String> staffNos) throws DataAccessException;
}
