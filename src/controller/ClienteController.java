package controller;

import dao.ClienteDAO;
import model.Cliente;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public boolean registrarCliente(Cliente cliente) {
        // Cambiamos "insertar" por "registrar" para que coincida con el DAO
        return clienteDAO.registrar(cliente); 
    }
}