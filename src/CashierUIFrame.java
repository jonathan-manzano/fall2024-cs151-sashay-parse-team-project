import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

/**
 * CashierUIFrame displays the controls for the application.
 * It allows the user to login, record shifts, and interact with inventory and invoice
 */
public class CashierUIFrame extends Frame{
	
	InvoiceFrame invoiceFrame;
	InventoryFrame inventoryFrame;
	Invoice invoice;
	Employee employee;
	Store store;
	Inventory inventory;
	
	// file path of app_info.json
	String filepath = "C:/Users/delga/eclipse-workspace/fall2024-cs151-sashay-parse-team-project/src/resources/app_info.json";
	
	/**
	 * Constructs a CashierUIFrame which initializes the necessary objects for the application
	 */
	public CashierUIFrame() {
		loadJSONStore();
		employee = new Employee();
		inventory = new Inventory();
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
		JLabel startDateTimeLabel = new JLabel("Start Date/Time: ");
		JLabel endDateTimeLabel = new JLabel("End Date/Time: ");
		JTextField employeeTextField = new JTextField(20);
		JTextField startDateTimeTextField = new JTextField(20);
		JTextField endDateTimeTextField = new JTextField(20);
		JButton endShiftButton = new JButton("End Shift");
		
		endShiftButton.setForeground(Constants.RED);
		
		employeeTextField.setEditable(false);
		startDateTimeTextField.setEditable(false);
		endDateTimeTextField.setEditable(false);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		shiftInfoPanel.add(employeeLabel, c);
		
		c.gridy++;
		shiftInfoPanel.add(startDateTimeLabel, c);
		
		c.gridy++;
		shiftInfoPanel.add(endDateTimeLabel, c);
		
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		shiftInfoPanel.add(employeeTextField, c);
		
		c.gridy++;
		shiftInfoPanel.add(startDateTimeTextField, c);
		
		c.gridy++;
		shiftInfoPanel.add(endDateTimeTextField, c);
		
		c.gridx = 2;
		c.gridy++;
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
		
		toggleComponents(shiftInfoPanel, false);
		toggleComponents(inventoryPanel, false);
		toggleComponents(controlPanel, false);
		
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
			// initialize employee name
			employee.setName(firstNameTextField.getText(), lastNameTextField.getText());
			
			// check if name has empty fields
			if (employee.getFirstName().length() != 0 && employee.getLastName().length() != 0) {
				employeeTextField.setText(employee.getName());
				startDateTimeTextField.setText(DateDecorator.readableFormat(java.time.LocalDateTime.now().toString()));
				endDateTimeTextField.setText("");
				firstNameTextField.setText("");
				lastNameTextField.setText("");
				
				toggleComponents(loginPanel, false);
				toggleComponents(shiftInfoPanel, true);
				 
				if (!inventory.isLoaded()) {
					loadInventoryButton.setEnabled(true);
				} else {
					showInventoryButton.setEnabled(true);
					toggleComponents(controlPanel, true);
				}
				
				if (invoiceFrame == null ) invoiceFrame = new InvoiceFrame(invoice, this);
				toggleComponents(invoiceFrame, true);
				
			} else {
				errorMessage.setText("Name must not be empty.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
			}
		});
		
		endShiftButton.addActionListener( e -> {
			
			// record end of shift
			endDateTimeTextField.setText(DateDecorator.readableFormat(java.time.LocalDateTime.now().toString()));
			
			// clears and disables InvoiceFrame
			invoice.clear();
			toggleComponents(invoiceFrame, false);
			
			// clears data and toggle off controls
	        productCodeTextField.setText("");
	        quantityTextField.setText("");
	        itemNumberTextField.setText("");
	        
	        toggleComponents(shiftInfoPanel, false);
	        toggleComponents(inventoryPanel, false);
	        toggleComponents(controlPanel, false);
	        toggleComponents(loginPanel, true);
		});
		
		loadInventoryButton.addActionListener( e -> {
			loadInventoryButton.setEnabled(false);
			
			loadJSONProducts();
	        
	        showInventoryButton.setEnabled(true);
	        toggleComponents(controlPanel, true);
		});
		
		showInventoryButton.addActionListener( e -> {
			// check if there exists an inventoryFrame, then dispose
			if (inventoryFrame != null) {
				inventoryFrame.dispose();
				inventoryFrame = null;
			}
			
			inventoryFrame = new InventoryFrame(inventory.getProducts(), productCodeTextField.getText(), this);
		});
		
		addItemButton.addActionListener( e -> {
			Product product = inventory.find(productCodeTextField.getText());
			int quantity = 0;
			
			// check if quantity has valid value
			try {
				quantity = Integer.valueOf(quantityTextField.getText());
			} catch (Exception exception) {
				errorMessage.setText("Invalid quantity value.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
				return;
			}
			
			// check if quantity is > 1
			if (quantity < 1) {
				errorMessage.setText("Quantity must be greater than zero.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
				return;
			}
			
			// check if code is valid
			if (product != null) {
				invoice.addItem(product, quantity);
			} else {
				errorMessage.setText("The product code entered does not exist.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
			}
		});
		
		removeItemButton.addActionListener( e -> {
			int index;
			
			// check if index is a valid integer
			try {
				index = Integer.valueOf(itemNumberTextField.getText());
			} catch (Exception exception) {
				errorMessage.setText("Invalid item number value.");
				errorDialog.setLocationRelativeTo(this);
				errorDialog.setVisible(true);
				return;
			}
			
			// check if index is within range
			if (index <= invoice.getSize() && index > 0) {
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
	
	/**
	 * Toggles (enable/disable) the components in a container
	 * @param container the container that contains the components to be toggled
	 * @param value the boolean value of which components are to be toggled to
	 */
	private static void toggleComponents(Container container, boolean value) {
        for (Component component : container.getComponents()) {
        	if (component instanceof JCheckBox) {
            	component.setEnabled(value);
            	((JCheckBox) component).setSelected(false);
        	}
        			
        	if (component instanceof JButton) {
        		component.setEnabled(value);
            }
        	
        	if (component instanceof JTextComponent && ((JTextComponent) component).isEditable()) {
        		component.setEnabled(value);
            }
            
            if (component instanceof Container) {
                toggleComponents((Container) component, value);
            }
        }
    }
	
	/**
	 * Loads the store information from a JSON file
	 */
	private void loadJSONStore() {
		try {
            ObjectMapper mapper = new ObjectMapper();
            AppData data = mapper.readValue(new File(filepath), AppData.class);
            store = data.getStoreInfo();
        } catch (Exception exception) {
        	System.out.println(exception.getMessage());
        }
	}
	
	/**
	 * Loads the product information from a JSON file
	 */
	private void loadJSONProducts() {
		try {
            ObjectMapper mapper = new ObjectMapper();
            AppData data = mapper.readValue(new File(filepath), AppData.class);
            List<Product> products = data.getProductInfo();
            
            for (Product product : products) {
                inventory.add(product);
            }
            
            inventory.setLoaded();
        } catch (Exception exception) {
        	System.out.println(exception.getMessage());
        }
	}
	
}