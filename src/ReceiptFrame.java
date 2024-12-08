import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ReceiptFrame extends Frame{
	
	public ReceiptFrame(Invoice invoice) {		
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
		
		setTitle("Receipt Window");
		pack();
		setVisible(true);
	}
	
}
