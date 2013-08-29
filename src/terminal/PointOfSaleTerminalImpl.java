package terminal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import terminal.Exception.ProductNotFoundException;
import terminal.Exception.RepeatCountException;

/**
 *
 * @author tacita
 */
public class PointOfSaleTerminalImpl implements PointOfSaleTerminal {

    private ProductsList productsList;
    private List<String> scannedCodes;

    public PointOfSaleTerminalImpl(ProductsList productsList) {
        this();
        this.productsList = productsList;
    }

    public PointOfSaleTerminalImpl() {
        scannedCodes = new LinkedList<String>();
    }

    @Override
    public void setPriceList(ProductsList productsList) {
        this.productsList = productsList;
    }

    @Override
    public void scan(String productCode) throws ProductNotFoundException {
        if (!productsList.exist(productCode)) {
            throw new ProductNotFoundException("product with code" + productCode + " is not found");
        }
        scannedCodes.add(productCode);
    }

    @Override
    public void scan(String productCode, int repeatCount) throws ProductNotFoundException, RepeatCountException {
        if (repeatCount < 0) {
            throw new RepeatCountException("The number of repetitions must be non-negative");
        }
        if (!productsList.exist(productCode)) {
            throw new ProductNotFoundException("product with code" + productCode + " is not found");
        }
        for (int i = 0; i < repeatCount; i++) {
            scannedCodes.add(productCode);


        }
    }

    @Override
    public void repeatScan(int repeatCount) throws RepeatCountException {
        if (repeatCount < 0) {
            throw new RepeatCountException("The number of repetitions must be non-negative");
        }
        String lastCode = scannedCodes.get(scannedCodes.size());
        for (int i = 0; i < repeatCount; i++) {
            try {
                scan(lastCode);
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancelScan(String productCode, int repeatCount) throws RepeatCountException {
        if (repeatCount < 0) {
            throw new RepeatCountException("The number of repetitions must be non-negative");
        }
        for (int i = 0; i < repeatCount; i++) {
            boolean contains = scannedCodes.remove(productCode);
            if (!contains) {
                return;
            }
        }
    }

    @Override
    public BigDecimal giveAmountToPay() {
        BigDecimal totalPrice = new BigDecimal(0.00);
        Set<String> screenedProductCodes = new HashSet<String>(scannedCodes);
        for (String nextCode : screenedProductCodes) {
            BigDecimal nextProductPrice = null;
            try {
                nextProductPrice = priceForOneProduct(nextCode, getProductCount(nextCode));
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
            totalPrice = totalPrice.add(nextProductPrice);
        }
        return totalPrice;

    }

    public BigDecimal priceForOneProduct(String productCode,
    int productCount) throws ProductNotFoundException {
        BigDecimal totalProductPrice = new BigDecimal(0.00);
        ProductPrices productPrices = productsList.getProductPrices(productCode);
        List<Integer> suitablePrices =
                new ArrayList<Integer>(productPrices.getSuitablePrice(productCount));
        while (productCount != 0) {
            for (int i = suitablePrices.size() - 1; i >= 0; i--) {
                int price = suitablePrices.get(i);
                int count = productCount / price;
                totalProductPrice = totalProductPrice.add(
                        productPrices.getPrice(price).multiply(
                        new BigDecimal(count)));
                productCount -= count * price;
            }
        }
        return totalProductPrice;
    }

    @Override
    public int getProductCount(String productCode) {
        return Collections.frequency(scannedCodes, productCode);
    }

    @Override
    public void clearScanned() {
        scannedCodes.clear();
    }
}
