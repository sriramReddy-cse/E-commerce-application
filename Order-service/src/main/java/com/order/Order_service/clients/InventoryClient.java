package com.order.Order_service.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory-service", url = "http://localhost:8082")
public interface InventoryClient {
     @GetMapping("/api/inventory")
     boolean isInStock(@RequestParam  String skuCode,@RequestParam Integer quantity);
}
