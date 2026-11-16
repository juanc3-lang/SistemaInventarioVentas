package view;

import controller.VentaController;
import dao.ProductoDAO;
import model.Producto;
import model.Venta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ReportesView extends JFrame {
    private JTable tablaReporte;
    private DefaultTableModel modeloTabla;
    private JButton btnVentasDia, btnStockBajo, btnFiltrarFecha;
    private JTextField txtFecha;
    private VentaController ventaController;
    private ProductoDAO productoDAO;

    public ReportesView() {
        ventaController = new VentaController();
        productoDAO = new ProductoDAO();
        setTitle("Modulo de Reportes");
        setSize(780, 480);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Panel de Reportes del Sistema");
        lblTitulo.setBounds(300, 15, 220, 25);
        add(lblTitulo);

        // Botones de reportes generales
        btnVentasDia = new JButton("Todas las Ventas");
        btnVentasDia.setBounds(30, 50, 150, 30);
        add(btnVentasDia);

        btnStockBajo = new JButton("Reporte Stock Bajo");
        btnStockBajo.setBounds(190, 50, 150, 30);
        add(btnStockBajo);

        // Componentes para filtrar por fecha
        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(360, 55, 130, 20);
        add(lblFecha);

        txtFecha = new JTextField("2026-11-11");
        txtFecha.setBounds(490, 53, 90, 25);
        add(txtFecha);

        btnFiltrarFecha = new JButton("Buscar Fecha");
        btnFiltrarFecha.setBounds(590, 50, 140, 30);
        add(btnFiltrarFecha);

        modeloTabla = new DefaultTableModel(new String[]{"ID Venta", "ID Cliente", "Comprobante", "Nro Comprobante", "Total", "Fecha"}, 0);
        tablaReporte = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaReporte);
        scroll.setBounds(30, 100, 700, 310);
        add(scroll);

        // Eventos
        btnVentasDia.addActionListener(e -> cargarReporteVentas());
        btnStockBajo.addActionListener(e -> cargarReporteStockBajo());
        btnFiltrarFecha.addActionListener(e -> cargarVentasPorFechaSeleccionada());
    }

    private void cargarReporteVentas() {
        modeloTabla.setColumnIdentifiers(new String[]{"ID Venta", "ID Cliente", "Comprobante", "Nro Comprobante", "Total", "Fecha"});
        modeloTabla.setRowCount(0);
        List<Venta> listaVentas = ventaController.listarVentasPorFecha(""); // O puedes usar listarVentas() del DAO si prefieres
        
        // Usamos el DAO o método general si deseas listar todas, aquí llamaremos directo a listarVentas general o por controlador
        // Nota: para listar todas puedes mantener tu DAO directo o agregarlo al controlador. Usemos una consulta general:
        dao.VentaDAO vDao = new dao.VentaDAO();
        List<Venta> lista = vDao.listarVentas();

        if (lista != null && !lista.isEmpty()) {
            for (Venta v : lista) {
                modeloTabla.addRow(new Object[]{
                    v.getIdVenta(),
                    v.getIdCliente(),
                    v.getTipoComprobante(),
                    v.getNroComprobante(),
                    v.getTotal(),
                    v.getFechaVenta()
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay registros de ventas en la base de datos.");
        }
    }

    private void cargarVentasPorFechaSeleccionada() {
        modeloTabla.setColumnIdentifiers(new String[]{"ID Venta", "ID Cliente", "Comprobante", "Nro Comprobante", "Total", "Fecha"});
        modeloTabla.setRowCount(0);
        String fecha = txtFecha.getText().trim();
        
        List<Venta> listaVentas = ventaController.listarVentasPorFecha(fecha);
        
        if (listaVentas != null && !listaVentas.isEmpty()) {
            for (Venta v : listaVentas) {
                modeloTabla.addRow(new Object[]{
                    v.getIdVenta(),
                    v.getIdCliente(),
                    v.getTipoComprobante(),
                    v.getNroComprobante(),
                    v.getTotal(),
                    v.getFechaVenta()
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron ventas para la fecha: " + fecha);
        }
    }

    private void cargarReporteStockBajo() {
        modeloTabla.setColumnIdentifiers(new String[]{"ID Producto", "Producto", "Stock Actual", "Stock Mínimo", "Precio"});
        modeloTabla.setRowCount(0);
        List<Producto> listaStockBajo = productoDAO.listarStockBajo();
        
        if (listaStockBajo != null && !listaStockBajo.isEmpty()) {
            for (Producto p : listaStockBajo) {
                modeloTabla.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombreProducto(),
                    p.getStockActual(),
                    p.getStockMinimo(),
                    p.getPrecioVenta()
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos con stock bajo en este momento.");
        }
    }
}