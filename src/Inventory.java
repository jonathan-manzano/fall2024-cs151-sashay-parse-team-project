import java.util.ArrayList;

public class Inventory {
	ArrayList<Product> products;
	
	public Inventory() {
		this.products = new ArrayList<Product>();
	}
	
	public void add(Product p) {
		products.add(p);
	}
	
	public ArrayList<Product> getProducts(){
		return (ArrayList<Product>) products.clone();
	}
	
	public Product find(String code) {
		for (Product p: products) {
			if (p.getCode().equals(code)) return p;
		}
		
		return null;
	}
}
