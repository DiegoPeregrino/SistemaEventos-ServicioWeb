package com.cibertec.services;

import java.util.List;

public interface CRUDService<T> {
    int registrar(T entity);
    int actualizar(int id, T entity);
    int eliminar(int id);
    T buscarPorId(int id);
    List<T> listar();
}
