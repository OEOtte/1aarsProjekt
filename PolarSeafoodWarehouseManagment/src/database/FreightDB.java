package database;

import java.util.ArrayList;

import model.Freight;

public class FreightDB implements FreightDBIF{
	private void init() {
		
	}
	
	@Override
	public Freight findFreightByFreightNumber(String freightNumber) {
		ArrayList<Freight> freighters = new ArrayList<>();
		Freight res = null;
//		for(Freight f : listOfFreighters) {
//			if(freightNumber == f.getFreightNumber()) {
//				res = f;
//			}
//		}
//		if(res == null) {
//			
//		}
		
		for(int i = 0; i < freighters.size(); i++) {
			if(freightNumber == freighters.get(i).getFreightNumber()) {
				res = freighters.get(i);
				i = freighters.size();
			}
		}
		if(res == null) {
			System.out.println("Freight number does not match any freighters in database");
		}
		
		return res;
	}
}
