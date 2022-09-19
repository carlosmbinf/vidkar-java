package vidkar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import vidkar.tablemodals.*;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scroolPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		setSize(800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		table = new JTable();
//		TableCellRenderer tableRenderer;
//		tableRenderer = table.getDefaultRenderer(JButton.class);

		table.addMouseListener(new JTableButtonMouseListener(table));
		table.setDefaultRenderer(JButton.class, new JTableButtonRenderer());
		
		table.setRowHeight(25);
		table.setModel(new MyTableModel());
//		table.getColumn("Button").setCellRenderer(buttonRenderer);
		
		scroolPane = new JScrollPane(table);
		contentPane.add(scroolPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyTableModel tablemodal = new MyTableModel();
				tablemodal.updateDataFromVidkar();
				table.setModel(tablemodal);
			}
		});
		panel.add(btnNewButton);
	}

}
