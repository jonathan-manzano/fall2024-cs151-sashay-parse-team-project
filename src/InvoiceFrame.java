import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class InvoiceFrame extends Frame{

	public InvoiceFrame() {
		Color defaultBorderColor = new Color(200, 221, 242);
		
		// dummy data
		Store store = new Store("COSTCO Wholesale", "San Jose", "CA", "(409) 123-9876", 5.0);
		String[] colNames = {"Item No.", "Product", "Quantity", "Price"};
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("Lorem", 12345, 5.0, "Lorem Ipsum Dolor"));
		products.add(new Product("Ipsum", 12353, 6.0, "Dolor Ipsum Lorem"));
		products.add(new Product("Dolor", 23412, 2.0, "Ipsum Lorem Dolor"));
		
		int colWidth = 20;
		String invoiceTableData = "";
		for (int i=0; i<colNames.length; i++) {
			invoiceTableData += StringAligner.centerAlignString(colNames[i], colWidth);
		}
		invoiceTableData += "\n";
		for (int i=0; i<products.size(); i++) {
			Product p = products.get(i);
			
			invoiceTableData += StringAligner.centerAlignString(String.valueOf(i), colWidth);
			invoiceTableData += StringAligner.centerAlignString(p.getName(), colWidth);
			invoiceTableData += StringAligner.centerAlignString(String.valueOf(1), colWidth);
			invoiceTableData += StringAligner.centerAlignString( "$" + String.valueOf(p.getPrice()), colWidth);
			invoiceTableData += "\n";
		}
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; // Column 0
		c.gridy = 0; // Row 0
		c.fill = GridBagConstraints.BOTH; // Fill the cell in both directions
		c.insets = new Insets(5, 5, 5, 5); // Add padding around the component
		
		
		JTextArea invoiceTextArea = new JTextArea(10, 20);
		invoiceTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		invoiceTextArea.setText(invoiceTableData);
		invoiceTextArea.setEditable(false);
		
		JPanel invoiceDataPanel = new JPanel();
		invoiceDataPanel.setLayout(new GridBagLayout());
		invoiceDataPanel.setBorder(BorderFactory.createTitledBorder("Invoice Information"));
		invoiceDataPanel.add(invoiceTextArea);
		
		c.gridx = 0;
		c.gridy = 0;
		add(invoiceDataPanel, c);
		
		JLabel salesTaxLabel = new JLabel("Sales Tax: ");
		JLabel discountLabel = new JLabel("Discount: ");
		JLabel discountCheckBoxLabel = new JLabel("Apply Discount: ");
		JLabel discountedPriceLabel = new JLabel("Discounted: ");
		JLabel totalPriceLabel = new JLabel("Total: ");
		JLabel grandTotalPriceLabel = new JLabel("Grand Price: ");
		
		JTextField salesTaxTextField = new JTextField(20);
		JTextField discountTextField = new JTextField(20);
		JCheckBox discountCheckBox = new JCheckBox();
		JTextField discountedPriceTextField = new JTextField(20);
		JTextField totalPriceTextField = new JTextField(20);
		JTextField grandTotalPriceTextField = new JTextField(20);
		
		JButton payPrintReceiptButton = new JButton("Pay and Print Receipt");
		
		JPanel invoiceControlPanel = new JPanel();
		invoiceControlPanel.setLayout(new GridBagLayout());
		invoiceControlPanel.setBorder(BorderFactory.createTitledBorder("Invoice Control"));
	
		c.gridy = 0;
		invoiceControlPanel.add(salesTaxLabel, c);
		
		c.gridy++;
		invoiceControlPanel.add(discountLabel, c);
		
		c.gridy++;
		invoiceControlPanel.add(discountCheckBoxLabel, c);

		c.gridy++;
		invoiceControlPanel.add(discountedPriceLabel, c);		

		c.gridy++;
		invoiceControlPanel.add(totalPriceLabel, c);
		
		c.gridy++;
		invoiceControlPanel.add(grandTotalPriceLabel, c);
		
		salesTaxTextField.setText(store.getLocation());
		salesTaxTextField.setEditable(false);
		
		c.gridx = 1;
		c.gridy = 0;
		invoiceControlPanel.add(salesTaxTextField, c);

		discountTextField.setText(store.getTax().toString() + "%");
		discountTextField.setEditable(false);
		c.gridy++;
		invoiceControlPanel.add(discountTextField, c); 
		
		c.gridy++;
		invoiceControlPanel.add(discountCheckBox, c); 

		c.gridy++;
		invoiceControlPanel.add(discountedPriceTextField, c);
		
		c.gridy++;
		invoiceControlPanel.add(totalPriceTextField, c);

		c.gridy++;
		invoiceControlPanel.add(grandTotalPriceTextField, c);
		
		c.gridy++;
		invoiceControlPanel.add(payPrintReceiptButton, c);
		
		
		
		
		
		
		
		
		
		
		c.gridx = 0;
		c.gridy = 0;
		add(invoiceDataPanel, c);
		
		c.gridy++;
		add(invoiceControlPanel, c);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		
		setTitle("Invoice Window");
		pack();
		setVisible(true);
	}
	
}
