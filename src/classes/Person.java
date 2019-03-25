package classes;

public class Person {
	
	/**
	 * Represents a person who is at the restaurant
	 * A person can be a customer or a staff of the restaurant
	 * 
	 * @author Rosario Gelli Ann Rosario
	 *
	 */
	
	
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
	 * The Id of this person
	 * 
	 */
	private int id;
	
	/**
	 * The name of this person
	 * 
	 */
	private String name;
	
	/**
	 * The gender of this person
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
	}
	
	/**
	 * Get id of this person
	 * @return this Person's id
	 * 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Changes the id of this person
	 * @param personId This person's new id
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get name of this person
	 * @return this Person's name
	 * 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Changes the name of this person
	 * @param name This person's new name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get gender of this person
	 * @return this Person's gender
	 * 
	 */
	public Gender getGender() {
		return gender;
	}
	
	/**
	 * Changes the gender of this person
	 * @param gender This person's new gender
	 * 
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
