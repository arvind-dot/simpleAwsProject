package org.example.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import org.example.model.Item;

import java.util.List;

@Service
public class ItemService {
    private final DynamoDbTable<Item> table;

    public ItemService(DynamoDbEnhancedClient enhancedClient) {
        this.table = enhancedClient.table("Items", TableSchema.fromBean(Item.class));
    }

    public void save(Item item) {
        table.putItem(item);
    }

    public Item getById(String id) {
        return table.getItem(Key.builder().partitionValue(id).build());
    }

    public void delete(String id) {
        table.deleteItem(Key.builder().partitionValue(id).build());
    }

    public List<Item> getAllItems() {
        return table.scan().items().stream().toList();
    }
}

