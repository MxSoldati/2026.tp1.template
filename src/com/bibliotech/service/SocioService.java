package com.bibliotech.service;

import com.bibliotech.model.Socio;
import com.bibliotech.repository.Repository;
import java.util.List;
import java.util.Optional;

public class SocioService {
    private final Repository<Socio, String> socioRepo;

    public SocioService(Repository<Socio, String> socioRepo) {
        this.socioRepo = socioRepo;
    }

    public void registrar(Socio socio) {
        socioRepo.guardar(socio);
    }

    public Optional<Socio> buscarPorDni(String dni) {
        return socioRepo.buscarPorId(dni);
    }

    public List<Socio> buscarTodos() {
        return socioRepo.buscarTodos();
    }
}
