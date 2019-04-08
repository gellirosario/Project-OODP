package classes;

/**
 * Represents the Invoice of an Order
 * 
 * @author wongwanting
 *
 */
public class Invoice {

	/**
	 * The id of the invoice
	 */
	private int id;

	/**
	 * The order of the invoice
	 */
	private Order order;

	/**
	 * The total tax of the invoice
	 */
	private double tax;

	/**
	 * The total svcChrg of the invoice
	 */
	private double svcChrg;

	/**
	 * The sub total of the invoice
	 */
	private double subTotal;

	/**
	 * The grand total of the invoice
	 */
	private double grandTotal;

	/**
	 * 
	 * Creates a new invoice with the given id, order, tax, svcChrg, subTotal and
	 * grandTotal
	 * 
	 * @param id         This is the Invoice's id
	 * @param order      This is the Order of the Invoice
	 * @param tax        This is the tax charged on the Invoice
	 * @param svcChrg    This is the svcChrg charged on the Invoice
	 * @param subTotal   This is the subTotal charged on the Invoice
	 * @param grandTotal This is the grandTotal charged on the Invoice
	 * 
	 */
	public Invoice(int id, Order order, double tax, double svcChrg, double subTotal, double grandTotal) {
		this.setId(id);
		this.setOrder(order);
		this.setTax(tax);
		this.setSvcChrg(svcChrg);
		this.setSubTotal(subTotal);
		this.setGrandTotal(grandTotal);
	}

	/**
	 * Gets the id of this Invoice
	 * 
	 * @return the invoiceId
	 */
	public int getId() {
		return id;
	}

	/**
	 * Changes the id of this Invoice
	 * 
	 * @param invoiceId set invoiceId
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the order for this Invoice
	 * 
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Changes the order for this Invoice
	 * 
	 * @param order set order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Gets the tax charged of this Invoice
	 * 
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * Changes the tax of this Invoice
	 * 
	 * @param tax set tax
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * Gets the service charge of this Invoice
	 * 
	 * @return the service charge
	 */
	public double getSvcChrg() {
		return svcChrg;
	}

	/**
	 * Changes the service charge of this Invoice
	 * 
	 * @param svcChrg set svcChrg
	 */
	public void setSvcChrg(double svcChrg) {
		this.svcChrg = svcChrg;
	}

	/**
	 * Gets the sub-total of this Invoice
	 * 
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}

	/**
	 * Changes the sub-total of this Invoice
	 * 
	 * @param subTotal set subTotal
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * Gets the grand total of this Invoice
	 * 
	 * @return the grandTotal
	 */
	public double getGrandTotal() {
		return grandTotal;
	}

	/**
	 * Changes the grand total of this Invoice
	 * 
	 * @param grandTotal set grandTotal
	 */
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
}
