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
 * Show, Create, Edit, Delete, Accept Reservations
 * 
 * @author Lexx Audrey Pecson Manalansan
 */
public class ReservationManager {

	private static Scanner sc = new Scanner (System.in);

	private static ArrayList<Reservation> reservations = Restaurant.reservations;
	private static ArrayList<Reservation> pastReservations = Restaurant.pastReservations;

	static String exit = "-1";


	/**-----------------------------Prints--------------------------------

	/**-------------------------------------------------------------------
	 * Print reservation details
	 * @param r Reservation being printed
	 */
	private static void printReservations(ArrayList<Reservation> reservation){
		int count = 1;
		for(Reservation r : reservation){
			System.out.println("["+ (count++) + "] " + "ID: " + r.getReservationID() + "	Customer Name: " + r.getCustName() + 
					"	Contact Number: " + r.getCustContact() + 
					"	Table: "+ r.getTableReservation().getId() + "	Time: " 
					+ r.getReservationTime().getTime() + "	Pax: "+ r.getNumOfPax());
		}
	}




	/**--------------------------Validations------------------------------

	/**-------------------------------------------------------------------
	 * Validate customer's contact number
	 * @return valid contact number of customer for reservation
	 */
	private static String checkValidCustContact(){
		String custContact = null;
		boolean validNo = false;
		do {
			System.out.println("Enter customer's contact number:  (Enter -1 to exit)");
			custContact = sc.nextLine();

			if(exit.equals(custContact))
				return null;

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

		return custContact;
	}




	/**-------------------------------------------------------------------
	 * Validate number of pax attending
	 * @return valid number of pax for reservation
	 */
	private static String checkValidNumOfPax(){
		String numOfPax = null;
		boolean validPax = false;
		do {
			System.out.println("Enter number of pax:  (Enter -1 to exit)");
			numOfPax = sc.nextLine();

			if(exit.equals(numOfPax))
				return null;

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

		return numOfPax;
	}




	/**-------------------------------------------------------------------
	 * Validate customer's name
	 * @return valid name of customer for reservation
	 */
	private static String checkValidCustName(){
		String custName = null;
		do {
			System.out.println("Enter customer's name:  (Enter -1 to exit)"); 
			custName = sc.nextLine();

			if(exit.equals(custName))
				return null;

			if (custName.length() == 0)
				System.out.println("***Please key in customer's name (Enter -1 to exit)");
		}while(custName.length() == 0);


		return custName;
	}




	/**-------------------------------------------------------------------
	 * Validate date. Check if date provided is current or future
	 * @param date Reservation date time
	 * @return boolean to confirm validity
	 */
	private static boolean checkValidDateTime(Calendar date){

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




	/**-------------------------------------------------------------------
	 * Validate date time format
	 * @return valid reservation DateTime
	 */
	private static Calendar checkValidDateTimeFormat(){

		boolean validDate = false;		
		Calendar reservationTime = Calendar.getInstance();
		SimpleDateFormat format = null;
		Date parsed = null;
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




	/**-------------------------------------------------------------------
	 * Validate choice
	 * @return valid choice
	 */
	private static String checkValidChoice(){
		String choice = null;
		boolean validAnswer = false;
		String input = null;

		do {
			System.out.println("Proceed to make reservation? (Y/N) (Enter -1 to exit)");
			choice = sc.nextLine();

			String y = "y";
			String Y = "Y";
			String n = "n";
			String N = "N";

			if(exit.equals(choice) || choice.equals(N)|| choice.equals(n))
				return null;

			if(!(choice.equals(Y)|| choice.equals(y) || choice.equals(N)|| choice.equals(n))) {
				System.out.println("***Please key in 'Y' or 'N' only");
				continue;
			}
			if(choice.equals("Y") || choice.equals("y"))
				choice = Y;
			validAnswer = true;
		}while(!validAnswer);

		return choice;
	}




	/**---------------------------Conditions------------------------------

	/**-------------------------------------------------------------------
	 * Check reservation time is less than 30mins before session closing time
	 * @param date Date and time of the reservation
	 * @param session Dining session: AM or PM
	 * @return 0(< 30mins before closing time) or  1(> 30mins before closing time)
	 */
	private static int checkClosingSession(Calendar date, int session){

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




	/**-------------------------------------------------------------------
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




	/**-------------------------------------------------------------------
	 * Cancel reservations that are past 30 minutes of the reservation start time
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




	/**-------------------------------------------------------------------
	 * Move pending reservation to past
	 * @param r Reservation being moved to past reservation
	 */
	private static void moveToPastReservation(Reservation r){

		Table t = r.getTableReservation();		
		t.removeTableReservation(r);		
		pastReservations.add(r);
		reservations.remove(r);
	}





	/**-------------------------------------------------------------------
	 * Show pending reservations
	 * @return Reservations pending
	 */
	private static ArrayList<Reservation> pendingReservations(){
		expiredReservations();
		return reservations;
	}




	/**-------------------------------------------------------------------
	 * Check existing reservations in customer's desired reservationTime's 
	 * date and session using contact number
	 * @param custContact contact number of customer booking reservation
	 * @param reservationTime datetime of reservation
	 * @return 1(duplicate) or 0(no duplicate)
	 */

	private static int checkDuplicateContact(int custContact, Calendar reservationTime){

		expiredReservations();
		int duplicate = 0;
		int session = getReservationTimeSession(reservationTime);
		boolean AM = (session == 1);

		for(Reservation r : reservations) {
			if(reservationTime.get(Calendar.MONTH)==r.getReservationTime().get(Calendar.MONTH))
				if(reservationTime.get(Calendar.DATE) == r.getReservationTime().get(Calendar.DATE))
					if(AM == (getReservationTimeSession(r.getReservationTime()) == 1))
						if (custContact == r.getCustContact()) {
							duplicate = 1;
						}
						else
							duplicate = 0;
		}

		return duplicate;
	}




	/**-------------------------------------------------------------------
	 * Find reservation by phone number
	 * @param reservation reservation being accepted
	 */

	private static Reservation findCustContactReservation(ArrayList<Reservation> reservation){

		Reservation ar = null;
		int x = 1;
		int j = 1;
		do {
			String custContact = checkValidCustContact();
			if(custContact == null)
				return ar;


			int count = 0;
			for(Reservation r : reservation){
				if (r.getCustContact() == Integer.parseInt(custContact)) {
					System.out.println(r);
					ar = reservation.get(count);

					x=0;
					j=0;
					break;
				}
				count++;
			}

			if (x==1){
				System.out.println("***Reservation not found! Please try again");
			}
		}while (j==1);
		return ar;
	}




	/**-------------------------------------------------------------------
	 * Find reservation of specific phone number by reservation date
	 * @param reservation reservation being accepted
	 */
	private static Reservation findReservationTimeReservation(ArrayList<Reservation> reservation){

		ArrayList<Reservation> ar = new ArrayList<Reservation>();
		int x = 1;
		int j = 1;
		do {
			String custContact = checkValidCustContact();
			if(custContact == null)
				return null;

			for(Reservation r : reservation){
				if (r.getCustContact() == Integer.parseInt(custContact)) {
					ar.add(r);

					x=0;
					j=0;
					break;
				}
			}

			if (x==1){
				System.out.println("***Reservation not found! Please try again");
			}
		}while (j==1);
		
		Reservation rr = null;
		x = 1;
		j = 1;
		do {
			Calendar reservationTime = checkValidDateTimeFormat();
			if (reservationTime == null)
				return rr;

			int session = getReservationTimeSession(reservationTime);
			boolean AM = (session == 1);

			for(Reservation r : ar){
				if(reservationTime.get(Calendar.MONTH)==r.getReservationTime().get(Calendar.MONTH))
					if(reservationTime.get(Calendar.DATE) == r.getReservationTime().get(Calendar.DATE))
						if(AM == (getReservationTimeSession(r.getReservationTime()) == 1)) {
							rr = r;
							x=0;
							j=0;
							break;
						}

				
			}

			if (x==1){
				System.out.println("***Reservation not found! Please try again");
			}
		}while (j==1);
		return rr;
	}




	/**-------------------------------------------------------------------
	 * Show reservations to be accepted with start time between current session
	 * @return Reservation with start time in current session
	 */
	private static ArrayList<Reservation> acceptTodaySessionReservation(){
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




	/**-------------------------------------------------------------------
	 * Add reservation
	 * @param custName Name of customer booking the reservation
	 * @param custContact Contact number of customer booking the reservation
	 * @param numOfPax Number of people attending the reservation
	 * @param reservationTime Date and time of the reservation
	 */
	private static void addReservation(String custName, int custContact, int numOfPax, Calendar reservationTime) {

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




	/**----------------------------Features-------------------------------

	/**-------------------------------------------------------------------
	 * Check available tables depending on the number of pax and DateTime
	 */
	public static void showAvailableTables(){

		String numOfPax = checkValidNumOfPax();
		if(numOfPax == null)
			return;

		Calendar reservationTime = checkValidDateTimeFormat();
		if (reservationTime == null)
			return;

		ArrayList<Table> availTables = TableManager.availableTables(reservationTime, Integer.parseInt(numOfPax));

		if(availTables == null || availTables.size() <= 0){
			System.out.println("***No tables available for reservation on datetime " + reservationTime.getTime());
			return;
		}

		System.out.println("\nTables Available for " + numOfPax + " person(s) on " + reservationTime.getTime());
		System.out.println("\n Table No. (Pax)");

		for(Table table : availTables)
			System.out.println("    " + table.getId() + " (" + table.getSeatingCapacity() + "pax)");

		String choice = checkValidChoice();
		if(choice == null)
			return;
		if(choice.equals("Y"))
			makeReservation(Integer.parseInt(numOfPax), reservationTime);
	}




	/**-------------------------------------------------------------------
	 * List of reservations for the next 30 days
	 */
	public static void showReservations(){

		ArrayList<Reservation> reservation = pendingReservations();

		if(reservation.size() <= 0 ){
			System.out.println("There are no reservations pending in the next 30 days");
			return;
		}

		System.out.println("List of pending reservations for the next 30 days: ");		
		printReservations(reservation);

	}




	/**-------------------------------------------------------------------
	 * Gathering reservation's information
	 * Making a reservation
	 * @param numPax number of people attending the reservation
	 * @param date date and time of the reservation
	 */
	public static void makeReservation(int numPax, Calendar date){

		int duplicate = 0; 
		int pax = 0;
		Calendar datetime = Calendar.getInstance();
		boolean validReservation = false;
		do {
			String custName = checkValidCustName();
			if(custName == null)
				return;

			String custContact = checkValidCustContact();
			if(custContact == null)
				return;

			if (numPax == 0){

				String numOfPax = checkValidNumOfPax();
				if(numOfPax == null)
					return;

				Calendar reservationTime = checkValidDateTimeFormat();
				if (reservationTime == null)
					return;


				duplicate = checkDuplicateContact(Integer.parseInt(custContact), reservationTime);
				pax = Integer.parseInt(numOfPax);
				datetime = reservationTime;
			}
			else {
				duplicate = checkDuplicateContact(Integer.parseInt(custContact), date);
				pax = numPax;
				datetime = date;
			}

			if (duplicate == 0) {
				addReservation(custName, Integer.parseInt(custContact), pax, datetime);
				validReservation = true;
			}
			else {
				System.out.println("***Reservation already exists for this contact number on date session selected. Please try again.");
			}
		}while(!validReservation);

	}




	/**-------------------------------------------------------------------
	 * Allows user to accept a reservation by phone number or index
	 * Make an order from the reservation 
	 * Move reservation to past reservations
	 * @param staff Staff accepting the reservation and making new order
	 */
	public static void acceptReservation(Staff staff){

		ArrayList<Reservation> reservation = acceptTodaySessionReservation();
		Reservation ar = null;
		printReservations(reservation);

		if(reservation.size() <= 0){
			System.out.println("There are no reservations to accept for current dining session.");
			return;
		}

		ar = findCustContactReservation(reservation);
		if(ar == null)
			return;
		ar.setAccepted(true);
		moveToPastReservation(ar);

		System.out.println("***Reservation accepted.");

		OrderManager.createOrder(Restaurant.menuItems,Restaurant.sets, Restaurant.orders,Restaurant.previousOrders, Restaurant.tables, ar, staff);

	}




	/**-------------------------------------------------------------------
	 * Allows user to cancel a reservation
	 * Move reservation to past reservations
	 */
	public static void removeReservation(){
		ArrayList<Reservation> reservation = pendingReservations();
		if(reservation.size() <= 0){
			System.out.println("There are no reservations to cancel.");
			return;
		}

		printReservations(reservation);


		Reservation rr = null;
		rr = findReservationTimeReservation(reservation);
		if(rr == null)
			return;
		moveToPastReservation(rr);
		System.out.println("***Reservation is successfully cancelled.");

	}
}

