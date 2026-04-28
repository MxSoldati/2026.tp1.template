package com.bibliotech.model;

public record Docente(
        String dni,
        String nombre,
        String email

) implements Socio{
        //constructor para las validaciones
        public Docente {
            if (dni == null || dni.isEmpty()) {
                throw new IllegalArgumentException("El docente debe presentar un DNI válido.");
            }
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("El docente debe presentar un email válido.");
            }
        }

        @Override
        public int maxPrestamos() {
            return 5;
        }
}
