package com.order.thread.controller;

import com.order.thread.entity.OrderEntity;
import com.order.thread.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getUserDetails(@PathVariable Integer userId) {
        String userDetails = orderService.getUserDetails(userId);
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/users")
    public ResponseEntity<String> getAllUserDetails() {
        String userDetails = orderService.getAllUserDetails();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/getAllRandomUser")
    public ResponseEntity<String> getAllRandomUsers() {
        String userDetails = orderService.getAllUserDetailsFromRandomApi();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/fetch-posts")
    public Mono<List<Object>> fetchPosts(@RequestParam(defaultValue = "1") int count) {
        List<CompletableFuture<String>> futures = IntStream.rangeClosed(1, count)
                .mapToObj(orderService::fetchPost).collect(Collectors.toList());
        return Mono.zip(futures.stream().map(Mono::fromFuture).collect(Collectors.toList()),results -> List.of(results));
    }


}
