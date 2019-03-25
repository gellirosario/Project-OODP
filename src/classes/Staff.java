package classes;

public class Staff extends Person{
	
	private String jobTitle;
	
	public Staff(int id, String name, Gender gender, String jobTitle) {
		super(id, name, gender);
		// TODO Auto-generated constructor stub
		
		this.setJobTitle(jobTitle);
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
