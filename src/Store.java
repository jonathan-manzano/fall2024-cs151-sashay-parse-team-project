
public class Store {
	String name;
	String city;
	String state;
	String phone;
	Double tax;
	
	public Store(){}
	
	public Store(String name, String city, String state, String phone, Double tax) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.tax = tax;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setTax(Double tax) {
		this.tax = tax;
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
	
	
}
