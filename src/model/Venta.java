package model;

public class Venta {
    private int idVenta;
    private int idCliente;
    private int idUsuario;
    private String fechaVenta;
    private String tipoComprobante;
    private String nroComprobante;
    private double total;
    private String estado;

    public Venta() {}

    public Venta(int idVenta, int idCliente, int idUsuario, String fechaVenta, String tipoComprobante, String nroComprobante, double total, String estado) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.fechaVenta = fechaVenta;
        this.tipoComprobante = tipoComprobante;
        this.nroComprobante = nroComprobante;
        this.total = total;
        this.estado = estado;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(String fechaVenta) { this.fechaVenta = fechaVenta; }

    public String getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(String tipoComprobante) { this.tipoComprobante = tipoComprobante; }

    public String getNroComprobante() { return nroComprobante; }
    public void setNroComprobante(String nroComprobante) { this.nroComprobante = nroComprobante; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}