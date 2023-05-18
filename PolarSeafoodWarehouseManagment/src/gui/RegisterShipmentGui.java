package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class RegisterShipmentGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtProductName;
	private JTextField txtBarcode;
	private JTextField txtQuantity;
	private JTextField txtExpira;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterShipmentGui frame = new RegisterShipmentGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterShipmentGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		Image img = new ImageIcon(this.getClass().getResource("/PolarSeafood1.png")).getImage();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{145, 311, 0};
		gbl_panel.rowHeights = new int[]{85, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		contentPane.add(panel, BorderLayout.NORTH);
		JLabel PolarSeafood = new JLabel("");
		PolarSeafood.setIcon(new ImageIcon(img));
		GridBagConstraints gbc_PolarSeafood = new GridBagConstraints();
		gbc_PolarSeafood.anchor = GridBagConstraints.WEST;
		gbc_PolarSeafood.gridx = 1;
		gbc_PolarSeafood.gridy = 0;
		panel.add(PolarSeafood, gbc_PolarSeafood);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{49, 66, 52, 0, 115, 38, 85, 32, 105, 25, 0};
		gbl_panel_1.rowHeights = new int[]{37, 0, 0, 0, 54, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lot = new JLabel("Lot:");
		GridBagConstraints gbc_lot = new GridBagConstraints();
		gbc_lot.anchor = GridBagConstraints.EAST;
		gbc_lot.insets = new Insets(0, 0, 5, 5);
		gbc_lot.gridx = 1;
		gbc_lot.gridy = 1;
		panel_1.add(lot, gbc_lot);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel freight = new JLabel("Freight:");
		GridBagConstraints gbc_freight = new GridBagConstraints();
		gbc_freight.anchor = GridBagConstraints.EAST;
		gbc_freight.insets = new Insets(0, 0, 5, 5);
		gbc_freight.gridx = 5;
		gbc_freight.gridy = 1;
		panel_1.add(freight, gbc_freight);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 7;
		gbc_textField_3.gridy = 1;
		panel_1.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel product = new JLabel("Product:");
		GridBagConstraints gbc_product = new GridBagConstraints();
		gbc_product.anchor = GridBagConstraints.EAST;
		gbc_product.insets = new Insets(0, 0, 5, 5);
		gbc_product.gridx = 1;
		gbc_product.gridy = 2;
		panel_1.add(product, gbc_product);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel staff = new JLabel("Staff:");
		GridBagConstraints gbc_staff = new GridBagConstraints();
		gbc_staff.insets = new Insets(0, 0, 5, 5);
		gbc_staff.anchor = GridBagConstraints.EAST;
		gbc_staff.gridx = 5;
		gbc_staff.gridy = 2;
		panel_1.add(staff, gbc_staff);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 7;
		gbc_textField_4.gridy = 2;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel warehouse = new JLabel("Warehouse:");
		GridBagConstraints gbc_warehouse = new GridBagConstraints();
		gbc_warehouse.anchor = GridBagConstraints.EAST;
		gbc_warehouse.insets = new Insets(0, 0, 5, 5);
		gbc_warehouse.gridx = 1;
		gbc_warehouse.gridy = 3;
		panel_1.add(warehouse, gbc_warehouse);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panel_1.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel registerShipment = new JLabel("Register Shipment");
		registerShipment.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_registerShipment = new GridBagConstraints();
		gbc_registerShipment.insets = new Insets(0, 0, 5, 5);
		gbc_registerShipment.gridx = 1;
		gbc_registerShipment.gridy = 5;
		panel_1.add(registerShipment, gbc_registerShipment);
		
		JLabel productName = new JLabel("Product Name:");
		GridBagConstraints gbc_productName = new GridBagConstraints();
		gbc_productName.insets = new Insets(0, 0, 5, 5);
		gbc_productName.anchor = GridBagConstraints.EAST;
		gbc_productName.gridx = 1;
		gbc_productName.gridy = 6;
		panel_1.add(productName, gbc_productName);
		
		txtProductName = new JTextField();
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductName.gridx = 2;
		gbc_txtProductName.gridy = 6;
		panel_1.add(txtProductName, gbc_txtProductName);
		txtProductName.setColumns(10);
		
		JLabel Barcode = new JLabel("Barcode:");
		GridBagConstraints gbc_Barcode = new GridBagConstraints();
		gbc_Barcode.insets = new Insets(0, 0, 5, 5);
		gbc_Barcode.anchor = GridBagConstraints.EAST;
		gbc_Barcode.gridx = 3;
		gbc_Barcode.gridy = 6;
		panel_1.add(Barcode, gbc_Barcode);
		
		txtBarcode = new JTextField();
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBarcode.gridx = 4;
		gbc_txtBarcode.gridy = 6;
		panel_1.add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel quantity = new JLabel("Quantity:");
		GridBagConstraints gbc_quantity = new GridBagConstraints();
		gbc_quantity.insets = new Insets(0, 0, 5, 5);
		gbc_quantity.anchor = GridBagConstraints.EAST;
		gbc_quantity.gridx = 5;
		gbc_quantity.gridy = 6;
		panel_1.add(quantity, gbc_quantity);
		
		txtQuantity = new JTextField();
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 6;
		gbc_txtQuantity.gridy = 6;
		panel_1.add(txtQuantity, gbc_txtQuantity);
		txtQuantity.setColumns(10);
		
		JLabel expiryDate = new JLabel("Expiry Date:");
		GridBagConstraints gbc_expiryDate = new GridBagConstraints();
		gbc_expiryDate.insets = new Insets(0, 0, 5, 5);
		gbc_expiryDate.anchor = GridBagConstraints.EAST;
		gbc_expiryDate.gridx = 7;
		gbc_expiryDate.gridy = 6;
		panel_1.add(expiryDate, gbc_expiryDate);
		
		txtExpira = new JTextField();
		GridBagConstraints gbc_txtExpira = new GridBagConstraints();
		gbc_txtExpira.insets = new Insets(0, 0, 5, 5);
		gbc_txtExpira.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtExpira.gridx = 8;
		gbc_txtExpira.gridy = 6;
		panel_1.add(txtExpira, gbc_txtExpira);
		txtExpira.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegister.gridx = 1;
		gbc_btnRegister.gridy = 7;
		panel_1.add(btnRegister, gbc_btnRegister);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 73, 84, 71, 75, 80, 77, 83, 0};
		gbl_panel_2.rowHeights = new int[]{30, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel = new JLabel("No. Pallet");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel productbottom = new JLabel("Product");
		GridBagConstraints gbc_productbottom = new GridBagConstraints();
		gbc_productbottom.insets = new Insets(0, 0, 5, 5);
		gbc_productbottom.gridx = 2;
		gbc_productbottom.gridy = 0;
		panel_2.add(productbottom, gbc_productbottom);
		
		JLabel barcodebottom = new JLabel("Barcode");
		GridBagConstraints gbc_barcodebottom = new GridBagConstraints();
		gbc_barcodebottom.insets = new Insets(0, 0, 5, 5);
		gbc_barcodebottom.gridx = 3;
		gbc_barcodebottom.gridy = 0;
		panel_2.add(barcodebottom, gbc_barcodebottom);
		
		JLabel NoOfBoxes = new JLabel("No. of Boxes");
		GridBagConstraints gbc_NoOfBoxes = new GridBagConstraints();
		gbc_NoOfBoxes.insets = new Insets(0, 0, 5, 5);
		gbc_NoOfBoxes.gridx = 4;
		gbc_NoOfBoxes.gridy = 0;
		panel_2.add(NoOfBoxes, gbc_NoOfBoxes);
		
		JLabel ExpirationDateBottom = new JLabel("Expiration Date");
		GridBagConstraints gbc_ExpirationDateBottom = new GridBagConstraints();
		gbc_ExpirationDateBottom.insets = new Insets(0, 0, 5, 5);
		gbc_ExpirationDateBottom.gridx = 5;
		gbc_ExpirationDateBottom.gridy = 0;
		panel_2.add(ExpirationDateBottom, gbc_ExpirationDateBottom);
		
		JLabel Weight = new JLabel("Weight");
		GridBagConstraints gbc_Weight = new GridBagConstraints();
		gbc_Weight.insets = new Insets(0, 0, 5, 5);
		gbc_Weight.gridx = 6;
		gbc_Weight.gridy = 0;
		panel_2.add(Weight, gbc_Weight);
		
		JLabel LotNo = new JLabel("Lot No.");
		GridBagConstraints gbc_LotNo = new GridBagConstraints();
		gbc_LotNo.insets = new Insets(0, 0, 5, 0);
		gbc_LotNo.gridx = 7;
		gbc_LotNo.gridy = 0;
		panel_2.add(LotNo, gbc_LotNo);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 1;
		panel_2.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 2;
		panel_2.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 0, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 3;
		panel_2.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
	}

}
