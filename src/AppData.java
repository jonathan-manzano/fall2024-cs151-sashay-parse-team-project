import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AppData {
    @JsonProperty("store_info")
    private Store store_info;

    @JsonProperty("product_info")
    private List<Product> product_info;

    public Store getStoreInfo() 
    {
        return store_info;
    }

    public List<Product> getProductInfo() 
    {
        return product_info;
    }
}
