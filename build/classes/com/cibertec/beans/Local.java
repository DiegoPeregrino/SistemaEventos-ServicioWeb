package com.cibertec.beans;

public class Local {
    private int idLocal;
    private String nombre;
    private String direccion;
    private int capacidad;
    private String disponibilidad;

    public Local() {}

    public Local(int idLocal, String nombre, String direccion, int capacidad, String disponibilidad) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.disponibilidad = disponibilidad;
    }

    // Getters y setters
    public int getIdLocal() { return idLocal; }
    public void setIdLocal(int idLocal) { this.idLocal = idLocal; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(String disponibilidad) { this.disponibilidad = disponibilidad; }
}