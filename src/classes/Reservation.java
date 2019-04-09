package classes;

import java.util.Calendar;
import classes.Table.Status;


/**
 * Represents the reservation at the restaurant
 * 
 * @author Lexx Audrey Pecson Manalansan
 * 
 *
 */
public class Reservation{
	
	/**
	 * The unique id of the reservation
	 */
	private int reservationID;
	
	
	
	/**
	 * Name of the customer making the reservation
	 */
	private String custName;
	
	
	
	/**
	 * Contact number of the customer
	 */
	private int custContact;
	
	
	
	/**
	 * Number of people attending the reservation
	 */
	private int numOfPax;
	
	
	
	/**
	 * Indicates the reservations status (accepted or pending)
	 */
	private boolean accepted;
	
	
	
	/**
	 * Start time of the reservation.
	 */
	private Calendar reservationTime;
	
	
	
	/**
	 * Table booked for the reservation
	 */
	private Table tableReservation;
	
	
	
	
	
	/**
	 * make a new reservation that includes customer's name, customer's contact number, number of people arriving,
	 * reservation start time, and reserved table
	 * 
	 * @param custName			Name of the customer who booked the reservation
	 * @param custContact		Contact number of the customer
	 * @param numPax			Number of people attending the reservation
	 * @param reservationTime	Start time of the reservation
	 * @param reservedTable		Table booked for the reservation
	 * 
	 */
	public Reservation(String custName, int custContact, int numOfPax, Calendar reservationTime, Table tableReservation){
		this.reservationID=Calendar.getInstance().hashCode();
		this.setCustName(custName);
		this.setCustContact(custContact);
		this.setNumOfPax(numOfPax);
		this.accepted = false;
		this.setReservationTime(reservationTime);
		this.setTableReservation(tableReservation);
		tableReservation.addTableReservation(this);
	}
	
	
	
	
	/**-------------------------------------------------
	 * set a unique ID for this reservation
	 * @param reservationID unique ID of this reservation
	 
	public void setReservationID(int reservationID)
	{ 
		this.reservationID = reservationID; 
	}
	*/
	
	
	/**
	 * get the unique ID of this reservation
	 * @return this reservation's unique ID
	 */
	public int getReservationID()
	{ 
		return reservationID; 
	}
	
	
	
	
	
	
	/**-------------------------------------------------
	 * set customer's name for this reservation
	 * @param newCustName name of the customer who made this reservation
	 */
	public void setCustName(String NewCustName)
	{ 
		this.custName = NewCustName; 
	}
	
	
	
	/**
	 * get the name of the customer who booked for this reservation
	 * @return this reservation's customer's name
	 */
	public String getCustName()
	{ 
		return custName; 
	}
	
	
	
	
	
	/**-------------------------------------------------
	 * set customer's contact number for this reservation
	 * @param newContact contact number of the customer for this reservation
	 */
	public void setCustContact(int NewCustContact)
	{ 
		this.custContact = NewCustContact; 
	}

	
	
	/**
	 * get the contact number of the customer who booked this reservation
	 * @return this reservation's customer contact number
	 */
	public int getCustContact()
	{ 
		return custContact; 
	}
	
	
	
	
	
	/**-------------------------------------------------
	 * set the number of people for this reservation
	 * @param numOfPax number of people attending this reservation
	 */
	public void setNumOfPax(int numOfPax)
	{ 
		this.numOfPax = numOfPax; 
	}
	
	
	
	/**
	 * get the number of people attending this reservation
	 * @return this reservation's number of people attending
	 */
	public int getNumOfPax()
	{ 
		return numOfPax; 
	}
	
	
	
	
	
	/**-------------------------------------------------
	 * set accepted = true
	 * change table's status to RESERVED
	 * remove this reservation in the ArrayList
	 */
	public void setAccepted(boolean accepted){
		this.accepted = true;
		this.tableReservation.setStatus(Status.Occupied);
		this.tableReservation.removeTableReservation(this);
	}
	
	
	
	/**
	 * get the accepted status of this reservation
	 * @return this reservation's accepted status
	 */
	public boolean getAcceptedStatus()
	{ 
		return accepted; 
	}
	
	
	
	
	
	/**-------------------------------------------------
	 * set the start time for this reservation
	 * @param reservationTime start time for this reservation
	 */
	public void setReservationTime(Calendar reservationTime)
	{ 
		this.reservationTime = reservationTime; 
	}
	
	
	
	/**
	 * get the start time of this reservation
	 * @return this reservation's start time
	 */
	public Calendar getReservationTime()
	{ 
		return reservationTime; 
	}
	
	
	
	
	
	/**-------------------------------------------------
	 * set the table to reserve for this reservation
	 * @param tableReservation reserved table for this reservation
	 */
	public void setTableReservation(Table tableResrvation)
	{ 
		this.tableReservation = tableResrvation;
	}
	
	
	
	/**
	 * get the table reserved for this reservation
	 * @return this reservation's reserved table
	 */
	public Table getTableReservation()
	{ 
		return tableReservation; 
	}
	
	
	
	
	
	/**-------------------------------------------------
	 * print reservation details (reservation ID, customer name,
	 * contact number, reservation time, number of pax and table id)
	 */
	public String toString(){
		return "Reservation ID: "+ this.reservationID + "    Customer Name:" + this.custName + 
				"    Contact Number: " + this.custContact + "    Reservation Time: " 
				+ this.reservationTime.getTime() + "    Pax: "+ this.numOfPax + 
				"    Table ID: "+ this.tableReservation.getId();
	}
	
}
