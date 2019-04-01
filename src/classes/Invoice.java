package classes;

import java.io.Serializable;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 4L;
	
	private int id;
	
	private Order order;
	
	private double tax;
	
	private double svcChrg;
	
	public Invoice(int id, Order order, double tax, double svcChrg) {
		this.setId(id);
		this.setOrder(order);
		this.setTax(tax);
		this.setSvcChrg(svcChrg);
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
}
