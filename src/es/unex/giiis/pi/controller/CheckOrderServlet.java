package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.sql.Connection;

import es.unex.giiis.pi.model.Order;
import es.unex.giiis.pi.dao.JDBCOrderDAOImpl;
import es.unex.giiis.pi.dao.OrderDAO;




/**
 * Servlet implementation class CheckOrderServlet
 */
@WebServlet("/CheckOrderServlet")
public class CheckOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Atendiendo GET");
		request.setAttribute("CheckType", "Confirm");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/CheckOrder.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
					
		logger.info("Atendiendo POST");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		OrderDAO orderDao = new JDBCOrderDAOImpl();
		orderDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		logger.info("Pedido confirmado con id de sesión: "+session.getId());
		Order order = (Order) session.getAttribute("order");
		
		
		if (order!=null) {
			orderDao.add(order);
			session.removeAttribute("order");
			order=null;
		}
			
		response.sendRedirect("ListOrderServlet");
	}

		
		
		
		
		
	}

































       
  