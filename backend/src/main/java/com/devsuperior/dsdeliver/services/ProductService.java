package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entity.Product;
import com.devsuperior.dsdeliver.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	//Transaction  -> para fazer uma transação no banco de dados
	@Transactional(readOnly = true)
	public List<ProductDTO> fidAll(){
		List<Product> List = repository.findAllByOrderByNameAsc();
		return List.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
 }
