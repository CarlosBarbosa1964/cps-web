package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.EventEntity;

public interface IEventRepository extends JpaRepository<EventEntity, Long>   {

}
