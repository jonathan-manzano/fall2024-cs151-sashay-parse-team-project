import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class InvoiceFrame extends Frame implements DataListener{
	
	Invoice invoiceData;

	public InvoiceFrame() {
		
		// dummy data (deletable)
		Store store = new Store("COSTCO Wholesale", "San Jose", "CA", "(409) 123-9876", 5.0);

		invoiceData = new Invoice(store.getTax());
		int colWidth = 20;
		// end of dummy data

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		
		
		// Invoice Text Area
		JTextArea invoiceTextArea = new JTextArea(10, 20);
		invoiceTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		invoiceTextArea.setEditable(false);
		
		JPanel invoiceDataPanel = new JPanel();
		invoiceDataPanel.setLayout(new GridBagLayout());
		invoiceDataPanel.setBorder(BorderFactory.createTitledBorder("Invoice Information"));
		invoiceDataPanel.add(invoiceTextArea);
		
		c.gridx = 0;
		c.gridy = 0;
		add(invoiceDataPanel, c);
		
		// Invoice Control Panel
		JPanel invoiceControlPanel = new JPanel();
		invoiceControlPanel.setLayout(new GridBagLayout());
		invoiceControlPanel.setBorder(BorderFactory.createTitledBorder("Invoice Control"));
		
		// components
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
		
		salesTaxTextField.setEditable(false);
		discountTextField.setEditable(false);
		totalPriceTextField.setEditable(false);
		discountedPriceTextField.setEditable(false);
		grandTotalPriceTextField.setEditable(false);
		
		// dummy data (deletable)
		salesTaxTextField.setText(store.getLocation());
		discountTextField.setText(store.getTax().toString() + "%");
		// end of dummy data
		
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
		
		c.gridx = 1;
		c.gridy = 0;
		invoiceControlPanel.add(salesTaxTextField, c);

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
		
		// Add Panels to Frame
		
		c.gridx = 0;
		c.gridy = 0;
		add(invoiceDataPanel, c);
		
		c.gridy++;
		add(invoiceControlPanel, c);
		
		
		// TO BE DONE
		
		discountCheckBox.addActionListener( e -> {
			// updates discounted price
		});
		
		payPrintReceiptButton.addActionListener( e -> {
			// opens receipt window
			new ReceiptFrame(); // constructor should accept data (Product & Quantity Array)
			// clears invoice?
			
		});
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		
		setTitle("Invoice Window");
		pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()));
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	    
		setVisible(true);
	}

	@Override
	public void dataChanged() {
		// TODO Auto-generated method stub
		
	}
	
}
