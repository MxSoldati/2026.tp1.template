package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.Repository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

// src/com/bibliotech/service/PrestamoService.java
public class PrestamoService {
    private final Repository<Recurso, String> recursoRepo;
    private final Repository<Socio, String> socioRepo;
    private final Repository<Prestamo, Long> prestamoRepo;
    private long nextPrestamoId = 1L;

    public PrestamoService(Repository<Recurso, String> recursoRepo,
                           Repository<Socio, String> socioRepo,
                           Repository<Prestamo, Long> prestamoRepo) {
        this.recursoRepo = recursoRepo;
        this.socioRepo = socioRepo;
        this.prestamoRepo = prestamoRepo;
    }

    public Prestamo realizarPrestamo(String isbn, String dni) throws BibliotecaException {
        Optional<Recurso> recursoOpt = recursoRepo.buscarPorId(isbn);
        if (recursoOpt.isEmpty()) {
            throw new BibliotecaException("Recurso no encontrado: " + isbn);
        }

        Optional<Socio> socioOpt = socioRepo.buscarPorId(dni);
        if (socioOpt.isEmpty()) {
            throw new BibliotecaException("Socio no encontrado: " + dni);
        }

        Recurso recurso = recursoOpt.get();
        Socio socio = socioOpt.get();

        boolean recursoDisponible = prestamoRepo.buscarTodos().stream()
                .noneMatch(p -> p.recurso().isbn().equals(recurso.isbn()) && !p.fechaFin().isBefore(LocalDate.now()));
        if (!recursoDisponible) {
            throw new BibliotecaException("El recurso no esta disponible.");
        }

        long prestamosActivos = prestamoRepo.buscarTodos().stream()
                .filter(p -> p.socio().dni().equals(socio.dni()))
                .filter(p -> !p.fechaFin().isBefore(LocalDate.now()))
                .count();
        if (prestamosActivos >= socio.maxPrestamos()) {
            throw new BibliotecaException("El socio alcanzo el limite de prestamos.");
        }

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = fechaInicio.plusDays(14);
        Prestamo prestamo = new Prestamo(nextPrestamoId++, recurso, socio, fechaInicio, fechaFin);
        prestamoRepo.guardar(prestamo);
        return prestamo;
    }

    public long registrarDevolucion(Long prestamoId, LocalDate fechaDevolucion) throws BibliotecaException {
        Optional<Prestamo> prestamoOpt = prestamoRepo.buscarPorId(prestamoId);
        if (prestamoOpt.isEmpty()) {
            throw new BibliotecaException("Prestamo no encontrado: " + prestamoId);
        }

        Prestamo prestamo = prestamoOpt.get();
        if (!fechaDevolucion.isAfter(prestamo.fechaFin())) {
            return 0;
        }

        return ChronoUnit.DAYS.between(prestamo.fechaFin(), fechaDevolucion);
    }
}