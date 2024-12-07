
public class InvoiceItem {
	Product product;
	int quantity;
	
	public InvoiceItem(Product p, int q) {
		this.product = p;
		this.quantity = q;
	}
	
	public String getName() {
		return product.getName();
	}
	
	public String getCode() {
		return product.getCode();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public double getRawTotal() {
		return product.price * (double) quantity;
	}
}

