package com.example.domain;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Municipio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "\"idDepartamento\"", nullable = false)
    private long idDepartamento;
    @Basic
    @Column(name = "codigo", nullable = false, length = -1)
    private String codigo;
    @Basic
    @Column(name = "nombre", nullable = false, length = -1)
    private String nombre;
    @OneToMany(mappedBy = "municipio", fetch = FetchType.EAGER)
    private Collection<Distrito> distritos;
    @ManyToOne
    @JoinColumn(insertable=false, updatable=false, name = "\"idDepartamento\"", referencedColumnName = "id", nullable = false)
    private Departamento departamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
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
        Municipio municipio = (Municipio) o;
        return id == municipio.id && idDepartamento == municipio.idDepartamento && Objects.equals(codigo, municipio.codigo) && Objects.equals(nombre, municipio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDepartamento, codigo, nombre);
    }

    public Collection<Distrito> getDistritos() {
        return distritos;
    }

    public void setDistritos(Collection<Distrito> distritos) {
        this.distritos = distritos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
