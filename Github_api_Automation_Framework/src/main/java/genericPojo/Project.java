package genericPojo;

public class Project {

	// from git swagger
	private String name;
	private String description;
	private String visibility;

	// constructor overloading

	public Project(String name, String description) {

		super();

		this.description = description;
		this.name = name;

	}

	public Project(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	Project() {

	}

}
