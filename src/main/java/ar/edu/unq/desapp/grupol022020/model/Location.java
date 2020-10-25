package ar.edu.unq.desapp.grupol022020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private String name;
	@Column
	private String province;
	@Column
	private Integer population;
	@Column
	private Boolean hasConnectivity;
	@Column
	private Boolean projectAssociated;

	public Location() { }
		
	public Location(String name, String province, Integer population, Boolean hasConnectivity) {
		this.name = name;
		this.province = province;
		this.population = population;
		this.hasConnectivity = hasConnectivity;
		this.projectAssociated = hasConnectivity;
	}
	
	public Integer getPopulation() {
		return population;
	}

	public String getName() {
		return name;
	}

	public String getProvince() {
		return province;
	}

	public Boolean getHasConnectivity() {
		return hasConnectivity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public void setHasConnectivity(Boolean hasConnectivity) {
		this.hasConnectivity = hasConnectivity;
	}
	
	public Boolean getProjectAssociated() {
		return projectAssociated;
	}

	public void setProjectAssociated(Boolean projectAssociated) {
		this.projectAssociated = projectAssociated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}