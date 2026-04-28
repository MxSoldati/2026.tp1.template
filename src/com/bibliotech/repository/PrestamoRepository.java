package com.bibliotech.repository;

import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Socio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrestamoRepository implements Repository<Prestamo, Long> {
    private final List<Prestamo> prestamos = new ArrayList<>();

    @Override
    public void guardar(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    @Override
    public Optional<Prestamo> buscarPorId(Long id) {
        return prestamos.stream()
                .filter(prestamo -> prestamo.id().equals(id))
                .findFirst();
    }

    @Override
    public List<Prestamo> buscarTodos() {
        return new ArrayList<>(prestamos);
    }

    public List<Prestamo> buscarPorSocio(Socio socio) {
        return prestamos.stream()
                .filter(prestamo -> prestamo.socio().equals(socio))
                .toList();
    }
}
