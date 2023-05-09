package database;

import java.util.ArrayList;

import model.Freight;

public class FreightDB implements FreightDBIF{
	private ArrayList<Freight> listOfFreighters;
	private void init() {
		
	}
	
	@Override
	public Freight findFreightByFreightNumber(String freightNumber) {
		Freight res = null;
//		for(Freight f : listOfFreighters) {
//			if(freightNumber == f.getFreightNumber()) {
//				res = f;
//			}
//		}
//		if(res == null) {
//			
//		}
		
		for(int i = 0; i < listOfFreighters.size(); i++) {
			if(freightNumber == listOfFreighters.get(i).getFreightNumber()) {
				res = listOfFreighters.get(i);
				i = listOfFreighters.size();
			}
		}
		if(res == null) {
			System.out.println("Freight number does not match any freighters in database");
		}
		
		return res;
	}
}
