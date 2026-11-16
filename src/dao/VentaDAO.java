package dao;

import model.DetalleVenta;
import model.Venta;
import util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    public boolean registrarVentaTransaccion(Venta venta, List<DetalleVenta> detalleList) {
        String sqlVenta = "INSERT INTO Ventas (IdCliente, IdUsuario, TipoComprobante, NroComprobante, Total, Estado) VALUES (?, ?, ?, ?, ?, 'Registrada')";
        String sqlDetalle = "INSERT INTO DetalleVenta (IdVenta, IdProducto, Cantidad, PrecioVenta) VALUES (?, ?, ?, ?)";
        String sqlStock = "UPDATE Productos SET StockActual = StockActual - ? WHERE IdProducto = ?";

        Connection con = null;
        PreparedStatement psVenta = null;
        PreparedStatement psDetalle = null;
        PreparedStatement psStock = null;
        ResultSet rsKeys = null;

        try {
            con = ConexionDB.conectar();
            con.setAutoCommit(false);

            psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, venta.getIdCliente());
            psVenta.setInt(2, venta.getIdUsuario());
            psVenta.setString(3, venta.getTipoComprobante());
            psVenta.setString(4, venta.getNroComprobante());
            psVenta.setDouble(5, venta.getTotal());
            psVenta.executeUpdate();

            rsKeys = psVenta.getGeneratedKeys();
            int idVentaGenerado = 0;
            if (rsKeys.next()) {
                idVentaGenerado = rsKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la venta.");
            }

            psDetalle = con.prepareStatement(sqlDetalle);
            psStock = con.prepareStatement(sqlStock);

            for (DetalleVenta item : detalleList) {
                psDetalle.setInt(1, idVentaGenerado);
                psDetalle.setInt(2, item.getIdProducto());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.setDouble(4, item.getPrecioVenta());
                psDetalle.executeUpdate();

                psStock.setInt(1, item.getCantidad());
                psStock.setInt(2, item.getIdProducto());
                psStock.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en venta (Rollback aplicado): " + e.getMessage());
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            try {
                if (rsKeys != null) rsKeys.close();
                if (psVenta != null) psVenta.close();
                if (psDetalle != null) psDetalle.close();
                if (psStock != null) psStock.close();
                if (con != null) con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public List<Venta> listarVentas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ventas";
        try (Connection con = util.ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("IdVenta"));
                v.setIdCliente(rs.getInt("IdCliente"));
                v.setIdUsuario(rs.getInt("IdUsuario"));
                v.setFechaVenta(rs.getString("FechaVenta"));
                v.setTipoComprobante(rs.getString("TipoComprobante"));
                v.setNroComprobante(rs.getString("NroComprobante"));
                v.setTotal(rs.getDouble("Total"));
                v.setEstado(rs.getString("Estado"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    // NUEVO MÉTODO PARA FILTRAR VENTAS POR FECHA (Formato esperado: "YYYY-MM-DD")
    public List<Venta> listarVentasPorFecha(String fechaBusqueda) {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ventas WHERE DATE(FechaVenta) = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fechaBusqueda);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Venta v = new Venta();
                    v.setIdVenta(rs.getInt("IdVenta"));
                    v.setIdCliente(rs.getInt("IdCliente"));
                    v.setIdUsuario(rs.getInt("IdUsuario"));
                    v.setFechaVenta(rs.getString("FechaVenta"));
                    v.setTipoComprobante(rs.getString("TipoComprobante"));
                    v.setNroComprobante(rs.getString("NroComprobante"));
                    v.setTotal(rs.getDouble("Total"));
                    v.setEstado(rs.getString("Estado"));
                    lista.add(v);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar ventas por fecha: " + e.getMessage());
        }
        return lista;
    }
}