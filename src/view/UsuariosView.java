package view;

import controller.UsuarioController;
import model.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuariosView extends JFrame {
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private UsuarioController usuarioController;

    public UsuariosView() {
        usuarioController = new UsuarioController();
        setTitle("Modulo de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Usuario", "Nombre Completo", "Rol"}, 0);
        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        scroll.setBounds(20, 20, 540, 320);
        add(scroll);

        cargarTabla();
    }

private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Usuario> lista = usuarioController.listarUsuarios();
        if (lista != null) {
            for (Usuario u : lista) {
                modeloTabla.addRow(new Object[]{
                    u.getIdUsuario(),
                    u.getNombreUsuario(), // <-- Aquí volvemos a getNombreUsuario()
                    u.getNombreCompleto(),
                    u.getRol()
                });
            }
        }
    }
}