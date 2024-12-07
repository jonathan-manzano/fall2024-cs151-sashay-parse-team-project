import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class InventoryFrame extends Frame{

	ArrayList<Product> products;

	public InventoryFrame(ArrayList<Product> productList, String filter, Component owner) {
		this.products = productList;

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		JTextArea receiptTextArea = new JTextArea(19, 80); // actual formula, 19 + no. of items.
		receiptTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		receiptTextArea.setText(this.toTable(filter));
		receiptTextArea.setEditable(false);

		JPanel receiptPanel = new JPanel();
		receiptPanel.setLayout(new GridBagLayout());
		receiptPanel.setBorder(BorderFactory.createTitledBorder("Receipt"));
		receiptPanel.add(receiptTextArea);

		add(receiptPanel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				dispose();
			}        
		});

		setTitle("Inventory Window");
		pack();
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	public String toTable(String filter) {
		String prefix = (filter.length() > 1 && filter.endsWith("*")) ? filter.substring(0, filter.length()-1) : "";
		
		String[] colNames = {"Name", "Code", "Price", "Description"};

		String inventoryTable = "";

		for (int i=0; i<colNames.length; i++) {
			inventoryTable += StringAligner.centerAlignString(colNames[i], Constants.COLWIDTH);
		}

		int count = 0;
		inventoryTable += "\n";
		for (int i=0; i<products.size(); i++) {
			Product p = products.get(i);

			if (p.getCode().startsWith(prefix)) {
				inventoryTable += StringAligner.centerAlignString(p.getName(), Constants.COLWIDTH);
				inventoryTable += StringAligner.centerAlignString(p.getCode(), Constants.COLWIDTH);
				inventoryTable += StringAligner.centerAlignString("$ " + p.getPrice(), Constants.COLWIDTH);
				inventoryTable += StringAligner.centerAlignString(p.getDescription(), Constants.COLWIDTH);
				inventoryTable += "\n";
				count++;
			}
		}
		
		if (count == 0) inventoryTable += StringAligner.centerAlignString("No product code matched", Constants.COLWIDTH * 4);

		return inventoryTable;
	}
}
