package tests;

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

    private static CustomerServiceInterface cs;

    private static Product product;

    private List<Customer> res;

    private List<Customer> res2;

    @BeforeClass
    public static void setUp() {
        cs = new CustomerService(DataProducer.getTestData(10));
        product = new Product(11, "Product: 11", Double.valueOf(11));
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
        assertEquals(Collections.<Customer> emptyList(), res2);
    }

    @Test
    public void customersWithNoOrdersTest() throws Exception {
        res = cs.customersWithNoOrders();

        assertThat(res.isEmpty(), is(false));
    }

    @Test
    public void addProductToAllCustomersTest() throws Exception {
        CustomerService cs2 = new CustomerService(DataProducer.getTestData(10));

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
}
