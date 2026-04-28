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
}
