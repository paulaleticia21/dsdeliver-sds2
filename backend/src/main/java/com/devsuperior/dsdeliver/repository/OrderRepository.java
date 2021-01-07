package com.devsuperior.dsdeliver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
