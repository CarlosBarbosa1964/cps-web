package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Floors")
@AttributeOverride(name = "id", column = @Column(name = "ID") )
public class FloorEntity extends BaseEntities<Long> {

	private static final long serialVersionUID = -5422297458121649602L;

	@Column(nullable = false)
	private String name;

	@Lob
	@Column(nullable = true)
	private String desc;

	@ManyToOne
	@JoinColumn(name = "siteID", nullable = false)
	private SiteEntity site;

	@OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
	private List<SectorEntity> sectors;

	@Column(nullable = false)
	private Date last_update;

	public FloorEntity() {

	}

	public FloorEntity(String name, String desc, SiteEntity site, List<SectorEntity> sectors, Date last_update) {
		super();
		this.name = name;
		this.desc = desc;
		this.site = site;
		this.sectors = sectors;
		this.last_update = last_update;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public SiteEntity getSite() {
		return site;
	}

	public void setSite(SiteEntity site) {
		this.site = site;
	}

	public List<SectorEntity> getSectors() {
		return sectors;
	}

	public void setSectors(List<SectorEntity> sectors) {
		this.sectors = sectors;
	}

}
