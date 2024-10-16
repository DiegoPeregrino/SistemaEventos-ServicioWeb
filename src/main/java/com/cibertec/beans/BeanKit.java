package com.cibertec.beans;

import java.sql.Date;

public class BeanKit {
    private int codiMedi;
    private String nombres;
    private String apellidos;
    private double tarifa;
    private Date fechaReg;
	
	public BeanKit(int codiMedi, String nombres, String apellidos, double tarifa, Date fechaReg) {
		super();
		this.codiMedi = codiMedi;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tarifa = tarifa;
		this.fechaReg = fechaReg;
	}

	public int getCodiMedi() {
		return codiMedi;
	}

	public void setCodiMedi(int codiMedi) {
		this.codiMedi = codiMedi;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public Date getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}
}
