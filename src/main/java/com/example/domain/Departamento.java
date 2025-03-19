package com.example.domain;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Departamento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "codigo", nullable = false, length = -1)
    private String codigo;
    @Basic
    @Column(name = "nombre", nullable = false, length = -1)
    private String nombre;
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<Municipio> municipios;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return id == that.id && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombre, municipios);
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}
