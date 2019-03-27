package classes;

/**
 * Represents the invoice given to the customer after making payment of the order
 * 
 * @author Ann
 *
 */
public class Invoice {
	
	private static final long serialVersionUID = 4L;
	
	/**
	 * The id of the Invoice
	 * 
	 */
	private int id;
	
	/**
	 * The order of the Invoice
	 * 
	 */
	private Order order;
	
	/**
	 * The tax of the Invoice
	 * 
	 */
	private double tax;
	
	/**
	 * Creates a new Invoice with the given id, order and tax
	 * @param id This is Invoice's id
	 * @param order This is Invoice's order
	 * @param tax This is Invoice's tax 
	 * 
	 */
	public Invoice(int id, Order order, double tax) {
		this.setId(id);
		this.setOrder(order);
		this.setTax(tax);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
}
