import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;


public class ReceiptFrame extends Frame{
	
	private final DecimalFormat df = new DecimalFormat("$ 0.00");
	
	public ReceiptFrame(Invoice invoice, Component owner) {		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		
		JTextArea receiptTextArea = new JTextArea(19, 80); // actual formula, 19 + no. of items.
		receiptTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		receiptTextArea.setText(createReceipt(invoice));
		receiptTextArea.setEditable(false);
		
		JButton closeButton = new JButton("Close");
		closeButton.setForeground(Constants.RED);
		
		JPanel receiptPanel = new JPanel();
		receiptPanel.setLayout(new GridBagLayout());
		receiptPanel.setBorder(BorderFactory.createTitledBorder("Receipt"));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		receiptPanel.add(receiptTextArea, c);
		
		c.gridy = 1;
		c.gridx = 2;
		c.fill = GridBagConstraints.NONE;
		receiptPanel.add(closeButton, c);
		
		add(receiptPanel);
		
		closeButton.addActionListener( e -> {
			dispose();
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				dispose();
			}        
		});
		
		setTitle("Receipt Window");
		pack();
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	private String createReceipt(Invoice invoice) {
		Store store = invoice.getStore();
		
		String receiptText = "\n\n"
				+ StringAligner.centerAlignString(store.getName(), Constants.WIDTH) + "\n"
				+ StringAligner.centerAlignString(store.getLocation(), Constants.WIDTH) + "\n"
				+ StringAligner.centerAlignString(store.getPhone(), Constants.WIDTH) + "\n"
				+ StringAligner.centerAlignString(DateDecorator.readableFormat(java.time.LocalDateTime.now().toString()), Constants.WIDTH) + "\n"
				+ "\n";
		
		String[] colNames = {"Product", "Code", "Quantity", "Total"};
		
		for (int i=0; i<colNames.length; i++) {
			receiptText += StringAligner.centerAlignString(colNames[i], Constants.COLWIDTH);
		}
		
		receiptText += "\n\n";
		for (int i=0; i<invoice.getSize(); i++) {
			InvoiceItem item = invoice.getData(i);
			
			receiptText += StringAligner.centerAlignString(item.getName(), Constants.COLWIDTH);
			receiptText += StringAligner.centerAlignString(item.getCode(), Constants.COLWIDTH);
			receiptText += StringAligner.centerAlignString(String.valueOf(item.getQuantity()), Constants.COLWIDTH);
			receiptText += StringAligner.centerAlignString(df.format(item.getRawTotal()), Constants.COLWIDTH);
			receiptText += "\n";
		}
		
		receiptText += "\n"
				+ String.format("%" + (Constants.WIDTH - 2) + "s", "Raw Total: " + df.format(invoice.rawTotal())) + "\n"
				+ String.format("%" + (Constants.WIDTH - 2) + "s", "Discounted Total: " + df.format(invoice.discountedTotal())) + "\n"
				+ String.format("%" + (Constants.WIDTH - 2) + "s", "Taxed Total: " + df.format(invoice.taxedTotal())) + "\n"
				+ String.format("%" + (Constants.WIDTH - 2) + "s", "Grand Total: " + df.format(invoice.grandTotal())) + "\n"
				+ "\n"
				+ String.format("%" + (Constants.WIDTH - 2) + "s", "Your cashier serving you today is " + invoice.getEmployee().getName() + ".") + "\n"
				+ String.format("%" + (Constants.WIDTH - 2) + "s", "Thank you for shopping!") + "\n\n\n";
		
		return receiptText;
	}
	
}
