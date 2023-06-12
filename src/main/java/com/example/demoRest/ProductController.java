package com.example.demoRest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/printTest")
    public ResponseEntity<String> printTest() {
        String products = productService.printTest();
        return ResponseEntity.ok().body(products);
    }


    @GetMapping("/api/users")
    public ResponseEntity<String> kapa() {
        User user = new User("Henrik", 23);


        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        return ResponseEntity.ok().body(jsonString);
    }


    @PostMapping("/api/some-resource")
    public ResponseEntity<String> printString(@RequestBody String p) {
        return ResponseEntity.ok().body(p);
    }


    @GetMapping("/loadGame/{name}")
    public ResponseEntity<String> getProductById(@PathVariable String name) {
        //Hent savegame fra lokal folder
        String gameboardString = "editthis";
        return ResponseEntity.ok().body(gameboardString);
    }


    //Gem boardJSON loakt i mappe med given name
    @PostMapping("/saveGame/{name}")
    public ResponseEntity<String> getBoardById(@PathVariable String name,@RequestBody String boardJson) {

        File file = new File(name + "." + "json");
        GsonBuilder simpleBuilder = new GsonBuilder().
                setPrettyPrinting();


        Gson gson = simpleBuilder.create();
        FileWriter fileWriter = null;
        JsonWriter writer = null;

        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(boardJson);
            writer = gson.newJsonWriter(fileWriter);
            gson.toJson(boardJson, boardJson.getClass(), writer);
            writer.close();
            return ResponseEntity.ok().body("ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}