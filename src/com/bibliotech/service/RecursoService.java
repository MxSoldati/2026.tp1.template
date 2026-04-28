package com.bibliotech.service;

import com.bibliotech.model.Recurso;
import com.bibliotech.repository.Repository;
import java.util.List;
import java.util.Optional;

public class RecursoService {
    private final Repository<Recurso, String> recursoRepo;

    public RecursoService(Repository<Recurso, String> recursoRepo) {
        this.recursoRepo = recursoRepo;
    }

    public void registrar(Recurso recurso) {
        recursoRepo.guardar(recurso);
    }

    public Optional<Recurso> buscarPorIsbn(String isbn) {
        return recursoRepo.buscarPorId(isbn);
    }

    public List<Recurso> buscarPorTitulo(String titulo) {
        return recursoRepo.buscarTodos().stream()
                .filter(recurso -> recurso.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }

    public List<Recurso> buscarPorCategoria(String categoria) {
        return recursoRepo.buscarTodos().stream()
                .filter(recurso -> recurso.categoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public List<Recurso> buscarTodos() {
        return recursoRepo.buscarTodos();
    }
}
