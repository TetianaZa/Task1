/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.math.BigDecimal;
import terminal.Exception.ProductNotFoundException;
import terminal.Exception.RepeatCountException;

/**
 *
 * @author tacita
 */
public interface PointOfSaleTerminal {

    /**
     * Sets the products List to get product's prices from.
     *
     * @param productsList price list to work with
     */
    public void setPriceList(ProductsList productsList);

    /**
     * Calculates the amount to pay.
     *
     * @return the amount to pay
     */
    public BigDecimal giveAmountToPay();

    /**
     * Adds a new product into shopping card.
     *
     * @param productCode code of the product
     * @throws ProductNotFoundException whether no product with this code is
     * found
     */
    public void scan(String productCode) throws ProductNotFoundException;

    /**
     * Repeats scan operation <tt>repeatCount</tt> times. Useful when the
     * shopper buys a lot units of the same product.
     *
     * @param repeatCount amount of repeate times
     */
    public void scan(String productCode, int repeatCount) throws ProductNotFoundException, RepeatCountException;

    /**
     * Repeats last scan operation <tt>repeatCount</tt> times. Useful when the
     * shopper buys a lot units of the same product.
     *
     * @param repeatCount amount of repeate times
     */
    public void repeatScan(int repeatCount)throws RepeatCountException;

    /**
     * Cancels a single scan of the specified product. If there is no such
     * product was scanned, no operation would be involved.
     *
     * @param productCode code of the product to cancel
     * @param repeatCount amount of repeat times
     */
    public void cancelScan(String productCode, int repeatCount)throws RepeatCountException;

    
    /**
     * Gets the count of product units that were bought.
   
     * @param productCode code of the product to find matches
     * @return amount of product units that were bought
     */
    public int getProductCount(String productCode);
    
    /**
     * Clears all scanned codes to start scanning the next customer.
     */
    public void clearScanned();
}
