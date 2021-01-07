package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entity.Order;
import com.devsuperior.dsdeliver.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	// Transaction -> para fazer uma transação no banco de dados
	@Transactional(readOnly = true)
	public List<OrderDTO> fidAll() {
		List<Order> List = repository.findOrdersWithProducts();
		return List.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
