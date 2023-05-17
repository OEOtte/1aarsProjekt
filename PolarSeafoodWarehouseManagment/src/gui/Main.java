package gui;

import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import controller.FreightCtrl;
import controller.ProductCtrl;
import controller.ShipmentCtrl;
import controller.StaffCtrl;
import controller.StorageCtrl;
import database.StaffDB;
import database.StaffDBIF;
import model.Freight;
import model.Product;
import model.Shipment;
import model.Staff;
import model.Warehouse;

public class Main {
	public static void main(String[] args) throws DataAccessException {
		
		
//		ProductCtrl pc = new ProductCtrl();
//		
//		Product p = pc.findProductByBarcode("4820226000099");
//		
//		System.out.println(p.getProductName());
//		
//		
//		FreightCtrl fc = new FreightCtrl();
//		Freight f = fc.findFreightByFreightNumber("9999");
//		System.out.println(f.getName());
//		
//		StaffCtrl sc = new StaffCtrl();
//		List<String> staffno = new ArrayList<>();
//		staffno.add("5555");
//		staffno.add("4444");
//		List<Staff> staffs = sc.findStaffById(staffno);
//		for(Staff s: staffs) {
//			System.out.println(s.getName());
//		}
//		
//		StorageCtrl sct = new StorageCtrl();
//		Warehouse w = sct.findWarehouseByName("PSU1");
//		System.out.println(w.getName());
//		
	
//		StaffDBIF s = new StaffDB();
//		int i = s.findStaffIdByNo("5555");
//		System.out.println(i);
	}
}
 