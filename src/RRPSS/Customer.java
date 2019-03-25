package RRPSS;

public class Customer extends Person{
	
	private int customerNo;
	
	public Customer(int personId, String name, Gender gender, int customerNo) {
		super(personId, name, gender);
		
		// TODO Auto-generated constructor stub
		this.setCustomerNo(customerNo);
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}
	
}
