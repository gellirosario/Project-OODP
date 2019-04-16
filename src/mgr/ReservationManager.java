package mgr; 

import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import classes.Restaurant;
import classes.Table;
import classes.Table.Status;
import classes.Reservation;
import classes.Staff;

/**
 * Represents the Reservation Manager of the restaurant
 * Create, Edit, Delete Reservations
 * 
 * @author Lexx Audrey Pecson Manalansan
 * 
 */
public class ReservationManager {

	private static Scanner sc = new Scanner (System.in);
	
	private static ArrayList<Reservation> reservations = Restaurant.reservations;
	private static ArrayList<Reservation> pastReservations = Restaurant.pastReservations;
	
	/**
	 * Cancel reservations that are past 30 minutes
	 * of the reservation start time
	 * 
	 * Move pending reservation to past
	 */
	public static void expiredReservations(){

		Calendar expired = Calendar.getInstance();
		expired.add(Calendar.MINUTE, -30);
		
		for (int i = reservations.size()-1; i >= 0 ; i--){
			Reservation r = reservations.get(i);	
		    if(r.getReservationTime().before(expired))
		    	moveToPastReservation(r);
		 }
		
	}
	
	/**
	 * * Move pending reservation to past
	 * @param r Reservation being moved to past reservation
	 */
	public static void moveToPastReservation(Reservation r){

		Table t = r.getTableReservation();		
		t.removeTableReservation(r);		
		pastReservations.add(r);
		reservations.remove(r);
	}
	
	
	/**
	 * Show pending reservations
	 * @return Reservations pending
	 */
	public static ArrayList<Reservation> pendingReservations(){
		expiredReservations();
		return reservations;
	}

	
	/**
	 * Check reservation time if less than
	 * 30mins before session closing time
	 * 
	 */
	public static int checkClosingSession(Calendar date, int session){
    	
		Calendar endAM = (Calendar) date.clone();
	    endAM.set(Calendar.HOUR_OF_DAY, Restaurant.SESSION_AMENDTIME);
	    endAM.set(Calendar.MINUTE, 0);
	    endAM.set(Calendar.SECOND, 0);
	    endAM.set(Calendar.MILLISECOND, 0);
	    endAM.add(Calendar.MINUTE, -30);
	    
	    Calendar endPM = (Calendar) date.clone();
	    endPM.set(Calendar.HOUR_OF_DAY, Restaurant.SESSION_PMENDTIME);
	    endPM.set(Calendar.MINUTE, 0);
	    endPM.set(Calendar.SECOND, 0);
	    endPM.set(Calendar.MILLISECOND, 0);
	    endPM.add(Calendar.MINUTE, -30);
	    
	    int num = 0;
	    
	    if(session == 1 && !(date.after(endAM)))
	    	num = 1;
	    
	    if(session == 2 && !(date.after(endPM)))
	    	num = 1;
	    
		return num;
	}
	
	/**
	 * Show reservations to be accepted with start time
	 * between current session
	 * 
	 * @return Reservation with start time in current session
	 */
	public static ArrayList<Reservation> acceptTodaySessionReservation(){
		expiredReservations();

		Calendar current = Calendar.getInstance();
		ArrayList<Reservation> ar = new ArrayList<Reservation>();

		int session = getReservationTimeSession(current);
		if(session == 0)
			return ar;
		
		boolean AM = (session== 1);
		
		for(Reservation r : reservations)
			if(current.get(Calendar.MONTH)==r.getReservationTime().get(Calendar.MONTH))
				if(current.get(Calendar.DATE) == r.getReservationTime().get(Calendar.DATE))
					if(AM == (getReservationTimeSession(r.getReservationTime()) == 1))
						ar.add(r);
		
		return ar;
	}
	
	
	
	
	/**
	 * Add a reservation
	 */
	public static void addReservation(String custName, int custContact, int numOfPax, Calendar reservationTime) {
		
		Table t = TableManager.findReservationTable(reservationTime, numOfPax);
		Reservation r;
		
		if(t != null){
			r = new Reservation(custName, custContact, numOfPax, reservationTime, t);
			t.setStatus(Status.Reserved);
			reservations.add(r);
			System.out.println("\nReservation is sucessfully added!");
			System.out.println("Customer name: " + r.getCustName());
			System.out.println("Contact number: " + r.getCustContact());
			System.out.println("Number of Pax: " + r.getNumOfPax());
			System.out.println("Reservation DateTime: " + r.getReservationTime().getTime());
			System.out.println("Table: " + r.getTableReservation().getId());
		}else{
			System.out.println("***No table available on " + reservationTime.getTime() + " for " + numOfPax + " people.");
		}
	
	}
	
