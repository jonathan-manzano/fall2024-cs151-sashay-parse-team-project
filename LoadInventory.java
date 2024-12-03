import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class LoadInventory {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InventoryData data = mapper.readValue(new File("C:/CS151Project/srcJSON/inventory.json"), InventoryData.class);
            Store storeData = data.getStoreInfo();
            List<Product> products = data.getProductInfo();

            System.out.println("Store Name: " + storeData.getStoreName());
            System.out.println("Phone: " + storeData.getPhone());
            System.out.println("City: " + storeData.getCity());
            System.out.println("State: " + storeData.getState());
            System.out.println("Tax Rate: " + storeData.getTaxRate());
            System.out.println("Discount: " + storeData.getDiscount());
            
            System.out.println("\nProducts: ");
            for (Product product : products) 
            {
                System.out.println(product.getProductName() + " costs $" + product.getPrice());
            }
        } 
        catch (Exception e) 
        {
            e.getMessage();
        }
    }
}
