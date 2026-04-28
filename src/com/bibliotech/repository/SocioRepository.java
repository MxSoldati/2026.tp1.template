package com.bibliotech.repository;


import com.bibliotech.model.Socio;

import java.util.ArrayList;
import java.util.List;

public class SocioRepository {
    private final List<Socio> socios = new ArrayList<>();

    public void guardar(Socio socio) {
        socios.add(socio);
    }

    public List<Socio> obtenerTodos() {
        return new ArrayList<>(socios);
    }
    public java.util.Optional<Socio> buscarPorDni(String dni) {
        // Recorremos la lista uno por uno
        for (Socio socio : socios) {
            // Si el DNI coincide, lo envolvemos en un Optional y lo devolvemos
            if (socio.dni().equals(dni)) {
                return java.util.Optional.of(socio);
            }
        }
        // Si termina el ciclo y no encontró nada, devolvemos un Optional vacío
        return java.util.Optional.empty();
    }
}

