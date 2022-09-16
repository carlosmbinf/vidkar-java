package vidkar.tablemodals;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};//same as before...
	@SuppressWarnings("removal")
	private Object[][] data = {
		    {"Kathy", "Smith",
		        "Snowboarding", new Integer(5), new Boolean(false)},
		       {"John", "Doe",
		        "Rowing", new Integer(3), new Boolean(true)},
		       {"Sue", "Black",
		        "Knitting", new Integer(2), new Boolean(false)},
		       {"Jane", "White",
		        "Speed reading", new Integer(20), new Boolean(true)},
		       {"Joe", "Brown",
		        "Pool", new Integer(10), new Boolean(false)}
		   };//same as before...

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
//		super.setValueAt(aValue, rowIndex, columnIndex);
		data[rowIndex][columnIndex] = aValue;
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
//		if (col < 2) {
//			return false;
//		} else {
//			return true;
//		}
		return true;
	}
	
	public void updateDataFromVidkar() {
		
	}
}