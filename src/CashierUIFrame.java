import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class CashierUIFrame extends Frame{
	
	InvoiceFrame invoiceFrame;
	Invoice invoice;
	Employee employee;
	Store store;
	Inventory inventory;
	
	// file path of app_info.json
	String filepath = "C:/Users/delga/eclipse-workspace/fall2024-cs151-sashay-parse-team-project/src/resources/app_info.json";
	
	public CashierUIFrame() {
		employee = new Employee();
		
		try {
            ObjectMapper mapper = new ObjectMapper();
            AppData data = mapper.readValue(new File(filepath), AppData.class);
            store = data.getStoreInfo();
        } catch (Exception exception) {
        	System.out.println(exception.getMessage());
        }
		
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
		
		startShiftButton.setForeground(Constants.GREEN);
		
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
		
		endShiftButton.setForeground(Constants.RED);
		
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
		
		loadInventoryButton.setForeground(Constants.BLUE);
		showInventoryButton.setForeground(Constants.BLUE);
		
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
		
		addItemButton.setForeground(Constants.GREEN);
		
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
		
		removeItemButton.setForeground(Constants.RED);
		
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
		
		JDialog errorDialog = new JDialog(this);
		errorDialog.setTitle("Error");
        errorDialog.setSize(275, 100);
        errorDialog.setModal(true);
        JLabel errorMessage = new JLabel("");
        errorMessage.setHorizontalAlignment(JLabel.CENTER);
        errorMessage.setForeground(Constants.RED);
        errorDialog.add(errorMessage);
        errorDialog.setLocationRelativeTo(this);
		
		startShiftButton.addActionListener( e -> {
			// initializes employee
			employee.setFirstName(firstNameTextField.getText());
			employee.setLastName(lastNameTextField.getText());
			
			if (employee.getFirstName().length() != 0 || employee.getLastName().length() != 0) {
				employeeTextField.setText(employee.getName());
				dateTimeTextField.setText(DateDecorator.readableFormat(java.time.LocalDateTime.now().toString()));
				
				// opens invoice frame
				if (invoiceFrame == null ) {
					invoiceFrame = new InvoiceFrame(invoice);
				}
				
				
				setVisible(false);
				
				loginPanel.setVisible(false);
				shiftInfoPanel.setVisible(true);
				inventoryPanel.setVisible(true);
				controlPanel.setVisible(true);
				
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
			} else {
				errorMessage.setText("Name must not be empty.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
			}
		});
		
		endShiftButton.addActionListener( e -> {
			// clears UI
			// enables login panel
			invoiceFrame.dispose();
			invoiceFrame = null;
			invoice.clear();
			
	        productCodeTextField.setText("");
	        quantityTextField.setText("");
	        itemNumberTextField.setText("");
			
			loginPanel.setVisible(true);
			shiftInfoPanel.setVisible(false);
			inventoryPanel.setVisible(false);
			controlPanel.setVisible(false);
			
			pack();
		});
		
		loadInventoryButton.addActionListener( e -> {
			loadInventoryButton.setEnabled(false);
			
			inventory = new Inventory();
	        
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            AppData data = mapper.readValue(new File(filepath), AppData.class);
	            List<Product> products = data.getProductInfo();
	            
	            for (Product product : products) {
	                inventory.add(product);
	            }
	        } 
	        catch (Exception exception) 
	        {
	        	System.out.println(exception.getMessage());
	        }
	        
	        showInventoryButton.setEnabled(true);
	        addItemButton.setEnabled(true);
	        productCodeTextField.setEditable(true);
	        quantityTextField.setEditable(true);
	        removeItemButton.setEnabled(true);
	        itemNumberTextField.setEditable(true);
		});
		
		showInventoryButton.addActionListener( e -> {
			// opens inventory frame
			new InventoryFrame(inventory.getProducts(), productCodeTextField.getText(), this); // constructor should accept data (Products Array)
		});
		
		addItemButton.addActionListener( e -> {
			Product p = inventory.find(productCodeTextField.getText()); // product
			int q = 0;
			
			try {
				q = Integer.valueOf(quantityTextField.getText()); // quantity
			} catch (Exception exception) {
				errorMessage.setText("Invalid quantity value.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
				return;
			}
			
			
			if (q < 1) {
				errorMessage.setText("Quantity must be greater than zero.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
				return;
			}
			
			if (p != null) {
				invoice.addItem(p, q);
			} else {
				errorMessage.setText("The product code entered does not exist.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
			}
		});
		
		removeItemButton.addActionListener( e -> {
			int index;
			
			try {
				index = Integer.valueOf(itemNumberTextField.getText());
			} catch (Exception exception) {
				errorMessage.setText("Invalid quantity value.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
				return;
			}
			
			if (index <= invoice.getSize() && index >= 0) {
				invoice.removeItem(index);
			} else {
				errorMessage.setText("The item number entered is invalid.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
			}
			
			
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