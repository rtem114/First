package ua.tableModel;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import ua.entity.Cases;

public class TableModel extends AbstractTableModel {


	private static final long serialVersionUID = -5548656821631906553L;
	private static final int DATE = 0;
	private static final int JUDGE = 1;
	private static final int NUMBER = 2;
	private static final int SIDES = 3;
	private static final int TYPE = 4;
	private static final int COURT = 5;

	private String[] columnNames = { "Дата", "Склад суду", "Номер справи", "Сторони", "Суть справи", "Суд" };

	private List<Cases> cases;

	public TableModel(List<Cases> theCases) {
		cases = theCases;
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
		return cases.size();
	}
	  /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
	// @Override
	// public Class<?> getColumnClass(int columnIndex) {
	// if (cases.isEmpty()) {
	// return Object.class;
	// }
	// return getValueAt(0, columnIndex).getClass();
	// }

	@Override
	public Object getValueAt(int row, int col) {
		Cases tempCases = cases.get(row);

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

	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public void setValueAt(Object value, int row, int col) {
//	    data[row][col] = value;
	    fireTableCellUpdated(row, col);
	}
	
	public void fireTableDataChanged() {
        fireTableChanged(new TableModelEvent(this));
//        fireTableDataChanged();
    }
}
