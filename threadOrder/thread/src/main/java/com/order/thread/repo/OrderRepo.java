package com.order.thread.repo;

import com.order.thread.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUserId(int userId);
}
