package com.example.demoRest;

import java.util.List;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController
{
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProduct()
    {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }



    @GetMapping("/api/users")
    public ResponseEntity<String> kapa()
    {
        User user = new User("Henrik", 23);


        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        return ResponseEntity.ok().body(jsonString);
    }


    @PostMapping("/api/some-resource")
    public ResponseEntity<String > printString(@RequestBody String p) {
            return ResponseEntity.ok().body("added");
    }


    @GetMapping("/loadGame/{name}")
    public ResponseEntity<String> getProductById(@PathVariable String name) {
        //Hent savegame fra lokal folder
        String gameboardString = "editthis";
        return ResponseEntity.ok().body(gameboardString);
    }


    //Gem boardJSON loakt i mappe med given name
    @PostMapping("/saveGame/{name}")
    public ResponseEntity<BoardTemplate> getBoardById(@RequestBody String boardJson, @PathVariable String name) {
        Product p = productService.getProductById(id);
        return ResponseEntity.ok().body(p);
    }

}