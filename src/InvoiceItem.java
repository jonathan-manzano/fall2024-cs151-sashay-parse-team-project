/**
 * A class that represents an item in an InvoiceData
 */
public class InvoiceItem {
	Product product;
	int quantity;
	
	/**
	 * Constructs an invoice item with the given product and quantity
	 * @param p the product to be sold
	 * @param q the quantity of the product
	 */
	public InvoiceItem(Product p, int q) {
		this.product = p;
		this.quantity = q;
	}
	
	/**
	 * Returns the name of the product of the invoice item
	 * @return the name of the product
	 */
	public String getName() {
		return product.getName();
	}
	
	/**
	 * Returns the code of the product of the invoice item
	 * @return the code of the product
	 */
	public String getCode() {
		return product.getCode();
	}
	
	/**
	 * Returns the quantity of the product to be sold
	 * @return the quantity of the product
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Returns the raw total of the invoice item by multiplying
	 * the product price and quantity
	 * @return the product of product price and quantity
	 */
	public double getRawTotal() {
		return product.price * (double) quantity;
	}
}

