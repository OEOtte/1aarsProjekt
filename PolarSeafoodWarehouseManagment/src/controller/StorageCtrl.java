package controller;

import model.*;

public class StorageCtrl {

	private Lot lot;

	public LotLine findAvailableLotByPriorityForProduct(Product product) {
		LotLine lotLine = new LotLine(product, 0, null, lot);
		return lotLine;
	}
}