import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * AppData maps JSON data into Java objects for use in the application.
 * It includes details about the store and the list of products available for sale.
 *
 */
public class AppData {
	@JsonProperty("store_info")
	private Store store_info;

	@JsonProperty("product_info")
	private List<Product> product_info;

	/**
	 * Returns the store information
	 * @return a Store object containing details about the store
	 */
	public Store getStoreInfo() {
		return store_info;
	}

	/**
	 * Returns the list of products
	 * @return a List of product objects containing each product details
	 */
	public List<Product> getProductInfo() {
		return product_info;
	}
}


