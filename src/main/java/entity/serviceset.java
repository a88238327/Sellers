package entity;

public class serviceset {

	private String name;
	private String enable;
	public serviceset(String name, String enable) {
		this.name = name;
		this.enable = enable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
}
