package com.cibertec.services;

import com.cibertec.beans.Evento;
import miLib.ConexionBDMySql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WSEvento implements CRUDService<Evento> {

    @Override
    public int registrar(Evento evento) {
        int registrados = 0;
        String sql = "INSERT INTO TEVENTO (NOMBRE, DESCRIPCION, FECHA_EVENTO, UBICACION, ESTADO, ORGANIZADOR) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getDescripcion());
            ps.setDate(3, new java.sql.Date(evento.getFechaEvento().getTime()));
            ps.setString(4, evento.getUbicacion());
            ps.setString(5, evento.getEstado());
            ps.setString(6, evento.getOrganizador());
            registrados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrados;
    }

    @Override
    public int actualizar(int id, Evento evento) {
        int actualizados = 0;
        String sql = "UPDATE TEVENTO SET NOMBRE = ?, DESCRIPCION = ?, FECHA_EVENTO = ?, UBICACION = ?, ESTADO = ?, ORGANIZADOR = ? WHERE ID_EVENTO = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getDescripcion());
            ps.setDate(3, new java.sql.Date(evento.getFechaEvento().getTime()));
            ps.setString(4, evento.getUbicacion());
            ps.setString(5, evento.getEstado());
            ps.setString(6, evento.getOrganizador());
            ps.setInt(7, id);
            actualizados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actualizados;
    }

    @Override
    public int eliminar(int id) {
        int eliminados = 0;
        String sql = "DELETE FROM TEVENTO WHERE ID_EVENTO = ?";

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
    public Evento buscarPorId(int id) {
        Evento evento = null;
        String sql = "SELECT * FROM TEVENTO WHERE ID_EVENTO = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                evento = new Evento(
                    rs.getInt("ID_EVENTO"),
                    rs.getString("NOMBRE"),
                    rs.getString("DESCRIPCION"),
                    rs.getDate("FECHA_EVENTO"),
                    rs.getString("UBICACION"),
                    rs.getString("ESTADO"),
                    rs.getString("ORGANIZADOR")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evento;
    }

    @Override
    public List<Evento> listar() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM TEVENTO";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento(
                    rs.getInt("ID_EVENTO"),
                    rs.getString("NOMBRE"),
                    rs.getString("DESCRIPCION"),
                    rs.getDate("FECHA_EVENTO"),
                    rs.getString("UBICACION"),
                    rs.getString("ESTADO"),
                    rs.getString("ORGANIZADOR")
                );
                lista.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
