package terminal;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import terminal.Exception.ProductNotFoundException;

/**
 *
 * @author tacita
 */
public class PointOfSaleTerminalImplTest {

    private PointOfSaleTerminalImpl terminal;

    public PointOfSaleTerminalImplTest() {
    }

    @Before
    public void setUp() {
        ProductsList productsList = new ProductsList();
        Product productA = new Product("A", "A");
        productA.getProductPrices().putPrice(1, new BigDecimal(1.25));
        productA.getProductPrices().putPrice(3, new BigDecimal(3.00));
        Product productB = new Product("B", "B");
        productB.getProductPrices().putPrice(1, new BigDecimal(4.25));
        Product productC = new Product("C", "C");
        productC.getProductPrices().putPrice(1, new BigDecimal(1.00));
        productC.getProductPrices().putPrice(6, new BigDecimal(5.00));
        Product productD = new Product("D", "D");
        productD.getProductPrices().putPrice(1, new BigDecimal(0.75));
        productsList.putProduct(productA);
        productsList.putProduct(productB);
        productsList.putProduct(productC);
        productsList.putProduct(productD);
        terminal = new PointOfSaleTerminalImpl(productsList);

    }

    /**
     * Test of giveAmountToPay method, of class PointOfSaleTerminalImpl.
     */
    @Test
    public void testGiveAmountToPay_NotScannedProduct() {
        assertEquals(new BigDecimal(0.00), terminal.giveAmountToPay());
    }

    /**
     * Test of giveAmountToPay method, of class PointOfSaleTerminalImpl.
     */
    @Test
    public void testGiveAmountToPay_ABCDABA() {
        try {
            terminal.clearScanned();
            terminal.scan("A");
            terminal.scan("B");
            terminal.scan("C");
            terminal.scan("D");
            terminal.scan("A");
            terminal.scan("B");
            terminal.scan("A");
            assertEquals(new BigDecimal(13.25), terminal.giveAmountToPay());
        } catch (ProductNotFoundException ex) {
            fail(ex.getMessage());
        }

    }

    /**
     * Test of giveAmountToPay method, of class PointOfSaleTerminalImpl.
     */
    @Test
    public void testGiveAmountToPay_CCCCCCC() {
        terminal.clearScanned();
        try {
            for (int i = 1; i <= 7; i++) {
                terminal.scan("C");
            }
            assertEquals(new BigDecimal(6.00), terminal.giveAmountToPay());
        } catch (ProductNotFoundException ex) {
            fail(ex.getMessage());
        }

    }

    /**
     * Test of giveAmountToPay method, of class PointOfSaleTerminalImpl.
     */
    @Test
    public void testGiveAmountToPay_ABCD() {
        terminal.clearScanned();
        try {
            terminal.scan("A");
            terminal.scan("B");
            terminal.scan("C");
            terminal.scan("D");
            assertEquals(new BigDecimal(7.25), terminal.giveAmountToPay());
        } catch (ProductNotFoundException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of scan method, of class PointOfSaleTerminalImpl.
     */
    @Test(expected =ProductNotFoundException.class )
    public void testScan_ProductNotFoundException() throws ProductNotFoundException {
        terminal.clearScanned();
        terminal.scan("G");
            
        }

    
}