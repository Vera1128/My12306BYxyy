package com.neuedu.my12306.usermgr.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.my12306.common.DBUtils;
import com.neuedu.my12306.usermgr.dao.OrderDetailsDaoImpl;
import com.neuedu.my12306.usermgr.domain.OrderDetails;

public class OrderDetailsService {
	private static final OrderDetailsService instance = new OrderDetailsService();

	public static OrderDetailsService getInstance() {
		return instance;
	}

	private OrderDetailsService() {

	}
	public List<OrderDetails> getOrderDetailsByOrderId(int id) throws SQLException {
		Connection conn = null;
		List<OrderDetails> od = null;
		try {
			conn = DBUtils.getConnection();
			OrderDetailsDaoImpl ctdi = new OrderDetailsDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			od = ctdi.getOrderDetailsByOrderId(id);
			DBUtils.commit(conn);
		} catch (Exception e) {
			DBUtils.rollBack(conn);
			System.out.println("getOrderDetailsByOrderId error!");
		} finally {
			 DBUtils.closeConnection(conn);
		}
		return od;
		
	}
	public boolean save(OrderDetails o) throws SQLException {
		Connection conn = null;
		int i = 0;
		try {
			conn = DBUtils.getConnection();
			OrderDetailsDaoImpl ctdi = new OrderDetailsDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			i = ctdi.save(o);
			DBUtils.commit(conn);
		} catch (Exception e) {
			DBUtils.rollBack(conn);
			System.out.println("getOrderDetailsByOrderId error!");
		} finally {
			 DBUtils.closeConnection(conn);
		}
		return i == 0 ? false : true;
		
	}
	
	public boolean deleteOrderDetails(int[] orderDetailIdList) throws Exception{
		Connection conn = null;
		boolean res = false;
		try {
			conn = DBUtils.getConnection();
			OrderDetailsDaoImpl ctdi = new OrderDetailsDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			res = ctdi.deleteOrderDetails(orderDetailIdList);
			DBUtils.commit(conn);
		} catch (Exception e) {
			DBUtils.rollBack(conn);
			System.out.println("getOrderDetailsByOrderId error!");
		} finally {
			 DBUtils.closeConnection(conn);
		}
		return res;
		
	}
}
