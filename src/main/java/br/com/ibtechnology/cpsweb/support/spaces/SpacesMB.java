package br.com.ibtechnology.cpsweb.support.spaces;

import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ibtechnology.cpsweb.model.entities.SiteEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IFloorRepository;
import br.com.ibtechnology.cpsweb.model.repositories.ISectorRepository;
import br.com.ibtechnology.cpsweb.model.repositories.ISiteRepository;
import br.com.ibtechnology.cpsweb.util.BaseBeans;

@Named(value = "spacesMB")
@SessionScoped
public class SpacesMB extends BaseBeans {

	private static final long serialVersionUID = 1005244262360209206L;

	private static final Logger logger = Logger.getLogger(SpacesMB.class);

	@Autowired
	private ISiteRepository siteRepository;
	
	@Autowired
	private IFloorRepository floorRepository;
	
	@Autowired
	private ISectorRepository sectorRepository;
	
	@Autowired
	private FacesContext context;

	private List<SiteEntity> sites;

	private SiteEntity selectedSite;

	private Long id;

	public SpacesMB() {
	}

	public void onLoad() {
		this.sites = this.siteRepository.findAll();
	}

	public List<SiteEntity> getSites() {
		return this.sites;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void delete() {
		if (this.selectedSite != null) {
			this.siteRepository.delete(this.selectedSite.getId());
		}
	}

	public void selectSite(SelectEvent evt) {
		try {
			if (evt.getObject() != null) {
				this.selectedSite = (SiteEntity) evt.getObject();
			} else {
				this.selectedSite = null;
			}
		} catch (Exception e) {
			this.selectedSite = null;

			logger.error(e.getMessage(), e);
		}
	}
	
	public void testClass(){
		if(this.selectedSite != null){
		System.out.println(this.selectedSite.getName());
		}else {
			System.out.println("Site Nulo");
			
		}
	}

	public void unselectSite() {
		this.selectedSite = null;
	}

	public SiteEntity getSelectedSite() {
		return this.selectedSite;
	}

	public void setSelectedSite(SiteEntity selectedSite) {
		this.selectedSite = selectedSite;
	}

	private String getResourceProperty(String resource, String label) {
		context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}
}
