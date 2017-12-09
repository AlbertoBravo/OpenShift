package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.giiis.pi.helper.DateTimeHelper;
import es.unex.giiis.pi.model.Order;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(
		urlPatterns = { "/OrderServlet" }
		)

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = 
			Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		logger.info("Atendiendo GET");
		HttpSession session = request.getSession();
		if (session.getAttribute("order")!=null) session.removeAttribute("order");//para evitar posible problema de volver atrás desde borrado de pedido en navegador sin confirmar y se mantengan los datos de esa orden para un nuevo pedido

		logger.info("Session id: "+session.getId());
		logger.info("Session new? "+session.isNew());
		logger.info("Session creation time: "+DateTimeHelper.time2Date(session.getCreationTime()));
		logger.info("Session last accessed time: "+DateTimeHelper.time2Date(session.getLastAccessedTime()));
		logger.info("Session max inactive time: "+DateTimeHelper.time2Date(session.getMaxInactiveInterval()));
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EditOrder.jsp");
		view.forward(request,response);	
				
	}
	
		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		logger.info("Atendiendo POST");
		
		String name = request.getParameter("name");
		
		Order order = new Order();
		order.setName(name);
		
		order.setEmail(request.getParameter("email"));
		order.setTel(request.getParameter("tel"));
		order.setSize(request.getParameter("size"));
		order.setType(request.getParameter("type"));
		order.setDelivery(request.getParameter("delivery"));
		order.setComments(request.getParameter("comments"));

		logger.info("Nombre cliente: "+order.getName());
		
		Map<String, String> messages = new HashMap<String, String>();
		if (order.validate(messages)) {
			HttpSession session = request.getSession();
			session.setAttribute("order",order);
			response.sendRedirect("CheckOrderServlet");
		} 
		else {
			request.setAttribute("messages",messages);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EditOrder.jsp");
			view.forward(request,response);
		}	
		
	}
	
		
		
		
		
	}

