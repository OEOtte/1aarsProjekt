package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.ShipmentLine;


public class ShipmentListTableModel extends AbstractTableModel {
	
	private static final String[] COL_NAMES = { "Qty", "Name", "Barcode", "Cost price", "Sales price", "Total" };
	private List<ShipmentLine> sl;

	public ShipmentListTableModel(List<ShipmentLine> sl) {
		this.sl = sl;
		if (this.sl == null) {
			this.sl = new ArrayList<>();
		}
	}
	
	@Override
	public String getColumnName(int col) {
		return COL_NAMES[col];
	}
	
	@Override
	public int getRowCount() {
		return sl.size();
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ShipmentLine sml = sl.get(rowIndex);
		String res = "";		
	
		switch(columnIndex) {
		case 0:
			res = sml.getProduct().getBarcode();
			break;
			
		default:
		res = "<UNKOWN " + columnIndex + ">";
	}
	return res;
}
	public ShipmentLine getDataAt(int rowIndex) {
		return sl.get(rowIndex);
	}

	public void setData(List<ShipmentLine> sl) {
		this.sl = sl;
		super.fireTableDataChanged();
	}

}
