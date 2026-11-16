package dao;

import util.ConexionDB; // <-- Aquí está el cambio correcto según tu clase
import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public boolean registrar(Cliente cliente) {
        String sql = "INSERT INTO clientes (NroDocumento, Nombres, Apellidos, Telefono) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); // <-- Y aquí se llama a tu método
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNroDocumento());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getTelefono());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection con = ConexionDB.conectar(); // <-- Y aquí también
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("IdCliente"));
                c.setNroDocumento(rs.getString("NroDocumento"));
                c.setNombres(rs.getString("Nombres"));
                c.setApellidos(rs.getString("Apellidos"));
                c.setTelefono(rs.getString("Telefono"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }
}