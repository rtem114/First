package ua.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import ua.entity.Courts;

public class CourtsTableModel extends AbstractTableModel {

	
	private static final long serialVersionUID = -4114169369350258196L;

	private static final int NAME = 0;
	
	private String[] columnNames = { "Назва суду" };
	
	private List<Courts> courts;
	
	public CourtsTableModel(List<Courts> theCourts) {
		courts = theCourts;
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
		
		return courts.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		
		Courts tempCourts = courts.get(row);
		
		switch (col) {
		case NAME:
			return tempCourts.getName();
		default:		
		return tempCourts.getName();
	}

}
}