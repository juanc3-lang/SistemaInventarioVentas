package model;

public class Proveedor {
    private int idProveedor;
    private String razonSocial;
    private String ruc;
    private String telefono;
    private String email;
    private String direccion;
    private int activo;

    public Proveedor() {}

    public Proveedor(int idProveedor, String razonSocial, String ruc, String telefono, String email, String direccion, int activo) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.activo = activo;
    }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }
}   