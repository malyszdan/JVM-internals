package services;

import java.lang.reflect.Field;
import java.util.Collections;
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
        return customers.stream().filter(
                customer -> customer.getBoughtProducts().stream().mapToDouble(p -> p.getPrice()).sum() > price)
                .collect(Collectors.toList());
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
        return customers.stream().filter(customer -> customer.getBoughtProducts().size() >= (includeEmpty ? 0 : 1))
                .map(customer -> customer.getBoughtProducts()).flatMap(List::stream)
                .collect(Collectors.toList()).stream().mapToDouble(Product::getPrice).sum()/customers.size();
    }

    @Override
    public boolean wasProductBought(Product p) {
        return customers.stream().anyMatch(customer -> customer.getBoughtProducts().contains(p));
    }

    @Override
    public List<Product> mostPopularProduct() {
       return customers.stream()
                .map(customers -> customers.getBoughtProducts()).filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    @Override
    public int countBuys(Product p) {
        return Collections.frequency(customers.stream().filter(customer -> customer.getBoughtProducts().contains(p))
                .map(customer -> customer.getBoughtProducts()).flatMap(List::stream).collect(Collectors.toList()), p);
    }

    @Override
    public int countCustomersWhoBought(Product p) {
        return (int) customers.stream().map(Customer::getBoughtProducts).filter(products -> products.contains(p)).count();
    }

}
