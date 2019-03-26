package classes;

/**
 * Represents a customer who is at the restaurant
 * 
 * @author Ann
 *
 */
public class Customer extends Person{
	
	/**
	 * The customerNo of the Customer
	 * 
	 */
	private int customerNo;
	
	/**
	 * Creates a new Customer with the given id, name, gender and customerNo
	 * @param id This is Customer's id
	 * @param name This is Customer's name
	 * @param gender This is Customer's age
	 * @param customerNo This is Customer's number
	 * 
	 */
	public Customer(int id, String name, Gender gender, int customerNo) {
		super(id, name, gender);
		
		// TODO Auto-generated constructor stub
		this.setCustomerNo(customerNo);
	}

	/**
	 * @return the customerNo
	 */
	public int getCustomerNo() {
		return customerNo;
	}

	/**
	 * @param customerNo the customerNo to set
	 */
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}
	
}
