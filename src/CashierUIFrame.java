import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CashierUIFrame extends Frame{
	
	InvoiceFrame invoiceFrame;
	Invoice invoice;
	Employee employee;
	Store store;
	Inventory inventory;
	
	public CashierUIFrame() {
		employee = new Employee();
		store = new Store("Costco", "408-723-0964", "San Jose", "CA", 9.375, 10.0);
		invoice = new Invoice(store, employee);
		
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
		JLabel dateTimeLabel = new JLabel("Date/Time: ");
		JTextField employeeTextField = new JTextField(20);
		JTextField dateTimeTextField = new JTextField(20);
		JButton endShiftButton = new JButton("End Shift");
		
		// placeholders (deletable)
		employeeTextField.setEditable(false);
		
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
		JLabel productCodeLabel = new JLabel("Product Code: ");
		JLabel quantityLabel = new JLabel("Quantity: ");
		JTextField productCodeTextField = new JTextField(20);		
		JTextField quantityTextField = new JTextField(20);
		
		c.gridx = 0;
		c.gridy = 0;
		addItemPanel.add(productCodeLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		addItemPanel.add(productCodeTextField, c);

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
		
		showInventoryButton.setEnabled(false);
        addItemButton.setEnabled(false);
        productCodeTextField.setEditable(false);
        quantityTextField.setEditable(false);
        removeItemButton.setEnabled(false);
        itemNumberTextField.setEditable(false);
		
		shiftInfoPanel.setVisible(false);
		inventoryPanel.setVisible(false);
		controlPanel.setVisible(false);
		
		// TO BE DONE
		
		startShiftButton.addActionListener( e -> {
			// disables login panel
			// initializes store?
			
			// initializes employee
			employee.setFirstName(firstNameTextField.getText());
			employee.setLastName(lastNameTextField.getText());
			employeeTextField.setText(employee.getName());
			dateTimeTextField.setText(DateDecorator.readableFormat(java.time.LocalDateTime.now().toString()));
			
			// opens invoice frame
			invoiceFrame = new InvoiceFrame(invoice);
			
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
			
	        productCodeTextField.setText("");;
	        quantityTextField.setText("");;
	        itemNumberTextField.setText("");;
			
			
			loginPanel.setVisible(true);
			shiftInfoPanel.setVisible(false);
			inventoryPanel.setVisible(false);
			controlPanel.setVisible(false);
			
			pack();
		});
		
		loadInventoryButton.addActionListener( e -> {
			loadInventoryButton.setEnabled(false);
			
			inventory = new Inventory();
			
			inventory.add(new Product("NY Cheesecake", "01145", 19.99, "Whole 14 Inch"));
	        inventory.add(new Product("Cookies", "01151", 10.25, "Chocolate Chip"));
	        inventory.add(new Product("Apples", "01102", 5.55, "1 lb Fuji"));
	        inventory.add(new Product("Clementines", "01136", 6.25, "3 lb Cuties"));
	        inventory.add(new Product("Salad", "01294", 8.40, "Mediterranean Mix"));
	        inventory.add(new Product("Chips", "01210", 4.99, "Lightly Salted"));
	        inventory.add(new Product("Cereal", "01257", 5.39, "Cheerios"));
	        inventory.add(new Product("Chocolate", "01211", 8.27, "Dove 60% Dark"));
	        inventory.add(new Product("Laptop", "01389", 429.99, "Dell Inspiron"));
	        inventory.add(new Product("TV", "01311", 1151.99, "LG 40 Inch OLED"));
	        inventory.add(new Product("Shoes", "01429", 29.99, "Croc Flip Flops"));
	        inventory.add(new Product("Shirt", "01484", 14.99, "Puma Golf Polo"));
	        
	        showInventoryButton.setEnabled(true);
	        addItemButton.setEnabled(true);
	        productCodeTextField.setEditable(true);
	        quantityTextField.setEditable(true);
	        removeItemButton.setEnabled(true);
	        itemNumberTextField.setEditable(true);
		});
		
		showInventoryButton.addActionListener( e -> {
			// opens inventory frame
			new InventoryFrame(inventory.getProducts(), productCodeTextField.getText()); // constructor should accept data (Products Array)
		});
		
		addItemButton.addActionListener( e -> {
			Product p = inventory.find(productCodeTextField.getText()); // product
			int q = Integer.valueOf(quantityTextField.getText()); // quantity
			
			if (p != null) invoice.addItem(p, q);
		});
		
		removeItemButton.addActionListener( e -> {
			invoice.removeItem(Integer.valueOf(itemNumberTextField.getText()));
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