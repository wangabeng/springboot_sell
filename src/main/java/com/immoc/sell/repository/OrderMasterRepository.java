package com.immoc.sell.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.immoc.sell.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
	Page<OrderMaster> findByBuyerOpenid(String buyderOpenid, Pageable pageable);
}
