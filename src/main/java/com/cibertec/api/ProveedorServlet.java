package com.cibertec.api;

import com.cibertec.beans.Proveedor;
import com.cibertec.services.WSProveedor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProveedorServlet extends HttpServlet {
    private final WSProveedor service = new WSProveedor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        resp.setContentType("application/json;charset=UTF-8");
        if (path == null || "/".equals(path)) {
            List<Proveedor> lista = service.listar();
            resp.getWriter().write(JsonUtil.toJson(lista));
            return;
        }
        try {
            int id = Integer.parseInt(path.substring(1));
            Proveedor p = service.buscarPorId(id);
            resp.getWriter().write(JsonUtil.toJson(p));
        } catch (Exception ex) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"id invalido\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Proveedor p = new Proveedor();
        p.setNombre(req.getParameter("nombre"));
        p.setTipoServicio(req.getParameter("tipoServicio"));
        p.setContacto(req.getParameter("contacto"));
        try { p.setIdEvento(Integer.parseInt(req.getParameter("idEvento"))); } catch (Exception ignored) {}
        int r = service.registrar(p);
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
            Proveedor p = new Proveedor();
            p.setNombre(req.getParameter("nombre"));
            p.setTipoServicio(req.getParameter("tipoServicio"));
            p.setContacto(req.getParameter("contacto"));
            try { p.setIdEvento(Integer.parseInt(req.getParameter("idEvento"))); } catch (Exception ignored) {}
            int r = service.actualizar(id, p);
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
