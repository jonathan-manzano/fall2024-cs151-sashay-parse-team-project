import java.util.*;

public class Invoice {
	ArrayList<Product> items;
	ArrayList<Integer> quantity;
	// listeners ?
	
	public Invoice() {
		this.items = new ArrayList<Product>();
		this.quantity = new ArrayList<Integer>();
	}
	
	public void addItem(Product p, int q) {
		this.items.add(p);
		this.quantity.add(q);
	}
	
	public void removeItem(int i) {
		this.items.remove(i);
		this.quantity.remove(i);
	}
	
	// TO BE DONE
	
	public double rawTotal() {
		return 0;
	}
	
	public double discountedTotal() {
		return 0;
	}
	
	public double grandTotal() {
		return 0;
	}
}
