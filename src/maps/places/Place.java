package maps.places;

public class Place {
	
	private String name;
	private String type;
	@SuppressWarnings("unused")
	private boolean crossable;
	
	public Place (String name, String type, boolean crossable) {
		this.name = name;
		this.type = type;
		this.crossable = crossable;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
