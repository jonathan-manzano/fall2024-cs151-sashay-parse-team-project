import java.util.*;

public class Invoice extends DataModel<InvoiceItem>{
	double discount;
	double tax;
	double rawTotal;
	double discountedTotal;
	double taxedTotal;
	double grandTotal;
	
	public Invoice(double tax) {
		this.tax = tax;
	}
	
	public void addItem(Product p, int q) {
		InvoiceItem newItem = new InvoiceItem(p, q);
		this.data.add(newItem);
		rawTotal += newItem.getRawTotal();
		calculateTotals();
	}
	
	public void removeItem(int i) {
		InvoiceItem removedItem = data.get(i);
		this.data.remove(i);
		rawTotal -= removedItem.getRawTotal();
		calculateTotals();
	}
	
	// TO BE DONE
	
	private void calculateTotals() {
		double discountMultiplier = ((100.0 - discount) / 100.0);
		double taxMultiplier = ((100.0 + tax) / 100.0);
		
		this.discountedTotal = rawTotal * discountMultiplier;
		this.taxedTotal = (discount > 0) ? discountedTotal * taxMultiplier : rawTotal * taxMultiplier;
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

	public String toTable(int colWidth) {
		String[] colNames = {"Item No.", "Product", "Quantity", "Total"};
		
		String invoiceTable = "";
		
		for (int i=0; i<colNames.length; i++) {
			invoiceTable += StringAligner.centerAlignString(colNames[i], colWidth);
		}
		
		invoiceTable += "\n";
		for (int i=0; i<data.size(); i++) {
			InvoiceItem item = data.get(i);
			double rawTotal = item.getRawTotal(); // double * int at the moment
			
			invoiceTable += StringAligner.centerAlignString(String.valueOf(i), colWidth);
			invoiceTable += StringAligner.centerAlignString(item.getName(), colWidth);
			invoiceTable += StringAligner.centerAlignString(String.valueOf(item.getQuantity()), colWidth);
			invoiceTable += StringAligner.centerAlignString( "$" + rawTotal, colWidth);
			invoiceTable += "\n";
		}
		
		return invoiceTable;
	}
}

