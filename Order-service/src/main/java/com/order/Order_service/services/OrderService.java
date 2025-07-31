package com.order.Order_service.services;

import com.order.Order_service.clients.InventoryClient;
import com.order.Order_service.dto.OrderRequest;
import com.order.Order_service.models.Order;
import com.order.Order_service.repos.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final InventoryClient inventoryClient;



    public void placeOrder(OrderRequest orderRequest) {
          //before placing the order check that the product is in stock or not from the inventory service
        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (!inStock) {
            throw new IllegalArgumentException("Product "+orderRequest.skuCode()+" is not in stock");
        }
        //if the product is in stock then save the order
        Order order = Order.builder()
                   .skuCode(orderRequest.skuCode())
                   .quantity(orderRequest.quantity())
                   .price(orderRequest.price())
                   .orderNumber(UUID.randomUUID().toString())
                   .build();
           orderRepo.save(order);
    }

}
