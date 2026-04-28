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
}
