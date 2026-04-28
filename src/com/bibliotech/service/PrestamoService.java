package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.Repository;

// src/com/bibliotech/service/PrestamoService.java
public class PrestamoService {
    private final Repository<Recurso, String> recursoRepo;
    private final Repository<Socio, String> socioRepo;

    public PrestamoService(Repository<Recurso, String> recursoRepo,
                           Repository<Socio, String> socioRepo) {
        this.recursoRepo = recursoRepo;
        this.socioRepo = socioRepo;
    }

    public void realizarPrestamo(String isbn, String dni) throws BibliotecaException {
        // Implementar validaciones y lógica
    }
}