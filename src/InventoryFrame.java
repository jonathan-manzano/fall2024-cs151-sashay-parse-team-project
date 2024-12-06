import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InventoryFrame extends Frame{
	
	public InventoryFrame() {
		Color defaultBorderColor = new Color(200, 221, 242);
		
		// dummy data
		Store store = new Store("COSTCO Wholesale", "San Jose", "CA", "(409) 123-9876", 5.0);
		Employee employee = new Employee("Clyde Joshua", "Delgado");
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		
		JTextArea receiptTextArea = new JTextArea(19, 80); // actual formula, 19 + no. of items.
		receiptTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		receiptTextArea.setText("Some text");
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
		setVisible(true);
	}
}
