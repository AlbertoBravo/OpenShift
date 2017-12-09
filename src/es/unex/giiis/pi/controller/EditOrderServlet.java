package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unex.giiis.pi.dao.JDBCOrderDAOImpl;
import es.unex.giiis.pi.dao.OrderDAO;
import es.unex.giiis.pi.model.Order;

/**
 * Servlet implementation class DetailOrderServlet
 */
@WebServlet(
		urlPatterns = { "/EditOrderServlet" }
		)
public class EditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		OrderDAO orderDao = new JDBCOrderDAOImpl();
		orderDao.setConnection(conn);
		try{
			String id = request.getParameter("id");
			logger.info("get parameter id ("+id+")");
			long oid = 0;
			oid = Long.parseLong(id); 
			logger.info("get parameter id ("+id+") and casting "+oid);
			Order order = orderDao.get(oid);
			if (order != null){
				request.setAttribute("order",order);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EditOrder.jsp");
				view.forward(request,response);
			}
			else response.sendRedirect("ListOrderServlet");
		}
			catch (Exception e) {
				logger.info("parameter id is not a number");
				response.sendRedirect("ListOrderServlet");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		OrderDAO orderDao = new JDBCOrderDAOImpl();
		orderDao.setConnection(conn);
		
		Order order = new Order();
		order.setId( Long.parseLong(request.getParameter("id")));
		order.setName(request.getParameter("name"));
		order.setEmail(request.getParameter("email"));
		order.setTel(request.getParameter("tel"));
		order.setSize(request.getParameter("size"));
		order.setType(request.getParameter("type"));
		order.setDelivery(request.getParameter("delivery"));
		order.setComments(request.getParameter("comments"));
		
		Map<String, String> messages = new HashMap<String, String>();
		if (order.validate(messages)) {
			orderDao.save(order);
			order=null;
			response.sendRedirect("ListOrderServlet");
		} 
		else {
			request.setAttribute("messages",messages);
			request.setAttribute("order",order);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EditOrder.jsp");
			view.forward(request,response);
		}	
		
		
		
	}

}