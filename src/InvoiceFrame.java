import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * InvoiceFrame displays the current state of InvoiceData such as the items, totals, and discounts
 * as well as options to clear and show the receipt
 */
public class InvoiceFrame extends Frame{
	
	Invoice invoiceData;
	private final DecimalFormat df = new DecimalFormat("$ 0.00");

	/**
	 * Constructs an InvoiceFrame with the given data in Invoice and sets its position to the right of the owner
	 * @param invoiceData the DataModel to be displayed and observed
	 * @param owner the Component that constructed the InvoiceFrame
	 */
	public InvoiceFrame(Invoice invoiceData, Component owner) {
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
		JButton clearButton = new JButton("Clear");
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
		clearButton.setForeground(Constants.RED);
		payPrintReceiptButton.setForeground(Constants.BLUE);
		
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
		
		c.gridx = 0;
		invoiceControlPanel.add(clearButton, c);
		
		// Add Panels to Frame
		
		c.gridx = 0;
		c.gridy = 0;
		add(invoiceDataPanel, c);
		
		c.gridy++;
		add(invoiceControlPanel, c);
		
		
		discountCheckBox.addActionListener( e -> {
			if (invoiceData.applyDiscount()) {
				discountedPriceLabel.setForeground(Constants.GREEN);
			} else {
				discountedPriceLabel.setForeground(Color.GRAY);
				discountedPriceTextField.setText("not applied");
			}
		});
		
		payPrintReceiptButton.addActionListener( e -> {
			if (invoiceData.getSize() != 0) {
				new ReceiptFrame(invoiceData, this);
			} else {
				JDialog errorDialog = new JDialog(this);
				errorDialog.setTitle("Error");
		        errorDialog.setSize(300, 100);
		        errorDialog.setModal(true);
		        JLabel errorMessage = new JLabel("Add at least one item to proceed to payment.");
		        errorMessage.setHorizontalAlignment(JLabel.CENTER);
		        errorMessage.setForeground(Constants.RED);
		        errorDialog.add(errorMessage);
		        errorDialog.setLocationRelativeTo(this);
		        errorDialog.setVisible(true);
			}
		});
		
		clearButton.addActionListener( e -> {
			invoiceData.clear();
			discountCheckBox.setSelected(false);
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		
		setTitle("Invoice Window");
		pack();
	    setLocation(owner.getX() + owner.getWidth(), owner.getY());
		setVisible(true);
	}
	
	/**
	 * InvoiceTextAreaListener is an inner class object that observers a DataModel and updates accordingly.
	 * It displays a table of the invoice
	 */
	private class InvoiceTextAreaListener extends JTextArea implements DataListener{
		Invoice model;
		
		/**
		 * Constructs an InvoiceTextAreaListener with the given rows, cols, and model
		 * @param rows the number of rows or lines of the text area
		 * @param cols the number of columns or character length per line of the text area
		 * @param model the DataModel to be observed
		 */
		private InvoiceTextAreaListener (int rows, int cols, Invoice model) {
			super(rows, cols);
			this.model = model;
			model.addListener(this);
		}

		/**
		 * Updates the table on display when data changes
		 */
		public void dataChanged() {
			this.setText(model.toTable());
		}
	}
	
	/**
	 * TextFieldListener is an inner abstract class object that observes a DataModel and updates accordingly
	 */
	private abstract class TextFieldListener extends JTextField implements DataListener{
		Invoice model;

		/**
		 * Constructs a TextFieldListener with the given cols and model
		 * @param cols the number of characters in the text field
		 * @param model the DataModel to be observed
		 */
		public TextFieldListener (int cols, Invoice model) {
			super(cols);
			this.model = model;
			model.addListener(this);
		}

		/**
		 * Updates its value when data changes
		 */
		public abstract void dataChanged();
	}
	
	/**
	 * RawTotalTextField is a concrete class that displays and updates the raw total
	 */
	private class RawTotalTextField extends TextFieldListener {
		
		/**
		 * Constructs a RawTotalTextField with the given cols and model
		 * @param cols the number of characters in the text field
		 * @param model the DataModel to be observed
		 */
		public RawTotalTextField (int cols, Invoice model) {
			super(cols, model);
		}
		 
		/**
		 * Updates the raw total displayed when data changes
		 */
		public void dataChanged(){
			this.setText(df.format(model.rawTotal()));
		};
	}
	
	/**
	 * DiscountedTextField is a concrete class that displays and updates the discounted price total
	 */
	private class DiscountedTextField extends TextFieldListener {
		
		/**
		 * Constructs a DiscountedTextField with the given cols and model
		 * @param cols the number of characters in the text field
		 * @param model the DataModel to be observed
		 */
		public DiscountedTextField (int cols, Invoice model) {
			super(cols, model);
		}
		
		/**
		 * Updates the discounted price total when data changes
		 * Displays "not applied" when discount is not applied to the transaction
		 */
		public void dataChanged(){
			if (model.isDiscounted()) {
				this.setText(df.format(model.discountedTotal()));
			} else {
				this.setText("not applied");
			}
			
		};
	}
	
	/**
	 * TaxedTotalTextField is a concrete class that displays and updates the discounted price total
	 */
	private class TaxedTotalTextField extends TextFieldListener {
		
		/**
		 * Constructs a TaxedTotalTextField with the given cols and model
		 * @param cols the number of characters in the text field
		 * @param model the DataModel to be observed
		 */
		public TaxedTotalTextField (int cols, Invoice model) {
			super(cols, model);
		}
		
		/**
		 * Updates the taxed total displayed when data changes
		 */
		public void dataChanged(){
			this.setText(df.format(model.taxedTotal()));
		};
	}
	
	/**
	 * GrandTotalTextField is a concrete class that displays and updates the discounted price total
	 */
	private class GrandTotalTextField extends TextFieldListener {
		
		/**
		 * Constructs a GrandTotalTextField with the given cols and model
		 * @param cols the number of characters in the text field
		 * @param model the DataModel to be observed
		 */
		public GrandTotalTextField (int cols, Invoice model) {
			super(cols, model);
		}
		
		/**
		 * Updates the grand total displayed when data changes
		 */
		public void dataChanged(){
			this.setText(df.format(model.grandTotal()));
		};
	}
}
