package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DataAccessException;
import database.DBConnection;
import database.DBMessages;
import model.Product;
import model.BoxedProduct;

public class ProductDB implements ProductDBIF {

	private static final String FIND_ALL_Q = "select * from Product";
	private PreparedStatement findAllPS;

	private static String FIND_BY_BARCODE_Q = "select * from product where barcode = ?;";
	private PreparedStatement findByBarcodePS;

	public ProductDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
			findByBarcodePS = connection.prepareStatement(FIND_BY_BARCODE_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}		
	

	@Override
	public Product findProductByBarcode (String barcode) throws DataAccessException {
		Product foundProduct = null;
		try {
			findByBarcodePS.setString(1, barcode);
			ResultSet rs = findByBarcodePS.executeQuery();
			if (rs.next()) {
				foundProduct = buildObject(rs);
			}

		} catch (SQLException e) {
			throw new DataAccessException("Could not find by phoneno = " + barcode, e);
		}
		return foundProduct;
	}

	private Product buildObject(ResultSet rs) throws DataAccessException {
		Product res = null;
		try {
			String type = rs.getString("productType").toLowerCase();
			
			switch(type){
			case ("product"):
				res = new Product(rs.getString("name"),
						rs.getString("itemNumber"),
						rs.getString("barcode"), 
						rs.getString("countryOfOrigin"), 
						rs.getDouble("percentOfGlaze"), 
						rs.getString("description"), 
						rs.getInt("weight"), 
						rs.getInt("minStock"),
						rs.getBoolean("priority"));

				break;
			case ("boxedproduct"):
				res = new BoxedProduct(rs.getString("name"),
						rs.getString("itemNumber"),
						rs.getString("barcode"), 
						rs.getString("countryOfOrigin"), 
						rs.getDouble("percentOfGlaze"), 
						rs.getString("description"), 
						rs.getInt("weight"), 
						rs.getInt("minStock"),
						rs.getBoolean("priority"),
						rs.getInt("quantityInBox"),
						rs.getString("parentBarcode"));
				break;
			default:
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}
	
	
}
