package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.ShipmentCtrl;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class PopUpRegisterShipment extends JDialog {
	private ShipmentCtrl sc;
	private List<String> staffNos;
	private StaffListTableModel sltm;
	private JTable tblStaff;
	private JTextField textFreightNumber;
	private JTextField textWarehouse;
	private JTextField textStaffNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PopUpRegisterShipment dialog = new PopUpRegisterShipment();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PopUpRegisterShipment() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alex\\Documents\\GitHub\\1aarsProjekt\\PolarSeafoodWarehouseManagment\\img\\PSULOGO.png"));
		setTitle("Create Shipment");
		setBounds(100, 100, 479, 309);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(panel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		tblStaff = new JTable();
		scrollPane.setViewportView(tblStaff);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(panel_2, BorderLayout.NORTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 155, 0, 115, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNewLabel = new JLabel("Freight Number");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);

		textFreightNumber = new JTextField();
		GridBagConstraints gbc_textFreightNumber = new GridBagConstraints();
		gbc_textFreightNumber.insets = new Insets(0, 0, 5, 5);
		gbc_textFreightNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFreightNumber.gridx = 1;
		gbc_textFreightNumber.gridy = 0;
		panel_2.add(textFreightNumber, gbc_textFreightNumber);
		textFreightNumber.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Warehouse: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textWarehouse = new JTextField();
		GridBagConstraints gbc_textWarehouse = new GridBagConstraints();
		gbc_textWarehouse.insets = new Insets(0, 0, 5, 5);
		gbc_textWarehouse.fill = GridBagConstraints.HORIZONTAL;
		gbc_textWarehouse.gridx = 1;
		gbc_textWarehouse.gridy = 1;
		panel_2.add(textWarehouse, gbc_textWarehouse);
		textWarehouse.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Staff Number:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textStaffNumber = new JTextField();
		GridBagConstraints gbc_textStaffNumber = new GridBagConstraints();
		gbc_textStaffNumber.insets = new Insets(0, 0, 5, 5);
		gbc_textStaffNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textStaffNumber.gridx = 1;
		gbc_textStaffNumber.gridy = 2;
		panel_2.add(textStaffNumber, gbc_textStaffNumber);
		textStaffNumber.setColumns(10);
				
						JButton btnAddStaff = new JButton("ADD STAFF");
						btnAddStaff.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addStaffClicked();
							}
						});
						GridBagConstraints gbc_btnAddStaff = new GridBagConstraints();
						gbc_btnAddStaff.insets = new Insets(0, 0, 5, 5);
						gbc_btnAddStaff.gridx = 2;
						gbc_btnAddStaff.gridy = 2;
						panel_2.add(btnAddStaff, gbc_btnAddStaff);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
						
								JButton btnCancel = new JButton("CANCEL");
								btnCancel.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										cancelClicked();
									}
								});
								panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
								panel_1.add(btnCancel);
						
								JButton btnCreateShipment = new JButton("CREATE SHIPMENT");
								btnCreateShipment.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											createShipmentClicked();
										} catch (DataAccessException e1) {
											// Make error thats shows it couldnt create shipment
										}
									}
								});
								panel_1.add(btnCreateShipment);

		init();
	}

	protected void cancelClicked() {
		super.setVisible(false);
		super.dispose();
	}

	protected void addStaffClicked() {
		if (staffNos == null) {
			staffNos = new ArrayList<String>();
		}
		if (!textStaffNumber.getText().isBlank()) {
			staffNos.add(textStaffNumber.getText());
			sltm.setData(textStaffNumber.getText());
			textStaffNumber.setText("");
		}

	}

	private void init() {
		sc = new ShipmentCtrl();
		staffNos = null;
		sltm = new StaffListTableModel(staffNos);
		tblStaff.setModel(sltm);

	}

	protected void createShipmentClicked() throws DataAccessException {
		RegisterShipmentGui rsg = new RegisterShipmentGui();

		if (!textFreightNumber.getText().isBlank() && !textWarehouse.getText().isBlank() && staffNos != null) {
			String freight = textFreightNumber.getText();
			String warehouse = textWarehouse.getText();

			sc.createShipment(staffNos, freight, warehouse);
			
			rsg.setShipmentCtrl(this.sc);
			
			rsg.setVisible(true);
			super.setVisible(false);
		} else {
			// pop could not create
			String st = "Could not create shipment, make sure to input correct information";
			JOptionPane.showMessageDialog(null, st);
		}
	}

}
