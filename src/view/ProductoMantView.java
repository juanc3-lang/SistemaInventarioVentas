package view;

import dao.ProductoDAO;
import model.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoMantView extends JFrame {
    private JTextField txtCodigo, txtNombre, txtPrecioCompra, txtPrecioVenta, txtStock, txtStockMin, txtIdCat, txtIdProv;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar, btnEliminar, btnLimpiar;
    private ProductoDAO productoDAO;
    private int idProductoSeleccionado = 0;

    public ProductoMantView() {
        productoDAO = new ProductoDAO();
        setTitle("Mantenimiento de Productos - Inventario");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiquetas y Campos de texto
        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(20, 20, 100, 25);
        add(lblCodigo);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 20, 150, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 150, 25);
        add(txtNombre);

        JLabel lblPCompra = new JLabel("P. Compra:");
        lblPCompra.setBounds(20, 100, 100, 25);
        add(lblPCompra);
        txtPrecioCompra = new JTextField();
        txtPrecioCompra.setBounds(120, 100, 150, 25);
        add(txtPrecioCompra);

        JLabel lblPVenta = new JLabel("P. Venta:");
        lblPVenta.setBounds(20, 140, 100, 25);
        add(lblPVenta);
        txtPrecioVenta = new JTextField();
        txtPrecioVenta.setBounds(120, 140, 150, 25);
        add(txtPrecioVenta);

        JLabel lblStock = new JLabel("Stock Actual:");
        lblStock.setBounds(20, 180, 100, 25);
        add(lblStock);
        txtStock = new JTextField();
        txtStock.setBounds(120, 180, 150, 25);
        add(txtStock);

        JLabel lblStockMin = new JLabel("Stock Min:");
        lblStockMin.setBounds(20, 220, 100, 25);
        add(lblStockMin);
        txtStockMin = new JTextField();
        txtStockMin.setBounds(120, 220, 150, 25);
        add(txtStockMin);

        JLabel lblCat = new JLabel("ID Categoria:");
        lblCat.setBounds(20, 260, 100, 25);
        add(lblCat);
        txtIdCat = new JTextField();
        txtIdCat.setBounds(120, 260, 150, 25);
        add(txtIdCat);

        JLabel lblProv = new JLabel("ID Proveedor:");
        lblProv.setBounds(20, 300, 100, 25);
        add(lblProv);
        txtIdProv = new JTextField();
        txtIdProv.setBounds(120, 300, 150, 25);
        add(txtIdProv);

        // Botones de accion
        btnGuardar = new JButton("Guardar / Registrar");
        btnGuardar.setBounds(20, 350, 250, 30);
        add(btnGuardar);

        btnEliminar = new JButton("Eliminar Seleccionado");
        btnEliminar.setBounds(20, 390, 250, 30);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar Campos");
        btnLimpiar.setBounds(20, 430, 250, 30);
        add(btnLimpiar);

        // Tabla para visualizar productos guardados
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Codigo", "Nombre", "P. Venta", "Stock"}, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBounds(300, 20, 515, 440);
        add(scroll);

        // Cargar datos al iniciar la ventana
        cargarTabla();

        // Eventos de botones
        btnGuardar.addActionListener(e -> guardarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Evento al hacer clic en una fila de la tabla para cargar datos en los campos
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tablaProductos.getSelectedRow();
                if (fila != -1) {
                    idProductoSeleccionado = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                    txtCodigo.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtNombre.setText(modeloTabla.getValueAt(fila, 2).toString());
                    txtPrecioVenta.setText(modeloTabla.getValueAt(fila, 3).toString());
                    txtStock.setText(modeloTabla.getValueAt(fila, 4).toString());
                }
            }
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Producto> lista = productoDAO.listarProductos();
        if (lista != null) {
            for (Producto p : lista) {
                modeloTabla.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getCodigo(),
                    p.getNombreProducto(),
                    p.getPrecioVenta(),
                    p.getStockActual()
                });
            }
        }
    }

    private void guardarProducto() {
        try {
            // Validar campos obligatorios vacíos
            if (txtCodigo.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() || 
                txtPrecioCompra.getText().trim().isEmpty() || txtPrecioVenta.getText().trim().isEmpty() || 
                txtStock.getText().trim().isEmpty() || txtStockMin.getText().trim().isEmpty() || 
                txtIdCat.getText().trim().isEmpty() || txtIdProv.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos antes de guardar.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idCat = Integer.parseInt(txtIdCat.getText().trim());
            int idProv = Integer.parseInt(txtIdProv.getText().trim());

            // Imprime en consola de NetBeans para verificar qué números está tomando
            System.out.println("Intentando guardar -> ID Categoria enviado: " + idCat + " | ID Proveedor enviado: " + idProv);

            Producto p = new Producto();
            p.setCodigo(txtCodigo.getText().trim());
            p.setNombreProducto(txtNombre.getText().trim());
            p.setDescripcion("Sin descripcion");
            p.setIdCategoria(idCat);
            p.setIdProveedor(idProv);
            p.setUnidadMedida("Und");
            p.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText().trim()));
            p.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText().trim()));
            p.setStockActual(Integer.parseInt(txtStock.getText().trim()));
            p.setStockMinimo(Integer.parseInt(txtStockMin.getText().trim()));
            p.setActivo(1);

            boolean resultado = false;
            if (idProductoSeleccionado == 0) {
                resultado = productoDAO.registrarProducto(p);
            } else {
                p.setIdProducto(idProductoSeleccionado);
                resultado = productoDAO.actualizarProducto(p);
            }

            if (resultado) {
                JOptionPane.showMessageDialog(null, "¡Operación realizada con éxito en la base de datos!");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "La base de datos rechazó el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error de formato numérico: Ingrese solo números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        if (idProductoSeleccionado != 0) {
            productoDAO.eliminarProducto(idProductoSeleccionado);
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
            cargarTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla para eliminar.");
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtStock.setText("");
        txtStockMin.setText("");
        txtIdCat.setText("");
        txtIdProv.setText("");
        idProductoSeleccionado = 0;
    }
}