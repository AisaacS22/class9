import isa.ejercicio.MongoDBConnection;
import isa.ejercicio.PersonaCRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonaGUI extends JFrame {
    private PersonaCRUD personaCRUD;

    private JTextField nombreField;
    private JTextField edadField;
    private JTextField ciudadField;

    public PersonaGUI(PersonaCRUD personaCRUD) {
        this.personaCRUD = personaCRUD;

        setTitle("Gestión de Personas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        mainPanel.add(nombreLabel);
        mainPanel.add(nombreField);

        JLabel edadLabel = new JLabel("Edad:");
        edadField = new JTextField();
        mainPanel.add(edadLabel);
        mainPanel.add(edadField);

        JLabel ciudadLabel = new JLabel("Ciudad:");
        ciudadField = new JTextField();
        mainPanel.add(ciudadLabel);
        mainPanel.add(ciudadField);

        JButton insertarButton = new JButton("Insertar Persona");
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                String ciudad = ciudadField.getText();
                personaCRUD.insertarPersona(nombre, edad, ciudad);
                JOptionPane.showMessageDialog(null, "Persona insertada correctamente");
            }
        });
        mainPanel.add(insertarButton);

        JButton mostrarButton = new JButton("Mostrar Personas");
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personasInfo = personaCRUD.mostrarPersonas();
                JOptionPane.showMessageDialog(null, "Listado de Personas:\n" + personasInfo);
            }
        });
        mainPanel.add(mostrarButton);

        JButton actualizarButton = new JButton("Actualizar Persona");
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreActual = nombreField.getText();
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                if (personaCRUD.actualizarPersona(nombreActual, nuevoNombre)) {
                    JOptionPane.showMessageDialog(null, "Persona actualizada correctamente");
                }
            }
        });
        mainPanel.add(actualizarButton);

        JButton eliminarButton = new JButton("Eliminar Persona");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                if (personaCRUD.eliminarPersona(nombre)) {
                    JOptionPane.showMessageDialog(null, "Persona eliminada correctamente");
                }
            }
        });
        mainPanel.add(eliminarButton);

        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        MongoDBConnection dbConnection = new MongoDBConnection("mongodb://localhost:27017", "miBaseDatos");
        PersonaCRUD personaCRUD = new PersonaCRUD(dbConnection.getDatabase(), "personas");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonaGUI(personaCRUD).setVisible(true);
            }
        });
    }
}
