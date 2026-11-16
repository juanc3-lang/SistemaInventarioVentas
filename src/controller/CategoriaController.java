package controller;

import dao.CategoriaDAO;
import model.Categoria;
import java.util.List;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listar();
    }

    public boolean registrarCategoria(Categoria categoria) {
        return categoriaDAO.insertar(categoria);
    }
}