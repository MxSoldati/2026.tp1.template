package com.bibliotech.service;

import com.bibliotech.model.*;
import com.bibliotech.repository.*;

// src/com/bibliotech/service/PrestamoService.java
public class PrestamoService {
    private final Repository<Libro, String> libroRepo;
    private final Repository<Socio, String> socioRepo;

    public PrestamoService(Repository<Libro, String> libroRepo,
                           Repository<Socio, String> socioRepo) {
        this.libroRepo = libroRepo;
        this.socioRepo = socioRepo;
    }

    public void realizarPrestamo(String isbn, String dni) throws BibliotecaException {
        // Implementar validaciones y lógica
    }
}