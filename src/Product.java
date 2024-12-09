/**
 * A class that represents a product
 */
public class Product {
	
	String name;
	String code;
	double price;
	String description;

	/**
	 * Constructs a Product with uninitialized attributes
	 */
	public Product() {}
	
	/**
	 * Constructs a Product with the given name, code, price, and description
	 * @param name the name of the product
	 * @param code the code of the product
	 * @param price the price of the product in dollars
	 * @param description a short description of the product
	 */
	public Product(String name, String code, double price, String description) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.description = description;
	}

	/**
	 * Returns the name of the product
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the code of the product
	 * @return the code of the product
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns the price of the product
	 * @return the price of the product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the description of the product
	 * @return the description of the product
	 */
	public String getDescription() {
		return description;
	}
}
