package classes;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	
	private int id;
	private SaleItem[] items;
	private Table table;
	private Date orderDateTime;
	
	public Order(int id, SaleItem[] items, Table table, Date orderDateTime)
	{
		this.setId(id);
		this.setItems(items);
		this.setTable(table);
		this.setOrderDateTime(orderDateTime);
	}

	/**
	 * @return the orderId
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the items
	 */
	public SaleItem[] getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(SaleItem[] items) {
		this.items = items;
	}

	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * @return the orderDateTime
	 */
	public Date getOrderDateTime() {
		return orderDateTime;
	}

	/**
	 * @param orderDateTime the orderDateTime to set
	 */
	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	
	/**
	 * Check if input id is valid; invalid if id already exist
	 * 
	 * @param orders
	 * @param id
	 * @return
	 */
	public static boolean isValidId(ArrayList<Order> orders, int id) {
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == id) {
				return false;
			}
		}
		return true;
	}
	
}