	/**
	 * Validate date. Check if date provided is current or future
	 * @param date Reservation date time
	 * @return boolean to confirm validity
	 */
	public static boolean checkValidDateTime(Calendar date){
    	
		Calendar current = Calendar.getInstance();
		
		if(date.before(current)){
    		System.out.println("***Please key in current or future date or time.");
    		return false;
		}
		
		boolean validDate = false;
		current.add(Calendar.DAY_OF_MONTH, 30);
	    
		
    	if(date.after(current))
    		System.out.println("***You are only allowed to make a reservation for a maximum of 30 days in advance");
    	else if (getReservationTimeSession(date) == 0)
    		System.out.println("***Reservation time invalid. Please key in a reservation time during operating hours.");
    	else if (checkClosingSession(date, getReservationTimeSession(date)) == 0)
    		System.out.println("***Reservation start time should be at least 30 mins before dining session closing time.");
    	else
    		validDate = true;
	    
		return validDate;
		
	}
	
	/**
	 * Determine the session code
	 * @param date DateTime to determine the session code
	 * @return session code 0 = invalid session, 1 = AM Session, 2 = PM Session
	 */
	public static int getReservationTimeSession(Calendar date){
		
	    Calendar startAM = (Calendar) date.clone();
	    startAM.set(Calendar.HOUR_OF_DAY, Restaurant.SESSION_AMSTARTTIME);
	    startAM.set(Calendar.MINUTE, 0);
	    startAM.set(Calendar.SECOND, 0);
	    startAM.set(Calendar.MILLISECOND, 0);
	    
	    Calendar endAM = (Calendar) date.clone();
	    endAM.set(Calendar.HOUR_OF_DAY, Restaurant.SESSION_AMENDTIME);
	    endAM.set(Calendar.MINUTE, 0);
	    endAM.set(Calendar.SECOND, 0);
	    endAM.set(Calendar.MILLISECOND, 0);
	    
	    Calendar startPM = (Calendar) date.clone();
	    startPM.set(Calendar.HOUR_OF_DAY, Restaurant.SESSION_PMSTARTTIME);
	    startPM.set(Calendar.MINUTE, 0);
	    startPM.set(Calendar.SECOND, 0);
	    startPM.set(Calendar.MILLISECOND, 0);
	    
	    Calendar endPM = (Calendar) date.clone();
	    endPM.set(Calendar.HOUR_OF_DAY, Restaurant.SESSION_PMENDTIME);
	    endPM.set(Calendar.MINUTE, 0);
	    endPM.set(Calendar.SECOND, 0);
	    endPM.set(Calendar.MILLISECOND, 0);
	    
    	if((date.before(startAM) || date.after(endAM)) && (date.before(startPM) || date.after(endPM)))
    		return 0;
    	if(!(date.before(startAM) || date.after(endAM)))	
    		return 1;
    	if(!(date.before(startPM) || date.after(endPM)))		
    		return 2;
    	
    	return 0;
	}
	
	
	/**
	 * Validate date time format
	 * @return Valid reservation DateTime
	 */
	public static Calendar checkValidDateTimeFormat(){
		
		boolean validDate = false;		
		Calendar reservationTime = Calendar.getInstance();
		SimpleDateFormat format = null;
		Date parsed = null;
		String exit = "-1";
		do{
			System.out.println("Enter reservation DateTime [dd/MM/yyyy HH:mm]: (Enter -1 to exit)");	
			String date  = sc.nextLine();
			
		    
			if(exit.equals(date))
				return null;
			
		    format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    try {
		    	parsed = format.parse(date);
		    } catch (ParseException e) {
		        System.out.println("***Entered date [" + date + "] is not valid according to [" +
		                ((SimpleDateFormat) format).toPattern() + "] pattern.");
		        continue;
		    }

		    reservationTime.setTime(parsed);
		    validDate = checkValidDateTime(reservationTime);

		} while(!validDate);
				
		return reservationTime;
	}
	
	
	
	
	
	/**
	 * ArrayList of pending reservations in the next 30 days
	 */
	public static void showReservations(){
		
		ArrayList<Reservation> reservation = pendingReservations();
		
		if(reservation.size() <= 0 ){
			System.out.println("There are no reservations pending in the next 30 days");
			return;
		}
		
		System.out.println("List of pending reservations for the next 30 days: ");		
		int count = 0;
		for(Reservation r : reservation){
			System.out.println("["+ (count++) + "] " + r);
		}
		
	}
	
