package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.SiteEntity;

public interface ISiteRepository extends JpaRepository<SiteEntity, Long> {

}
