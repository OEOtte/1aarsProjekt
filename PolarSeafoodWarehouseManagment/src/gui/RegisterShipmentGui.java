package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.ShipmentCtrl;
import model.ShipmentLine;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;

public class RegisterShipmentGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtLot;
	private JTextField txtProduct;
	private JTextField txtWarehouse;
	private JTextField txtBarcode;
	private JTextField txtQuantity;
	private ShipmentCtrl sc;
	private ShipmentListTableModel sltm;
	private List<ShipmentLine> sl;
	private JTable tblShipmentLine;
	private JTextField txtYear;
	private JTextField txtMonth;
	private JTextField txtDay;

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
		setBounds(100, 100, 685, 507);
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
		gbl_panel_1.columnWidths = new int[]{49, 66, 52, 0, 115, 47, 60, 32, 49, 57, 41, 0};
		gbl_panel_1.rowHeights = new int[]{22, 0, 0, 0, 34, 0, 0, 29, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lot = new JLabel("Lot:");
		GridBagConstraints gbc_lot = new GridBagConstraints();
		gbc_lot.anchor = GridBagConstraints.EAST;
		gbc_lot.insets = new Insets(0, 0, 5, 5);
		gbc_lot.gridx = 1;
		gbc_lot.gridy = 1;
		panel_1.add(lot, gbc_lot);
		
		txtLot = new JTextField();
		GridBagConstraints gbc_txtLot = new GridBagConstraints();
		gbc_txtLot.gridwidth = 2;
		gbc_txtLot.insets = new Insets(0, 0, 5, 5);
		gbc_txtLot.fill = GridBagConstraints.BOTH;
		gbc_txtLot.gridx = 2;
		gbc_txtLot.gridy = 1;
		panel_1.add(txtLot, gbc_txtLot);
		txtLot.setColumns(10);
		
		JLabel product = new JLabel("Product:");
		GridBagConstraints gbc_product = new GridBagConstraints();
		gbc_product.anchor = GridBagConstraints.EAST;
		gbc_product.insets = new Insets(0, 0, 5, 5);
		gbc_product.gridx = 1;
		gbc_product.gridy = 2;
		panel_1.add(product, gbc_product);
		
		txtProduct = new JTextField();
		GridBagConstraints gbc_txtProduct = new GridBagConstraints();
		gbc_txtProduct.gridwidth = 2;
		gbc_txtProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduct.gridx = 2;
		gbc_txtProduct.gridy = 2;
		panel_1.add(txtProduct, gbc_txtProduct);
		txtProduct.setColumns(10);
		
		JLabel warehouse = new JLabel("Warehouse:");
		GridBagConstraints gbc_warehouse = new GridBagConstraints();
		gbc_warehouse.anchor = GridBagConstraints.EAST;
		gbc_warehouse.insets = new Insets(0, 0, 5, 5);
		gbc_warehouse.gridx = 1;
		gbc_warehouse.gridy = 3;
		panel_1.add(warehouse, gbc_warehouse);
		
		txtWarehouse = new JTextField();
		GridBagConstraints gbc_txtWarehouse = new GridBagConstraints();
		gbc_txtWarehouse.gridwidth = 2;
		gbc_txtWarehouse.insets = new Insets(0, 0, 5, 5);
		gbc_txtWarehouse.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWarehouse.gridx = 2;
		gbc_txtWarehouse.gridy = 3;
		panel_1.add(txtWarehouse, gbc_txtWarehouse);
		txtWarehouse.setColumns(10);
		
		JLabel registerShipment = new JLabel("Register Shipment");
		registerShipment.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_registerShipment = new GridBagConstraints();
		gbc_registerShipment.insets = new Insets(0, 0, 5, 5);
		gbc_registerShipment.gridx = 1;
		gbc_registerShipment.gridy = 5;
		panel_1.add(registerShipment, gbc_registerShipment);
		
		JLabel lblNewLabel = new JLabel("Year");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 8;
		gbc_lblNewLabel.gridy = 5;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Month");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 9;
		gbc_lblNewLabel_1.gridy = 5;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Day");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 10;
		gbc_lblNewLabel_2.gridy = 5;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel Barcode = new JLabel("Barcode:");
		GridBagConstraints gbc_Barcode = new GridBagConstraints();
		gbc_Barcode.insets = new Insets(0, 0, 5, 5);
		gbc_Barcode.anchor = GridBagConstraints.EAST;
		gbc_Barcode.gridx = 2;
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
		
		txtYear = new JTextField();
		GridBagConstraints gbc_txtYear = new GridBagConstraints();
		gbc_txtYear.insets = new Insets(0, 0, 5, 5);
		gbc_txtYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYear.gridx = 8;
		gbc_txtYear.gridy = 6;
		panel_1.add(txtYear, gbc_txtYear);
		txtYear.setColumns(10);
		
		txtMonth = new JTextField();
		GridBagConstraints gbc_txtMonth = new GridBagConstraints();
		gbc_txtMonth.insets = new Insets(0, 0, 5, 5);
		gbc_txtMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMonth.gridx = 9;
		gbc_txtMonth.gridy = 6;
		panel_1.add(txtMonth, gbc_txtMonth);
		txtMonth.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegisterShipmentClicked();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txtDay = new JTextField();
		GridBagConstraints gbc_txtDay = new GridBagConstraints();
		gbc_txtDay.insets = new Insets(0, 0, 5, 0);
		gbc_txtDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDay.gridx = 10;
		gbc_txtDay.gridy = 6;
		panel_1.add(txtDay, gbc_txtDay);
		txtDay.setColumns(10);
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegister.gridx = 1;
		gbc_btnRegister.gridy = 7;
		panel_1.add(btnRegister, gbc_btnRegister);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{332, 63, 0};
		gbl_panel_2.rowHeights = new int[]{133, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_2.add(scrollPane, gbc_scrollPane);
		
		tblShipmentLine = new JTable();
		scrollPane.setRowHeaderView(tblShipmentLine);
		
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelClicked();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 1;
		panel_2.add(btnCancel, gbc_btnCancel);
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridx = 1;
		gbc_btnConfirm.gridy = 1;
		panel_2.add(btnConfirm, gbc_btnConfirm);
	}	
	{
		init();
	}
	
	private void init() {
		sc = new ShipmentCtrl();
		//sltm = new ShipmentListTableModel(sl);
		//tblShipmentLine.setModel(sltm);
		
	}
	
	protected void RegisterShipmentClicked() throws DataAccessException {
		if(txtBarcode != null && txtQuantity!= null) {		
		String barcode = txtBarcode.getText();
		int year = Integer.parseInt(txtYear.getText());
		int month = Integer.parseInt(txtMonth.getText());
		int day = Integer.parseInt(txtDay.getText());
		LocalDate d = LocalDate.of(year, month, day);
		int qnty = Integer.parseInt(txtQuantity.getText());
		
		
		//LocalDate date = txtExpiryDate.getText()
		
		sc.scanProduct(qnty, barcode, d);
		
		
	}
}
	protected void cancelClicked() {
		super.setVisible(false);
		super.dispose();
	}
}
