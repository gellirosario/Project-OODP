package classes;

import java.io.Serializable;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 4L;
	
	private int id;
	
	private Order order;
	
	private double tax;
	
	private double svcChrg;
	
	private double subTotal;
	
	private double grandTotal;
	
	public Invoice(int id, Order order, double tax, double svcChrg, double subTotal, double grandTotal) {
		this.setId(id);
		this.setOrder(order);
		this.setTax(tax);
		this.setSvcChrg(svcChrg);
		this.setSubTotal(subTotal);
		this.setGrandTotal(grandTotal);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public double getTax() {
		return tax;
	}
	
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	public double getSvcChrg() {
		return svcChrg;
	}
	
	public void setSvcChrg(double svcChrg) {
		this.svcChrg = svcChrg;
	}
	
	public double getSubTotal() {
		return subTotal;
	}
	
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	public double getGrandTotal() {
		return grandTotal;
	}
	
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
}
