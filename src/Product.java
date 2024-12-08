
public class Product {
	
	String name;
	String code;
	double price;
	String description;

	public Product() {}
	
	public Product(String name, String code, double price, String description) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return this.name + ", " + this.code + ", " + this.price + ", " + this.description;
	}

}
