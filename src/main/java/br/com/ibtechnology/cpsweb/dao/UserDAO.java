package br.com.ibtechnology.cpsweb.dao;

import java.util.List;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

public interface UserDAO {
    List<UserEntity> findAll();
 
    void save(UserEntity user);
 
    void update(UserEntity user);
 
    void remove(UserEntity user);
 
    UserEntity getById(Long id);
     
    UserEntity login(String username, String password);
}
