import java.text.DecimalFormat;
import java.util.*;

public class Invoice extends DataModel<InvoiceItem>{
	Store store;
	Employee employee;
	boolean discount;
	double rawTotal;
	double discountedTotal;
	double taxedTotal;
	double grandTotal;
	private final DecimalFormat df = new DecimalFormat("$ 0.00");
	
	public Invoice(Store store, Employee employee) {
		this.store = store;
		this.employee = employee;	
	}
	
	public Store getStore() {
		return store;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public double getDiscount() {
		return store.getDiscount();
	}
	
	public boolean applyDiscount() {
		discount = !(discount);
		calculateTotals();
		notifyListeners();
		
		return discount;
	}
	
	public void addItem(Product p, int q) {
		InvoiceItem newItem = new InvoiceItem(p, q);
		this.data.add(newItem);
		rawTotal += newItem.getRawTotal();
		calculateTotals();
		notifyListeners();
	}
	
	public void removeItem(int i) {
		if (i < 1) return;
		
		InvoiceItem removedItem = data.get(i - 1);
		this.data.remove(i - 1);
		rawTotal -= removedItem.getRawTotal();
		if (rawTotal < 0) rawTotal = 0;
		calculateTotals();
		notifyListeners();
	}
	
	private void calculateTotals() {
		double discountMultiplier = ((100.0 - store.getDiscount()) / 100.0);
		double taxMultiplier = ((100.0 + store.getTax()) / 100.0);
		
		this.discountedTotal = rawTotal * discountMultiplier;
		this.taxedTotal = (discount) ? discountedTotal * taxMultiplier : rawTotal * taxMultiplier;
		this.grandTotal = taxedTotal;
	}
	
	public double rawTotal() {
		return rawTotal;
	}
	
	public double discountedTotal() {
		return discountedTotal;
	}
	
	public double taxedTotal() {
		return taxedTotal;
	}
	
	public double grandTotal() {
		return grandTotal;
	}

	public String toTable() {
		String[] colNames = {"Item No.", "Product", "Quantity", "Total"};
		
		String invoiceTable = "";
		
		for (int i=0; i<colNames.length; i++) {
			invoiceTable += StringAligner.centerAlignString(colNames[i], Constants.COLWIDTH);
		}
		
		invoiceTable += "\n";
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
	
	public void clear() {
		data.clear();
	}
}

