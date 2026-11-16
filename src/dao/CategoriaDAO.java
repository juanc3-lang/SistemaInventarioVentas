package dao;

import model.Categoria;
import util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT IdCategoria, NombreCategoria, Descripcion FROM Categorias";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Categoria c = new Categoria(
                    rs.getInt("IdCategoria"),
                    rs.getString("NombreCategoria"),
                    rs.getString("Descripcion")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar categorias: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Categoria cat) {
        String sql = "INSERT INTO Categorias (NombreCategoria, Descripcion) VALUES (?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cat.getNombreCategoria());
            ps.setString(2, cat.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar categoria: " + e.getMessage());
            return false;
        }
    }
}