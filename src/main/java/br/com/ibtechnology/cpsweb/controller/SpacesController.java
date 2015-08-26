package br.com.ibtechnology.cpsweb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ibtechnology.cpsweb.model.entities.FloorEntity;
import br.com.ibtechnology.cpsweb.model.entities.SectorEntity;
import br.com.ibtechnology.cpsweb.model.entities.SiteEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IFloorRepository;
import br.com.ibtechnology.cpsweb.model.repositories.ISectorRepository;
import br.com.ibtechnology.cpsweb.model.repositories.ISiteRepository;
import br.com.ibtechnology.cpsweb.util.BaseBeans;

@Named(value = "spacesController")
@RequestScoped
public class SpacesController extends BaseBeans {

	private static final long serialVersionUID = 1005244262360209206L;

	private static final Logger logger = Logger.getLogger(SpacesController.class);
	
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

	private SiteEntity newSite;

	private Long id;

	private TreeNode root;

	private TreeNode selectedNode;

	private String returnLabel;

	private boolean visibleDetailSite;

	public SpacesController() {
		this.sites = new ArrayList<SiteEntity>();
		this.newSite = new SiteEntity();
	}

	public List<SiteEntity> getSites() {
		this.sites = siteRepository.findAll();
		return this.sites;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SiteEntity getNewSite() {
		return newSite;
	}

	public void setNewSite(SiteEntity newSite) {
		this.newSite = newSite;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getReturnLabel() {

		if (this.selectedNode != null) {
			returnLabel = this.getResourceProperty("labels", this.selectedNode.getType());
		} else {
			returnLabel = "";
		}

		return returnLabel;
	}

	public void setReturnLabel(String returnLabel) {
		this.returnLabel = returnLabel;
	}

	public boolean isVisibleDetailSite() {
		visibleDetailSite = (this.selectedNode != null) ? true : false;
		return visibleDetailSite;
	}

	public void setVisibleDetailSite(boolean visibleDetail) {
		this.visibleDetailSite = visibleDetail;
	}

	public void onLoad() {
		this.sites = siteRepository.findAll();

		this.root = new DefaultTreeNode("Root", null);

		addSiteNodes(this.sites, this.root);

	}

	private void addSiteNodes(List<SiteEntity> sites, TreeNode root) {
		for (SiteEntity site : sites) {
			TreeNode no = new DefaultTreeNode("site", site, root);
			addFloorNodes(site.getFloors(), no);
		}
	}

	private void addFloorNodes(List<FloorEntity> floors, TreeNode node) {
		for (FloorEntity floor : floors) {
			TreeNode no = new DefaultTreeNode("floor", floor, node);
			addSectorsNodes(floor.getSectors(), no);
		}
	}

	private void addSectorsNodes(List<SectorEntity> sectors, TreeNode node) {
		for (SectorEntity sector : sectors) {
			TreeNode no = new DefaultTreeNode("sector", sector, node);
		}
	}

    public void onNodeSelect(NodeSelectEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    	Long idNode;
    	idNode=Long.parseLong(selectedNode.getRowKey());
    	switch (selectedNode.getType()) {
		case "site":
//			this.selectedSite = siteRepository.findOne(Long.parseLong(selectedNode.getRowKey()));
			this.selectedSite = (SiteEntity) selectedNode.getData();
			break;

		default:
			break;
		}
    }
    
    public void displaySelectedSite(){
    	this.selectedSite = (SiteEntity) selectedNode.getData();
    }

	public void delete() {
		FacesMessage message = null;
		String titleMsg = "";
		String msg = "";
		if (this.selectedSite != null) {
			try {
				this.siteRepository.delete(this.selectedSite.getId());
				titleMsg = this.getResourceProperty("labels", "message_title_success");
				msg = this.getResourceProperty("labels", "site_deleted_success");
			} catch (Exception e) {
				this.selectedSite = null;
				titleMsg = this.getResourceProperty("labels", "message_title_error");
				msg = e.getMessage();

				logger.error(e.getMessage(), e);
			}

		}
		titleMsg = this.getResourceProperty("labels", "message_title_fail");
		msg = this.getResourceProperty("labels", "site_deleted_fail");
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, titleMsg, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
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

	public void testClass() {
		if (this.selectedSite != null) {
			System.out.println(this.selectedSite.getName());
		} else {
			System.out.println("Site Nulo");

		}
	}

	public void unselectSite() {
		this.selectedSite = null;
	}

	public void prepareNewSite() {
		this.newSite = new SiteEntity();
	}

	public SiteEntity getSelectedSite() {
		return this.selectedSite;
	}

	public void setSelectedSite(SiteEntity selectedSite) {
		this.selectedSite = selectedSite;
	}

	public void saveSite() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean saved = false;
		if (this.newSite != null && !this.newSite.getName().equals("")) {
			this.newSite.setLast_update(new Date());
			// Add
			this.siteRepository.save(this.newSite);
			if (this.newSite.getId() > 0) {
				saved = true;
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						this.getResourceProperty("labels", "site_add_success"), this.newSite.getName());
				this.selectedSite = this.newSite;
			} else {
				saved = false;
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						this.getResourceProperty("labels", "site_add_fail"), this.newSite.getName());
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("saved", saved);
		this.prepareNewSite();
	}

	public void updateSite() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean saved = false;
		// Update
		this.siteRepository.save(this.selectedSite);
		if (this.selectedSite != null) {
			saved = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					this.getResourceProperty("labels", "site_update_success"), this.selectedSite.getName());
		} else {
			saved = false;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					this.getResourceProperty("labels", "site_update_fail"), this.selectedSite.getName());
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("saved", saved);
	}

	private String getResourceProperty(String resource, String label) {
		context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}
}
