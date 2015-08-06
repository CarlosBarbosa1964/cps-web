package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.PersonEntity;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long>  {

}
