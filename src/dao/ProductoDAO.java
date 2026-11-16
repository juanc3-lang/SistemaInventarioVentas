package dao;

import model.Producto;
import util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listarProductosEnMemoria() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT IdProducto, Codigo, NombreProducto, Descripcion, IdCategoria, IdProveedor, UnidadMedida, PrecioCompra, PrecioVenta, StockActual, StockMinimo, Activo FROM Productos WHERE Activo = 1";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("IdProducto"),
                    rs.getString("Codigo"),
                    rs.getString("NombreProducto"),
                    rs.getString("Descripcion"),
                    rs.getInt("IdCategoria"),
                    rs.getInt("IdProveedor"),
                    rs.getString("UnidadMedida"),
                    rs.getDouble("PrecioCompra"),
                    rs.getDouble("PrecioVenta"),
                    rs.getInt("StockActual"),
                    rs.getInt("StockMinimo"),
                    rs.getInt("Activo")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos en memoria: " + e.getMessage());
        }
        return lista;
    }

    // Método requerido por la vista de mantenimiento para la tabla
    public List<Producto> listarProductos() {
        return listarProductosEnMemoria();
    }

    // Método para obtener productos con stock bajo o crítico (StockActual <= StockMinimo)
    public List<Producto> listarStockBajo() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT IdProducto, Codigo, NombreProducto, Descripcion, IdCategoria, IdProveedor, UnidadMedida, PrecioCompra, PrecioVenta, StockActual, StockMinimo, Activo FROM Productos WHERE StockActual <= StockMinimo AND Activo = 1";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("IdProducto"),
                    rs.getString("Codigo"),
                    rs.getString("NombreProducto"),
                    rs.getString("Descripcion"),
                    rs.getInt("IdCategoria"),
                    rs.getInt("IdProveedor"),
                    rs.getString("UnidadMedida"),
                    rs.getDouble("PrecioCompra"),
                    rs.getDouble("PrecioVenta"),
                    rs.getInt("StockActual"),
                    rs.getInt("StockMinimo"),
                    rs.getInt("Activo")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar stock bajo: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Producto p) {
        String sql = "INSERT INTO Productos (Codigo, NombreProducto, Descripcion, IdCategoria, IdProveedor, UnidadMedida, PrecioCompra, PrecioVenta, StockActual, StockMinimo, Activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombreProducto());
            ps.setString(3, p.getDescripcion());
            ps.setInt(4, p.getIdCategoria());
            ps.setInt(5, p.getIdProveedor());
            ps.setString(6, p.getUnidadMedida());
            ps.setDouble(7, p.getPrecioCompra());
            ps.setDouble(8, p.getPrecioVenta());
            ps.setInt(9, p.getStockActual());
            ps.setInt(10, p.getStockMinimo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // Método alias requerido por la vista de mantenimiento
    public boolean registrarProducto(Producto p) {
        return insertar(p);
    }

    public boolean actualizarProducto(Producto p) {
        String sql = "UPDATE Productos SET Codigo = ?, NombreProducto = ?, Descripcion = ?, IdCategoria = ?, IdProveedor = ?, UnidadMedida = ?, PrecioCompra = ?, PrecioVenta = ?, StockActual = ?, StockMinimo = ? WHERE IdProducto = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombreProducto());
            ps.setString(3, p.getDescripcion());
            ps.setInt(4, p.getIdCategoria());
            ps.setInt(5, p.getIdProveedor());
            ps.setString(6, p.getUnidadMedida());
            ps.setDouble(7, p.getPrecioCompra());
            ps.setDouble(8, p.getPrecioVenta());
            ps.setInt(9, p.getStockActual());
            ps.setInt(10, p.getStockMinimo());
            ps.setInt(11, p.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM Productos WHERE IdProducto = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}