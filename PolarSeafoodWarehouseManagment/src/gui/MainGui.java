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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class MainGui extends JFrame {

	private static MainGui frame;
	private JPanel contentPane;
	private JTextField txtProductName;
	private JTextField txtWarehouse;
	private JTable tblProducts;
	private JTextField textQuantity;
	private StorageCtrl storageCtrl;
	private ProductListTableModel pltm;

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
			if (!tblProducts.hasFocus()) {
				try {
					products = storageCtrl.findProductsByPartialName(txtProductName.getText());
					pltm = new ProductListTableModel(products);
					pltm.setData(products);
					this.tblProducts.setModel(pltm);

				} catch (DataAccessException e) {
					JOptionPane.showMessageDialog(null, "Could not update list");
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alex\\Documents\\GitHub\\1aarsProjekt\\PolarSeafoodWarehouseManagment\\img\\PSULOGO.png"));
		setTitle("Polar Seafood Ukraine");
		setFont(new Font("Dialog", Font.PLAIN, 10));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 746);
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
		panel_1.setBorder(null);
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

		tblProducts = new JTable();
		scrollPane.setViewportView(tblProducts);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 35, 256, 191, 293, 0 };
		gbl_panel_2.rowHeights = new int[] { 49, 0, 0, 0, 0, 43, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel findProduct = new JLabel("Find Product");
		findProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_findProduct = new GridBagConstraints();
		gbc_findProduct.anchor = GridBagConstraints.WEST;
		gbc_findProduct.insets = new Insets(0, 0, 5, 5);
		gbc_findProduct.gridx = 2;
		gbc_findProduct.gridy = 0;
		panel_2.add(findProduct, gbc_findProduct);

		JLabel lblProductName = new JLabel("Product Name:");
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductName.anchor = GridBagConstraints.EAST;
		gbc_lblProductName.gridx = 1;
		gbc_lblProductName.gridy = 1;
		panel_2.add(lblProductName, gbc_lblProductName);

		txtProductName = new JTextField();
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductName.gridx = 2;
		gbc_txtProductName.gridy = 1;
		panel_2.add(txtProductName, gbc_txtProductName);
		txtProductName.setColumns(10);

		JButton btnRegisterShipment = new JButton("REGISTER SHIPMENT");
		GridBagConstraints gbc_btnRegisterShipment = new GridBagConstraints();
		gbc_btnRegisterShipment.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegisterShipment.gridx = 4;
		gbc_btnRegisterShipment.gridy = 1;
		panel_2.add(btnRegisterShipment, gbc_btnRegisterShipment);
		btnRegisterShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterShipmentClicked();
			}
		});
		btnRegisterShipment.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblWarehouse = new JLabel("Warehouse:");
		GridBagConstraints gbc_lblWarehouse = new GridBagConstraints();
		gbc_lblWarehouse.insets = new Insets(0, 0, 5, 5);
		gbc_lblWarehouse.anchor = GridBagConstraints.EAST;
		gbc_lblWarehouse.gridx = 1;
		gbc_lblWarehouse.gridy = 2;
		panel_2.add(lblWarehouse, gbc_lblWarehouse);

		txtWarehouse = new JTextField();
		GridBagConstraints gbc_txtWarehouse = new GridBagConstraints();
		gbc_txtWarehouse.insets = new Insets(0, 0, 5, 5);
		gbc_txtWarehouse.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWarehouse.gridx = 2;
		gbc_txtWarehouse.gridy = 2;
		panel_2.add(txtWarehouse, gbc_txtWarehouse);
		txtWarehouse.setColumns(10);

		JButton btnReserveProduct = new JButton("RESERVE PRODUCT");
		GridBagConstraints gbc_btnReserveProduct = new GridBagConstraints();
		gbc_btnReserveProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnReserveProduct.gridx = 4;
		gbc_btnReserveProduct.gridy = 2;
		panel_2.add(btnReserveProduct, gbc_btnReserveProduct);
		btnReserveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserveProductClicked();
			}
		});
		btnReserveProduct.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblQuantity.gridx = 1;
		gbc_lblQuantity.gridy = 3;
		panel_2.add(lblQuantity, gbc_lblQuantity);

		textQuantity = new JTextField();
		GridBagConstraints gbc_textQuantity = new GridBagConstraints();
		gbc_textQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_textQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textQuantity.gridx = 2;
		gbc_textQuantity.gridy = 3;
		panel_2.add(textQuantity, gbc_textQuantity);
		textQuantity.setColumns(10);

		JButton btnPickProduct = new JButton("PICK PRODUCT");
		btnPickProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pickProductClicked();
				} catch (NumberFormatException | DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPickProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnPickProduct = new GridBagConstraints();
		gbc_btnPickProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnPickProduct.gridx = 4;
		gbc_btnPickProduct.gridy = 3;
		panel_2.add(btnPickProduct, gbc_btnPickProduct);

		JLabel lblNewLabel_1 = new JLabel("-");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

		init();
	}

	private void init() {
		storageCtrl = new StorageCtrl();
		updateProductList();

	}

	private void reserveProductClicked() {
		JOptionPane.showMessageDialog(this, "This use-case has not been implemented :)");
	}

	private void pickProductClicked() throws NumberFormatException, DataAccessException {

		if (!txtWarehouse.getText().isBlank() && !textQuantity.getText().isBlank()) {
			Product p = pltm.getDataAt(tblProducts.getSelectedRow());
			String warehouseName = txtWarehouse.getText();
			String qty = textQuantity.getText();

			List<LotLine> lls = storageCtrl.findAvailableProductInWarehouseAndPrepareToRemove(p, Integer.parseInt(qty), warehouseName);

			int input = JOptionPane.showConfirmDialog(contentPane, buildLotLines(lls), "Remove Products", JOptionPane.OK_CANCEL_OPTION);
			if(input == 0) {
				storageCtrl.confirmRemovalOfProductInWarehouse(lls);
			} else if (input == 1);
			//TODO: implement method that goes back and removes values on "removedqty" on LotLines 
			
		}

	}

	private String buildLotLines(List<LotLine> lls) {
		StringBuilder res = new StringBuilder();
		res.append("Lot Numbers in warehouse: ");
		res.append(lls.get(lls.size()-1).getLot().getWarehouse().getName());
		for (LotLine l : lls) {
			res.append("\nLot: ");
			res.append(l.getLot().getLotNumber());
		}
		return res.toString();
	}

	private void RegisterShipmentClicked() {
		PopUpRegisterShipment ps = new PopUpRegisterShipment();
		ps.setVisible(true);
	}

}
