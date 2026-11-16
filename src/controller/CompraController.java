package controller;

import dao.CompraDAO;
import model.Compra;
import model.DetalleCompra;
import java.util.List;

public class CompraController {
    private CompraDAO compraDAO;

    public CompraController() {
        this.compraDAO = new CompraDAO();
    }

    public boolean registrarCompra(Compra compra, List<DetalleCompra> detalleList) {
        if (detalleList == null || detalleList.isEmpty()) {
            return false;
        }
        return compraDAO.registrarCompraTransaccion(compra, detalleList);
    }
}