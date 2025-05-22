import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import backend.Contacte;
import backend.Controlador;

public class GUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private Controlador controlador;

    public GUI(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Gestor de Contactos");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Teléfono", "Email"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton addBtn = new JButton("Añadir");
        JButton deleteBtn = new JButton("Eliminar");
        JButton updateBtn = new JButton("Actualizar");
        JButton searchBtn = new JButton("Buscar");

        panel.add(addBtn);
        panel.add(deleteBtn);
        panel.add(updateBtn);
        panel.add(searchBtn);
        add(panel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> showAddDialog());
        deleteBtn.addActionListener(e -> deleteSelectedContact());
        updateBtn.addActionListener(e -> showUpdateDialog());
        searchBtn.addActionListener(e -> searchContact());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Contacto> contacts = controlador.getContactes();
        for (Contacte c : contacts) {
            model.addRow(new Object[]{c.getId(), c.getNom(), c.getCognoms(), c.getTelefon(), c.getEmail()});
        }
    }

    private void showAddDialog() {
        ContacteForm form = new ContacteForm(this, "Añadir Contacto");
        form.setOnSave((nom, cognoms, telefon, email) -> {
            controlador.nouContacte(nom, cognoms, Integer.parseInt(telefon), email);
            refreshTable();
        });
        form.setVisible(true);
    }

    private void deleteSelectedContact() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = (int) model.getValueAt(row, 0);
            controlador.eliminarContacte(id);
            refreshTable();
        }
    }

    private void showUpdateDialog() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = (int) model.getValueAt(row, 0);
            String nom = (String) model.getValueAt(row, 1);
            String cognoms = (String) model.getValueAt(row, 2);
            String telefon = model.getValueAt(row, 3).toString();
            String email = (String) model.getValueAt(row, 4);

            ContacteForm form = new ContacteForm(this, "Actualizar Contacto", nom, cognoms, telefon, email);
            form.setOnSave((n, c, t, e) -> {
                controlador.actualitzarContacte(id, n, c, Integer.parseInt(t), e);
                refreshTable();
            });
            form.setVisible(true);
        }
    }

    private void searchContact() {
        String field = JOptionPane.showInputDialog(this, "Campo (nom, cognoms, telefon, email):");
        String value = JOptionPane.showInputDialog(this, "Valor:");
        List<Contacte> results = controlador.getContactesPerCamp(field, value);
        model.setRowCount(0);
        for (Contacte c : results) {
            model.addRow(new Object[]{c.getId(), c.getNom(), c.getCognoms(), c.getTelefon(), c.getEmail()});
        }
    }
}