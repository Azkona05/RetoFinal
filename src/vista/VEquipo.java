package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Equipo;
import modelo.Jugador;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VEquipo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane jscrollJugadores;
	private JTable tablaJugadores;
	
	public VEquipo(VMenuPrincipal padre, boolean modal, Equipo equipo) throws LoginException {
		super(padre);
		setModal(modal); 
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEquipo = new JLabel("New label");
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo.setBounds(35, 40, 150, 13);
		contentPanel.add(lblEquipo);
		
		jscrollJugadores = new JScrollPane();
		jscrollJugadores.setBounds(497, 509, 460, 101);
		getContentPane().add(jscrollJugadores);
		presentarTabla(equipo);
	}

	private void presentarTabla(Equipo equi) throws LoginException {
		tablaJugadores = this.cargarTabla(equi);
		tablaJugadores.setBounds(0, 10, 75, 75);
		jscrollJugadores.setViewportView(tablaJugadores);
	}

	private JTable cargarTabla(Equipo equi) throws LoginException {
		String[] coulumnasNombre = { "Dni", "Nombre", "Apellido","Posicion", "Dorsal" };
		String[] colum = new String[5];
		DefaultTableModel model = new DefaultTableModel(null, coulumnasNombre) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; 
			}
		};
		List<Jugador> jugadores = Principal.jugadorEquipo(equi);
		for (Jugador jug : jugadores) {
			colum[0] = String.valueOf(jug.getDni());
			colum[1] = jug.getNombre();
			colum[2] = jug.getApellido();
			colum[3] = String.valueOf(jug.getDorsal());
			colum[4] = jug.getPosicion().name();
			model.addRow(colum);
		}

		return new JTable(model);
	}
}
