package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Competicion;

import modelo.Partido;

import java.awt.BorderLayout;
import javax.swing.JTable;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;


public class VMenuPrincipal extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLogin;
	private JCalendar calendario;
	private JTable tablaClasi;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VInicio frame = new VInicio();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 * 
	 * 
	 * /** Create the frame.
	 */
	public VMenuPrincipal() throws LoginException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 428);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);

		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.EAST);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		calendario = new JCalendar();
		panel_1.add(calendario);
		calendario.getDayChooser();

		JComboBox cbElegirLiga = new JComboBox();
		panel_3.add(cbElegirLiga);

		JLabel lblEquipo = new JLabel("Equipos");
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblEquipo);

		btnLogin = new JButton("Login");
		panel_3.add(btnLogin);
		btnLogin.addActionListener(this);

		Map<String, Competicion> competiciones;
		competiciones = Principal.leerCompeticiones();
		for (Competicion comp : competiciones.values()) {
			cbElegirLiga.addItem(comp);
		}
		presentarTabla((Competicion) cbElegirLiga.getSelectedItem());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(calendario)) {

		} else if (e.getSource().equals(btnLogin)) {
			VLogin vL = new VLogin(this, true);
			vL.setVisible(true);
		} else {

		}

	}

	private void presentarTabla(Competicion liga) {
		// cargarTabla (prop);
		JScrollPane jscroll = new JScrollPane();
		tablaClasi = this.cargarTabla(liga);
		jscroll.setViewportView(tablaClasi);

		panel_2.add(jscroll);
		jscroll.setBounds(5, 5, 412, 70);
	}

	private JTable cargarTabla(Competicion liga) {
		String[] coulumnasNombre = { "Posicion", "Nombre", "Victorias" };
		String[] colum = new String[3];
		DefaultTableModel model = new DefaultTableModel(null, coulumnasNombre);
		List<Partido> partidos = Principal.buscarEquiLiga(liga);
		List<String> diferentesEquipos = Principal.devolverEquipos(liga);
		Map<String, String> orden = new TreeMap<>(Comparator.reverseOrder());
		int cont = 0;
		for (String equipo : diferentesEquipos) {
			cont = 0;
			for (Partido p : partidos) {
				if (equipo.equals(p.getGanador())) {
					cont++;
				}
			}
			orden.put(cont + "-" + equipo, equipo);
		}

		cont = 1;
		for (Map.Entry<String, String> entry : orden.entrySet()) {
			System.out.println(entry.getKey());
			colum[0] = String.valueOf(cont);
			colum[2] = entry.getKey().split("-")[0];
			colum[1] = entry.getValue();
			model.addRow(colum);
			cont++;
		}

		return new JTable(model);
	}
}
