package vidkar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import vidkar.tablemodals.*;

public class Main extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JScrollPane scroolPane;
	public static MyTableModel tablemodal;
	public static JButton btnNewButton = new JButton("Actualizar");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			            if ("Nimbus".equals(info.getName())) {
			                UIManager.setLookAndFeel(info.getClassName());
			                System.out.println("CHOSEN THIS");
			                break;
			            } else {
			                UIManager.setLookAndFeel  ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			            }
			        }
			    } catch (Exception e) {
			        // If Nimbus is not available, you can set to another look and feel.
			        // I can't get it to compile or work.
			    }
				
				try {
					Main frame = new Main();
					frame.setVisible(true);
					HiloUpdateModal tread = new HiloUpdateModal();
					tread.start();
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

		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HiloUpdateModal thread = new HiloUpdateModal();
				System.out.println("Actualizando Code");
				thread.start();
			}
		});
		panel.add(btnNewButton);
	}

}

class HiloUpdateModal extends Thread {
	public static void main(String[] args) {
		HiloUpdateModal thread = new HiloUpdateModal();
		System.out.println("Actualizando Code");
		thread.start();

	}

	@SuppressWarnings("deprecation")
	public void run() {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Main.btnNewButton.setEnabled(false);
		Main.btnNewButton.setText("Cargando...");
		Main.tablemodal = new MyTableModel();
		Main.tablemodal.updateDataFromVidkar();
		Main.table.setModel(Main.tablemodal);
//		Main.table.getModel().updateDataFromVidkar();
		System.out.println("Actualizacion Terminada");
		Main.btnNewButton.setText("Actualizar");
		Main.btnNewButton.setEnabled(true);
	}
}