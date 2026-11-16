package view;

import controller.ClienteController;
import model.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClientesView extends JFrame {
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombres, txtApellidos, txtDocumento, txtTelefono;
    private JButton btnRegistrar;
    private ClienteController clienteController;

    public ClientesView() {
        clienteController = new ClienteController();
        setTitle("Modulo de Clientes");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblDoc = new JLabel("Nro Documento:");
        lblDoc.setBounds(20, 20, 100, 25);
        add(lblDoc);
        txtDocumento = new JTextField();
        txtDocumento.setBounds(120, 20, 120, 25);
        add(txtDocumento);

        JLabel lblNom = new JLabel("Nombres:");
        lblNom.setBounds(260, 20, 70, 25);
        add(lblNom);
        txtNombres = new JTextField();
        txtNombres.setBounds(330, 20, 150, 25);
        add(txtNombres);

        JLabel lblApe = new JLabel("Apellidos:");
        lblApe.setBounds(20, 60, 100, 25);
        add(lblApe);
        txtApellidos = new JTextField();
        txtApellidos.setBounds(120, 60, 160, 25);
        add(txtApellidos);

        JLabel lblTel = new JLabel("Telefono:");
        lblTel.setBounds(290, 60, 70, 25);
        add(lblTel);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(360, 60, 120, 25);
        add(txtTelefono);

        btnRegistrar = new JButton("Registrar Cliente");
        btnRegistrar.setBounds(500, 40, 150, 30);
        add(btnRegistrar);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Documento", "Nombres", "Apellidos", "Telefono"}, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(20, 110, 640, 280);
        add(scroll);

        cargarTabla();

        btnRegistrar.addActionListener(e -> {
            Cliente c = new Cliente();
            c.setTipoDocumento("DNI");
            c.setNroDocumento(txtDocumento.getText());
            c.setNombres(txtNombres.getText());
            c.setApellidos(txtApellidos.getText());
            c.setTelefono(txtTelefono.getText());
            c.setEmail("");
            c.setDireccion("");

            if (clienteController.registrarCliente(c)) {
                JOptionPane.showMessageDialog(null, "Cliente registrado con exito.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Cliente> lista = clienteController.listarClientes();
        for (Cliente c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getIdCliente(),
                c.getNroDocumento(),
                c.getNombres(),
                c.getApellidos(),
                c.getTelefono()
            });
        }
    }
}