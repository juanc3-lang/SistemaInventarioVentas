package controller;

import dao.ProveedorDAO;
import model.Proveedor;
import java.util.List;

public class ProveedorController {
    private ProveedorDAO proveedorDAO;

    public ProveedorController() {
        this.proveedorDAO = new ProveedorDAO();
    }

    public List<Proveedor> listarProveedores() {
        return proveedorDAO.listar();
    }

    public boolean registrarProveedor(Proveedor proveedor) {
        return proveedorDAO.insertar(proveedor);
    }
}