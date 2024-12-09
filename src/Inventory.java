import java.util.ArrayList;

/**
 * A class that represents an Inventory of products
 */
public class Inventory {
	ArrayList<Product> products;
	boolean loaded;
	
	/**
	 * Constructs an Inventory with an empty product list
	 */
	public Inventory() {
		this.products = new ArrayList<Product>();
	}
	
	/**
	 * Adds product to the inventory
	 * @param p the product to be added
	 */
	public void add(Product p) {
		products.add(p);
	}
	
	/**
	 * Returns an array list of products in the inventory
	 * @return an array list of products in the inventory
	 */
	public ArrayList<Product> getProducts() {
		return (ArrayList<Product>) products;
	}
	
	/**
	 * Searches for a product with the matching code.
	 * @param code the product code of the product to be searched
	 * @return the product if found, else a null object
	 */
	public Product find(String code) {
		for (Product p: products) {
			if (p.getCode().equals(code)) return p;
		}
		
		return null;
	}
	
	/**
	 * Sets the inventory to loaded when JSON read was successful
	 * Stays true until the application exits
	 */
	public void setLoaded() {
		this.loaded = true;
	}
	
	/**
	 * Returns whether inventory is loaded
	 * @return inventory load state
	 */
	public boolean isLoaded() {
		return this.loaded;
	}
}
