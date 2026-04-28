package com.bibliotech.model;

public record Ebook(
        String isbn,
        String titulo,
        String autor,
        int anio,
        String categoria,
        String formato
) implements Recurso{
    public Ebook {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN es obligatorio.");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo es obligatorio.");
        }
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("El autor es obligatorio.");
        }
        if (anio <= 0) {
            throw new IllegalArgumentException("El anio debe ser mayor a 0.");
        }
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoria es obligatoria.");
        }
        if (formato == null || formato.isBlank()) {
            throw new IllegalArgumentException("El formato es obligatorio.");
        }
    }
}
