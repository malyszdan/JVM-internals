package tests;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.Product;
import services.CustomerService;
import services.CustomerServiceInterface;
import entities.Customer;

public class CustomerServiceTest {

    private static final int NUMBER_OF_CUSTOMERS = 10;
    private static CustomerServiceInterface cs;
    private static Product product;

    private List<Customer> res;

    private List<Customer> res2;

    @BeforeClass
    public static void setUp() {
        cs = new CustomerService(DataProducer.getTestData(NUMBER_OF_CUSTOMERS));
        product = new Product(NUMBER_OF_CUSTOMERS + 1, "Product: 11", Double.valueOf(11));
    }


    @Test
    public void testFindByName() throws Exception {
        res = cs.findByName("Customer: 1");

        assertNotNull("Result can't be null", res);
        assertEquals(1, res.size());
    }

    @Test
    public void findByFieldTest() throws Exception {
        res = cs.findByField("name", "Customer: 3");
        res2 = cs.findByField("id", 3);

        assertEquals(res, res2);
        assertEquals(1, res.size());
    }

    @Test
    public void customersWhoBoughtMoreThanTest() throws Exception {
        res2 = cs.customersWhoBoughtMoreThan(7);

        assertEquals(7, cs.customersWhoBoughtMoreThan(0).size());
        assertEquals(Collections.<Customer>emptyList(), res2);
    }

    @Test
    public void customersWhoSpentMoreThanTest() throws Exception {
        assertTrue(!cs.customersWhoSpentMoreThan(0).isEmpty());
    }

    @Test
    public void customersWithNoOrdersTest() throws Exception {
        res = cs.customersWithNoOrders();

        assertThat(res.isEmpty(), is(false));
    }

    @Test
    public void addProductToAllCustomersTest() throws Exception {
        CustomerService cs2 = new CustomerService(DataProducer.getTestData(NUMBER_OF_CUSTOMERS));

        cs2.addProductToAllCustomers(product);

        assertTrue(cs.customersWhoBoughtMoreThan(0).size() < cs2.customersWhoBoughtMoreThan(0).size());
    }

    @Test
    public void wasProductBought() throws Exception {
        CustomerService cs2 = new CustomerService(DataProducer.getTestData(10));

        assertFalse(cs2.wasProductBought(product));
        cs2.addProductToAllCustomers(product);
        assertTrue(cs2.wasProductBought(product));
    }

    @Test
    public void mostPopularProductTest() throws Exception {
        cs.addProductToAllCustomers(product);

        assertTrue(cs.mostPopularProduct().get(0).equals(product));
    }

    @Test
    public void avgOrdersTest() throws Exception {
        assertTrue(cs.avgOrders(true) > 0.0);
    }

    @Test
    public void countBuysTest() throws Exception {
        CustomerService cs2 = new CustomerService(DataProducer.getTestData(NUMBER_OF_CUSTOMERS));

        cs2.addProductToAllCustomers(product);

        assertThat(cs2.countBuys(product), is(NUMBER_OF_CUSTOMERS));
    }

    @Test
    public void countCustomersWhoBought() throws Exception {
        CustomerService cs2 = new CustomerService(DataProducer.getTestData(NUMBER_OF_CUSTOMERS));

        cs2.addProductToAllCustomers(product);

        assertThat(cs2.countCustomersWhoBought(product), is(NUMBER_OF_CUSTOMERS));
    }
}
