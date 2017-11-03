package ua.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.entity.CasesToFind;


public class CasesToFindModel extends AbstractTableModel {


	
	private static final long serialVersionUID = 6609188973451600620L;
	private static final int CASESTOFIND = 0;
	private static final int COURTSNAME = 1;


	private String[] columnNames = { "Дані для пошуку", "Назва суду" };

	private List<CasesToFind> casesToFind;

	public CasesToFindModel(List<CasesToFind> theCasesToFind) {
		casesToFind = theCasesToFind;
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

		return casesToFind.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		CasesToFind tempCasesToFind = casesToFind.get(row);

		switch (col) {
		case CASESTOFIND:
			return tempCasesToFind.getCasesToFind();
			
		case COURTSNAME:
			return tempCasesToFind.getCourt();
	
		default:
			return tempCasesToFind.getCasesToFind();
		}

	}

}
