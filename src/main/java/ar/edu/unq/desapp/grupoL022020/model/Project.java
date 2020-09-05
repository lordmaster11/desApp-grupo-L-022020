package ar.edu.unq.desapp.grupoL022020.model;

import java.util.Date;
import java.util.List;

public class Project {
	private String name;
	private String factor;
	private Location location;
	private Date dateInitial;
	private Date dateEnding;
	private Integer percentageMinimun;
	private Integer percentageMissing;
	private List<User> donors;
}
