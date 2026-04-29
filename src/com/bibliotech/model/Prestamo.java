package com.bibliotech.model;

import java.time.LocalDate;
import java.util.Optional;

public record Prestamo(
        Long id,
        Recurso recurso,
        Socio socio,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        Optional<LocalDate> fechaDevolucion
) {
    // Constructor
    public Prestamo {
        if (id == null) {
            throw new IllegalArgumentException("El préstamo debe tener un id.");
        }
        if (recurso == null) {
            throw new IllegalArgumentException("El préstamo debe estar asociado a un recurso.");
        }
        if (socio == null) {
            throw new IllegalArgumentException("El préstamo debe tener un socio asignado.");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("Error de lógica: La fecha de fin no puede ser anterior a la de inicio.");
        }
        if (fechaDevolucion == null) {
            throw new IllegalArgumentException("La fecha de devolución no puede ser null.");
        }
        if (fechaDevolucion.isPresent() && fechaDevolucion.get().isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de devolución no puede ser anterior a la de inicio.");
        }
    }
}
