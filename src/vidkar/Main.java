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
import javax.swing.JTextField;

public class Main extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JScrollPane scroolPane;
	public static MyTableModel tablemodal;
	private JPanel panel_1;
	private JPanel panel_2;
	public static JButton btnNewButton;
	private JTextField textField;
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
//					HiloUpdateModal tread = new HiloUpdateModal();
//					tread.start();
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
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1,BorderLayout.CENTER);
		
		btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HiloUpdateModal hiloUpdate = new HiloUpdateModal();
				hiloUpdate.start();
			}
		});
		panel_1.add(btnNewButton);
		
		panel_2 = new JPanel();
		panel.add(panel_2,BorderLayout.EAST);
		
		textField = new JTextField();
		textField.setToolTipText("Buscar Usuario");
		panel_2.add(textField);
		textField.setColumns(10);
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