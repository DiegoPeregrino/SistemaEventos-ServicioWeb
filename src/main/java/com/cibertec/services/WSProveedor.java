package com.cibertec.services;

import com.cibertec.beans.Proveedor;
import miLib.ConexionBDMySql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WSProveedor implements CRUDService<Proveedor> {

    @Override
    public int registrar(Proveedor proveedor) {
        int registrados = 0;
        String sql = "INSERT INTO TPROVEEDOR (NOMBRE, TIPO_SERVICIO, CONTACTO, ID_EVENTO) VALUES (?, ?, ?, ?)";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTipoServicio());
            ps.setString(3, proveedor.getContacto());
            ps.setInt(4, proveedor.getIdEvento());
            registrados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrados;
    }

    @Override
    public int actualizar(int id, Proveedor proveedor) {
        int actualizados = 0;
        String sql = "UPDATE TPROVEEDOR SET NOMBRE = ?, TIPO_SERVICIO = ?, CONTACTO = ?, ID_EVENTO = ? WHERE ID_PROVEEDOR = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTipoServicio());
            ps.setString(3, proveedor.getContacto());
            ps.setInt(4, proveedor.getIdEvento());
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
        String sql = "DELETE FROM TPROVEEDOR WHERE ID_PROVEEDOR = ?";

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
    public Proveedor buscarPorId(int id) {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM TPROVEEDOR WHERE ID_PROVEEDOR = ?";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("bdEventos");
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                proveedor = new Proveedor(
                    rs.getInt("ID_PROVEEDOR"),
                    rs.getString("NOMBRE"),
                    rs.getString("TIPO_SERVICIO"),
                    rs.getString("CONTACTO"),
                    rs.getInt("ID_EVENTO")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedor;
    }

    @Override
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM TPROVEEDOR";

        try (Connection cnx = ConexionBDMySql.obtenerConexion("db_eventos");
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                    rs.getInt("ID_PROVEEDOR"),
                    rs.getString("NOMBRE"),
                    rs.getString("TIPO_SERVICIO"),
                    rs.getString("CONTACTO"),
                    rs.getInt("ID_EVENTO")
                );
                lista.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}