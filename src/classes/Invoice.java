package classes;

import java.io.Serializable;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 4L;
	
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
	 * Creates a new invoice with the given id, order, tax, svcChrg, subTotal and grandTotal
	 * @param id This is the Invoice's id
	 * @param order This is the Order of the Invoice
	 * @param tax This is the tax charged on the Invoice
	 * @param svcChrg This is the svcChrg charged on the Invoice
	 * @param subTotal This is the subTotal charged on the Invoice
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
	 * 
	 * @return the invoiceId
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param invoiceId set invoiceId
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * 
	 * @param order set order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * 
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}
	
	/**
	 * 
	 * @param tax set tax
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	/**
	 * 
	 * @return the svcChrg
	 */
	public double getSvcChrg() {
		return svcChrg;
	}
	
	/**
	 * 
	 * @param svcChrg set svcChrg
	 */
	public void setSvcChrg(double svcChrg) {
		this.svcChrg = svcChrg;
	}
	
	/**
	 * 
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}
	
	/**
	 * 
	 * @param subTotal set subTotal
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	/**
	 * 
	 * @return the grandTotal
	 */
	public double getGrandTotal() {
		return grandTotal;
	}
	
	/**
	 * 
	 * @param grandTotal set grandTotal
	 */
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
}
