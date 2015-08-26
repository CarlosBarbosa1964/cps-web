package br.com.ibtechnology.cpsweb.dao;

import java.util.List;

import br.com.ibtechnology.cpsweb.model.entities.SiteEntity;

public interface SitesDAO {
	
	public List<SiteEntity> findAll();

}
