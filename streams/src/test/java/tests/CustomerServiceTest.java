package tests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import services.CustomerService;
import services.CustomerServiceInterface;
import entities.Customer;

public class CustomerServiceTest {

    private static CustomerServiceInterface cs;
    private List<Customer> res;
    private List<Customer> res2;

    @BeforeClass
    public static void setUp() {
        cs = new CustomerService(DataProducer.getTestData(10));
    }

    @Test
    public void testFindByName() throws Exception{
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
    public void customersWhoBoughtMoreThanTest() throws Exception{
        res = cs.customersWhoBoughtMoreThan(0);
        res2 = cs.customersWhoBoughtMoreThan(7);

        assertEquals(7, res.size());
        assertEquals(Collections.<Customer>emptyList(), res2);
    }

    @Test
    public void customersWhoSpentMoreThanTest() throws Exception{
        res = cs.customersWhoSpentMoreThan(90);
        res2 = cs.customersWhoSpentMoreThan(50);

        assertEquals(0, res.size());
        assertEquals(5, res2.size());
    }


}
