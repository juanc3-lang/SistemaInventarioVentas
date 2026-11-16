package controller;

import dao.ProductoDAO;
import model.Producto;
import java.util.List;
import java.util.ArrayList;

public class ProductoController {
    private ProductoDAO productoDAO;
    private List<Producto> listaProductosMemoria;

    public ProductoController() {
        this.productoDAO = new ProductoDAO();
        this.listaProductosMemoria = new ArrayList<>();
        cargarProductosAMemoria();
    }

    public void cargarProductosAMemoria() {
        this.listaProductosMemoria = productoDAO.listarProductosEnMemoria();
        ordenarPorCodigoQuickSort(0, listaProductosMemoria.size() - 1);
    }

    public List<Producto> getListaProductosMemoria() {
        return listaProductosMemoria;
    }

    public void ordenarPorCodigoQuickSort(int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(bajo, alto);
            ordenarPorCodigoQuickSort(bajo, pi - 1);
            ordenarPorCodigoQuickSort(pi + 1, alto);
        }
    }

    private int particion(int bajo, int alto) {
        int pivoteNum = extraerNumero(listaProductosMemoria.get(alto).getCodigo());
        int i = (bajo - 1);
        
        for (int j = bajo; j < alto; j++) {
            int actualNum = extraerNumero(listaProductosMemoria.get(j).getCodigo());
            if (actualNum < pivoteNum) {
                i++;
                Producto temp = listaProductosMemoria.get(i);
                listaProductosMemoria.set(i, listaProductosMemoria.get(j));
                listaProductosMemoria.set(j, temp);
            }
        }
        Producto temp = listaProductosMemoria.get(i + 1);
        listaProductosMemoria.set(i + 1, listaProductosMemoria.get(alto));
        listaProductosMemoria.set(alto, temp);
        return i + 1;
    }

    // Método auxiliar para limpiar letras y comparar puramente los números
    private int extraerNumero(String codigo) {
        try {
            String soloNumeros = codigo.replaceAll("[^0-9]", "");
            return soloNumeros.isEmpty() ? 0 : Integer.parseInt(soloNumeros);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Producto buscarPorCodigoBinaria(String codigoBuscado) {
        ordenarPorCodigoQuickSort(0, listaProductosMemoria.size() - 1);
        
        int busquedaNum = extraerNumero(codigoBuscado);
        int izquierda = 0;
        int derecha = listaProductosMemoria.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Producto productoMedio = listaProductosMemoria.get(medio);
            int medioNum = extraerNumero(productoMedio.getCodigo());

            if (medioNum == busquedaNum) {
                return productoMedio; 
            }
            if (medioNum < busquedaNum) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return null; 
    }

    public boolean registrarProducto(Producto p) {
        boolean resultado = productoDAO.insertar(p);
        if (resultado) {
            cargarProductosAMemoria();
        }
        return resultado;
    }
}