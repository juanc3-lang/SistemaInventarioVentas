package dao;

import model.Proveedor;
import util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        // Solo consultamos las columnas que existen físicamente en tu tabla
        String sql = "SELECT IdProveedor, RazonSocial, RUC FROM Proveedores";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("IdProveedor"));
                p.setRazonSocial(rs.getString("RazonSocial"));
                p.setRuc(rs.getString("RUC"));
                p.setTelefono(""); // Valor por defecto ya que la columna no está en la BD
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Proveedor prov) {
        // Insertamos solo en las columnas reales
        String sql = "INSERT INTO Proveedores (RazonSocial, RUC) VALUES (?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, prov.getRazonSocial());
            ps.setString(2, prov.getRuc());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }
}