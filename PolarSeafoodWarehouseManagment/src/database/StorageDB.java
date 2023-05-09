package database;

import java.sql.PreparedStatement;

public class StorageDB implements StorageDBIF{
	private static final String FIND_ALL_Q = "select * from Lot";
	private PreparedStatement findAllPS;

	private static String FIND_BY_PRIORITY_Q = "select * from Lot where priority = ?;";
	private PreparedStatement findByPriorityPS;
}
