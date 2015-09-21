package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.ibtechnology.cpsweb.model.entities.SectorEntity;

@Transactional(readOnly = true)
public interface ISectorRepository extends JpaRepository<SectorEntity, Long>{
	
	@Modifying
	@Transactional
	@Query("delete from SectorEntity s where s.id = ?1")
	void deleteByID(Long id);
	
}
