package com.cibertec.beans;

public class Participante {
    private int idParticipante;
    private String nombre;
    private String email;
    private String telefono;
    private int idEvento;

    public Participante() {}

    public Participante(int idParticipante, String nombre, String email, String telefono, int idEvento) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.idEvento = idEvento;
    }

    // Getters y setters
    public int getIdParticipante() { return idParticipante; }
    public void setIdParticipante(int idParticipante) { this.idParticipante = idParticipante; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }
}