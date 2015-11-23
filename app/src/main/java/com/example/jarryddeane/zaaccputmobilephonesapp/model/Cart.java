package com.example.jarryddeane.zaaccputmobilephonesapp.model;

import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.CustomerServiceImpl;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.OrdersServiceImpl;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/19.
 */
public class Cart {

    private static Cart cart = null;
    private static Customer customer = null;
    private static List<Product> products = null;
    private static List<Integer> quantity = null;

    private Cart(List<Product> products){
        this.products = products;
    }

    public static Cart getInstance() {
        if(cart == null) {
            products = new ArrayList<>();
            quantity = new ArrayList<>();
            cart = new Cart(products);
        } else if(products == null) {
            products = new ArrayList<>();
            quantity = new ArrayList<>();
        }
        return cart;
    }

    public void destroyCart() {
        cart = null;
        customer = null;
        products = null;
        quantity = null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        Cart.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        Cart.products = products;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public int getProductQuantity(Product product) {
        int number = 0;
        boolean found = false;

        for(int i = 0; i < products.size() && found == false; i++)
        {
            if(product.getId().equals(products.get(i).getId())) {
                found = true;
                number = quantity.get(i);
            }
        }
        return number;
    }

    public void setQuantity(List<Integer> quantity) {
        Cart.quantity = quantity;
    }

    public void addProduct(Product product) {
        int index1 = 0;
        boolean found1 = false;

        for(int i = 0; i < products.size() && found1 == false; i++)
        {
            if(product.getId().equals(products.get(i).getId())) {
                index1 = i;
                found1 = true;
            }
        }

        if(found1) {
            addToQuantity(index1);
        } else {
            products.add(product);
            int index2 = 0;
            boolean found2 = false;

            for(int j = 0; j < products.size() && found2 == false; j++)
            {
                if(product.getId().equals(products.get(j).getId())) {
                    index2 = j;
                    found2 = true;
                }
            }

            if(found2) {
                addToQuantity(index2);
            }
        }
    }

    public void removeProduct(Product product) {
        int index = 0;
        boolean found = false;

        for(int i = 0; i < products.size() && found == false; i++)
        {
            if(product.getId().equals(products.get(i).getId())) {
                index = i;
                found = true;
            }
        }

        if(found) {
            if(quantity.get(index) == 1) {
                products.remove(index);
                quantity.remove(index);
            } else {
                removeFromQuantity(index);
            }
        }
    }

    public void addToQuantity(int position) {
        try {
            if(quantity.size() == 0 || quantity.get(position) == null) {
                quantity.add(1);
            } else {
                int val = quantity.get(position)+1;
                quantity.remove(position);
                if(quantity.size() == 0) {
                    quantity.add(val);
                } else {
                    quantity.add(position, val);
                }
            }
        } catch(Exception e) {
            quantity.add(1);
        }
    }

    public void removeFromQuantity(int position) {
        int val = quantity.get(position)-1;
        quantity.remove(position);
        quantity.add(position, val);
    }

    public Double orderTotal() {
        double total = 0;

        for(int i = 0; i < products.size(); i++) {
            String strbalance = products.get(i).getPrice().toString();
            double balance = Double.parseDouble(strbalance);
            int number = quantity.get(i);
            balance = balance * number;
            total = total + balance;
        }

        return total;
    }

    public boolean placeOrder() {
        if(cart != null) {
            List<OrderProduct> orderProducts = new ArrayList<>();

            for(int i = 0; i < products.size(); i++) {
                OrderProduct orderProduct = new OrderProduct(quantity.get(i));
                orderProducts.add(orderProduct);
            }


            Orders order = new Orders();
            order.setOrderStatus("Confirmed");
            order.setDateOrderPlaced(new Date().toString());
            order.setTotalOrderPrice(BigDecimal.valueOf(orderTotal()));
            order.setOrderProductList(orderProducts);

            CustomerServiceImpl service = new CustomerServiceImpl();
            customer.getOrderList().add(order);
            service.update(customer);

            OrdersServiceImpl service1 = new OrdersServiceImpl();
            List<Orders> orders = service1.findAll();
            Orders lastOrder = orders.get(orders.size() - 1);

            ProductServiceImpl service2 = new ProductServiceImpl();
            List<OrderProduct> lastOrderProduct = lastOrder.getOrderProductList();
            for(int i = 0; i < products.size(); i++) {
                products.get(i).getOrderProductList().add(lastOrderProduct.get(i));
                service2.update(products.get(i));
            }
        }

        products = null;
        quantity = null;
        return true;
    }

}
