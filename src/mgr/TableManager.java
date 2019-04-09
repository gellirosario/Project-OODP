package mgr;

import java.util.Calendar;
import java.util.ArrayList;

import classes.Restaurant;
import classes.Reservation;
import classes.Table;
import classes.Table.Status;

/**
 * Table manager class allowing user to find
 * reservation table availability
 * @author Lexx Audrey Pecson Manalansan
 * 
 */


public class TableManager {

	private static ArrayList<Table> tables = Restaurant.tables;
	

	
	/**
	 * Find table available depending on the number of pax and DateTime
	 * @param reservationTime start time of reservation
	 * @param numOfPax number of people attending the reservation
	 * @return tables available and best suited to reserve
	 */
	 
	public static ArrayList<Table> availableTables(Calendar reservationTime, int numOfPax){
		
		ArrayList<Table> availTables = new ArrayList<Table>();
		ReservationManager.expiredReservations();
		
		Calendar current = Calendar.getInstance();	
		boolean AM = (reservationTime.get(Calendar.HOUR_OF_DAY) < Restaurant.SESSION_AMENDTIME);
		boolean avail;
		
		
		for(Table t : tables){
			
			if(current.get(Calendar.MONTH)==reservationTime.get(Calendar.MONTH))
				if(current.get(Calendar.DATE) == reservationTime.get(Calendar.DATE))
					if(AM == (current.get(Calendar.HOUR_OF_DAY) < Restaurant.SESSION_AMENDTIME))
						if(t.getStatus()==Status.Occupied || t.getStatus()==Status.Reserved)
							continue;
			
			if(t.getSeatingCapacity() < numOfPax) continue;
			
			avail = true;
			for(Reservation r : t.getTableReservation()){
				
				if(r.getReservationTime().get(Calendar.YEAR)!=reservationTime.get(Calendar.YEAR))
					continue;
				if(r.getReservationTime().get(Calendar.MONTH)!=reservationTime.get(Calendar.MONTH))
					continue;
				if(r.getReservationTime().get(Calendar.DATE)!=reservationTime.get(Calendar.DATE))
					continue;
				if(AM == (r.getReservationTime().get(Calendar.HOUR_OF_DAY) < Restaurant.SESSION_AMENDTIME)){
					avail=false;
					break;
				}
				
			}
			if(avail) availTables.add(t);

		}
		
		return availTables;
	
	}
	
	
	
	
	
	
	
	/**
	 * Find tables depending on the number of pax and DateTime
	 * @param reservationTime start time of reservation
	 * @param numOfPax number of people attending the reservation
	 * @return table best suited to reserve
	 */
	public static Table findReservationTable(Calendar reservationTime, int numOfPax){
		
		ArrayList<Table> availTables = availableTables(reservationTime, numOfPax);
		if(availTables.size() == 0 || availTables == null){
			System.out.println("There are no tables available for reservtaion on " + reservationTime.getTime());
			return null;
		}
		
		Table capacityTable = availTables.get(0);
		for(Table table : availTables)
			if(table.getSeatingCapacity() < capacityTable.getSeatingCapacity())
				capacityTable = table;

		return capacityTable;
	}
}
