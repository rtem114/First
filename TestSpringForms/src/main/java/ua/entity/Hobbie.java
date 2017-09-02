package ua.entity;

public class Hobbie {

	private String id;
	private String name;

	public Hobbie() {
		super();
	}

	public Hobbie(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hobbie [id=" + id + ", name=" + name + "]";
	}
	
	

}
