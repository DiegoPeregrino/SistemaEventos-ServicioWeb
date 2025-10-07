package com.cibertec.api;

import com.cibertec.beans.Evento;
import com.cibertec.services.WSEvento;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventoServlet extends HttpServlet {
    private final WSEvento service = new WSEvento();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // / or /{id}
        resp.setContentType("application/json;charset=UTF-8");
        if (path == null || "/".equals(path)) {
            List<Evento> lista = service.listar();
            resp.getWriter().write(JsonUtil.toJson(lista));
            return;
        }
        try {
            int id = Integer.parseInt(path.substring(1));
            Evento e = service.buscarPorId(id);
            resp.getWriter().write(JsonUtil.toJson(e));
        } catch (Exception ex) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"id invalido\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Para simplicidad, leemos parámetros de formulario: nombre, descripcion, fechaEvento (yyyy-MM-dd), ubicacion, estado, organizador
        Evento e = new Evento();
        e.setNombre(req.getParameter("nombre"));
        e.setDescripcion(req.getParameter("descripcion"));
        try { e.setFechaEvento(java.sql.Date.valueOf(req.getParameter("fechaEvento"))); } catch (Exception ignored) {}
        e.setUbicacion(req.getParameter("ubicacion"));
        e.setEstado(req.getParameter("estado"));
        e.setOrganizador(req.getParameter("organizador"));
        int r = service.registrar(e);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write("{\"insertados\":"+r+"}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        resp.setContentType("application/json;charset=UTF-8");
        if (path == null || path.length() <= 1) { resp.setStatus(400); resp.getWriter().write("{\"error\":\"id requerido\"}"); return; }
        try {
            int id = Integer.parseInt(path.substring(1));
            Evento e = new Evento();
            e.setNombre(req.getParameter("nombre"));
            e.setDescripcion(req.getParameter("descripcion"));
            try { e.setFechaEvento(java.sql.Date.valueOf(req.getParameter("fechaEvento"))); } catch (Exception ignored) {}
            e.setUbicacion(req.getParameter("ubicacion"));
            e.setEstado(req.getParameter("estado"));
            e.setOrganizador(req.getParameter("organizador"));
            int r = service.actualizar(id, e);
            resp.getWriter().write("{\"actualizados\":"+r+"}");
        } catch (Exception ex) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"id invalido\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        resp.setContentType("application/json;charset=UTF-8");
        if (path == null || path.length() <= 1) { resp.setStatus(400); resp.getWriter().write("{\"error\":\"id requerido\"}"); return; }
        try {
            int id = Integer.parseInt(path.substring(1));
            int r = service.eliminar(id);
            resp.getWriter().write("{\"eliminados\":"+r+"}");
        } catch (Exception ex) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"id invalido\"}");
        }
    }
}
