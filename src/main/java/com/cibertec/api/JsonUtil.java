package com.cibertec.api;

import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;

public class JsonUtil {

    private static String esc(String s) {
        if (s == null) return "null";
        return '"' + s.replace("\\", "\\\\").replace("\"", "\\\"") + '"';
    }

    private static String date(Date d) {
        if (d == null) return "null";
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return '"' + f.format(d) + '"';
    }

    public static String toJson(com.cibertec.beans.Evento e) {
        if (e == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"idEvento\":").append(e.getIdEvento()).append(',');
        sb.append("\"nombre\":").append(esc(e.getNombre())).append(',');
        sb.append("\"descripcion\":").append(esc(e.getDescripcion())).append(',');
        sb.append("\"fechaEvento\":").append(date(e.getFechaEvento())).append(',');
        sb.append("\"ubicacion\":").append(esc(e.getUbicacion())).append(',');
        sb.append("\"estado\":").append(esc(e.getEstado())).append(',');
        sb.append("\"organizador\":").append(esc(e.getOrganizador()));
        sb.append('}');
        return sb.toString();
    }

    public static String toJson(com.cibertec.beans.Local l) {
        if (l == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"idLocal\":").append(l.getIdLocal()).append(',');
        sb.append("\"nombre\":").append(esc(l.getNombre())).append(',');
        sb.append("\"direccion\":").append(esc(l.getDireccion())).append(',');
        sb.append("\"capacidad\":").append(l.getCapacidad()).append(',');
        sb.append("\"disponibilidad\":").append(esc(l.getDisponibilidad()));
        sb.append('}');
        return sb.toString();
    }

    public static String toJson(com.cibertec.beans.Participante p) {
        if (p == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"idParticipante\":").append(p.getIdParticipante()).append(',');
        sb.append("\"nombre\":").append(esc(p.getNombre())).append(',');
        sb.append("\"email\":").append(esc(p.getEmail())).append(',');
        sb.append("\"telefono\":").append(esc(p.getTelefono())).append(',');
        sb.append("\"idEvento\":").append(p.getIdEvento());
        sb.append('}');
        return sb.toString();
    }

    public static String toJson(com.cibertec.beans.Proveedor p) {
        if (p == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"idProveedor\":").append(p.getIdProveedor()).append(',');
        sb.append("\"nombre\":").append(esc(p.getNombre())).append(',');
        sb.append("\"tipoServicio\":").append(esc(p.getTipoServicio())).append(',');
        sb.append("\"contacto\":").append(esc(p.getContacto())).append(',');
        sb.append("\"idEvento\":").append(p.getIdEvento());
        sb.append('}');
        return sb.toString();
    }

    public static String toJson(Collection<?> col) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean first = true;
        for (Object o : col) {
            if (!first) sb.append(',');
            first = false;
            if (o instanceof com.cibertec.beans.Evento) sb.append(toJson((com.cibertec.beans.Evento)o));
            else if (o instanceof com.cibertec.beans.Local) sb.append(toJson((com.cibertec.beans.Local)o));
            else if (o instanceof com.cibertec.beans.Participante) sb.append(toJson((com.cibertec.beans.Participante)o));
            else if (o instanceof com.cibertec.beans.Proveedor) sb.append(toJson((com.cibertec.beans.Proveedor)o));
            else sb.append(esc(o == null ? null : o.toString()));
        }
        sb.append(']');
        return sb.toString();
    }
}
