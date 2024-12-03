import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class InventoryData {
    private Store storeInfo;
    private List<Product> productInfo;

    public Store getStoreInfo() 
    {
        return storeInfo;
    }

    public List<Product> getProductInfo() 
    {
        return productInfo;
    }
}
