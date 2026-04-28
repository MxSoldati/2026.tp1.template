package com.bibliotech.model;

import java.time.LocalDate;

public record Prestamo(
        Recurso recurso,
        Socio socio,
        LocalDate fechaInicio,
        LocalDate fechaFin
) {
    // Constructor
    public Prestamo {
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
    }
}
