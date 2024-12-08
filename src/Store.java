
public class Store {
	String name;
	String city;
	String state;
	String phone;
	Double tax;
	Double discount;
	
	public Store(){}
	
	public Store(String name, String phone, String city, String state, Double tax, Double discount) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.tax = tax;
		this.discount = discount;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return city + ", " + state;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Double getTax() {
		return tax;
	}
	
	public Double getDiscount() {
		return discount;
	}
}
