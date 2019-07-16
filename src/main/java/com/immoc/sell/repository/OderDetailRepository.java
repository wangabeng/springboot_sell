package com.immoc.sell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immoc.sell.dataobject.OrderDetail;

public interface OderDetailRepository extends JpaRepository<OrderDetail, String> {
	List<OrderDetail> findByOrderId(String orderId);
}
