import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("product_name")
    private String product_name;

    private String code;
    private double price;
    private String description;

    public String getCode() 
    {
        return code;
    }

    public String getProductName() 
    {
        return product_name;
    }


    public double getPrice() 
    {
        return price;
    }

    public String getDescription() 
    {
        return description;
    }

}
