import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class CashierUIFrame extends Frame{
	
	public CashierUIFrame() {
		Color defaultBorderColor = new Color(200, 221, 242);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; // Column 0
		c.gridy = 0; // Row 0
		//c.gridwidth = 1; // Occupies 1 column
		//c.gridheight = 1; // Occupies 1 row
		//c.weightx = 1.0; // Relative horizontal weight
		//c.weighty = 1.0; // Relative vertical weight
		c.fill = GridBagConstraints.BOTH; // Fill the cell in both directions
		//c.anchor = GridBagConstraints.CENTER; // Anchor the component in the center of the cell
		c.insets = new Insets(5, 5, 5, 5); // Add padding around the component

		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Last Name: ");
		
		JTextField firstNameTextField = new JTextField(20);		
		JTextField lastNameTextField = new JTextField(20);
		
		JButton startShiftButton = new JButton("Start Shift");
		// startShiftButton.setBackground(Color.GREEN);
		
		JPanel loginPanel = new JPanel(new GridBagLayout());
		
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		loginPanel.add(firstNameLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		loginPanel.add(firstNameTextField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		loginPanel.add(lastNameLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		loginPanel.add(lastNameTextField, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		loginPanel.add(startShiftButton, c);
		
		c.gridx = 0;
		c.gridy = 0;
		add(loginPanel, c);
		
		
		
		
		JLabel employeeLabel = new JLabel("Employee: ");
		JLabel dateTimeLabel = new JLabel("Date: ");
		
		JTextField employeeTextField = new JTextField(20);
		employeeTextField.setText("Clyde Joshua Delgado");
		employeeTextField.setEditable(false);
		JTextField dateTimeTextField = new JTextField(20);
		dateTimeTextField.setText(java.time.LocalDateTime.now().toString());
		dateTimeTextField.setEditable(false);
		
		JButton endShiftButton = new JButton("End Shift");
		
		JPanel shiftInfoPanel = new JPanel(new GridBagLayout());
		shiftInfoPanel.setBorder(BorderFactory.createTitledBorder("Shift Information"));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		shiftInfoPanel.add(employeeLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		shiftInfoPanel.add(employeeTextField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		shiftInfoPanel.add(dateTimeLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		shiftInfoPanel.add(dateTimeTextField, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		shiftInfoPanel.add(endShiftButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(shiftInfoPanel, c);
		
		
		
		JButton loadInventoryButton = new JButton("Load Inventory");
		JButton showInventoryButton = new JButton("Show Inventory");
		
		JPanel inventoryPanel = new JPanel(new GridBagLayout());
		inventoryPanel.setBorder(BorderFactory.createLineBorder(defaultBorderColor));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		inventoryPanel.add(loadInventoryButton, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		inventoryPanel.add(showInventoryButton, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(inventoryPanel, c);
		
		
		
		
		JButton addItemButton = new JButton("Add Item");
		
		JLabel productNumberLabel = new JLabel("Product Number: ");
		JLabel quantityLabel = new JLabel("Quantity: ");
		
		JTextField productNumberTextField = new JTextField(20);		
		JTextField quantityTextField = new JTextField(20);
		
		JPanel addItemPanel = new JPanel(new GridBagLayout());
		addItemPanel.setBorder(BorderFactory.createTitledBorder("Add Item"));
		
		c.gridx = 0;
		c.gridy = 0;
		addItemPanel.add(productNumberLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		addItemPanel.add(productNumberTextField, c);

		c.gridx = 0;
		c.gridy = 1;
		addItemPanel.add(quantityLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		addItemPanel.add(quantityTextField, c);
		
		c.gridx = 1;
		c.gridy = 2;
		addItemPanel.add(addItemButton, c);
		
		
		
		JPanel removeItemPanel = new JPanel(new GridBagLayout());
		removeItemPanel.setBorder(BorderFactory.createTitledBorder("Remove Item"));
		
		JLabel itemNumberLabel = new JLabel("Item Number: ");
		JTextField itemNumberTextField = new JTextField(20);
		JButton removeItemButton = new JButton("Remove");
		
		c.gridx = 0;
		c.gridy = 0;
		removeItemPanel.add(itemNumberLabel, c);
		
		c.gridx = 1;
		removeItemPanel.add(itemNumberTextField, c);;
		
		c.gridx = 2;
		removeItemPanel.add(removeItemButton, c);
		
		
		JPanel controlPanel = new JPanel(new GridBagLayout());
		controlPanel.setBorder(BorderFactory.createLineBorder(defaultBorderColor));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		controlPanel.add(addItemPanel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		controlPanel.add(removeItemPanel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(controlPanel, c);
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		
		setTitle("Cashier Window");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}