package com.example.repository;

import com.example.domain.Departamento;
import com.example.domain.Distrito;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DistritoRepository implements PanacheRepository<Distrito> {
}
