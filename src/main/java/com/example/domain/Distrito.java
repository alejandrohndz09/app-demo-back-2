package com.example.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Distrito {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name =  "\"idMunicipio\"", nullable = false)
    private long idMunicipio;
    @Basic
    @Column(name = "codigo", nullable = false, length = -1)
    private String codigo;
    @Basic
    @Column(name = "nombre", nullable = false, length = -1)
    private String nombre;
    @ManyToOne
    @JoinColumn(insertable=false, updatable=false, name = "\"idMunicipio\"",referencedColumnName = "id", nullable = false)
    private Municipio municipio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(long idMunicipio) {
        this.idMunicipio = idMunicipio;
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
        Distrito distrito = (Distrito) o;
        return id == distrito.id && idMunicipio == distrito.idMunicipio && Objects.equals(codigo, distrito.codigo) && Objects.equals(nombre, distrito.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMunicipio, codigo, nombre);
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
