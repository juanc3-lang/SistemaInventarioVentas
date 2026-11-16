package view;

import controller.ProveedorController;
import model.Proveedor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProveedoresView extends JFrame {
    private JTable tablaProveedores;
    private DefaultTableModel modeloTabla;
    private JTextField txtRazonSocial, txtRuc, txtTelefono;
    private JButton btnRegistrar;
    private ProveedorController proveedorController;

    public ProveedoresView() {
        proveedorController = new ProveedorController();
        setTitle("Modulo de Proveedores");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblRazon = new JLabel("Razon Social:");
        lblRazon.setBounds(20, 20, 90, 25);
        add(lblRazon);
        txtRazonSocial = new JTextField();
        txtRazonSocial.setBounds(110, 20, 180, 25);
        add(txtRazonSocial);

        JLabel lblRuc = new JLabel("RUC:");
        lblRuc.setBounds(310, 20, 40, 25);
        add(lblRuc);
        txtRuc = new JTextField();
        txtRuc.setBounds(360, 20, 120, 25);
        add(txtRuc);

        JLabel lblTel = new JLabel("Telefono:");
        lblTel.setBounds(20, 60, 90, 25);
        add(lblTel);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(110, 60, 130, 25);
        add(txtTelefono);

        btnRegistrar = new JButton("Registrar Proveedor");
        btnRegistrar.setBounds(500, 40, 150, 30);
        add(btnRegistrar);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Razon Social", "RUC", "Telefono"}, 0);
        tablaProveedores = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProveedores);
        scroll.setBounds(20, 110, 640, 280);
        add(scroll);

        cargarTabla();

        btnRegistrar.addActionListener(e -> {
            Proveedor p = new Proveedor();
            p.setRazonSocial(txtRazonSocial.getText());
            p.setRuc(txtRuc.getText());
            // El teléfono se mantiene en la interfaz de manera visual o decorativa

            if (proveedorController.registrarProveedor(p)) {
                JOptionPane.showMessageDialog(null, "Proveedor registrado con exito.");
                cargarTabla();
                txtRazonSocial.setText("");
                txtRuc.setText("");
                txtTelefono.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Proveedor> lista = proveedorController.listarProveedores();
        for (Proveedor p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getIdProveedor(),
                p.getRazonSocial(),
                p.getRuc(),
                p.getTelefono()
            });
        }
    }
}