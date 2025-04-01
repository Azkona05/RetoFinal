package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Principal;
import excepciones.LoginException;
import modelo.EnumPosicion;
import modelo.Jugador;
import modelo.Partido;

public class MostrarPartidos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MostrarPartidos dialog = new MostrarPartidos();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MostrarPartidos(VMenuAdmin padre, boolean modal) {
		super(padre);
    	this.setModal(modal);
        setTitle("Lista de Jugadores");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        String[] columnNames = { "Codigo", "Local", "Visitante", "Ganador", "Fecha", "Codigo de competicion" };
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Doble clic para pasar los datos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int cod = (Integer) model.getValueAt(row, 0);
                        String eqLocal = (String) model.getValueAt(row, 1);
                        String eqVisitante = (String) model.getValueAt(row, 2);
                        String ganador = (String) model.getValueAt(row, 3);
                        LocalDate fecha = (LocalDate) model.getValueAt(row, 4);
                        String codComp = (String) model.getValueAt(row, 5);

                        VMenuAdmin vma = new VMenuAdmin(null, true);
                        vma.cargarDatosPart(cod, eqLocal, eqVisitante, ganador, fecha, codComp);
                        vma.setVisible(true);
                        dispose();
                    }
                }
            }
        });

        try {
			cargarDatos();
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    private void cargarDatos() throws LoginException {
        Partido part = new Partido();
		Object[][] datos = Principal.devolverPartidos(part);
		actualizarDatos(datos);
    }

    private void actualizarDatos(Object[][] datos) {
        model.setRowCount(0); // Limpiar tabla antes de agregar los nuevos datos
        for (Object[] fila : datos) {
            model.addRow(fila);
        }
    }

}
