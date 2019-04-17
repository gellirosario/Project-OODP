package classes;

import java.util.ArrayList;

/**
 * Represents a table in the restaurant
 * The table can be occupied or vacated
 * 
 * @author Ann & Lexx
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
	 * ArrayList of all reservations of the table
	 */
	private ArrayList<Reservation> tableReservation;

	/**
	 * Creates a new Table with the given id, status and seatingCapacity
	 * @param id This is Table's id
	 * @param status This is Table's status
	 * @param seatingCapacity This is Table's seating capacity
	 * @param tableReservation empty ArrayList
	 * 
	 */
	public Table(int id, Status status, int seatingCapacity)
	{
		this.setId(id);
		this.setStatus(status);
		this.setSeatingCapacity(seatingCapacity);
		this.tableReservation = new ArrayList<Reservation>();

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
		//status = Status.Vacated;
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

	/**
	 * get the ArrayList of reservations
	 * @return this table's reservations
	 */	
	public ArrayList<Reservation> getTableReservation()
	{ 
		return this.tableReservation; 
	}

	/**
	 * add a reservation to the table's list
	 * @param reservation New table reservation
	 */
	public void addTableReservation(Reservation reservation)
	{ 
		this.tableReservation.add(reservation); 
	}

	/**
	 * Remove a reservation from the reservation list
	 * @param reservation Delete table reservation
	 */
	public void removeTableReservation(Reservation reservation)
	{ 
		this.tableReservation.remove(reservation); 
	}


}

