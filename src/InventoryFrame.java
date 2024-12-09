import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * InventoryFrame displays the products sold in the store
 * Items can be filtered using a prefix
 */
public class InventoryFrame extends Frame{

	ArrayList<Product> products;
	JTextArea inventoryTextArea;
	private final DecimalFormat df = new DecimalFormat("$ 0.00");

	/**
	 * Constructs an InventoryFrame with the given products and filter 
	 * and is positioned to the left of its owner
	 * @param productList the products to be displayed
	 * @param filter the code filter used to select products to show (must end with * to apply)
	 * @param owner the Component that constructed this
	 */
	public InventoryFrame(ArrayList<Product> productList, String filter, Component owner) {
		this.products = productList;

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		inventoryTextArea = new JTextArea(20, 80);
		inventoryTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		inventoryTextArea.setText(this.toTable(filter));
		inventoryTextArea.setEditable(false);

		JButton closeButton = new JButton("Close");
		closeButton.setForeground(Constants.RED);
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setLayout(new GridBagLayout());
		inventoryPanel.setBorder(BorderFactory.createTitledBorder("Inventory"));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		inventoryPanel.add(inventoryTextArea, c);
		
		c.gridy = 1;
		c.gridx = 2;
		c.fill = GridBagConstraints.NONE;
		inventoryPanel.add(closeButton, c);
		
		add(inventoryPanel);

		closeButton.addActionListener( e -> {
			dispose();
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				dispose();
			}        
		});

		setTitle("Inventory Window");
		pack();
		setLocation(owner.getX() - getWidth(), owner.getY());
		setVisible(true);
	}

	/**
	 * Creates a String formatted as a table with the current data
	 * @param filter the code filter used to select products to show
	 * @return a product list formatted as a table
	 */
	public String toTable(String filter) {
		String prefix = (filter.length() > 1 && filter.endsWith("*")) ? filter.substring(0, filter.length()-1) : "";
		
		String[] colNames = {"Name", "Code", "Price", "Description"};

		String inventoryTable = "\n";

		if (prefix.length() > 0) {
			inventoryTable += StringAligner.centerAlignString("Showing products with starting code " + prefix, Constants.WIDTH);
			inventoryTable += "\n\n";
		}
		
		for (int i=0; i<colNames.length; i++) {
			inventoryTable += StringAligner.centerAlignString(colNames[i], Constants.COLWIDTH);
		}
		
		inventoryTable += "\n";

		int count = 0;
		inventoryTable += "\n";
		for (int i=0; i<products.size(); i++) {
			Product p = products.get(i);

			if (p.getCode().startsWith(prefix)) {
				inventoryTable += StringAligner.centerAlignString(p.getName(), Constants.COLWIDTH);
				inventoryTable += StringAligner.centerAlignString(p.getCode(), Constants.COLWIDTH);
				inventoryTable += StringAligner.centerAlignString(df.format(p.getPrice()), Constants.COLWIDTH);
				inventoryTable += StringAligner.centerAlignString(p.getDescription(), Constants.COLWIDTH);
				inventoryTable += "\n";
				count++;
			}
		}
		
		if (count == 0) inventoryTable += StringAligner.centerAlignString("No product code matched", Constants.WIDTH);

		return inventoryTable;
	}
}
