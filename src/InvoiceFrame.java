import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.text.DecimalFormat;


public class InvoiceFrame extends Frame{
	
	Invoice invoiceData;
	private final DecimalFormat df = new DecimalFormat("$ 0.00");

	public InvoiceFrame(Invoice invoiceData) {
		this.invoiceData = invoiceData;

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		
		
		// Invoice Text Area
		InvoiceTextAreaListener invoiceTextArea = new InvoiceTextAreaListener(12, Constants.WIDTH + 5, invoiceData);
		invoiceTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		invoiceTextArea.setEditable(false);
		invoiceTextArea.setText(invoiceData.toTable());
		JScrollPane scrollPane = new JScrollPane(invoiceTextArea);
		
		JPanel invoiceDataPanel = new JPanel();
		invoiceDataPanel.setLayout(new GridBagLayout());
		invoiceDataPanel.setBorder(BorderFactory.createTitledBorder("Invoice Information"));
		invoiceDataPanel.add(scrollPane);
		
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
		JLabel rawTotalPriceLabel = new JLabel("Raw Total: ");
		JLabel discountedPriceLabel = new JLabel("Discounted: ");
		JLabel taxedTotalPriceLabel = new JLabel("Taxed Total: ");
		JLabel grandTotalPriceLabel = new JLabel("Grand Price: ");
		JTextField salesTaxTextField = new JTextField(20);
		JTextField discountTextField = new JTextField(20);
		JCheckBox discountCheckBox = new JCheckBox();
		RawTotalTextField rawTotalPriceTextField = new RawTotalTextField(20, invoiceData);
		DiscountedTextField discountedPriceTextField = new DiscountedTextField(20, invoiceData);
		TaxedTotalTextField taxedTotalPriceTextField = new TaxedTotalTextField(20, invoiceData);
		GrandTotalTextField grandTotalPriceTextField = new GrandTotalTextField(20, invoiceData);
		JButton payPrintReceiptButton = new JButton("Pay and Print Receipt");
		
		salesTaxTextField.setEditable(false);
		rawTotalPriceTextField.setEditable(false);
		discountTextField.setEditable(false);
		taxedTotalPriceTextField.setEditable(false);
		discountedPriceTextField.setEditable(false);
		grandTotalPriceTextField.setEditable(false);
		
		rawTotalPriceTextField.setText("$ 0.00");
		taxedTotalPriceTextField.setText("$ 0.00");
		discountedPriceTextField.setText("not applied");
		grandTotalPriceTextField.setText("$ 0.00");
		
		discountedPriceLabel.setForeground(Color.GRAY);
		
		salesTaxTextField.setText(invoiceData.getStore().getTax() + "%, " + invoiceData.getStore().getLocation());
		discountTextField.setText(invoiceData.getDiscount() + "%");
		
		c.gridy = 0;
		invoiceControlPanel.add(salesTaxLabel, c);
		
		c.gridy++;
		invoiceControlPanel.add(discountLabel, c);
		
		c.gridy++;
		invoiceControlPanel.add(discountCheckBoxLabel, c);

		c.gridy++;
		invoiceControlPanel.add(rawTotalPriceLabel, c);
		
		c.gridy++;
		invoiceControlPanel.add(discountedPriceLabel, c);		

		c.gridy++;
		invoiceControlPanel.add(taxedTotalPriceLabel, c);
		
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
		invoiceControlPanel.add(rawTotalPriceTextField, c);
		
		c.gridy++;
		invoiceControlPanel.add(discountedPriceTextField, c);
		
		c.gridy++;
		invoiceControlPanel.add(taxedTotalPriceTextField, c);

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
		
		
		discountCheckBox.addActionListener( e -> {
			// updates discounted price
			if (invoiceData.applyDiscount()) {
				discountedPriceLabel.setForeground(Constants.GREEN);
			} else {
				discountedPriceLabel.setForeground(Color.GRAY);
				discountedPriceTextField.setText("not applied");
			}
			
			// change text color according if discount is applied.
			// set text to "not applied"
			
			pack();
		});
		
		payPrintReceiptButton.addActionListener( e -> {
			// opens receipt window
			new ReceiptFrame(invoiceData, this);
			
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
	
	private class InvoiceTextAreaListener extends JTextArea implements DataListener{
		Invoice model;
		
		private InvoiceTextAreaListener (int rows, int cols, Invoice model) {
			super(rows, cols);
			this.model = model;
			model.addListener(this);
		}

		public void dataChanged() {
			this.setText(model.toTable());
		}
	}
	
	private abstract class TextFieldListener extends JTextField implements DataListener{
		Invoice model;
		
		public TextFieldListener (int cols, Invoice model) {
			super(cols);
			this.model = model;
			model.addListener(this);
		}

		public abstract void dataChanged();
	}
	
	private class RawTotalTextField extends TextFieldListener {
		
		public RawTotalTextField (int cols, Invoice model) {
			super(cols, model);
		}
		 
		public void dataChanged(){
			this.setText(df.format(model.rawTotal()));
		};
	}
	
	private class DiscountedTextField extends TextFieldListener {
		
		public DiscountedTextField (int cols, Invoice model) {
			super(cols, model);
		}
		 
		public void dataChanged(){
			this.setText(df.format(model.discountedTotal()));
		};
	}
	
	private class TaxedTotalTextField extends TextFieldListener {
		
		public TaxedTotalTextField (int cols, Invoice model) {
			super(cols, model);
		}
		 
		public void dataChanged(){
			this.setText(df.format(model.taxedTotal()));
		};
	}
	
	private class GrandTotalTextField extends TextFieldListener {
		
		public GrandTotalTextField (int cols, Invoice model) {
			super(cols, model);
		}
		 
		public void dataChanged(){
			this.setText(df.format(model.grandTotal()));
		};
	}
}
