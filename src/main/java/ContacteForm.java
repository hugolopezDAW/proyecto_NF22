import javax.swing.*;
import java.awt.*;

class ContacteForm extends JDialog {
    private JTextField nomField = new JTextField(20);
    private JTextField cognomsField = new JTextField(20);
    private JTextField telefonField = new JTextField(10);
    private JTextField emailField = new JTextField(20);
    private JButton saveButton = new JButton("Guardar");

    interface SaveCallback {
        void onSave(String nom, String cognoms, String telefon, String email);
    }

    private SaveCallback callback;

    public ContacteForm(JFrame parent, String title) {
        this(parent, title, "", "", "", "");
    }

    public ContacteForm(JFrame parent, String title, String nom, String cognoms, String telefon, String email) {
        super(parent, title, true);
        setLayout(new GridLayout(5, 2));
        add(new JLabel("Nombre:")); add(nomField);
        add(new JLabel("Apellidos:")); add(cognomsField);
        add(new JLabel("Teléfono:")); add(telefonField);
        add(new JLabel("Email:")); add(emailField);
        add(saveButton);

        nomField.setText(nom);
        cognomsField.setText(cognoms);
        telefonField.setText(telefon);
        emailField.setText(email);

        saveButton.addActionListener(e -> {
            if (callback != null) {
                callback.onSave(nomField.getText(), cognomsField.getText(), telefonField.getText(), emailField.getText());
            }
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public void setOnSave(SaveCallback callback) {
        this.callback = callback;
    }
}
