package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Usuario;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class VMenuPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JCalendar calendar;
	private JTable table;
	private JList list;
	private JButton btnLogin;
	
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VInicio frame = new VInicio();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public VMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 428);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.NORTH);
		panelPrincipal.setLayout(new GridLayout(1, 0, 0, 0));
		
		JComboBox comboBox = new JComboBox();
		panelPrincipal.add(comboBox);
		
		JLabel lblEquipos = new JLabel("EQUIPOS");
		lblEquipos.setHorizontalAlignment(SwingConstants.CENTER);
		panelPrincipal.add(lblEquipos);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(this);
		panelPrincipal.add(btnLogin);
		
		JPanel panelTabla = new JPanel();
		getContentPane().add(panelTabla, BorderLayout.EAST);
		
		table = new JTable();
		panelTabla.add(table);
		
		JPanel panelCalendario = new JPanel();
		getContentPane().add(panelCalendario, BorderLayout.CENTER);
		
		calendar = new JCalendar();
		panelCalendario.add(calendar);
		
		JPanel panelLista = new JPanel();
		getContentPane().add(panelLista, BorderLayout.WEST);
		
		
		list = new JList();
		panelLista.add(list);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLogin)) {
			VLogin vL = new VLogin(this, true);
			vL.setVisible(true);
		}
		
	}
}
