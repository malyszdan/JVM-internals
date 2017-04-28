package services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import entities.Customer;
import entities.Product;

import org.apache.commons.lang3.reflect.FieldUtils;

public class CustomerService implements CustomerServiceInterface {

    private List<Customer> customers;

    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public List<Customer> findByName(String name) {
        return customers.stream().filter(customer -> name.equals(customer.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByField(String fieldName, Object value) {
        return customers.stream().filter(customer -> {
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
        return customers.stream().filter(customer -> customer.getBoughtProducts().size() > number).collect(Collectors.toList());
    }

    @Override
    public List<Customer> customersWhoSpentMoreThan(double price) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> customersWithNoOrders() {
        return customers.stream().filter(customer -> customer.getBoughtProducts().isEmpty()).collect(Collectors.toList());
    }

    @Override
    public void addProductToAllCustomers(Product p) {
        if ( p != null ) {
            customers.stream().forEach(customer -> {
                customer.addProduct(p);
            });
        }
    }

    @Override
    public double avgOrders(boolean includeEmpty) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean wasProductBought(Product p) {
        return customers.stream().anyMatch(customer -> customer.getBoughtProducts().contains(p));
    }

    @Override
    public List<Product> mostPopularProduct() {
       return customers.stream()
                // map person to tag & filter null tag out
                .map(customers -> customers.getBoughtProducts()).filter(Objects::nonNull)
                // summarize tags
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                // fetch the max entry
                .entrySet().stream().max(Map.Entry.comparingByValue())
                // map to tag
                .map(Map.Entry::getKey).orElse(null);
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
