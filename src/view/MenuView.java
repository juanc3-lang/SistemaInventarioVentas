package view;

import javax.swing.*;

public class MenuView extends JFrame {

    public MenuView() {
        setTitle("Menu Principal - Sistema de Inventario y Ventas");
        setSize(500, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnProductos = new JButton("Gestion de Productos (Busqueda Binaria / QuickSort)");
        btnProductos.setBounds(50, 30, 380, 35);
        add(btnProductos);

        JButton btnMantProductos = new JButton("Mantenimiento de Productos (CRUD BD)");
        btnMantProductos.setBounds(50, 80, 380, 35);
        add(btnMantProductos);

        JButton btnVentas = new JButton("Punto de Ventas (Ventas)");
        btnVentas.setBounds(50, 130, 380, 35);
        add(btnVentas);

        JButton btnClientes = new JButton("Gestion de Clientes");
        btnClientes.setBounds(50, 180, 380, 35);
        add(btnClientes);

        JButton btnProveedores = new JButton("Gestion de Proveedores");
        btnProveedores.setBounds(50, 230, 380, 35);
        add(btnProveedores);

        JButton btnCompras = new JButton("Gestion de Compras");
        btnCompras.setBounds(50, 280, 380, 35);
        add(btnCompras);

        JButton btnUsuarios = new JButton("Gestion de Usuarios");
        btnUsuarios.setBounds(50, 330, 380, 35);
        add(btnUsuarios);

        JButton btnReportes = new JButton("Reportes del Sistema");
        btnReportes.setBounds(50, 380, 380, 35);
        add(btnReportes);

        // Eventos de los botones
        btnProductos.addActionListener(e -> new ProductosView().setVisible(true));
        btnMantProductos.addActionListener(e -> new ProductoMantView().setVisible(true));
        btnVentas.addActionListener(e -> new VentasView().setVisible(true));
        btnClientes.addActionListener(e -> new ClientesView().setVisible(true));
        btnProveedores.addActionListener(e -> new ProveedoresView().setVisible(true));
        btnCompras.addActionListener(e -> new ComprasView().setVisible(true));
        btnUsuarios.addActionListener(e -> new UsuariosView().setVisible(true));
        btnReportes.addActionListener(e -> new ReportesView().setVisible(true));
    }
}