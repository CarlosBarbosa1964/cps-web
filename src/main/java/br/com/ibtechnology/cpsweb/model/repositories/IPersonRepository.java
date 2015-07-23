package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

public interface IPersonRepository extends JpaRepository<UserEntity, Long>  {

}
