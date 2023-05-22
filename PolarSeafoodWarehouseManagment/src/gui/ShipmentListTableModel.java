package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.ShipmentLine;

public class ShipmentListTableModel extends AbstractTableModel {

	private static final String[] COL_NAMES = { "Quantity", "Product", "Barcode", "Weight" };
	private List<ShipmentLine> data;

	public ShipmentListTableModel(List<ShipmentLine> sl) {
		this.data = sl;
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
		ShipmentLine sml = data.get(rowIndex);
		String res = "";

		switch (columnIndex) {
		case 0:
			res = Integer.toString(sml.getQuantity());
			break;
		case 1:
			res = sml.getProduct().getProductName();
			break;
		case 2:
			res = sml.getProduct().getBarcode();
			break;
		case 3:
			res = Double.toString(sml.getWeight());
			break;
		default:
			res = "<UNKOWN " + columnIndex + ">";
		}
		return res;
	}

	public ShipmentLine getDataAt(int rowIndex) {
		return data.get(rowIndex);
	}

	public void setData(List<ShipmentLine> sl) {
		this.data = sl;
		super.fireTableDataChanged();
	}

}
