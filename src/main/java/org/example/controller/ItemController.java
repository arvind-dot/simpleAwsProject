package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.service.ItemService;
import org.example.model.Item;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;
    public ItemController(ItemService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        service.save(item);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> get(@PathVariable String id) {
        Item item = service.getById(id);
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(item);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAll() {
        List<Item> items = service.getAllItems(); // Ensure this method exists in ItemService
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
