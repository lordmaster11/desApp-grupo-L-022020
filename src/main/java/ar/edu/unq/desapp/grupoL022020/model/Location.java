package ar.edu.unq.desapp.grupoL022020.model;

public class Location {
	private String name;
	private String province;
	private Integer population;
	private Boolean hasConnectivity;
		
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
}