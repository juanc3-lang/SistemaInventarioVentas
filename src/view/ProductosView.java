package view;

import controller.ProductoController;
import model.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductosView extends JFrame {
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscarCodigo;
    private JButton btnBuscarBinario, btnOrdenarQuickSort;
    private ProductoController productoController;

    public ProductosView() {
        productoController = new ProductoController();
        setTitle("Modulo de Productos - Estructuras de Datos");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBuscar = new JLabel("Buscar Codigo:");
        lblBuscar.setBounds(20, 20, 100, 25);
        add(lblBuscar);

        txtBuscarCodigo = new JTextField();
        txtBuscarCodigo.setBounds(120, 20, 150, 25);
        add(txtBuscarCodigo);

        btnBuscarBinario = new JButton("Busqueda Binaria");
        btnBuscarBinario.setBounds(280, 20, 140, 25);
        add(btnBuscarBinario);

        btnOrdenarQuickSort = new JButton("QuickSort (Memoria)");
        btnOrdenarQuickSort.setBounds(430, 20, 160, 25);
        add(btnOrdenarQuickSort);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Codigo", "Nombre", "Precio Venta", "Stock"}, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBounds(20, 70, 690, 360);
        add(scroll);

        cargarTabla(productoController.getListaProductosMemoria());

        btnBuscarBinario.addActionListener(e -> {
            String codigo = txtBuscarCodigo.getText().trim();
            if (!codigo.isEmpty()) {
                Producto encontrado = productoController.buscarPorCodigoBinaria(codigo);
                modeloTabla.setRowCount(0);
                if (encontrado != null) {
                    modeloTabla.addRow(new Object[]{
                        encontrado.getIdProducto(),
                        encontrado.getCodigo(),
                        encontrado.getNombreProducto(),
                        encontrado.getPrecioVenta(),
                        encontrado.getStockActual()
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado por Busqueda Binaria.");
                    cargarTabla(productoController.getListaProductosMemoria());
                }
            }
        });

        btnOrdenarQuickSort.addActionListener(e -> {
            List<Producto> lista = productoController.getListaProductosMemoria();
            productoController.ordenarPorCodigoQuickSort(0, lista.size() - 1);
            cargarTabla(lista);
            JOptionPane.showMessageDialog(null, "Lista ordenada con exito usando QuickSort.");
        });
    }

    private void cargarTabla(List<Producto> lista) {
        modeloTabla.setRowCount(0);
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