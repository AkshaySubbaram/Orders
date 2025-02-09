package com.order.thread.service;

import com.order.thread.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    private final WebClient jsonPlaceholderClient;

    private WebClient webClient;

    public OrderService(WebClient.Builder webClientBuilder) {
        this.jsonPlaceholderClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
        this.webClient = webClientBuilder.baseUrl("http://localhost:8888").build();
    }

    public String getUserDetails(Integer userId) {
        return webClient.get().uri("/user/{userId}", userId)
                .retrieve().onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                clientResponse -> Mono.error(new RuntimeException("Error fetching user details: " + clientResponse.statusCode())))
                .bodyToMono(String.class).block();
    }

    public String getAllUserDetails() {
        return webClient.get().uri("/user/getAll").retrieve().bodyToMono(String.class).block();
    }

    public CompletableFuture<String> fetchPost(int postId) {
        return jsonPlaceholderClient.get().uri("/posts/{id}", postId).retrieve().bodyToMono(String.class).toFuture();
    }

    public String getAllUserDetailsFromRandomApi() {
        return webClient.get().uri("/user/getAllRandomUser").retrieve().bodyToMono(String.class).block();
    }

}
