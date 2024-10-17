package com.cibertec.beans;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String tipoServicio;
    private String contacto;
    private int idEvento;

    public Proveedor() {}

    public Proveedor(int idProveedor, String nombre, String tipoServicio, String contacto, int idEvento) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.tipoServicio = tipoServicio;
        this.contacto = contacto;
        this.idEvento = idEvento;
    }

    // Getters y setters
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }
}