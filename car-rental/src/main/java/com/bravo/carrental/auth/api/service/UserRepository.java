package com.bravo.carrental.auth.api.service;

public interface UserRepository {
    ScopedValue<Object> findFirstByEmail(String s);
}
