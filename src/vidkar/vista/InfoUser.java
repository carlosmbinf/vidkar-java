package vidkar.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

import org.bson.Document;

import vidkar.controlador.*;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.FlowLayout;

public class InfoUser extends JDialog {

	JPanel panelPrincipal = new JPanel();
	JScrollPane scrollPane = new JScrollPane(panelPrincipal);
	JPanel panel = new JPanel();
	public static JLabel lblName;
	FindUserByIdInData Threaduser = new FindUserByIdInData();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panelUser = new JPanel();
	private final JLabel lblNewLabel_1 = new JLabel("Usuario:");
	public static JLabel lblUsuario;
	private final JPanel panelEmail = new JPanel();
	private final JLabel lblNewLabel_1_1 = new JLabel("Email:");
	public static JLabel lblEmail;

	public InfoUser(String id) {
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setTitle("Información de Usuario");

		lblName = new JLabel("Cargando...");
		panel.add(lblName);
		panelPrincipal.add(panel, BorderLayout.NORTH);

		panelPrincipal.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(8, 0, 0, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panelUser.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		panel_2.add(panelUser);

		panelUser.add(lblNewLabel_1);

		lblUsuario = new JLabel("Cargando...");
		panelUser.add(lblUsuario);
		FlowLayout flowLayout = (FlowLayout) panelEmail.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		panel_2.add(panelEmail);

		panelEmail.add(lblNewLabel_1_1);

		lblEmail = new JLabel("Cargando...");
		panelEmail.add(lblEmail);

		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(300, 300);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		Threaduser.setId(id);
		Threaduser.start();
	}

	public InfoUser() {
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setTitle("Información de Usuario");

		lblName = new JLabel("Cargando...");
		panel.add(lblName);
		panelPrincipal.add(panel, BorderLayout.NORTH);

		panelPrincipal.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(8, 0, 0, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panelUser.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		panel_2.add(panelUser);

		panelUser.add(lblNewLabel_1);

		lblUsuario = new JLabel("Cargando...");
		panelUser.add(lblUsuario);
		FlowLayout flowLayout = (FlowLayout) panelEmail.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		panel_2.add(panelEmail);

		panelEmail.add(lblNewLabel_1_1);

		lblEmail = new JLabel("Cargando...");
		panelEmail.add(lblEmail);

		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(300, 300);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		Threaduser.setId("Pa4hQXHpZNKDjZKyy");
		Threaduser.start();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InfoUser a = new InfoUser();
		a.show();
	}

}

class FindUserByIdInData extends Thread {
	private Connection conexion = new Connection();
	private String id = "";
	private Document user = null;

	public String getid() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Document getUser() {
		return user;
	}

	public void setUser(Document user) {
		this.user = user;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		user = conexion.findUserById(id);

		String email = ((Document) ((ArrayList) (user.get("emails"))).get(0)).get("address") != null
				? ((Document) ((ArrayList) (user.get("emails"))).get(0)).get("address").toString()
				: "N/A";
		System.out.println(email);

		InfoUser.lblName.setText(((Document) user.get("profile")).get("firstName").toString() + " "
				+ ((Document) user.get("profile")).get("lastName").toString());
		InfoUser.lblUsuario.setText(user.get("username").toString());
		InfoUser.lblEmail.setText(email);

		System.out.println(user.toJson());
	}

}
