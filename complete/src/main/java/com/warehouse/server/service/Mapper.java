package com.warehouse.server.service;

public interface Mapper<D, E> {

    D mapToDto(E entity);

    E mapToEntity(D dto);

}
