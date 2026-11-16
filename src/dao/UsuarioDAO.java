package dao;

import model.Usuario;
import util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario validarLogin(String user, String pass) {
        Usuario usuario = null;
        String sql = "SELECT IdUsuario, NombreUsuario, Password, Rol FROM Usuarios WHERE NombreUsuario = ? AND Password = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("IdUsuario"));
                    usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                    usuario.setRol(rs.getString("Rol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT IdUsuario, NombreUsuario, Password, Rol FROM Usuarios";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("IdUsuario"));
                u.setNombreUsuario(rs.getString("NombreUsuario"));
                // Si tu modelo requiere NombreCompleto pero no está en la BD, 
                // le asignamos el mismo NombreUsuario temporalmente para que no falle la vista:
                u.setNombreCompleto(rs.getString("NombreUsuario")); 
                u.setRol(rs.getString("Rol"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }
}