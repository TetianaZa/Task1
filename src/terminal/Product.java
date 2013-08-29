package terminal;

/**
 *
 * @author tacita
 */
public class Product {

    private String productCode;
    private String productName;
    private ProductPrices productPrices = new ProductPrices();

    public Product(String productCode, String productName) {
        this.productCode = productCode;
        this.productName = productName;
    }

    public Product(String productCode, String productName, ProductPrices productPrices) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrices = productPrices;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrices(ProductPrices productPrices) {
        this.productPrices = productPrices;
    }

    public ProductPrices getProductPrices() {
        return productPrices;
    }

    @Override
    public int hashCode() {
        return productCode.hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product product=(Product)obj;
            if (this.productCode.equals(product.productCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return  productCode+"  "+productName;

    }
    
    
    
    
}
