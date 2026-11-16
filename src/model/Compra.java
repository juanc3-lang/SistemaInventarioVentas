package model;

public class Compra {
    private int idCompra;
    private int idProveedor;
    private int idUsuario;
    private String fechaCompra;
    private String nroDocumento;
    private double total;
    private String estado;

    public Compra() {}

    public Compra(int idCompra, int idProveedor, int idUsuario, String fechaCompra, String nroDocumento, double total, String estado) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
        this.fechaCompra = fechaCompra;
        this.nroDocumento = nroDocumento;
        this.total = total;
        this.estado = estado;
    }

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(String fechaCompra) { this.fechaCompra = fechaCompra; }

    public String getNroDocumento() { return nroDocumento; }
    public void setNroDocumento(String nroDocumento) { this.nroDocumento = nroDocumento; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}