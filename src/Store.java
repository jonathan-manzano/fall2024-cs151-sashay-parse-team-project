
/**
 * A class that represents a Store
 */
public class Store {
	String name;
	String city;
	String state;
	String phone;
	Double tax;
	Double discount;
	
	/**
	 * Constructs a Store object with empty attributes
	 */
	public Store() {}
	
	/**
	 * Constructs a Store object with the given name, phone, city, state, tax, and discount
	 * @param name the name of the store
	 * @param phone the phone number of the store
	 * @param city the city where store is located
	 * @param state the state where store is located
	 * @param tax the tax applied to transaction at the store
	 * @param discount the discount applied at the store
	 */
	public Store(String name, String phone, String city, String state, Double tax, Double discount) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.tax = tax;
		this.discount = discount;
	}
	
	/**
	 * Returns the name of the store
	 * @return name of the Store
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the location of the Store
	 * @return location of the Store
	 */
	public String getLocation() {
		return city + ", " + state;
	}
	
	/**
	 * Returns the city where the Store is located
	 * @return city where store is located
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Returns the state where the Store is located
	 * @return state where store is located
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Return the phone number of the Store
	 * @return phone number of the Store
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Returns the tax percentage of the city where store is located
	 * @return tax of percentage of city
	 */
	public Double getTax() {
		return tax;
	}
	
	/**
	 * Returns the discount offered by Store
	 * @return discount of Store
	 */
	public Double getDiscount() {
		return discount;
	}
}
