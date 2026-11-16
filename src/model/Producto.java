package model;

public class Producto {
    private int idProducto;
    private String codigo;
    private String nombreProducto;
    private String descripcion;
    private int idCategoria;
    private int idProveedor;
    private String unidadMedida;
    private double precioCompra;
    private double precioVenta;
    private int stockActual;
    private int stockMinimo;
    private int activo;

    public Producto() {}

    public Producto(int idProducto, String codigo, String nombreProducto, String descripcion, int idCategoria, int idProveedor, String unidadMedida, double precioCompra, double precioVenta, int stockActual, int stockMinimo, int activo) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.unidadMedida = unidadMedida;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.activo = activo;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }
}