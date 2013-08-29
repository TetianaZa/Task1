package terminal;

import java.math.BigDecimal;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author tacita
 */
public class ProductPrices {

    NavigableMap<Integer, BigDecimal> prices;

    public ProductPrices() {
        this.prices = new TreeMap<Integer, BigDecimal>();
    }

    public ProductPrices(Map<Integer, BigDecimal> prices) {
        if (prices != null) {
            this.prices = new TreeMap<Integer, BigDecimal>(prices);
        } else {
            this.prices = new TreeMap<Integer, BigDecimal>();
        }
    }

    public BigDecimal getPrice(int productCount) {
        return prices.get(productCount);
    }

    public void setPrices(NavigableMap<Integer, BigDecimal> prices) {
        this.prices = prices;
    }

    public void putPrice(int productCount, BigDecimal price) {
        prices.put(productCount, price);
    }

    public void deletePrice(int productCount) {
        prices.remove(productCount);
    }

    public Map<Integer, BigDecimal> getPrices() {
        return prices;
    }

    
    
    public Set<Integer> getSuitablePrice(int productCount) {
        return prices.headMap(productCount, true).keySet();
    }

    @Override
    public String toString() {
        return prices.toString();
    }
}
