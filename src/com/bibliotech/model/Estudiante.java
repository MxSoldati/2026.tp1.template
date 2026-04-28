package com.bibliotech.model;

public record Estudiante(
        String dni,
        String nombre,
        String email
) implements Socio {
    //constructor para las validaciones
    public Estudiante {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("Error: El DNI es obligatorio y no puede estar vacío.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Error: El email del estudiante debe contener un '@'.");
        }
    }

    @Override
    public int maxPrestamos() {
        return 3;
    }
}
