
public class Employee {
	String firstName;
	String lastName;
	
	public Employee() {}
	
	public Employee(String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
	
	public void setName (String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
	public void setFirstName (String first) {
		this.firstName = first;
	}
	
	public void setLastName (String last) {
		this.lastName = last;
	}
	
	public String getName () {
		return firstName + " " + lastName;
	}
	
	public String getFirstName () {
		return firstName;
	}
	
	public String getLastName () {
		return lastName;
	}
}
