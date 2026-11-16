package model;

public class Cliente {
    private int idCliente;
    private String tipoDocumento;
    private String nroDocumento;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String direccion;
    private int activo;

    public Cliente() {}

    public Cliente(int idCliente, String tipoDocumento, String nroDocumento, String nombres, String apellidos, String telefono, String email, String direccion, int activo) {
        this.idCliente = idCliente;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.activo = activo;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNroDocumento() { return nroDocumento; }
    public void setNroDocumento(String nroDocumento) { this.nroDocumento = nroDocumento; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }
}