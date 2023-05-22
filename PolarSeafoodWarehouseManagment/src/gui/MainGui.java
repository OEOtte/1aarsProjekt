package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private JTextField textProduct;
	private JTextField textWarehouse;
	private JTextField txtLot;
	private JTextField txtProductName;
	private JTextField txtBarcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
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
	public MainGui() {
		setTitle("Polar Seafood Ukraine");
		setFont(new Font("Dialog", Font.PLAIN, 10));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\oscar\\OneDrive\\Dokumenter\\PolarSeafood1.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel PolarSeafood = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/PolarSeafood1.png")).getImage();
		PolarSeafood.setIcon(new ImageIcon(img));
		panel.add(PolarSeafood);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{37, 72, 106, 269, 0};
		gbl_panel_1.rowHeights = new int[]{30, 40, 36, 30, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lot = new JLabel("Lot:");
		lot.setBackground(new Color(0, 0, 255));
		lot.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lot = new GridBagConstraints();
		gbc_lot.anchor = GridBagConstraints.WEST;
		gbc_lot.insets = new Insets(0, 0, 5, 5);
		gbc_lot.gridx = 1;
		gbc_lot.gridy = 1;
		panel_1.add(lot, gbc_lot);
		
		txtLot = new JTextField();
		GridBagConstraints gbc_txtLot = new GridBagConstraints();
		gbc_txtLot.insets = new Insets(0, 0, 5, 5);
		gbc_txtLot.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLot.gridx = 2;
		gbc_txtLot.gridy = 1;
		panel_1.add(txtLot, gbc_txtLot);
		txtLot.setColumns(10);
		
		JButton btnRegisterShipment = new JButton("Register Shipment");
		btnRegisterShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterShipmentClicked();
			}
		});
		btnRegisterShipment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnRegisterShipment = new GridBagConstraints();
		gbc_btnRegisterShipment.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegisterShipment.gridx = 3;
		gbc_btnRegisterShipment.gridy = 1;
		panel_1.add(btnRegisterShipment, gbc_btnRegisterShipment);
		
		JLabel product = new JLabel("Product:");
		product.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_product = new GridBagConstraints();
		gbc_product.anchor = GridBagConstraints.WEST;
		gbc_product.insets = new Insets(0, 0, 5, 5);
		gbc_product.gridx = 1;
		gbc_product.gridy = 2;
		panel_1.add(product, gbc_product);
		
		textProduct = new JTextField();
		GridBagConstraints gbc_textProduct = new GridBagConstraints();
		gbc_textProduct.insets = new Insets(0, 0, 5, 5);
		gbc_textProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_textProduct.gridx = 2;
		gbc_textProduct.gridy = 2;
		panel_1.add(textProduct, gbc_textProduct);
		textProduct.setColumns(10);
		
		JButton btnReserveProduct = new JButton("Reserve Product");
		btnReserveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReserveProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnReserveProduct = new GridBagConstraints();
		gbc_btnReserveProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnReserveProduct.gridx = 3;
		gbc_btnReserveProduct.gridy = 2;
		panel_1.add(btnReserveProduct, gbc_btnReserveProduct);
		
		JLabel warehouse = new JLabel("Warehouse:");
		warehouse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_warehouse = new GridBagConstraints();
		gbc_warehouse.anchor = GridBagConstraints.WEST;
		gbc_warehouse.insets = new Insets(0, 0, 0, 5);
		gbc_warehouse.gridx = 1;
		gbc_warehouse.gridy = 3;
		panel_1.add(warehouse, gbc_warehouse);
		
		textWarehouse = new JTextField();
		GridBagConstraints gbc_textWarehouse = new GridBagConstraints();
		gbc_textWarehouse.insets = new Insets(0, 0, 0, 5);
		gbc_textWarehouse.fill = GridBagConstraints.HORIZONTAL;
		gbc_textWarehouse.gridx = 2;
		gbc_textWarehouse.gridy = 3;
		panel_1.add(textWarehouse, gbc_textWarehouse);
		textWarehouse.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{35, 187, 293, 0};
		gbl_panel_2.rowHeights = new int[]{49, 0, 0, 0, 43, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel findProduct = new JLabel("Find Product");
		findProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_findProduct = new GridBagConstraints();
		gbc_findProduct.anchor = GridBagConstraints.WEST;
		gbc_findProduct.insets = new Insets(0, 0, 5, 5);
		gbc_findProduct.gridx = 1;
		gbc_findProduct.gridy = 0;
		panel_2.add(findProduct, gbc_findProduct);
		
		txtProductName = new JTextField();
		txtProductName.setText("Product Name");
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductName.gridx = 1;
		gbc_txtProductName.gridy = 1;
		panel_2.add(txtProductName, gbc_txtProductName);
		txtProductName.setColumns(10);
		
		txtBarcode = new JTextField();
		txtBarcode.setText("Barcode");
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBarcode.gridx = 1;
		gbc_txtBarcode.gridy = 2;
		panel_2.add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.WEST;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 3;
		panel_2.add(btnSearch, gbc_btnSearch);
	}

	protected void RegisterShipmentClicked() {
		PopUpRegisterShipment ps = new PopUpRegisterShipment();
		ps.setVisible(true);
	}

}
