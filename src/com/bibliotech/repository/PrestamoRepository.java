package com.bibliotech.repository;

import com.bibliotech.model.Prestamo;
import java.util.ArrayList;
import java.util.List;

public class PrestamoRepository {
    private final List<Prestamo> prestamos = new ArrayList<>();

    public void guardar(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public List<Prestamo> obtenerTodos() {
        return new ArrayList<>(prestamos);
    }
}