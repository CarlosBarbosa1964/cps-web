package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.SectorEntity;

public interface ISectorRepository extends JpaRepository<SectorEntity, Long>{

}
