package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.FloorEntity;

public interface IFloorRepository extends JpaRepository<FloorEntity, Long> {

}
