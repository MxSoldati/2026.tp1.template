package com.bibliotech.model;

public record Docente(
        String dni,
        String nombre,
        String email

) implements Socio{
        //constructor para las validaciones
        public Docente {
            if (dni == null || dni.isBlank()) {
                throw new IllegalArgumentException("DNI no puede ser nulo o vacío");
            }
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("Nombre no puede ser nulo o vacío");
            }
            if (email == null || email.isBlank() || !email.contains("@")) {
                throw new IllegalArgumentException("Email no es válido");
            }
        }

        @Override
        public int maxPrestamos() {
            return 5;
        }
}
