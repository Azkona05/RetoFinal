
package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Principal;
import modelo.Competicion;
import modelo.Equipo;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VGestionPartidos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbLiga, cbLocal, cbVisitante, cbGanador;
	private Competicion comp;

	public VGestionPartidos(VMenuAdmin padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblLiga = new JLabel("Liga: ");
		lblLiga.setBounds(33, 31, 85, 13);
		contentPanel.add(lblLiga);

		cbLiga = new JComboBox();
		cbLiga.setBounds(128, 27, 130, 21);
		contentPanel.add(cbLiga);
		List<Competicion> competiciones = Principal.devolverCompeticiones();
		for (Competicion comp : competiciones) {
			cbLiga.addItem(comp);
		}
		comp = (Competicion) cbLiga.getSelectedItem();
		cbLiga.addActionListener(this);

		JLabel lblEquipoLocal = new JLabel("Local: ");
		lblEquipoLocal.setBounds(33, 73, 85, 13);
		contentPanel.add(lblEquipoLocal);

		cbLocal = new JComboBox();
		cbLocal.setBounds(128, 69, 130, 21);
		contentPanel.add(cbLocal);
		cbLocal.setEnabled(false);
		cbLocal.addActionListener(this);

		JLabel lblVisitante = new JLabel("Visitante: ");
		lblVisitante.setBounds(33, 111, 85, 13);
		contentPanel.add(lblVisitante);

		cbVisitante = new JComboBox();
		cbVisitante.setBounds(128, 107, 130, 21);
		contentPanel.add(cbVisitante);
		cbVisitante.setEnabled(false);

		JLabel lblGanador = new JLabel("Ganador: ");
		lblGanador.setBounds(33, 152, 85, 13);
		contentPanel.add(lblGanador);
		
		cbGanador = new JComboBox();
		cbGanador.setBounds(128, 148, 130, 21);
		contentPanel.add(cbGanador);
		cbGanador.setEnabled(false);
		
		cbGanador.addItem("Local");
		cbGanador.addItem("Visitante");
		cbGanador.setSelectedIndex(-1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cbLiga)) {
			cbLocal.setEnabled(true);
			actualizarEquipos();
			cbVisitante.setEnabled(true);
			cbGanador.setEnabled(true);
		} else if (e.getSource().equals(cbLocal)) {
			cbLocal.removeAllItems();
			cbLocal.setSelectedIndex(-1);
			List<Equipo> equiposLocal = Principal.devolverEquipos((Competicion) cbLiga.getSelectedItem());
			for (Equipo equi : equiposLocal) {
				cbLocal.addItem(equi.getNombre_equipo());
			}		
			cbVisitante.removeAllItems();
			cbVisitante.setSelectedIndex(-1);
			List<Equipo>equiposVisitante = Principal.devolverEquipos((Competicion) cbLiga.getSelectedItem());
			for (Equipo equi : equiposVisitante) {
				cbVisitante.addItem(equi.getNombre_equipo());
			}
			for (int i = 0; i < cbVisitante.getItemCount(); i++) {
			    if (cbVisitante.getItemAt(i).equals(cbLocal.getSelectedItem())) {
			        cbVisitante.removeItemAt(i);
			        break; // Detenemos el loop después de eliminarlo
			    }
			}
			cbGanador.setSelectedItem(-1);
		}else {
			
		}
	}
	public void actualizarEquipos () {
		cbLocal.removeAllItems();
		cbLocal.setSelectedIndex(-1);
		List<Equipo> equiposLocal = Principal.devolverEquipos((Competicion) cbLiga.getSelectedItem());
		for (Equipo equi : equiposLocal) {
			cbLocal.addItem(equi.getNombre_equipo());
		}		
		cbVisitante.removeAllItems();
		cbVisitante.setSelectedIndex(-1);
		List<Equipo>equiposVisitante = Principal.devolverEquipos((Competicion) cbLiga.getSelectedItem());
		for (Equipo equi : equiposVisitante) {
			cbVisitante.addItem(equi.getNombre_equipo());
		}
		for (int i = 0; i < cbVisitante.getItemCount(); i++) {
		    if (cbVisitante.getItemAt(i).equals(cbLocal.getSelectedItem())) {
		        cbVisitante.removeItemAt(i);
		        break; // Detenemos el loop después de eliminarlo
		    }
		}
		cbGanador.setSelectedItem(-1);
	}
}
