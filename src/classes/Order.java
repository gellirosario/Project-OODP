package classes;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents the order at the restaurant
 * 
 * @author Ann
 *
 */
public class Order{
	
	/**
	 * The id of the order
	 * 
	 */
	private int id;
	
	/**
	 * The Staff in-charged of creating the order
	 * 
	 */
	private Staff staff;
	
	/**
	 * The list of Sale Items of the order
	 * 
	 */
	private ArrayList<SaleItem> items;
	
	/**
	 * The table of the order
	 * 
	 */
	private Table table;
	
	/**
	 * The orderDateTime of the order
	 * 
	 */
	private Calendar orderDateTime;
	
	
	/**
	 * Creates a new Order with the given id, staff, items, table and orderDateTime
	 * @param id This is Order's id
	 * @param staff This is Order created under the specified staff
	 * @param table This is the Order table
	 * @param orderDateTime This is the date and time of the Order
	 * 
	 * 
	 */
	public Order(int id, Staff staff,ArrayList<SaleItem> items, Table table, Calendar orderDateTime)
	{
		this.setId(id);
		this.setStaff(staff);
		this.setItems(items);
		this.setTable(table);
		this.setOrderDateTime(orderDateTime);
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
	 * @return the staff
	 */
	public Staff getStaff() {
		return staff;
	}



	/**
	 * @param staff the staff to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}



	/**
	 * @return the items
	 */
	public ArrayList<SaleItem> getItems() {
		return items;
	}



	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<SaleItem> items) {
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
	public Calendar getOrderDateTime() {
		return orderDateTime;
	}



	/**
	 * @param orderDateTime the orderDateTime to set
	 */
	public void setOrderDateTime(Calendar orderDateTime) {
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
	
	
	/**
	 * returns a String of an Order, including all of its attributes
	 */
	public String toString(String name) {
		String s = "";
		s = name+"|" + getId() + "|" + getStaff().getId() + "|" + getTable().getId() + "|" + getOrderDateTime().getTime();
		if (items.size() != 0) {
			s += "|";
			int saleItemId = 0;
			for (int i = 0; i < items.size(); i++) {
				saleItemId = items.get(i).getId();
				s += saleItemId + ",";
			}
			s = s.substring(0, s.length() - 1); // remove last char ','
		}
		return s;
	}
}