	/**
	 * Allows user to cancel a reservation
	 */
	public static void removeReservation(){
		ArrayList<Reservation> reservation = pendingReservations();
		if(reservation.size() <= 0){
			System.out.println("There are no reservations to cancel.");
			return;
		}
		
		
		System.out.println("Choose which reservation to cancel: (Enter -1 to exit)");
		
		boolean validIndex = false;
		
		int count = 0;
		for(Reservation r : reservation){
			System.out.println("["+ (count++) + "] " + r);
		}
		
		String choice = sc.nextLine();
		String exit = "-1";
		do {
			if(exit.equals(choice))
				break;
			
			try {
				Reservation rr = reservation.get(Integer.parseInt(choice));
				moveToPastReservation(rr);
				System.out.println("***Reservation [" + choice + "] is successfully cancelled.");
				count = 0;
				for(Reservation r : reservation){
					System.out.println("["+ (count++) + "] " + r);
				}
				}
			catch (NumberFormatException e)  {
				System.out.println("***Please key in a valid index only");
				System.out.println("Choose which reservation to cancel: (Enter -1 to exit)");
				choice = sc.nextLine();
				continue;
			}catch(IndexOutOfBoundsException e){
				System.out.println("***Failed to cancel reservation! (Invalid index provided)");
				System.out.println("Choose which reservation to cancel: (Enter -1 to exit)");
				choice = sc.nextLine();
				continue;
			}
		
			validIndex = true;
		}while(!validIndex);
	}
	
	/**
	 * Allow user to accept a reservation.
	 * Make an order from the reservation 
	 * Move reservation to past reservations
	 * @param staff Staff accepting the reservation and making new order
	 */
	public static void acceptReservation(Staff staff){
		
		ArrayList<Reservation> reservation = acceptTodaySessionReservation();
		String exit = "-1";
		
		int count = 0;
		for(Reservation r : reservation){
			
			System.out.println("["+ (count++) + "] " + r);
		}
		
		if(reservation.size() <= 0){
			System.out.println("There are no reservations to accept for current dining session.");
			return;
		}

		String c;
		int x = 1, j = 0;
		do {
			System.out.println("Accept reservation by phone number(1) or index(2)? (Enter -1 to exit)");
			c = sc.nextLine();
		
			if(exit.equals(c))
				return;
		
			try {
				   Integer.parseInt(c);
				}
				catch (NumberFormatException e)  {
					System.out.println("***Please enter only 1 or 2");
					continue;
				}
			
			if (Integer.parseInt(c) == 1 || Integer.parseInt(c) == 2)
					j=1;
					break;
		}while (j==0);
			
			
		if (Integer.parseInt(c) == 1)
		{
			String custContact = null;
			boolean validNo = false;
			do {
				System.out.println("Enter customer's contact number:  (Enter -1 to exit)");
				custContact = sc.nextLine();
				
				if(exit.equals(custContact))
					return;
				
				try {
					   Integer.parseInt(custContact);
				}
				catch (NumberFormatException e)  {
					System.out.println("***Please only enter 8-digit number");
					continue;
				}
				if (custContact.length() != 8)
					System.out.println("***Please only enter 8-digit number.");
				else
					validNo = true;
					
				
			}while(!validNo);
			
			
		
			count = 0;
			for(Reservation r : reservation){
				if (r.getCustContact() == Integer.parseInt(custContact)) {
					System.out.println(r);
					Reservation ar = reservation.get(count);
				
					ar.setAccepted(true);
					moveToPastReservation(ar);
					System.out.println("***Reservation accepted.");
					
					OrderManager.createOrder(Restaurant.menuItems,Restaurant.sets, Restaurant.orders,Restaurant.previousOrders, Restaurant.tables, ar, staff);
					x=0;
					break;
				}
				count++;
			}
			
			if (x != 0)
				System.out.println("***Reservation not found! Please try again");	
		
		}
		
		if (Integer.parseInt(c) == 2)
		{
		System.out.println("Choose which reservation to accept: (Enter -1 to exit)");	
		
		boolean validIndex = false;
		
		String choice = sc.nextLine();
		do {
			if(exit.equals(choice))
				break;
			try {
				Reservation ar = reservation.get(Integer.parseInt(choice));
				ar.setAccepted(true);
				moveToPastReservation(ar);
				
				System.out.println("***Reservation accepted.");
				
				OrderManager.createOrder(Restaurant.menuItems,Restaurant.sets, Restaurant.orders,Restaurant.previousOrders, Restaurant.tables, ar, staff);
				
			}
			catch (NumberFormatException e)  {
				System.out.println("***Please key in a valid index only");
				System.out.println("Choose which reservation to cancel: (Enter -1 to exit)");
				choice = sc.nextLine();
				continue;
			}catch(IndexOutOfBoundsException e){
				System.out.println("***Failed to accept reservation! (Invalid index provided)");
				System.out.println("Choose which reservation to cancel: (Enter -1 to exit)");
				choice = sc.nextLine();
				continue;
			}
			validIndex = true;
		}while(!validIndex);
		}
	}
	
