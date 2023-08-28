package com.example.bookstoreproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookstoreproject.entity.Order;
import com.example.bookstoreproject.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM Order o WHERE o.userId = :userId ORDER BY o.updatedAt DESC", nativeQuery = true)
    List<Order> findOrdersByUserIdOrderByUpdatedAtDesc(@Param("userId") Long userId);
//    List<Order> findOrdersByUserIdOrderByUpdatedAtDesc(User user);
}
