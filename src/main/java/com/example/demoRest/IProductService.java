package com.example.demoRest;

import java.util.List;
public interface IProductService
{
    List<Product> findAll();
    public Product getProductById(int id);
    boolean addProduct(Product p);
    public boolean updateProduct(int id, Product p);
    public boolean deleteProductById(int id);
    public boolean isCompleteMove() ;

    public void setCompleteMove(boolean completeMove);
    public String printTest();

   public void updateMaxPlayers(int maxPlayers);

    public int getMaxPlayers();

    void updatePlayerCounter(int parseInt);

    public int getPlayerCounter();
}