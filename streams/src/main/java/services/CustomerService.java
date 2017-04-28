package services;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import entities.Customer;
import entities.Product;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.math3.util.Precision;

public class CustomerService implements CustomerServiceInterface {

    private List<Customer> customers;

    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public List<Customer> findByName(String name) {
        return customers
                .stream()
                .filter(customer -> name.equals(customer.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByField(String fieldName, Object value) {
        return customers
                .stream()
                .filter(customer -> {
                    Field field = FieldUtils.getField(customer.getClass(), fieldName, true);
                    try {
                        return value.equals(field.get(customer));
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
    }


    @Override
    public List<Customer> customersWhoBoughtMoreThan(int number) {
        return customers.stream()
                .filter(customer -> customer.getBoughtProducts().size() > number)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> customersWhoSpentMoreThan(double price) {

    }

    @Override
    public List<Customer> customersWithNoOrders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addProductToAllCustomers(Product p) {
        // TODO Auto-generated method stub

    }

    @Override
    public double avgOrders(boolean includeEmpty) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean wasProductBought(Product p) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Product> mostPopularProduct() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int countBuys(Product p) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int countCustomersWhoBought(Product p) {
        // TODO Auto-generated method stub
        return 0;
    }

}
