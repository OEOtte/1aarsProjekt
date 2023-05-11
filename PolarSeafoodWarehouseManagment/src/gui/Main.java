package gui;

import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import controller.ShipmentCtrl;
import model.Product;
import model.Shipment;

public class Main {
	public static void main(String[] args) throws DataAccessException {
		ShipmentCtrl sc = new ShipmentCtrl();
		List<String> sn = new ArrayList<>();
		sn.add("21");
		
		
		Shipment shipment = sc.createShipment(sn, "1020");
		System.out.println(shipment.toString());
		
		Product product = sc.scanProduct(60, "21931");
		System.out.println(product);
		
		
	}
}
