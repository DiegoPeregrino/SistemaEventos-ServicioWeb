package com.cibertec.beans;

import java.util.Date;

public class Evento {
    private int idEvento;
    private String nombre;
    private String descripcion;
    private Date fechaEvento;
    private String ubicacion;
    private String estado;
    private String organizador;

    public Evento() {}

    public Evento(int idEvento, String nombre, String descripcion, Date fechaEvento, String ubicacion, String estado, String organizador) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.organizador = organizador;
    }

    // Getters y setters
    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(Date fechaEvento) { this.fechaEvento = fechaEvento; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getOrganizador() { return organizador; }
    public void setOrganizador(String organizador) { this.organizador = organizador; }
}