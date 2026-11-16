package controller;

import dao.UsuarioDAO;
import model.Usuario;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }
}