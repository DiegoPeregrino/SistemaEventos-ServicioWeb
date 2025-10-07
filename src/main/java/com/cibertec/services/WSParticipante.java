package com.cibertec.services;

import com.cibertec.beans.Participante;
import miLib.ConexionBDMySql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WSParticipante implements CRUDService<Participante> {

    @Override
    public int registrar(Participante participante) {
        int registrados = 0;
        String sql = "INSERT INTO TPARTICIPANTE (NOMBRE, EMAIL, TELEFONO, ID_EVENTO) VALUES (?, ?, ?, ?)";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, participante.getNombre());
            ps.setString(2, participante.getEmail());
            ps.setString(3, participante.getTelefono());
            ps.setInt(4, participante.getIdEvento());
            registrados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrados;
    }

    @Override
    public int actualizar(int id, Participante participante) {
        int actualizados = 0;
        String sql = "UPDATE TPARTICIPANTE SET NOMBRE = ?, EMAIL = ?, TELEFONO = ?, ID_EVENTO = ? WHERE ID_PARTICIPANTE = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, participante.getNombre());
            ps.setString(2, participante.getEmail());
            ps.setString(3, participante.getTelefono());
            ps.setInt(4, participante.getIdEvento());
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
        String sql = "DELETE FROM TPARTICIPANTE WHERE ID_PARTICIPANTE = ?";

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
    public Participante buscarPorId(int id) {
        Participante participante = null;
        String sql = "SELECT * FROM TPARTICIPANTE WHERE ID_PARTICIPANTE = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                participante = new Participante(
                    rs.getInt("ID_PARTICIPANTE"),
                    rs.getString("NOMBRE"),
                    rs.getString("EMAIL"),
                    rs.getString("TELEFONO"),
                    rs.getInt("ID_EVENTO")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participante;
    }

    @Override
    public List<Participante> listar() {
        List<Participante> lista = new ArrayList<>();
        String sql = "SELECT * FROM TPARTICIPANTE";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Participante participante = new Participante(
                    rs.getInt("ID_PARTICIPANTE"),
                    rs.getString("NOMBRE"),
                    rs.getString("EMAIL"),
                    rs.getString("TELEFONO"),
                    rs.getInt("ID_EVENTO")
                );
                lista.add(participante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}