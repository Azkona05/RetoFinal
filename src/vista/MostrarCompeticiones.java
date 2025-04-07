package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Competicion;
import modelo.EnumPosicion;
import modelo.Jugador;

public class MostrarCompeticiones extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MostrarCompeticiones dialog = new MostrarCompeticiones();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param b 
	 * @param vMenuAdmin 
	 */
	public MostrarCompeticiones(VMenuAdmin padre, boolean modal) {
		super(padre);
    	this.setModal(modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
        setTitle("Lista de Competiciones");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        String[] columnNames = { "Codigo", "Nombre" };
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
                        String codComp = (String) model.getValueAt(row, 0);
                        String nombre = (String) model.getValueAt(row, 1);

                        VMenuAdmin vma = new VMenuAdmin(null, true);
                        vma.cargarDatosComp(codComp, nombre);
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
        Competicion comp = new Competicion();
		Object[][] datos = Principal.devolverCompeticiones(comp);
		actualizarDatos(datos);
		dispose();
    }

    private void actualizarDatos(Object[][] datos) {
        model.setRowCount(0); // Limpiar tabla antes de agregar los nuevos datos
        for (Object[] fila : datos) {
            model.addRow(fila);
        }
    }
}
