package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.LotLine;
import model.Product;
import model.ShipmentLine;

public class ProductListTableModel extends AbstractTableModel {

	private static final String[] COL_NAMES = { "Item number", "Barcode" , "Product", "Weight" };
	private List<Product> data;

	public ProductListTableModel(List<Product> products) {
		this.data = products;
		if (this.data == null) {
			this.data = new ArrayList<>();
		}
	}

	@Override
	public String getColumnName(int col) {
		return COL_NAMES[col];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product p = data.get(rowIndex);
		String res = "";

		switch (columnIndex) {
		case 0:
			res = p.getItemNumber();
			break;
		case 1:
			res = p.getBarcode();
			break;
		case 2:
			res = p.getProductName();
			break;
		case 3:
			res = Double.toString(p.getWeight());
			break;
		default:
			res = "<UNKOWN " + columnIndex + ">";
		}
		return res;

	}

	public Product getDataAt(int rowIndex) {
		return data.get(rowIndex);
	}

	public void setData(List<Product> product) {
		this.data = product;
		super.fireTableDataChanged();
	}

}
