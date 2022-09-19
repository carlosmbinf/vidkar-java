package vidkar.tablemodals;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.bson.Document;

import vidkar.Connection;
import vidkar.Main;

public class MyTableModel extends AbstractTableModel {
	private Connection vidkarDB = new Connection();
	private String[] columnNames = { "Nombre", "Apellido", "UserName", "Megas Gastados", "Button" };// same as before...
	@SuppressWarnings("removal")

	private Object[][] data;

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public MyTableModel() {
		// TODO Auto-generated constructor stub
		updateDataFromVidkar();
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
		return false;
	}
	
	

	
	public void updateDataFromVidkar() {
		LinkedList<Document> list = vidkarDB.findAllElement();
		data = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			Document doc = list.get(i);
			
			try {
				data[i][0] = ((Document) doc.get("profile")).get("firstName");
				data[i][1] = ((Document) doc.get("profile")).get("lastName");
				data[i][2] = doc.get("username");
				data[i][3] = (doc.get("megasGastadosinBytes") == null || doc.get("megasGastadosinBytes") == "0"
						|| doc.get("megasGastadosinBytes") == "") ? 0
//								Long.parseLong(doc.get("megasGastadosinBytes").toString()) / 1024
								: ((Float.parseFloat(doc.get("megasGastadosinBytes").toString()) / 1024000));
//							doc.get("megasGastadosinBytes");	
				
				////////CREANDO BOTON PARA VER AL USUARIO//////
				JButton usuarioBtn = new JButton("Ver a " + doc.get("username"));
				usuarioBtn.setMargin(new Insets(10, 10, 10, 10));
				usuarioBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(doc.get("_id"));
						JDialog diag = new JDialog();
						diag.setSize(300, 300);
						diag.setLocationRelativeTo(null);
						diag.setModal(true);
						diag.show();
						
					}
				});
				data[i][4] = usuarioBtn;
			} catch (NumberFormatException e) {
				System.out.println(e);
			}

		}

	}
}
