package classes;

import java.io.Serializable;

/**
 * Represents a table in the restaurant
 * The table can be occupied or vacated
 * 
 * @author Ann
 *
 */
public class Table{
	
	
	/**
	 * Enumeration type used to determine the status of the table
	 * 
	 */
	public static enum Status{
		Occupied("Occupied"),
		Vacated("Vacated"),
		Reserved("Reserved");
		
		private final String value;
		  
		private Status(String value) {
			this.value = value;
		}
		
		public String toStrValue() {
			return value;
		}
	}
	
	/**
	 * The unique id of the table for identifying specific tables
	 * 
	 */
	private int id;
	
	/**
	 * The status of this table
	 * 
	 */
	private Status status;
	
	/**
	 * The seatingCapacity of table
	 * 
	 */
	private int seatingCapacity;
	
	/**
	 * Creates a new Table with the given id, status and seatingCapacity
	 * @param id This is Table's id
	 * @param status This is Table's status
	 * @param seatingCapacity This is Table's seating capacity
	 * 
	 */
	public Table(int id, Status status, int seatingCapacity)
	{
		this.setId(id);
		this.setStatus(status);
		this.setSeatingCapacity(seatingCapacity);
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
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the seatingCapacity
	 */
	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	/**
	 * @param seatingCapacity the seatingCapacity to set
	 */
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	
}
