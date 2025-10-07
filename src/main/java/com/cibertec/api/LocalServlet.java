package com.cibertec.api;

import com.cibertec.beans.Local;
import com.cibertec.services.WSLocal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LocalServlet extends HttpServlet {
    private final WSLocal service = new WSLocal();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        resp.setContentType("application/json;charset=UTF-8");
        if (path == null || "/".equals(path)) {
            List<Local> lista = service.listar();
            resp.getWriter().write(JsonUtil.toJson(lista));
            return;
        }
        try {
            int id = Integer.parseInt(path.substring(1));
            Local l = service.buscarPorId(id);
            resp.getWriter().write(JsonUtil.toJson(l));
        } catch (Exception ex) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"id invalido\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Local l = new Local();
        l.setNombre(req.getParameter("nombre"));
        l.setDireccion(req.getParameter("direccion"));
        try { l.setCapacidad(Integer.parseInt(req.getParameter("capacidad"))); } catch (Exception ignored) {}
        l.setDisponibilidad(req.getParameter("disponibilidad"));
        int r = service.registrar(l);
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
            Local l = new Local();
            l.setNombre(req.getParameter("nombre"));
            l.setDireccion(req.getParameter("direccion"));
            try { l.setCapacidad(Integer.parseInt(req.getParameter("capacidad"))); } catch (Exception ignored) {}
            l.setDisponibilidad(req.getParameter("disponibilidad"));
            int r = service.actualizar(id, l);
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
