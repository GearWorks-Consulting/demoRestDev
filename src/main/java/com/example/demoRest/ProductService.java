package com.example.demoRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService
{
    //creating an object of ArrayList
    ArrayList<Product> products = new ArrayList<Product>();

    public ProductService() {
    //adding products to the List
        products.add(new Product(100, "Mobile", 9000.00));
        products.add(new Product(101, "Smart TV",  60000.00));
        products.add(new Product(102, "Washing Machine", 9000.00));
        products.add(new Product(103, "Laptop", 24000.00));
        products.add(new Product(104, "Air Conditioner", 30000.00));
        products.add(new Product(105, "Refrigerator ", 10000.00));
    }

    @Override
    public List<Product> findAll()
    {
    //returns a list of product
        return products;
    }


    public String printTest()
    {
        return "test";
    }

    @Override
    public boolean addProduct(Product p) {
        products.add(p);
        return true;
    }

    @Override
    public Product getProductById(int id) {
        for(Product p : products) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean updateProduct(int id, Product p) {
        for(Product currProd : products) {
            if(currProd.getId() == id) {
                currProd.setId(p.getId());
                currProd.setPname(p.getPname());
                currProd.setPrice(p.getPrice());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteProductById(int id) {
        ArrayList<Product> newProducts = new ArrayList<Product>();
        int oldSize = products.size();
        products.forEach((product -> {
            if(product.getId() == id)
                    newProducts.add(product);
        }));
        products = newProducts;
        return oldSize < products.size() ? true : false;
    }
}