	/**
	 * Gathering reservation's information
	 * Making a reservation
	 */
	public static void makeReservation(int numPax, Calendar date){
		
		String custName = null;
		String exit = "-1";
		do {
			System.out.println("Enter customer's name:  (Enter -1 to exit)"); 
			custName = sc.nextLine();
		
			if(exit.equals(custName))
				return;
			
			if (custName.length() == 0)
				System.out.println("***Please key in customer's name (Enter -1 to exit)");
		}while(custName.length() == 0);
		
		
		String custContact = null;
		boolean validNo = false;
		do {
			System.out.println("Enter customer's contact number:  (Enter -1 to exit)");
			custContact = sc.nextLine();
			
			if(exit.equals(custContact))
				return;
			
			try {
				   Integer.parseInt(custContact);
				}
				catch (NumberFormatException e)  {
					System.out.println("***Please only enter 8-digit number");
					continue;
				}
				if (custContact.length() != 8)
					System.out.println("***Please only enter 8-digit number.");
				else 
					validNo = true;
		}while(!validNo);
		
		Calendar reservationTime;
		String numOfPax;
		if (numPax == 0)
		{
			numOfPax = null;
			boolean validPax = false;
			do {
				System.out.println("Enter number of pax:  (Enter -1 to exit)");
				numOfPax = sc.nextLine();
			
				if(exit.equals(numOfPax))
					return;
				
				try {
				   Integer.parseInt(numOfPax);
				}
				catch (NumberFormatException e)  {
					System.out.println("***Please only enter number from 1 - 10");
					continue;
				}
					if(Integer.parseInt(numOfPax) < 1)
						System.out.println("***Minimum 1 pax per reservation");
					else if(Integer.parseInt(numOfPax) > 10)
						System.out.println("***Maximum of 10 pax per reservation");
					else
						validPax = true;
			}while(!validPax);
			
			
			reservationTime = checkValidDateTimeFormat();
		
		
			if (reservationTime == null) {
				return;
			}
			else
				addReservation(custName, Integer.parseInt(custContact), Integer.parseInt(numOfPax), reservationTime);
		}
		else {
			addReservation(custName, Integer.parseInt(custContact), numPax, date);
		}
		
	}
	
	
	
	/**
	 * Check available tables depending on the number of pax and DateTime
	 */
	public static void showAvailableTables(){
		
		String numOfPax = null;
		boolean validPax = false;
		String exit = "-1";
		do {
			System.out.println("Enter number of pax:  (Enter -1 to exit)");
			numOfPax = sc.nextLine();
		
			if(exit.equals(numOfPax))
				return;
			
			try {
			   Integer.parseInt(numOfPax);
			}
			catch (NumberFormatException e)  {
				System.out.println("***Please only enter number from 1 - 10");
				continue;
			}
				if(Integer.parseInt(numOfPax) < 1)
					System.out.println("***Minimum 1 pax per reservation");
				else if(Integer.parseInt(numOfPax) > 10)
					System.out.println("***Maximum of 10 pax per reservation");
				else
					validPax = true;
		}while(!validPax);
		
		Calendar reservationTime = checkValidDateTimeFormat();
		if (reservationTime == null) {
			return;
		}
		
		ArrayList<Table> availTables = TableManager.availableTables(reservationTime, Integer.parseInt(numOfPax));
		if(availTables == null || availTables.size() <= 0){
			System.out.println("***No tables available for reservation on datetime " + reservationTime.getTime());
			return;
		}
		
		System.out.println("\nTables Available for " + numOfPax + " person(s) on " + reservationTime.getTime());
		System.out.println("Table number			Capacity			Status");
		for(Table table : availTables)
			System.out.println(table.getId() + "				" + table.getSeatingCapacity() + "				" + table.getStatus());
		
		String answer = null;
		boolean validAnswer = false;
		
		do {
			System.out.println("Proceed to make reservation? (Y/N) (Enter -1 to exit)");
			answer = sc.nextLine();
			
			String y = "y";
			String Y = "Y";
			String n = "n";
			String N = "N";
			
			if(exit.equals(answer) || answer.equals(N)|| answer.equals(n))
				return;
			
			if(!(answer.equals(Y)|| answer.equals(y) || answer.equals(N)|| answer.equals(n))) {
				System.out.println("***Please key in 'Y' or 'N' only");
				continue;
			}
			
			if(answer.equals(Y)|| answer.equals(y))
				makeReservation(Integer.parseInt(numOfPax), reservationTime);
				validAnswer = true;
		}while(!validAnswer);
		

	}
	
	
}

