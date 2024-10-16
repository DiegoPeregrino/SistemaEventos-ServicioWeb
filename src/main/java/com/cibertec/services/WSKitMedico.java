package com.cibertec.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cibertec.beans.BeanKit;
import miLib.ConexionBDMySql;

public class WSKitMedico {

    public int registrar(String nombres, String apellidos, double tarifa, java.sql.Date fechaReg) {
        int kitRegistrados = 0;

        Connection cnx = ConexionBDMySql.obtenerConexion("casobd");
        String sql = "INSERT INTO TKITROBOTICA (NOMBRES, APELLIDOS, TARIFA, FECHA_REG) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setDouble(3, tarifa);
            ps.setDate(4, fechaReg);
            kitRegistrados = ps.executeUpdate();
            ps.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kitRegistrados;
    }

    public int actualizar(int codiMedi, String nombres, String apellidos, double tarifa, java.sql.Date fechaReg) {
        int kitActualizados = 0;
        Connection cnx = ConexionBDMySql.obtenerConexion("casobd");
        String sql = "UPDATE TKITROBOTICA SET NOMBRES = ?, APELLIDOS = ?, TARIFA = ?, FECHA_REG = ? WHERE CODIMEDI = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setDouble(3, tarifa);
            ps.setDate(4, fechaReg);
            ps.setInt(5, codiMedi);
            kitActualizados = ps.executeUpdate();
            ps.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kitActualizados;
    }

    public int eliminar(int codiMedi) {
        int kitEliminados = 0;
        Connection cnx = ConexionBDMySql.obtenerConexion("casobd");
        String sql = "DELETE FROM TKITROBOTICA WHERE CODIMEDI = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, codiMedi);
            kitEliminados = ps.executeUpdate();
            ps.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kitEliminados;
    }

    public ArrayList<BeanKit> listar() {
        ArrayList<BeanKit> lista = new ArrayList<BeanKit>();
        Connection cnx = ConexionBDMySql.obtenerConexion("casobd");
        String sql = "SELECT * FROM TKITROBOTICA";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BeanKit bean = new BeanKit(rs.getInt("CODIMEDI"), rs.getString("NOMBRES"), 
                                           rs.getString("APELLIDOS"), rs.getDouble("TARIFA"), 
                                           rs.getDate("FECHA_REG"));
                lista.add(bean);
            }
            rs.close();
            st.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}