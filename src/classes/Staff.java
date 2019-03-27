package classes;

import java.io.Serializable;

/**
 * Represents a customer who works at the restaurant
 * 
 * @author Ann
 *
 */
public class Staff extends Person implements Serializable{
	
	private static final long serialVersionUID = 4L;
	
	/**
	 * The jobTitle of the staff
	 * 
	 */
	private String jobTitle;
	
	
	/**
	 * Creates a new Staff with the given id, name, gender and jobTitle
	 * @param id This is Staff's id
	 * @param name This is Staff's name
	 * @param gender This is Staff's age
	 * @param jobTitle This is Staff's job title
	 * 
	 */
	public Staff(int id, String name, Gender gender, String jobTitle) {
		super(id, name, gender);
		// TODO Auto-generated constructor stub
		
		this.setJobTitle(jobTitle);
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
