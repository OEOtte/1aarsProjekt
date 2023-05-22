package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class StaffListTableModel extends AbstractTableModel {

	private static final String[] COL_NAMES = { "Staff Number" };
	private List<String> data;

	public StaffListTableModel(List<String> staffNos) {
		this.data = staffNos;
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
		String staffNumber = data.get(rowIndex);
		String res = "";
		switch (columnIndex) {
		case 0:
			res = staffNumber;
			break;
		default:
			res = "<UNKOWN " + columnIndex + ">";
		}
		return res;

	}

	public String getDataAt(int rowIndex) {
		return data.get(rowIndex);
	}

	public void setData(String staffNos) {
		this.data.add(staffNos);
		super.fireTableDataChanged();
	}

}
