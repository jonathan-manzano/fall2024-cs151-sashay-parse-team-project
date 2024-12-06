import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class CashierUIFrame extends Frame{
	
	InvoiceFrame invoiceFrame;
	
	public CashierUIFrame() {
		Color defaultBorderColor = new Color(200, 221, 242);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		// Login Panel
		JPanel loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
		
		// components
		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Last Name: ");
		JTextField firstNameTextField = new JTextField(20);		
		JTextField lastNameTextField = new JTextField(20);
		JButton startShiftButton = new JButton("Start Shift");
		
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
		
		
		// Shift Panel
		JPanel shiftInfoPanel = new JPanel(new GridBagLayout());
		shiftInfoPanel.setBorder(BorderFactory.createTitledBorder("Shift Information"));
		
		// components
		JLabel employeeLabel = new JLabel("Employee: ");
		JLabel dateTimeLabel = new JLabel("Date: ");
		JTextField employeeTextField = new JTextField(20);
		JTextField dateTimeTextField = new JTextField(20);
		JButton endShiftButton = new JButton("End Shift");
		
		// placeholders (deletable)
		employeeTextField.setText("Clyde Joshua Delgado");
		employeeTextField.setEditable(false);
		dateTimeTextField.setText(java.time.LocalDateTime.now().toString());
		dateTimeTextField.setEditable(false);
		
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
		
		
		// Inventory Panel
		JPanel inventoryPanel = new JPanel(new GridBagLayout());
		inventoryPanel.setBorder(BorderFactory.createLineBorder(defaultBorderColor));
		
		// components
		JButton loadInventoryButton = new JButton("Load Inventory");
		JButton showInventoryButton = new JButton("Show Inventory");
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		inventoryPanel.add(loadInventoryButton, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		inventoryPanel.add(showInventoryButton, c);

		
		// Add Item Control Panel
		JPanel addItemPanel = new JPanel(new GridBagLayout());
		addItemPanel.setBorder(BorderFactory.createTitledBorder("Add Item"));
		
		JButton addItemButton = new JButton("Add Item");
		JLabel productNumberLabel = new JLabel("Product Number: ");
		JLabel quantityLabel = new JLabel("Quantity: ");
		JTextField productNumberTextField = new JTextField(20);		
		JTextField quantityTextField = new JTextField(20);
		
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
		
		
		// Remove Item Control Panel
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
		
		
		// Joined Control Panel (Add and Remove)
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
		
		
		// Add Panels to Frame
		c.gridx = 0;
		c.gridy = 0;
		add(loginPanel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(shiftInfoPanel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(inventoryPanel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(controlPanel, c);
		
		shiftInfoPanel.setVisible(false);
		inventoryPanel.setVisible(false);
		controlPanel.setVisible(false);
		
		// TO BE DONE
		
		startShiftButton.addActionListener( e -> {
			// disables login panel
			// initializes store?
			// initializes employee
			// opens invoice frame
			invoiceFrame = new InvoiceFrame();
			
			loginPanel.setVisible(false);
			shiftInfoPanel.setVisible(true);
			inventoryPanel.setVisible(true);
			controlPanel.setVisible(true);
			
			pack();
		});
		
		endShiftButton.addActionListener( e -> {
			// clears UI
			// enables login panel
			
			invoiceFrame.dispose();
			
			loginPanel.setVisible(true);
			shiftInfoPanel.setVisible(false);
			inventoryPanel.setVisible(false);
			controlPanel.setVisible(false);
			
			pack();
		});
		
		loadInventoryButton.addActionListener( e -> {
			// loads inventory from JSON file
		});
		
		showInventoryButton.addActionListener( e -> {
			// requires inventory to be loaded
			// opens inventory frame
			new InventoryFrame(); // constructor should accept data (Products Array)
		});
		
		addItemButton.addActionListener( e -> {
			// add item to invoice
		});
		
		removeItemButton.addActionListener( e -> {
			// remove item from invoice
		});
		
		
		
		
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