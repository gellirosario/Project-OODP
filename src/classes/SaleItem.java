package classes;

/**
 * Abstract class, represents an item that the Restaurant is selling. <br>
 * A SaleItem can be a MenuItem or Set etc.
 * 
 * @author Kailing
 *
 */
public abstract class SaleItem {    
	/**
	 * id of SaleItem
	 */
	private int id;
	/**
	 * name of SaleItem
	 */
	private String name;
	/**
	 * description of SaleItem
	 */
	private String description;
	/**
	 * price of SaleItem
	 */
	private double price;

	/**
	 * Default constructor of SaleItem
	 */
	public SaleItem() {

	}

	/**
	 * Constructor with all attributes of SaleItem
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 */
	public SaleItem(int id, String name, String description, double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * Abstract method, print() of SaleItem
	 */
	public abstract void print();

	/**
	 * Abstract method, toString() of SaleItem
	 */
	public abstract String toString();

	/**
	 * Get id of SaleItem
	 * 
	 * @return id of SaleItem
	 */
	public int getId() {
		return id;
	}

	/**
	 * Change id of SaleItem
	 * 
	 * @param id new id of SaleItem
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get name of SaleItem
	 * 
	 * @return name of SaleItem
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change name of SaleItem
	 * 
	 * @param name new name of SaleItem
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get description of SaleItem
	 * 
	 * @return description of SaleItem
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Change description of SaleItem
	 * 
	 * @param description new description of SaleItem
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get price of SaleItem
	 * 
	 * @return price of SaleItem
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Change price of SaleItem
	 * 
	 * @param price new price of SaleItem
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
