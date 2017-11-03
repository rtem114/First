package ua.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import ua.entity.SelectedCases;

public class SelectedCasesTableModel extends AbstractTableModel{
	
	
	private static final long serialVersionUID = -8704211228949389973L;
	private static final int DATE = 0;
	private static final int JUDGE = 1;
	private static final int NUMBER = 2;
	private static final int SIDES = 3;
	private static final int TYPE = 4;
	private static final int COURT = 5;

	private String[] columnNames = { "Дата", "Склад суду", "Номер справи", "Сторони", "Суть справи", "Суд" };

	private List<SelectedCases> selectedCases;

	public SelectedCasesTableModel(List<SelectedCases> theSelectedCases) {
		selectedCases = theSelectedCases;
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
		return selectedCases.size();
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	
//	 @Override
//	 public Class<?> getColumnClass(int columnIndex) {
//	 if (selectedCases.isEmpty()) {
//	 return Object.class;
//	 }
//	 return getValueAt(0, columnIndex).getClass();
//	 }

	@Override
	public Object getValueAt(int row, int col) {
		SelectedCases tempCases = selectedCases.get(row);

		switch (col) {
		case DATE:
			return tempCases.getDate();
		case JUDGE:
			return tempCases.getJudge();
		case NUMBER:
			return tempCases.getNumber();
		case SIDES:
			return tempCases.getSides();
		case TYPE:
			return tempCases.getType();
		case COURT:
			return tempCases.getCourt();
		default:
			return tempCases.getSides();
		}

	}

}
