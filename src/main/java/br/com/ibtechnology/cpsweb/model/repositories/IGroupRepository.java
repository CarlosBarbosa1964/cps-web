package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.GroupEntity;

public interface IGroupRepository extends JpaRepository<GroupEntity, Long> {

}
