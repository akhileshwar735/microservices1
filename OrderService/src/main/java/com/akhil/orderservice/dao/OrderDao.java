package com.akhil.orderservice.dao;

import com.akhil.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends CrudRepository<Order, Integer> {
}
