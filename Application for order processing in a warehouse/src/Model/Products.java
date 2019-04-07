package Model;

public class Products {
	private int id;
	private String name;
	private int price;
	private String description;
	private int stoc;
	
	public Products(int id, String name, int price, String description, int stoc) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stoc = stoc;
	}
	
	public Products() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStoc() {
		return stoc;
	}
	
	public void setStoc(int stoc) {
		this.stoc = stoc;
	}
}
