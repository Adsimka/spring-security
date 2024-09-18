package com.security.mapper;

public interface BaseMapper<F, T> {

    F convert(T object);
}
