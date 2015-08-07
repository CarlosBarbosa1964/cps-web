package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibtechnology.cpsweb.model.entities.LogTrace;

public interface IlogTraceRepository extends JpaRepository<LogTrace, Long>{

}
