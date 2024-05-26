package com.bravo.carrental.util;

public interface ModelMapper<T, E> {
    E toEntity(T dto);
    T toDto(E entity);
}