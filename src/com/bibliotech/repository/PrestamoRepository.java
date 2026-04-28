package com.bibliotech.repository;

import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Socio;

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

    public List<Prestamo> buscarPorSocio(Socio socio) {
        // guardamos los resultados de lo que buscamos
        List<Prestamo> resultados = new ArrayList<>();

        // Recorremos la lista
        for (Prestamo prestamo : prestamos) {
            // Si es el que buscamos, lo guardamos
            if (prestamo.socio().equals(socio)) {
                resultados.add(prestamo);
            }
        }

        // Puede ser algo vacio o con varios resultados
        return resultados;
    }
}
