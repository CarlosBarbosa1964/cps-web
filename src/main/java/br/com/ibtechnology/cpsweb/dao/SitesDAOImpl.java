package br.com.ibtechnology.cpsweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ibtechnology.cpsweb.model.entities.SiteEntity;
import br.com.ibtechnology.cpsweb.model.repositories.ISiteRepository;


public class SitesDAOImpl implements SitesDAO {

	@Autowired
	private ISiteRepository siteRepository;
	
	@Override
	public List<SiteEntity> findAll() {
		return siteRepository.findAll();
	}

}
