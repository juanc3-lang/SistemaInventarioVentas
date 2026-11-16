package view;

import controller.CompraController;
import model.Compra;
import model.DetalleCompra;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ComprasView extends JFrame {
    private JTextField txtIdProveedor, txtNroDoc, txtIdProducto, txtCantidad, txtPrecioCompra;
    private JButton btnAgregar, btnProcesar;
    private JTable tablaCompra;
    private DefaultTableModel modeloCompra;
    private List<DetalleCompra> listaDetalle;
    private CompraController compraController;

    public ComprasView() {
        compraController = new CompraController();
        listaDetalle = new ArrayList<>();

        setTitle("Modulo de Compras y Abastecimiento");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblProv = new JLabel("ID Proveedor:");
        lblProv.setBounds(20, 20, 90, 25);
        add(lblProv);
        txtIdProveedor = new JTextField("1");
        txtIdProveedor.setBounds(110, 20, 60, 25);
        add(txtIdProveedor);

        JLabel lblDoc = new JLabel("Nro Documento:");
        lblDoc.setBounds(190, 20, 100, 25);
        add(lblDoc);
        txtNroDoc = new JTextField("F001-0001");
        txtNroDoc.setBounds(290, 20, 130, 25);
        add(txtNroDoc);

        JLabel lblProd = new JLabel("ID Prod:");
        lblProd.setBounds(20, 60, 60, 25);
        add(lblProd);
        txtIdProducto = new JTextField();
        txtIdProducto.setBounds(70, 60, 50, 25);
        add(txtIdProducto);

        JLabel lblCant = new JLabel("Cant:");
        lblCant.setBounds(130, 60, 40, 25);
        add(lblCant);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(170, 60, 50, 25);
        add(txtCantidad);

        JLabel lblPrec = new JLabel("Precio Compra:");
        lblPrec.setBounds(230, 60, 90, 25);
        add(lblPrec);
        txtPrecioCompra = new JTextField();
        txtPrecioCompra.setBounds(320, 60, 60, 25);
        add(txtPrecioCompra);

        btnAgregar = new JButton("Agregar Detalle");
        btnAgregar.setBounds(400, 60, 140, 25);
        add(btnAgregar);

        modeloCompra = new DefaultTableModel(new String[]{"ID Producto", "Cantidad", "Precio Unitario", "Subtotal"}, 0);
        tablaCompra = new JTable(modeloCompra);
        JScrollPane scroll = new JScrollPane(tablaCompra);
        scroll.setBounds(20, 110, 640, 270);
        add(scroll);

        btnProcesar = new JButton("Registrar Compra y Aumentar Stock");
        btnProcesar.setBounds(360, 400, 300, 35);
        add(btnProcesar);

        btnAgregar.addActionListener(e -> {
            try {
                int idProd = Integer.parseInt(txtIdProducto.getText());
                int cant = Integer.parseInt(txtCantidad.getText());
                double precio = Double.parseDouble(txtPrecioCompra.getText());

                DetalleCompra dc = new DetalleCompra();
                dc.setIdProducto(idProd);
                dc.setCantidad(cant);
                dc.setPrecioCompra(precio);
                listaDetalle.add(dc);

                modeloCompra.addRow(new Object[]{idProd, cant, precio, (cant * precio)});
                JOptionPane.showMessageDialog(null, "Item agregado a la compra.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese valores numericos validos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnProcesar.addActionListener(e -> {
            try {
                int idProv = Integer.parseInt(txtIdProveedor.getText());
                String nroDoc = txtNroDoc.getText();

                double total = 0;
                for (DetalleCompra dc : listaDetalle) {
                    total += dc.getCantidad() * dc.getPrecioCompra();
                }

                Compra compra = new Compra();
                compra.setIdProveedor(idProv);
                compra.setIdUsuario(1);
                compra.setNroDocumento(nroDoc);
                compra.setTotal(total);

                boolean exito = compraController.registrarCompra(compra, listaDetalle);
                if (exito) {
                    JOptionPane.showMessageDialog(null, "¡Compra registrada con exito y stock actualizado!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }
}