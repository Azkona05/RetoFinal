package vista;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Equipo;

public class MostrarEquipos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;

    /**
     * Constructor que crea el diálogo para mostrar los equipos.
     * 
     * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
     * @param padre El JFrame padre de este diálogo
     * @param modal Indica si el diálogo debe ser modal (true) o no (false)
     */
	public MostrarEquipos(VMenuAdmin padre, boolean modal) {
		super(padre);
    	this.setModal(modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
        setTitle("Lista de Equipos");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        String[] columnNames = { "Codigo", "Nombre" };
        model = new DefaultTableModel(columnNames, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Configura el listener para doble clic
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String codEq = (String) model.getValueAt(row, 0);
                        String nombre_equipo = (String) model.getValueAt(row, 1);

                        padre.cargarDatosEq(codEq, nombre_equipo);
                        padre.setVisible(true);
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
	  /**
     * Carga los datos de los partidos desde la capa de controlador.
     * 
     * @throws LoginException Si ocurre un error relacionado con la autenticación
     */

    private void cargarDatos() throws LoginException {
        Equipo eq = new Equipo();
		Object[][] datos = Principal.devolverEquipos(eq);
		actualizarDatos(datos);
		dispose();
    }

    /**
     * Actualiza la tabla con los datos proporcionados.
     * 
     * @param datos Matriz de objetos que contiene los datos de los partidos a mostrar
     */
    private void actualizarDatos(Object[][] datos) {
        model.setRowCount(0); // Limpiar tabla antes de agregar los nuevos datos
        for (Object[] fila : datos) {
            model.addRow(fila);
        }
    }

}
