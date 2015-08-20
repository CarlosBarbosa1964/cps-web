package br.com.ibtechnology.cpsweb.service;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

public interface LoginService {
    UserEntity login(String username, String password) throws IllegalArgumentException;
}