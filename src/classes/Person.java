package classes;

import java.io.Serializable;

/**
 * Represents a person who is at the restaurant
 * A person can be a customer or a staff of the restaurant
 * 
 * @author Ann
 *
 */
public class Person implements Serializable{
	
	private static final long serialVersionUID = 4L;
	
	/**
	 * Enumeration type used to determine the gender of the person
	 * 
	 */
	public static enum Gender{
		Male("Male"),
		Female("Female");
		
		private final String value;
		  
		private Gender(String value) {
			this.value = value;
		}
		
		public String toStrValue() {
			return value;
		}
	}
	
	/**
	 * The id of the person
	 * 
	 */
	private int id;
	
	/**
	 * The name of the person
	 * 
	 */
	private String name;
	
	/**
	 * The gender of the person
	 * 
	 */
	private Gender gender;
	
	
	/**
	 * Creates a new Person with the given id, name and gender
	 * @param id This is Person's id
	 * @param name This is Person's name
	 * @param gender This is Person's age
	 * 
	 */
	public Person(int id, String name, Gender gender)
	{
		this.setId(id);
		this.setName(name);
		this.setGender(gender);
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
