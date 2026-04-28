package com.bibliotech.repository;

import com.bibliotech.model.Recurso;
import java.util.ArrayList;
import java.util.List;

public class RecursoRepository {
    private final List<Recurso> recursos = new ArrayList<>();

    public void guardar(Recurso recurso) {
        recursos.add(recurso);
    }

    public List<Recurso> obtenerTodos() {
        return new ArrayList<>(recursos);
    }

    public java.util.Optional<Recurso> buscarPorIsbn(String isbn) {
        // recorremos la lista
        for (Recurso recurso : recursos) {
            // ISBN buscado coincide
            if (recurso.isbn().equals(isbn)) {
                return java.util.Optional.of(recurso);
            }
        }
        // termina el for y el resultado vuelve vacio
        return java.util.Optional.empty();
    }
    public List<Recurso> buscarPorTitulo(String titulo) {
        List<Recurso> resultados = new ArrayList<>();

        for (Recurso recurso : recursos) {
            // Busqueda del titulo en minuscula
            if (recurso.titulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(recurso);
            }
        }
        return resultados;
    }
}
