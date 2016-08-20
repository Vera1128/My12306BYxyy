package com.neuedu.my12306.usermgr.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.my12306.common.DBUtils;
import com.neuedu.my12306.usermgr.dao.CityDaoImpl;
import com.neuedu.my12306.usermgr.dao.OrderDaoImpl;
import com.neuedu.my12306.usermgr.dao.UserDaoImpl;
import com.neuedu.my12306.usermgr.domain.Order;
import com.neuedu.my12306.usermgr.domain.User;

public class OrderService {
	private static final OrderService instance = new OrderService();

	public static OrderService getInstance() {
		return instance;
	}

	private OrderService() {
		
	}
	public Order findOrder(Order o) throws Exception {
		Connection conn = null;
		Order order = null;
		try {

			conn = DBUtils.getConnection();
			OrderDaoImpl oddi = new OrderDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			order = oddi.findOrder(o);
			DBUtils.commit(conn);
		} catch (Exception e) {
			DBUtils.rollBack(conn);
			System.out.println("getUserList: 事务提交失败");
			e.printStackTrace();
		} finally {
			// DBUtils.closeConnection(conn);
		}
		return order;
	}
	
	public List<Order> getOrderList(User user, Order o) {
		Connection conn = null;
		List<Order> ct = null;
		try {

			conn = DBUtils.getConnection();
			OrderDaoImpl oddi = new OrderDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			ct = oddi.getOrderList(user, o);
			DBUtils.commit(conn);
		} catch (Exception e) {
			DBUtils.rollBack(conn);
			System.out.println("getUserList: 事务提交失败");
			e.printStackTrace();
		} finally {
			// DBUtils.closeConnection(conn);
		}
		return ct;
	}
	public boolean save(Order o) throws Exception {
		Connection conn = null;
		List<Order> ct = null;
		int i = 0;
		try {

			conn = DBUtils.getConnection();
			OrderDaoImpl oddi = new OrderDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			i=oddi.save(o);
			DBUtils.commit(conn);
		} catch (Exception e) {
			DBUtils.rollBack(conn);
			System.out.println("getUserList: 事务提交失败");
			e.printStackTrace();
		} finally {
			// DBUtils.closeConnection(conn);
		}
		return i != 0 ? true : false;
	}
}
