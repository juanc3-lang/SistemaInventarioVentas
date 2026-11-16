package dao;

import model.DetalleCompra;
import model.Compra;
import util.ConexionDB;
import java.sql.*;
import java.util.List;

public class CompraDAO {

    public boolean registrarCompraTransaccion(Compra compra, List<DetalleCompra> detalleList) {
        // Consulta adaptada exactamente a las columnas que tiene tu tabla compras en la base de datos
        String sqlCompra = "INSERT INTO Compras (IdProveedor, IdUsuario, Total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO DetalleCompra (IdCompra, IdProducto, Cantidad, PrecioCompra) VALUES (?, ?, ?, ?)";
        String sqlStock = "UPDATE Productos SET StockActual = StockActual + ? WHERE IdProducto = ?";

        Connection con = null;
        PreparedStatement psCompra = null;
        PreparedStatement psDetalle = null;
        PreparedStatement psStock = null;
        ResultSet rsKeys = null;

        try {
            con = ConexionDB.conectar();
            con.setAutoCommit(false);

            psCompra = con.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
            psCompra.setInt(1, compra.getIdProveedor());
            psCompra.setInt(2, compra.getIdUsuario());
            psCompra.setDouble(3, compra.getTotal());
            psCompra.executeUpdate();

            rsKeys = psCompra.getGeneratedKeys();
            int idCompraGenerado = 0;
            if (rsKeys.next()) {
                idCompraGenerado = rsKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la compra.");
            }

            psDetalle = con.prepareStatement(sqlDetalle);
            psStock = con.prepareStatement(sqlStock);

            for (DetalleCompra item : detalleList) {
                psDetalle.setInt(1, idCompraGenerado);
                psDetalle.setInt(2, item.getIdProducto());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.setDouble(4, item.getPrecioCompra());
                psDetalle.executeUpdate();

                psStock.setInt(1, item.getCantidad());
                psStock.setInt(2, item.getIdProducto());
                psStock.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en compra (Rollback aplicado): " + e.getMessage());
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            try {
                if (rsKeys != null) rsKeys.close();
                if (psCompra != null) psCompra.close();
                if (psDetalle != null) psDetalle.close();
                if (psStock != null) psStock.close();
                if (con != null) con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}