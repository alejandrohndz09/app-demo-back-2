package com.example.validation;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    @Inject
    EntityManager entityManager;
    private String field;
    private String idField;
    private Class<?> entity;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.field = constraintAnnotation.field();
//        this.idField = constraintAnnotation.idField();
        this.entity = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // Consulta para verificar unicidad en la base de datos
        String jpql = "SELECT COUNT(e) FROM " + entity.getSimpleName() + " e WHERE e." + field + " = :value";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("value", value);

        return query.getSingleResult() == 0; // Si el resultado es 0, el valor es único
    }
}
