package com.neuedu.my12306.usermgr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.neuedu.my12306.common.DBUtils;
import com.neuedu.my12306.usermgr.domain.CertType;
import com.neuedu.my12306.usermgr.domain.City;
import com.neuedu.my12306.usermgr.domain.Order;
import com.neuedu.my12306.usermgr.domain.Province;
import com.neuedu.my12306.usermgr.domain.User;
import com.neuedu.my12306.usermgr.domain.UserType;

public class OrderDaoImpl implements OrderDao {
	private Connection conn;

	public OrderDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Order> getOrderList(User user, Order o)
			throws Exception {

		List<Order> list = new ArrayList<Order>();
		Order one = null;
		StringBuffer str = new StringBuffer();
		str.append("SELECT o.*, u.id uid, u.username username, u.realname realname, u.sex sex ");
		str.append(" from tab_user u, tab_order o WHERE u.id=o.userid and u.id = ?");
		
		int status = o.getStatus();
		if(status != 0)
		{
			str.append(" and o.status = " + status);
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(str.toString());
			pstmt.setInt(1, user.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				one = new Order();
				one.setId(rs.getInt("id"));
				one.setOrder_date(rs.getString("order_date"));
				
				User u = new User();
				u.setId(rs.getInt("uid"));
				u.setUsername(rs.getString("username"));
				u.setRealname(rs.getString("realname"));
				u.setSex(rs.getString("sex"));
				one.setUserid(u);
				
				one.setTickets(rs.getInt("tickets"));
				one.setTotalmoney(rs.getDouble("totalmoney"));
				one.setStatus(rs.getInt("status"));
				
				list.add(one);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(pstmt, rs);

		}
		return list;
	}

	@Override
	public int save(Order o) throws Exception {
		int i = 0, idx = 1;
		String insert_sql = "insert into tab_order(order_date,userid,tickets,"
				+ "totalmoney,status) " + "values(?,?,?,?,?)";
		// String insert_sql = "insert into tab_user(username,password) " +
		// "values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insert_sql);
			pstmt.setString(idx++, o.getOrder_date());
			pstmt.setInt(idx++, o.getUserid().getId());
			pstmt.setInt(idx++, o.getTickets());
			pstmt.setDouble(idx++, o.getTotalmoney());
			pstmt.setInt(idx++, o.getStatus());
			i = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(pstmt, null);
		}
		return i;
	}

	@Override
	public Order findOrder(Order o) throws Exception {

		Order one = null;
		boolean tag = false;

		StringBuffer str = new StringBuffer();
		str.append("SELECT o.*, u.id uid, u.username username, u.realname realname, u.sex sex ");
		str.append(" from tab_user u, tab_order o WHERE u.id=o.userid");

		
		if ( o.getId() != 0) {///////
			str.append(" AND o.id=" + o.getId());
			tag = true;
		}
		if (o.getOrder_date() != null && !o.getOrder_date().isEmpty()) {
			str.append(" AND o.order_date='" + o.getOrder_date() + "'");
			tag = true;
		} 
		if (o.getUserid() != null && o.getUserid().getId() != 0) {
			str.append(" AND o.userid=" + o.getUserid().getId());
			tag = true;
		} 
		if (o.getTickets() != 0) {
			str.append(" AND o.tickets=" + o.getTickets());
			tag = true;
		} if (o.getTotalmoney() != 0) {
			str.append(" AND o.totalmoney=" + o.getTotalmoney());
			tag = true;
		} if (o.getStatus() != 0) {
			str.append(" AND o.status=" + o.getStatus());
			tag = true;
		} 
		if (!tag) {
			return null;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(str.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				one = new Order();
				one.setId(rs.getInt("id"));
				one.setOrder_date(rs.getString("order_date"));
				
				User u = new User();
				u.setId(rs.getInt("uid"));
				u.setUsername(rs.getString("username"));
				u.setRealname(rs.getString("realname"));
				u.setSex(rs.getString("sex"));
				one.setUserid(u);
				
				one.setTickets(rs.getInt("tickets"));
				one.setTotalmoney(rs.getDouble("totalmoney"));
				one.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(pstmt, rs);
		}
		return one;
	}
}