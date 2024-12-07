import java.util.*;

public class Invoice {
	ArrayList<Product> products;
	ArrayList<Integer> quantity;
	// listeners ?
	
	public Invoice() {
		this.products = new ArrayList<Product>();
		this.quantity = new ArrayList<Integer>();
		
		// dummy data (deletable)
		products.add(new Product("Lorem", 12345, 5.0, "Lorem Ipsum Dolor"));
		products.add(new Product("Ipsum", 12353, 6.0, "Dolor Ipsum Lorem"));
		products.add(new Product("Dolor", 23412, 2.0, "Ipsum Lorem Dolor"));
		
		quantity.add(1);
		quantity.add(2);
		quantity.add(3);
	}
	
	public void addItem(Product p, int q) {
		this.products.add(p);
		this.quantity.add(q);
	}
	
	public void removeItem(int i) {
		this.products.remove(i);
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

	public String toTable(int colWidth) {
		String[] colNames = {"Item No.", "Product", "Quantity", "Total"};
		
		String invoiceTable = "";
		
		for (int i=0; i<colNames.length; i++) {
			invoiceTable += StringAligner.centerAlignString(colNames[i], colWidth);
		}
		
		invoiceTable += "\n";
		for (int i=0; i<products.size(); i++) {
			Product p = products.get(i);
			double rawTotal = p.getPrice() * quantity.get(i); // double * int at the moment
			
			invoiceTable += StringAligner.centerAlignString(String.valueOf(i), colWidth);
			invoiceTable += StringAligner.centerAlignString(p.getName(), colWidth);
			invoiceTable += StringAligner.centerAlignString(String.valueOf(quantity.get(i)), colWidth);
			invoiceTable += StringAligner.centerAlignString( "$" + rawTotal, colWidth);
			invoiceTable += "\n";
		}
		
		return invoiceTable;
	}

}

