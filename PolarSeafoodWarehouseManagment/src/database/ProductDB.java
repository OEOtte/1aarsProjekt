package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import database.DBConnection;
import database.DBMessages;
import model.Product;
import model.Supplier;
import model.BoxedProduct;

public class ProductDB implements ProductDBIF {

	private static final String FIND_ALL_Q = "select * from Product LEFT OUTER JOIN BoxedProduct;";
	private PreparedStatement findAllPS;

	private static final String FIND_BY_BARCODE_Q = "SELECT * FROM Product LEFT OUTER JOIN BoxedProduct ON Product.id = BoxedProduct.product_id WHERE barcode = ? or parentBarcode = ?;";
	private PreparedStatement findByBarcodePS;

	private static final String FIND_BY_PARTIAL_NAME_Q = "SELECT * FROM Product LEFT OUTER JOIN BoxedProduct ON Product.id = BoxedProduct.product_id WHERE productName LIKE ?;";
	private PreparedStatement findByPartialNamePS;

	public ProductDB() throws DataAccessException {
		init();
	}
	
	/**
	 * initializes the ProductDB object by setting up a database connection and preparing statements
	 * @throws DataAccessException
	 */

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
			findByBarcodePS = connection.prepareStatement(FIND_BY_BARCODE_Q);
			findByPartialNamePS = connection.prepareStatement(FIND_BY_PARTIAL_NAME_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	/**
	 * findProductByBarcode uses the input parameter "barcode" to create a result set containing various matching products
	 * 
	 * @param barcode a string of the barcode found on the physical product
	 * @return foundProduct a list of products constructed using the result set
	 */

	@Override
	public Product findProductByBarcode(String barcode) throws DataAccessException {
		Product foundProduct = null;
		try {
			findByBarcodePS.setString(1, barcode);
			findByBarcodePS.setString(2, barcode);
			ResultSet rs = findByBarcodePS.executeQuery();
			if (rs.next()) {
				foundProduct = buildProduct(rs);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return foundProduct;
	}
	
	/**
	 * buildObject constructs objects with data from the result set found in findProductByBarcode
	 * @param rs
	 * @return res an instance of Product
	 * @throws DataAccessException
	 */

	private Product buildProduct(ResultSet rs) throws DataAccessException {
		Product res = null;
		try {
			String type = rs.getString("type").toLowerCase();

			switch (type) {
			case ("product"):
				res = new Product(rs.getInt("id"), rs.getString("productName"), rs.getString("itemNumber"),
						rs.getString("barcode"), rs.getInt("percentOfGlaze"), rs.getString("description"),
						rs.getDouble("weight"), rs.getInt("minStock"), rs.getBoolean("priority"),
						rs.getInt("countryOfOrigin_Id"), new Supplier(rs.getInt("supplier_id")));
				break;
			case ("boxedproduct"):
				res = new BoxedProduct(rs.getInt("id"), rs.getString("productName"), rs.getString("itemNumber"),
						rs.getString("barcode"), rs.getInt("percentOfGlaze"), rs.getString("description"),
						rs.getDouble("weight"), rs.getInt("minStock"), rs.getBoolean("priority"),
						rs.getInt("countryOfOrigin_Id"), new Supplier(rs.getInt("supplier_id")),
						rs.getInt("quantityInBox"), rs.getString("parentBarcode"));

				break;
			default:
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

		return res;
	}
	
	
	/**
	 * findProductByPartialName a method that allows finding various products, with only a partial name.
	 * finding and returning a list of all products that contain matching strings
	 * 
	 * @param productName a string containing the partial name of a product
	 */

	@Override
	public List<Product> findProductByPartialName(String productName) throws DataAccessException {
		List<Product> products = new ArrayList<>();
		Product foundProduct;
		try {
			findByPartialNamePS.setString(1, "%" + productName + "%");
			ResultSet rs = findByPartialNamePS.executeQuery();
			while (rs.next()) {
				foundProduct = buildProduct(rs);
				products.add(foundProduct);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}

		return products;
	}

}
