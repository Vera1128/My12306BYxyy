package com.neuedu.my12306.usermgr.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.neuedu.my12306.usermgr.domain.Order;
import com.neuedu.my12306.usermgr.domain.OrderDetails;
import com.neuedu.my12306.usermgr.domain.Train;
import com.neuedu.my12306.usermgr.domain.User;
import com.neuedu.my12306.usermgr.service.OrderDetailsService;
import com.neuedu.my12306.usermgr.service.OrderService;
import com.neuedu.my12306.usermgr.service.TrainService;
import com.neuedu.my12306.usermgr.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
// @WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println(action);
		if ("show".equals(action)) {
			try {
				doShow(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if ("showUserInfo".equals(action)) {
			try {
				doShowUserInfo(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if ("showContacts".equals(action)) {
			try {
				doShowContacts(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (action == null || "search".equals(action)) {
			try {
				doSearch(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (action.equals("order")) {
			try {
				doOrder(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (action.equals("submitOrder")) {
			try {
				doSubmitOrder(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if (action.equals("showOrderDetail")) {
			try {
				doShowOrderDetail(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	
		
		else if (action.equals("deleteDetail")) {
			try {
				doDeleteDetail(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	
	}
	

    //删除订单中的某一张票
	private void doDeleteDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub	 
		//String detailId = request.getParameter("detailsId[]");
//		int id = Integer.parseInt(detailId);
		
		String[] ids = request.getParameter("detailsId[]").split(",");
		int[] odIdList = new int[ids.length];
		for (int i = 0; i < odIdList.length; i++) {
			odIdList[i] = Integer.parseInt(ids[i]);
		}
		
//		OrderService os = OrderService.getInstance();
		OrderDetailsService ods = OrderDetailsService.getInstance();
//		Order o = (Order)request.getSession().getAttribute("orderList");
//		if(o.getTickets() == odIdList.length)
//		{
////			os.
//			System.out.println("asd");
//		}
		ods.deleteOrderDetails(odIdList);
		request.getRequestDispatcher("/User/Complete.jsp").forward(request,
				response);
	}
    
	//获取订单详情(仍有bug，订单与订单详情仍然不能一一对应)
	private void doShowOrderDetail(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String orderDetailId = request.getParameter("orderDetailId");
		int id = Integer.parseInt(orderDetailId);
		OrderDetailsService ods = OrderDetailsService.getInstance();
		List<OrderDetails> od = ods.getOrderDetailsByOrderId(id);
		System.out.println(od);
		JSONObject jsonData = new JSONObject();
		if (od != null) {
			jsonData.put("orderDetails", od);
		} else {
			jsonData.put("orderDetails", null);
		}
		request.setAttribute("detaildata", jsonData);
		
		request.getRequestDispatcher("/User/Complete.jsp").forward(request,
				response);
		
	}

	private void doShowContacts(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserService us = UserService.getInstance();
		User u = new User();
		u.setRule("2");
		List<User> userList = us.getUserList(10, 1, u);
		JSONObject jsonData = new JSONObject();
		if (userList != null) {
			jsonData.put("users", userList);
		} else {
			jsonData.put("users", null);
		}
		request.setAttribute("data", jsonData);
		request.getRequestDispatcher("/User/Contacts.jsp").forward(request,
				response);
	}

	// 点击提交订单时的action
	private void doSubmitOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取此次预订的票数的总数
		String[] passenger = request.getParameter("passengers[]").split(",");
		int[] pas = new int[passenger.length];
		for (int i = 0; i < passenger.length; i++) {
			pas[i] = Integer.parseInt(passenger[i]);
		}
		OrderService os = OrderService.getInstance();
		Order o = new Order();
		// 设置订单产生时间
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d HH:mm:ss");
		String order_date = sdf.format(new Date());
		o.setOrder_date(order_date);
		// 从session中获取user（登录的用户）以及train对象并初始化order中对应属性
		User u_order = (User) request.getSession().getAttribute("user");
		o.setUserid(u_order);

		// 从session中获取user以及train对象并初始化order中对应属性
		User u = (User) request.getSession().getAttribute("user");
		o.setUserid(u);
		int tickets = pas.length;
		// System.out.println("乘客数目："+tickets);
		System.out.println("乘客数目：" + tickets);
		o.setTickets(tickets);

		Train train = (Train) request.getSession().getAttribute("train");
		o.setTotalmoney(train.getPrice() * tickets);
		o.setStatus(2);
		os.save(o);

		UserService us = UserService.getInstance();
		OrderDetailsService ods = OrderDetailsService.getInstance();
		User u_oderDetail = new User();

		Order order = os.findOrder(o);
		// 根据每个乘客的id存储订单详情
		for (int i : pas) {
			u_oderDetail.setId(i);
			User contact = us.findUser(u_oderDetail);
			OrderDetails od = new OrderDetails();
			od.setOrderid(order);
			od.setTrainid(train);
			od.setUserid(contact);
			ods.save(od);
		}
		request.getRequestDispatcher("/User/user?action=show&status=2")
				.forward(request, response);
	}

	// 点击车票预订时的action
	private void doOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("ticketid");
		int ticketid = Integer.parseInt(id);

		TrainService ts = TrainService.getInstance();
		Train train = ts.getTrainById(ticketid);
		request.getSession().setAttribute("train", train);
		System.out.println("ticketid: " + ticketid);

		// 显示页面中能够用于订票的用户
		UserService us = UserService.getInstance();
		User u = new User();
		u.setRule("2");
		List<User> userList = us.getUserList(10, 1, u);
		System.out.println("userlist.size:" + userList.size());
		JSONObject jsonData = new JSONObject();
		if (userList != null) {
			jsonData.put("users", userList);
			jsonData.put("train", train);
		} else {
			jsonData.put("users", null);
		}
		request.setAttribute("data", jsonData);
		request.getRequestDispatcher("/User/Submit.jsp").forward(request,
				response);
		// System.out.println("success turn");
	}

	private void doShowUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		User u = (User) request.getSession().getAttribute("user");
		User u1 = new User();
		u1.setUsername(u.getUsername());
		// System.out.println("show useringfo :u_username :" + u.getRealname());
		UserService us = UserService.getInstance();
		JSONObject data = new JSONObject();
		User user = us.findUser(u1);
		// System.out.println("show useringfo :user :" + user);
		if (user != null) {
			data.put("UserMessage", user);
		}
		request.setAttribute("userdata", data);
		request.getRequestDispatcher("/User/message.jsp").forward(request,
				response);
	}

	//
	// protected void doCancel(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// OrderService os = OrderService.getInstance();
	// JSONObject data = new JSONObject();
	// String[] ids = request.getParameter("ids_d").split(",");
	// int[] orderIdList = new int[ids.length];
	// for (int i = 0; i < orderIdList.length; i++) {
	// orderIdList[i] = Integer.parseInt(ids[i]);
	// }
	// try {
	// if (os.deleteOrder(orderIdList)) {
	// data.put("flag", "yes");
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// response.setContentType("text/html;charset=utf-8");
	// PrintWriter out = response.getWriter();
	// out.print(data.toString());
	// out.close();
	// }
	//
	// private void doAddOrder(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// // TODO Auto-generated method stub
	// OrderService os = OrderService.getInstance();
	// JSONObject data = new JSONObject();
	// Order u = new Order();
	// populate(request, u);
	//
	// if (os.save(u)) {
	// data.put("flag", "yes");
	// }
	// response.setContentType("text/html;charset=utf-8");
	// PrintWriter out = response.getWriter();
	// out.print(data.toString());
	// out.close();
	// }

	protected void doShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String status = request.getParameter("status");
		int orderstatus = Integer.parseInt(status);
		System.out.println("status" + orderstatus);
		OrderService os = OrderService.getInstance();
		// fill user first
		Order o = new Order();
		if (orderstatus != 0)
			o.setStatus(orderstatus);
		User u = (User) request.getSession().getAttribute("user");
		OrderDetailsService ods = OrderDetailsService.getInstance();

		List<Order> orderList = os.getOrderList(u, o);
		// 根据orderid来查询订单详情
//		List<OrderDetails> orderDetailsList = null;

		JSONObject data = new JSONObject();
//		List<JSONObject> list = new ArrayList<JSONObject>();
//		if (orderList != null) {
//			for (Order order : orderList) {
//				JSONObject one = new JSONObject();
//				one.put("order", order);// 推入订单数据
//				orderDetailsList = ods.getOrderDetailsByOrderId(order.getId());
//				one.put("detailList", orderDetailsList);// 推入订单详细列表数据
//				list.add(one);
//			}
//		} else {
//			list = null;
//		}
		data.put("orderList", orderList);
		request.setAttribute("data", data);
		request.getSession().setAttribute("data", data);
		request.getRequestDispatcher("/User/Complete.jsp").forward(request,
				response);
	}

	protected void populate(HttpServletRequest request, Order o)
			throws NumberFormatException, Exception {
		// String loginIp = request.getRemoteAddr();
		// String username = request.getParameter("username");
		String order_date = request.getParameter("order_date");
		String name = request.getParameter("name");
		String fromplace = request.getParameter("fromplace");
		String toplace = request.getParameter("toplace");
		String begin = request.getParameter("begin");

		o.setOrder_date(order_date);
		o.setName(name);

	}

	protected void doSearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TrainService ts = TrainService.getInstance();
		String fromplace = request.getParameter("myfromplace");
		System.out.println(fromplace);
		String toplace = request.getParameter("mytoplace");
		System.out.println(toplace);
		String date = request.getParameter("mydate");
		System.out.println(date);

		List<Train> trainList = ts.getTrainList(fromplace, toplace, date);
		// List<Train> trainList = ts.getTrainList("娌堥槼", "鍝堝皵婊�, "2016-8-2");
		System.out.println(trainList); //
		JSONObject jsonData = new JSONObject();
		if (trainList != null) {
			jsonData.put("trains", trainList);
		} else {
			jsonData.put("trains", null);
		}
		request.setAttribute("data", jsonData);
		request.getRequestDispatcher("/User/Train.jsp").forward(request,
				response);
	}
}
