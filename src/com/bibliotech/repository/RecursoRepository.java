package com.bibliotech.repository;

import com.bibliotech.model.Recurso;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecursoRepository implements Repository<Recurso, String> {
    private final List<Recurso> recursos = new ArrayList<>();

    @Override
    public void guardar(Recurso recurso) {
        for (int i = 0; i < recursos.size(); i++) {
            if (recursos.get(i).isbn().equals(recurso.isbn())) {
                recursos.set(i, recurso);
                return;
            }
        }
        recursos.add(recurso);
    }

    @Override
    public Optional<Recurso> buscarPorId(String isbn) {
        return recursos.stream()
                .filter(recurso -> recurso.isbn().equals(isbn))
                .findFirst();
    }

    @Override
    public List<Recurso> buscarTodos() {
        return new ArrayList<>(recursos);
    }

    public List<Recurso> buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(recurso -> recurso.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }

    public List<Recurso> buscarPorCategoria(String categoria) {
        return recursos.stream()
                .filter(recurso -> recurso.categoria().equalsIgnoreCase(categoria))
                .toList();
    }
}
