package maps.places;

public class Place {
	
	private String name;
	
	@SuppressWarnings("unused")
	private boolean crossable;
	
	private String type;
	
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
