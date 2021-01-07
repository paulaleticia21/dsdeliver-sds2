package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entity.Order;
import com.devsuperior.dsdeliver.entity.OrderStatus;
import com.devsuperior.dsdeliver.entity.Product;
import com.devsuperior.dsdeliver.repository.OrderRepository;
import com.devsuperior.dsdeliver.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	// Transaction -> para fazer uma transação no banco de dados
	@Transactional(readOnly = true)
	public List<OrderDTO> fidAll() {
		List<Order> List = repository.findOrdersWithProducts();
		return List.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	//para inserir um novo produto 
	@Transactional(readOnly = true)
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order (null, dto.getAddress(), dto.getLatitude() , dto.getLongitude(), Instant.now(),OrderStatus.PENDING);
	for (ProductDTO p :dto.getProducts()) {
		Product product = productRepository.getOne(p.getId());
		order.getProducts().add(product);
	}
	order = repository.save(order);
	return new OrderDTO(order);
	}
	
}
