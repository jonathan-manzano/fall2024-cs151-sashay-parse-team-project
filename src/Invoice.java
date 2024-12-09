import java.text.DecimalFormat;
import java.util.*;

/**
 * Invoice is a DataModel that contains invoice items
 * It contains the information about the store and the employee doing the transaction
 * It also does the necessary calculates on the items it holds such as taxes and discounts.
 */
public class Invoice extends DataModel<InvoiceItem>{
	Store store;
	Employee employee;
	boolean discount;
	double rawTotal;
	double discountedTotal;
	double taxedTotal;
	double grandTotal;
	private final DecimalFormat df = new DecimalFormat("$ 0.00");
	
	/**
	 * Constructs an Invoice with the given store and employee
	 * @param store the store where the transaction is held
	 * @param employee the employee doing the transaction
	 */
	public Invoice(Store store, Employee employee) {
		this.store = store;
		this.employee = employee;	
	}
	
	/**
	 * Returns the store of the Invoice
	 * @return the store of the Invoice
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * Returns the employee of the Invoice
	 * @return the employee of the Invoice
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * Returns the discount applied by the store
	 * @return the discount applied by the store
	 */
	public double getDiscount() {
		return store.getDiscount();
	}
	
	
	/**
	 * Returns whether a discount is applied on the transaction
	 * @return true when discounted, else false
	 */
	public boolean isDiscounted() {
		return discount;
	}
	
	/**
	 * Returns the raw total of the invoice
	 * @return the raw total of the invoice
	 */
	public double rawTotal() {
		return rawTotal;
	}
	
	/**
	 * Returns the discounted total of the invoice
	 * @return the discounted total of the invoice
	 */
	public double discountedTotal() {
		return discountedTotal;
	}
	
	/**
	 * Returns the taxed total of the invoice
	 * @return the taxed total of the invoice
	 */
	public double taxedTotal() {
		return taxedTotal;
	}
	
	/**
	 * Returns the grand total of the invoice
	 * @return the grand total of the invoice
	 */
	public double grandTotal() {
		return grandTotal;
	}
	
	/**
	 * Applies or removes the discount of the transaction, 
	 * updates values, and notifies listeners
	 * @return whether a discount is applied
	 */
	public boolean applyDiscount() {
		discount = !(discount);
		calculateTotals();
		notifyListeners();
		
		return discount;
	}
	
	/**
	 * Adds an invoice item to the invoice, updates values, 
	 * and notifies listeners
	 * @param p the product associated with the invoice item
	 * @param q the quantity of the product
	 */
	public void addItem(Product p, int q) {
		InvoiceItem newItem = new InvoiceItem(p, q);
		this.data.add(newItem);
		calculateTotals();
		notifyListeners();
	}
	
	/**
	 * Removes an invoice item from the invoice, updates values,
	 * and notifies listeners
	 * @param i the index of the item to be removed
	 */
	public void removeItem(int i) {
		if (i < 1) return;
		
		InvoiceItem removedItem = data.get(i - 1);
		this.data.remove(i - 1);
		calculateTotals();
		notifyListeners();
	}
	
	/**
	 * Removes all items in the invoice. Updates all the values and notifies listeners
	 */
	public void clear() {
		data.clear();
		this.discount = false;
		calculateTotals();
		notifyListeners();
	}
	
	/**
	 * Recalculates the total balances of the product
	 */
	private void calculateTotals() {
		double discountMultiplier = ((100.0 - store.getDiscount()) / 100.0);
		double taxMultiplier = ((100.0 + store.getTax()) / 100.0);
		
		this.rawTotal = 0;
		
		for(InvoiceItem item: data) {
			this.rawTotal += item.getRawTotal();
		}
		
		this.discountedTotal = rawTotal * discountMultiplier;
		this.taxedTotal = (discount) ? discountedTotal * taxMultiplier : rawTotal * taxMultiplier;
		this.grandTotal = taxedTotal;
	}
	
	
	/**
	 * Creates a String formatted as a table with the current data
	 * @return an invoice item list formatted as a table
	 */
	public String toTable() {
		String[] colNames = {"Item No.", "Product", "Quantity", "Total"};
		
		String invoiceTable = "\n";
		
		for (int i=0; i<colNames.length; i++) {
			invoiceTable += StringAligner.centerAlignString(colNames[i], Constants.COLWIDTH);
		}
		
		invoiceTable += "\n\n";
		if (data.size() == 0) {
			invoiceTable += StringAligner.centerAlignString("Add items to the order\n", Constants.WIDTH);
		}
		
		for (int i=0; i<data.size(); i++) {
			InvoiceItem item = data.get(i);
			
			invoiceTable += StringAligner.centerAlignString(String.valueOf(i + 1), Constants.COLWIDTH);
			invoiceTable += StringAligner.centerAlignString(item.getName(), Constants.COLWIDTH);
			invoiceTable += StringAligner.centerAlignString(String.valueOf(item.getQuantity()), Constants.COLWIDTH);
			invoiceTable += StringAligner.centerAlignString(df.format(item.getRawTotal()), Constants.COLWIDTH);
			invoiceTable += "\n";
		}
		
		return invoiceTable;
	}
}

