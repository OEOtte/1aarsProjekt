package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.LotLine;
import model.Product;

public class LotLineListTableModel extends AbstractTableModel {

	private static final String[] COL_NAMES = { "Staff Number" };
	private List<LotLine> data;

	public LotLineListTableModel(List<LotLine> lotLines) {
		this.data = lotLines;
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
