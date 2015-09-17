package br.com.ibtechnology.cpsweb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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

	/*
	 * ############################## # Declaração de # # Variaveis #
	 * ##############################
	 */

	private static final long serialVersionUID = 1005244262360209206L;

	private static final Logger logger = Logger.getLogger(SpacesController.class);

	@Autowired
	private ISiteRepository siteRepository; // Repositorio para sites

	@Autowired
	private IFloorRepository floorRepository; // Repositorio para floors

	@Autowired
	private ISectorRepository sectorRepository; // Repositorio para sectors

	@Autowired
	private FacesContext context;

	private List<SiteEntity> sites;

	private List<FloorEntity> floors;

	private List<SelectItem> selectOneSite; // Utilizado para preenchimento do
											// ComboBox

	private List<SelectItem> selectOneFloor; // Utilizado para preenchimento do
												// ComboBox

	private SiteEntity selectedSite; // Utilizado para saber qual é o site
										// selecionado

	private SiteEntity newSite; // Novo Site

	private FloorEntity selectedFloor; // Utilizado para saber qual é o floor
										// selecionado

	private FloorEntity newFloor; // Novo Floor

	private SectorEntity selectedSector; // Utilizado para saber qual é o sector
											// selecionado

	private SectorEntity newSector; // Novo Floor

	private Long id;

	private TreeNode root; // Raiz para elaboração da Arvore contendo Sites ->
							// Andares -> Setores

	private TreeNode selectedNode; // Item que está selecionado no TreeView

	private String returnLabel; // Contem o Label que será exibido no Detalhe

	private boolean visibleDetailSite; // Informa se o DetailSite é visivel

	private boolean visibleDetailFloor; // Informa se o DetailFloor é visivel

	private boolean visibleDetailSector; // Informa se o DetailFloor é visivel

	private boolean enButtonAddFloor; // Informa se o botão de Adicionar Andar
										// está disponivel

	private boolean enButtonAddSector; // Informa se o botão de Adicionar Setor
										// está disponivel

	/*
	 * Metodos Comuns
	 */

	public SpacesController() {
		this.sites = new ArrayList<SiteEntity>();
		this.floors = new ArrayList<FloorEntity>();
		this.newSite = new SiteEntity();
//		this.selectedSite = new SiteEntity();
//		this.selectedFloor = new FloorEntity();
//		this.selectedSector = new SectorEntity();
		this.prepareNewFloor();
		this.prepareNewSector();
	}

	/*
	 * Getters and Setters
	 */

	public List<SiteEntity> getSites() {
		this.sites = siteRepository.findAll();
		return this.sites;
	}

	public List<FloorEntity> getFloors() {
		this.floors = floorRepository.findAll();
		return this.floors;
	}

	public List<SelectItem> getSelectOneSite() {
		this.selectOneSite = new ArrayList<SelectItem>();

		sites = siteRepository.findAll();

		for (SiteEntity st : sites) {
			SelectItem selectItem = new SelectItem(st.getId(), st.getName());
			this.selectOneSite.add(selectItem);
		}

		return selectOneSite;
	}

	public List<SelectItem> getSelectOneFloor() {
		this.selectOneFloor = new ArrayList<SelectItem>();

		floors = floorRepository.findAll();

		for (FloorEntity fl : floors) {
			SelectItem selectItem = new SelectItem(fl.getId(), fl.getName());
			this.selectOneFloor.add(selectItem);
		}

		return selectOneFloor;
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

	public FloorEntity getNewFloor() {
		return newFloor;
	}

	public void setNewFloor(FloorEntity newFloor) {
		this.newFloor = newFloor;
	}

	public SectorEntity getNewSector() {
		return newSector;
	}

	public void setNewSector(SectorEntity newSector) {
		this.newSector = newSector;
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
		this.visibleDetailSite = (this.selectedNode != null && this.selectedNode.getType().equals("site")) ? true : false;
		return this.visibleDetailSite;
	}

	public void setVisibleDetailSite(boolean visibleDetail) {
		this.visibleDetailSite = visibleDetail;
	}

	public boolean isVisibleDetailFloor() {
		this.visibleDetailFloor = (this.selectedNode != null && this.selectedNode.getType().equals("floor")) ? true : false;
		System.out.println(this.visibleDetailFloor);
		return this.visibleDetailFloor;
	}

	public void setVisibleDetailFloor(boolean visibleDetailFloor) {
		this.visibleDetailFloor = visibleDetailFloor;
	}

	public boolean isVisibleDetailSector() {
		this.visibleDetailSector = (this.selectedNode != null && this.selectedNode.getType().equals("sector")) ? true
				: false;
		System.out.println(this.visibleDetailFloor);
		return this.visibleDetailSector;
	}

	public void setVisibleDetailSector(boolean visibleDetailSector) {
		this.visibleDetailSector = visibleDetailSector;
	}

	public boolean isEnButtonAddFloor() {
		this.enButtonAddFloor = (this.selectedNode != null && this.selectedNode.getType().equals("site")) ? true : false;
		return this.enButtonAddFloor;
	}

	public void setEnButtonAddFloor(boolean enButtonAddFloor) {
		this.enButtonAddFloor = enButtonAddFloor;
	}

	public boolean isEnButtonAddSector() {
		String Tipo = this.selectedNode.getType();
		this.enButtonAddSector = (this.selectedNode != null && this.selectedNode.getType().equals("floor")) ? true : false;
		return this.enButtonAddSector;
	}

	public void setEnButtonAddSector(boolean enButtonAddSector) {
		this.enButtonAddSector = enButtonAddSector;
	}

	/*
	 * ################################## # Métodos Comuns #
	 * ##################################
	 */

	public void onLoad() {
		this.sites = siteRepository.findAll();
		this.floors = floorRepository.findAll();

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
			new DefaultTreeNode("sector", sector, node);
		}
	}

	public void onNodeSelect(NodeSelectEvent event) {
		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		// "Selected", event.getTreeNode().toString());
		// FacesContext.getCurrentInstance().addMessage(null, message);
		switch (selectedNode.getType()) {
		case "site":
			this.selectedSite = (SiteEntity) selectedNode.getData();
			break;

		case "floor":
			this.selectedFloor = (FloorEntity) selectedNode.getData();
			break;

		case "sector":
			this.selectedSector = (SectorEntity) selectedNode.getData();
			break;

		default:
			break;
		}
	}

	public void displaySelectedSite() {
		this.selectedSite = (SiteEntity) selectedNode.getData();
	}

	public void displaySelectedFloor() {
		this.selectedFloor = (FloorEntity) selectedNode.getData();
	}

	public void displaySelectedSector() {
		this.selectedSector = (SectorEntity) selectedNode.getData();
	}

	public void deleteSite() {
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

		} else {
			titleMsg = this.getResourceProperty("labels", "message_title_fail");
			msg = this.getResourceProperty("labels", "site_deleted_fail");
		}
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, titleMsg, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.onLoad();
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

	public void unselectSite() {
		this.selectedSite = null;
	}

	public void prepareNewSite() {
		this.newSite = new SiteEntity();
	}

	public void unselectFloor() {
		this.selectedFloor = null;
	}

	public void prepareNewFloor() {
		this.newFloor = new FloorEntity();
		this.newFloor.setSite(getSelectedSite());
	}

	public void unselectSector() {
		this.selectedSector = null;
	}

	public void prepareNewSector() {
		this.newSector = new SectorEntity();
		this.newSector.setFloor(getSelectedFloor());
	}

	public SiteEntity getSelectedSite() {
		if (this.selectedSite == null) {
			this.selectedSite = new SiteEntity();
		}
		return this.selectedSite;
	}

	public void setSelectedSite(SiteEntity selectedSite) {
		this.selectedSite = selectedSite;
	}

	public FloorEntity getSelectedFloor() {
		if (this.selectedFloor == null) {
			this.selectedFloor = new FloorEntity();
			this.selectedFloor.setSite(new SiteEntity());
		}
		return selectedFloor;
	}

	public void setSelectedFloor(FloorEntity selectedFloor) {
		this.selectedFloor = selectedFloor;
	}

	public SectorEntity getSelectedSector() {
		if (this.selectedSector == null) {
			this.selectedSector = new SectorEntity();
			this.selectedSector.setFloor(new FloorEntity());
		}
		return selectedSector;
	}

	public void setSelectedSector(SectorEntity selectedSector) {
		this.selectedSector = selectedSector;
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
		this.prepareNewSite();
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("success", saved);
		this.onLoad();
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
		context.addCallbackParam("success", saved);
	}

	/*
	 * ############################# # Metodos para Classe Floor #
	 * #############################
	 */
	public void saveFloor() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean saved = false;
		if (this.newFloor != null && !this.newFloor.getName().equals("")) {
			this.newFloor.setLast_update(new Date());
			this.newFloor.setSite(this.selectedSite);
			// Add
			this.floorRepository.save(this.newFloor);
			if (this.newFloor.getId() > 0) {
				saved = true;
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						this.getResourceProperty("labels", "floor_add_success"), this.newFloor.getName());
				this.selectedFloor = this.newFloor;
			} else {
				saved = false;
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						this.getResourceProperty("labels", "floor_add_fail"), this.newFloor.getName());
			}
		}
		this.prepareNewFloor();
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("success", saved);
		this.onLoad();
	}

	public void updateFloor() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean saved = false;
		// Update
		this.floorRepository.save(this.selectedFloor);
		if (this.selectedFloor != null) {
			saved = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					this.getResourceProperty("labels", "floor_update_success"), this.selectedFloor.getName());
		} else {
			saved = false;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					this.getResourceProperty("labels", "floor_update_fail"), this.selectedFloor.getName());
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("success", saved);
		this.onLoad();
	}

	public void deleteFloor() {
		FacesMessage message = null;
		String titleMsg = "";
		String msg = "";
		if (this.selectedFloor != null) {
			try {
				this.floorRepository.delete(this.selectedFloor.getId());
				titleMsg = this.getResourceProperty("labels", "message_title_success");
				msg = this.getResourceProperty("labels", "floor_deleted_success");
			} catch (Exception e) {
				this.selectedFloor = null;
				titleMsg = this.getResourceProperty("labels", "message_title_error");
				msg = e.getMessage();

				logger.error(e.getMessage(), e);
			}

		} else {
			titleMsg = this.getResourceProperty("labels", "message_title_fail");
			msg = this.getResourceProperty("labels", "floor_deleted_fail");
		}
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, titleMsg, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.onLoad();
	}

	/*
	 * ############################# # Metodos para Classe Sector #
	 * #############################
	 */
	public void saveSector() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean saved = false;
		if (this.newSector != null && !this.newSector.getName().equals("")) {
			this.newSector.setLast_update(new Date());
			this.newSector.setFloor(this.selectedFloor);
			// Add
			this.sectorRepository.save(this.newSector);
			if (this.newSector.getId() > 0) {
				saved = true;
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						this.getResourceProperty("labels", "sector_add_success"), this.newSector.getName());
				this.selectedSector = this.newSector;
			} else {
				saved = false;
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						this.getResourceProperty("labels", "sector_add_fail"), this.newSector.getName());
			}
		}
		this.prepareNewSector();
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("success", saved);
		this.onLoad();
	}

	public void updateSector() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean saved = false;
		// Update
		this.sectorRepository.save(this.selectedSector);
		if (this.selectedSector != null) {
			saved = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					this.getResourceProperty("labels", "sector_update_success"), this.selectedSector.getName());
		} else {
			saved = false;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					this.getResourceProperty("labels", "sector_update_fail"), this.selectedSector.getName());
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("success", saved);
		this.onLoad();
	}

	public void deleteSector() {
		FacesMessage message = null;
		String titleMsg = "";
		String msg = "";
		if (this.selectedSector != null) {
			try {
				this.sectorRepository.delete(this.selectedSector.getId());
				titleMsg = this.getResourceProperty("labels", "message_title_success");
				msg = this.getResourceProperty("labels", "sector_deleted_success");
			} catch (Exception e) {
				this.selectedSector = null;
				titleMsg = this.getResourceProperty("labels", "message_title_error");
				msg = e.getMessage();

				logger.error(e.getMessage(), e);
			}

		} else {
			titleMsg = this.getResourceProperty("labels", "message_title_fail");
			msg = this.getResourceProperty("labels", "sector_deleted_fail");
		}
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, titleMsg, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.onLoad();
	}

	private String getResourceProperty(String resource, String label) {
		context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}
}
