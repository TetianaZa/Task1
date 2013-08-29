package terminal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import terminal.Exception.ProductNotFoundException;

/**
 *
 * @author tacita
 */
public class ProductsList {

    Map<String, Product> products;

    public ProductsList(Map<String, Product> products) {
        this.products = products;
    }

    public ProductsList() {
        products = new HashMap<String, Product>();
    }

    public ProductsList(Collection<Product> products) {
        for (Product product : products) {
            this.products.put(product.getProductCode(), product);
        }
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> product) {
        this.products = product;
    }

    public void putProduct(Product product) {
        products.put(product.getProductCode(), product);
    }

    public void deleteProduct(String productCode) {
        products.remove(productCode);
    }

    public boolean exist(String productCode) {
        return products.containsKey(productCode);
    }
      

    public ProductPrices getProductPrices(String productCode) throws ProductNotFoundException {
        if (!exist(productCode)) {
            throw new ProductNotFoundException("product with code" + productCode + " is not found");
        }
        return products.get(productCode).getProductPrices();
    }
    
    @Override
    public String toString() {
        return products.toString();
    }
}
