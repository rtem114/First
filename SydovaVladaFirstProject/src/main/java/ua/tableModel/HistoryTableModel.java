package ua.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;



public class HistoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1694728022396262928L;

	private static final int ID = 0;
	private static final int NAME = 1;

	private String[] columnNames = { "Дати судових засідань по даній справі:" };

	private List<String> history;

	public HistoryTableModel(List<String> theHistory) {
		history = theHistory;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {

		return history.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		String tempCourts = history.get(row);

		switch (col) {
		case NAME:
			return tempCourts;
		default:
			return tempCourts;
		}

	}

}
