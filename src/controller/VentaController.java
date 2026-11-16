package controller;

import dao.VentaDAO;
import model.DetalleVenta;
import model.Venta;
import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public boolean procesarVenta(Venta venta, List<DetalleVenta> detalleList) {
        if (detalleList == null || detalleList.isEmpty()) {
            return false;
        }
        return ventaDAO.registrarVentaTransaccion(venta, detalleList);
    }

    // NUEVO MÉTODO PARA LLAMAR AL DAO DESDE EL CONTROLADOR
    public List<Venta> listarVentasPorFecha(String fecha) {
        return ventaDAO.listarVentasPorFecha(fecha);
    }
}