package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // Si tu puerto es 3307, cambia 3306 por 3307 aquí:
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_inventario_ventas?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Coloca tu contraseña de root si tienes una

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontro el Driver de MySQL -> " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de conexion a la BD: " + e.getMessage());
        }
        return con;
    }
}