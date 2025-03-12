package com.example.dto.mapper;

public interface MapperService <E, DTO>{

    public void toEntity(E e,DTO dto);
    public DTO toDTO(E entity);

}
