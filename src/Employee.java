/**
 * A class that represents an Employee
 */
public class Employee {
	String firstName;
	String lastName;
	
	/**
	 * Constructs an Employee object with uninitialized attributes
	 */
	public Employee() {}
	
	/**
	 * Constructs an Employee object with the given first and last name
	 * @param first the employee's first name
	 * @param last the employee's last name
	 */
	public Employee(String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
	/**
	 * Sets the full name of the employee with the given first and last name
	 * @param first the employee's first name
	 * @param last the employee's last name
	 */
	public void setName (String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
	/**
	 * Returns the full name of the employee
	 * @return the combined first and last name
	 */
	public String getName () {
		return firstName + " " + lastName;
	}
	
	/**
	 * Returns the first name of the employee
	 * @return the employee's first name
	 */
	public String getFirstName () {
		return firstName;
	}
	
	/**
	 * Returns the last name of the employee
	 * @return the employee's last name
	 */
	public String getLastName () {
		return lastName;
	}
}
