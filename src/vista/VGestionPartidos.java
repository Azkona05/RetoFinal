
package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VGestionPartidos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
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
		
		JComboBox cbLiga = new JComboBox();
		cbLiga.setBounds(128, 27, 130, 21);
		contentPanel.add(cbLiga);
		
		
		JLabel lblEquipoLocal = new JLabel("Local: ");
		lblEquipoLocal.setBounds(33, 73, 85, 13);
		contentPanel.add(lblEquipoLocal);
		
		JComboBox cbLocal = new JComboBox();
		cbLocal.setBounds(128, 69, 130, 21);
		contentPanel.add(cbLocal);
		
		
		JLabel lblVisitante = new JLabel("Visitante: ");
		lblVisitante.setBounds(33, 111, 85, 13);
		contentPanel.add(lblVisitante);
		
		JComboBox cbVisitante = new JComboBox();
		cbVisitante.setBounds(128, 107, 130, 21);
		contentPanel.add(cbVisitante);
		System.out.println("1");
		
		JLabel lblGanador = new JLabel("Ganador: ");
		lblGanador.setBounds(33, 152, 85, 13);
		contentPanel.add(lblGanador);
		
		JComboBox cbGanador = new JComboBox();
		cbGanador.setBounds(128, 148, 130, 21);
		contentPanel.add(cbGanador);
		
	}
}
