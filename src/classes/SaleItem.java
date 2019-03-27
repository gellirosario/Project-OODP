package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class SaleItem implements Serializable{
	
	private static final long serialVersionUID = 4L;
	
	private int id;
	private String name;
	private String description;
	private double price;

	public SaleItem() {

	}

	public SaleItem(int id, String name, String description, double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

//	/** //TODO remove if not used, by submit
//	 * check if input id is valid; 
//	 * <br>invalid if id already exist
//	 * @param saleItems
//	 * @param id
//	 * @return
//	 */
//	public static boolean isValidId(ArrayList<SaleItem> saleItems, int id) {
//		for (int i = 0; i < saleItems.size(); i++) {
//			if (saleItems.get(i).getId() == id) {
//				return false;
//			}
//		}
//		return true;
//	}

//	/** //TODO remove if not used, by submit
//	 * return SaleItem by Id
//	 * @param saleItems
//	 * @param id
//	 * @return null if SaleItem not found;
//	 */
//	public static SaleItem getSaleItemById(ArrayList<SaleItem> saleItems, int id) {
//		for (int i = 0; i < saleItems.size(); i++) {
//			if (saleItems.get(i).getId() == id) {
//				return saleItems.get(i);
//			}
//		}
//		return null;
//	}

	// getter and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
