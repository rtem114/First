package ua.mainLogic;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.swing.table.AbstractTableModel;

import ua.entity.Cases;

public class TableModel extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4800709817737161762L;
	private static final int DATE = 0;
	private static final int JUDGE = 1;
	private static final int NUMBER = 2;
	private static final int SIDES = 3;
	private static final int TYPE = 4;

	private String[] columnNames = { "Date", "Judge", "Number", "Sides", "Type" };

	private List<Cases> cases;

	public TableModel(List<Cases> theCases) {
		cases = theCases;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;

	}

	@Override
	public int getRowCount() {
		return cases.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Cases tempCases = cases.get(row);

		switch (column) {
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
		default:
			return tempCases.getSides();
		}

	}

//	@Override
//	public Class getColumnClass(int c) {
//		return getValueAt(0, c).getClass();
//	}

}
