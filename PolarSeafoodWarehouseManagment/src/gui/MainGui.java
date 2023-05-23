package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.StorageCtrl;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;
import model.*;

public class MainGui extends JFrame {

	private static MainGui frame;
	private JPanel contentPane;
	private JTextField txtProductName;
	private JTextField txtBarcode;
	private JTable tblLotLines;
	private JTextField textQuantity;
	private StorageCtrl storageCtrl;
	private ProductListTableModel lltm;
	private boolean changesMade;
	private String tempProductName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// should not happen - we don't interrupt this thread
					e.printStackTrace();
				}
				frame.updateProductList();
			}
		}).start();
	}

	private void updateProductList() {
		SwingUtilities.invokeLater(() -> {
			List<Product> products;
			if (tempProductName != txtProductName.getText()) {
				try {
					products = storageCtrl.findProductsByPartialName(txtProductName.getText());
					ProductListTableModel lltm = new ProductListTableModel(products);
					lltm.setData(products);
					this.tblLotLines.setModel(lltm);
					changesMade = true;
				} catch (DataAccessException e) {
					JOptionPane.showMessageDialog(null, "Could not update list");
					e.printStackTrace();
				}

			} else {
				changesMade = false;
			}

		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setTitle("Polar Seafood Ukraine");
		setFont(new Font("Dialog", Font.PLAIN, 10));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\Alex\\Documents\\GitHub\\1aarsProjekt\\PolarSeafoodWarehouseManagment\\img\\PolarSeafood320.png"));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 452, 0 };
		gbl_panel_1.rowHeights = new int[] { 402, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);

		tblLotLines = new JTable();
		scrollPane.setViewportView(tblLotLines);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 35, 256, 191, 293, 0 };
		gbl_panel_2.rowHeights = new int[] { 49, 0, 0, 0, 0, 43, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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

		JButton btnRegisterShipment = new JButton("REGISTER SHIPMENT");
		GridBagConstraints gbc_btnRegisterShipment = new GridBagConstraints();
		gbc_btnRegisterShipment.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegisterShipment.gridx = 3;
		gbc_btnRegisterShipment.gridy = 1;
		panel_2.add(btnRegisterShipment, gbc_btnRegisterShipment);
		btnRegisterShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterShipmentClicked();
			}
		});
		btnRegisterShipment.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtBarcode = new JTextField();
		txtBarcode.setText("Barcode");
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBarcode.gridx = 1;
		gbc_txtBarcode.gridy = 2;
		panel_2.add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);

		JButton btnReserveProduct = new JButton("RESERVE PRODUCT");
		GridBagConstraints gbc_btnReserveProduct = new GridBagConstraints();
		gbc_btnReserveProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnReserveProduct.gridx = 3;
		gbc_btnReserveProduct.gridy = 2;
		panel_2.add(btnReserveProduct, gbc_btnReserveProduct);
		btnReserveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserveProductClicked();
			}
		});
		btnReserveProduct.setFont(new Font("Tahoma", Font.BOLD, 14));

		textQuantity = new JTextField();
		textQuantity.setText("Quantity");
		GridBagConstraints gbc_textQuantity = new GridBagConstraints();
		gbc_textQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_textQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textQuantity.gridx = 1;
		gbc_textQuantity.gridy = 3;
		panel_2.add(textQuantity, gbc_textQuantity);
		textQuantity.setColumns(10);

		JButton btnPickProduct = new JButton("PICK PRODUCT");
		btnPickProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickProductClicked();
			}
		});
		btnPickProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnPickProduct = new GridBagConstraints();
		gbc_btnPickProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnPickProduct.gridx = 3;
		gbc_btnPickProduct.gridy = 3;
		panel_2.add(btnPickProduct, gbc_btnPickProduct);

		init();
	}

	private void init() {
		storageCtrl = new StorageCtrl();
		// lltm = new ProductListTableModel(null);
		updateProductList();

	}

	protected void reserveProductClicked() {
		// TODO Auto-generated method stub

	}

	protected void pickProductClicked() {
		// TODO Auto-generated method stub

	}

	protected void RegisterShipmentClicked() {
		PopUpRegisterShipment ps = new PopUpRegisterShipment();
		ps.setVisible(true);
	}

}
