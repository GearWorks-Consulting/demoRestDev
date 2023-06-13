package com.example.demoRest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
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
    public ResponseEntity<String> loadGame(@PathVariable String name) {
        // Load the game from the local folder
        try {
            File file = new File(name + ".json");
            String gameboardString = Files.readString(file.toPath());
            return ResponseEntity.ok().body(gameboardString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Gem boardJSON loakt i mappe med given name
    @PostMapping("/saveGame/{name}")
    public ResponseEntity<String> saveGame(@PathVariable String name, @RequestBody String boardJson) {
        File file = new File(name + ".json");
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try (FileWriter fileWriter = new FileWriter(file)) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(boardJson);
            String prettyPrintedJson = gson.toJson(jsonElement);

            fileWriter.write(prettyPrintedJson);
            return ResponseEntity.ok().body("ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}