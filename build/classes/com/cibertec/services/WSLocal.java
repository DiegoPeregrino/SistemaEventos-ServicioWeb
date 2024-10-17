package com.cibertec.services;

import com.cibertec.beans.Local;
import miLib.ConexionBDMySql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WSLocal implements CRUDService<Local> {

    @Override
    public int registrar(Local local) {
        int registrados = 0;
        String sql = "INSERT INTO TLOCAL (NOMBRE, DIRECCION, CAPACIDAD, DISPONIBILIDAD) VALUES (?, ?, ?, ?)";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, local.getNombre());
            ps.setString(2, local.getDireccion());
            ps.setInt(3, local.getCapacidad());
            ps.setString(4, local.getDisponibilidad());
            registrados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrados;
    }

    @Override
    public int actualizar(int id, Local local) {
        int actualizados = 0;
        String sql = "UPDATE TLOCAL SET NOMBRE = ?, DIRECCION = ?, CAPACIDAD = ?, DISPONIBILIDAD = ? WHERE ID_LOCAL = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, local.getNombre());
            ps.setString(2, local.getDireccion());
            ps.setInt(3, local.getCapacidad());
            ps.setString(4, local.getDisponibilidad());
            ps.setInt(5, id);
            actualizados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actualizados;
    }

    @Override
    public int eliminar(int id) {
        int eliminados = 0;
        String sql = "DELETE FROM TLOCAL WHERE ID_LOCAL = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(1, id);
            eliminados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminados;
    }

    @Override
    public Local buscarPorId(int id) {
        Local local = null;
        String sql = "SELECT * FROM TLOCAL WHERE ID_LOCAL = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                local = new Local(
                    rs.getInt("ID_LOCAL"),
                    rs.getString("NOMBRE"),
                    rs.getString("DIRECCION"),
                    rs.getInt("CAPACIDAD"),
                    rs.getString("DISPONIBILIDAD")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return local;
    }

    @Override
    public List<Local> listar() {
        List<Local> lista = new ArrayList<>();
        String sql = "SELECT * FROM TLOCAL";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Local local = new Local(
                    rs.getInt("ID_LOCAL"),
                    rs.getString("NOMBRE"),
                    rs.getString("DIRECCION"),
                    rs.getInt("CAPACIDAD"),
                    rs.getString("DISPONIBILIDAD")
                );
                lista.add(local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
