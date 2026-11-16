package view;

import controller.VentaController;
import model.DetalleVenta;
import model.Venta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class VentasView extends JFrame {
    private JTextField txtIdCliente, txtIdProducto, txtCantidad, txtPrecio, txtNroComprobante;
    private JButton btnAgregarItem, btnProcesarVenta;
    private JTable tablaCarrito;
    private DefaultTableModel modeloCarrito;
    private List<DetalleVenta> listaDetalle;
    private VentaController ventaController;

    public VentasView() {
        ventaController = new VentaController();
        listaDetalle = new ArrayList<>();

        setTitle("Punto de Venta - Transacciones");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblCliente = new JLabel("ID Cliente:");
        lblCliente.setBounds(20, 20, 80, 25);
        add(lblCliente);
        txtIdCliente = new JTextField("1");
        txtIdCliente.setBounds(100, 20, 60, 25);
        add(txtIdCliente);

        JLabel lblComp = new JLabel("Comprobante:");
        lblComp.setBounds(180, 20, 90, 25);
        add(lblComp);
        txtNroComprobante = new JTextField("B001-0001");
        txtNroComprobante.setBounds(280, 20, 120, 25);
        add(txtNroComprobante);

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

        JLabel lblPrec = new JLabel("Precio:");
        lblPrec.setBounds(230, 60, 50, 25);
        add(lblPrec);
        txtPrecio = new JTextField();
        txtPrecio.setBounds(280, 60, 60, 25);
        add(txtPrecio);

        btnAgregarItem = new JButton("Agregar al Carrito");
        btnAgregarItem.setBounds(360, 60, 150, 25);
        add(btnAgregarItem);

        modeloCarrito = new DefaultTableModel(new String[]{"ID Producto", "Cantidad", "Precio Unitario", "Subtotal"}, 0);
        tablaCarrito = new JTable(modeloCarrito);
        JScrollPane scroll = new JScrollPane(tablaCarrito);
        scroll.setBounds(20, 110, 640, 270);
        add(scroll);

        btnProcesarVenta = new JButton("Procesar Venta y Descontar Stock");
        btnProcesarVenta.setBounds(380, 400, 280, 35);
        add(btnProcesarVenta);

        btnAgregarItem.addActionListener(e -> {
            try {
                int idProd = Integer.parseInt(txtIdProducto.getText());
                int cant = Integer.parseInt(txtCantidad.getText());
                double precio = Double.parseDouble(txtPrecio.getText());

                DetalleVenta dv = new DetalleVenta();
                dv.setIdProducto(idProd);
                dv.setCantidad(cant);
                dv.setPrecioVenta(precio);
                listaDetalle.add(dv);

                modeloCarrito.addRow(new Object[]{idProd, cant, precio, (cant * precio)});
                JOptionPane.showMessageDialog(null, "Item agregado al carrito.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese valores numericos validos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnProcesarVenta.addActionListener(e -> {
            try {
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                String nroComp = txtNroComprobante.getText();

                double total = 0;
                for (DetalleVenta dv : listaDetalle) {
                    total += dv.getCantidad() * dv.getPrecioVenta();
                }

                Venta venta = new Venta();
                venta.setIdCliente(idCliente);
                venta.setIdUsuario(1);
                venta.setTipoComprobante("Boleta");
                venta.setNroComprobante(nroComp);
                venta.setTotal(total);

                boolean exito = ventaController.procesarVenta(venta, listaDetalle);
                if (exito) {
                    JOptionPane.showMessageDialog(null, "¡Venta registrada con exito y stock actualizado!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al procesar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }
}