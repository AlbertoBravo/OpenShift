package es.unex.giiis.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.giiis.pi.model.Order;

public class JDBCOrderDAOImpl implements OrderDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCOrderDAOImpl.class.getName());

	@Override
	public List<Order> getAll() {

		
		
		if (conn == null) return null;
		
		
				
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery("SELECT * FROM Public.Pizzaorder");
			}
			while ( rs.next() ) {
				Order order = new Order();
				order.setId(rs.getLong("id"));
				order.setName(rs.getString("name"));
				order.setTel(rs.getString("tel"));
				order.setEmail(rs.getString("email"));
				order.setSize(rs.getString("size"));
				order.setType(rs.getString("type"));
				order.setDelivery(rs.getString("delivery"));
				order.setComments(rs.getString("comments"));
				
				orders.add(order);
				logger.info("fetching orders: "+order.getId()+" "+order.getName()+" "+order.getTel()+" "+
						order.getSize()+" "+order.getType()+" "+order.getDelivery()+" "+order.getComments());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public Order get(long id) {
		if (conn == null) return null;
		
		Order order = null;		
		
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery("SELECT * FROM Public.Pizzaorder WHERE id ="+id);			 
			}
			if (!rs.next()) return null; 
			order  = new Order();	 
			order.setId(rs.getLong("id"));
			order.setName(rs.getString("Name"));
			order.setTel(rs.getString("Tel"));
			order.setEmail(rs.getString("Email"));
			order.setSize(rs.getString("size"));
			order.setType(rs.getString("type"));
			order.setDelivery(rs.getString("delivery"));
			order.setComments(rs.getString("comments"));
			
			logger.info("fetching order: "+order.getId()+" "+order.getName()+" "+order.getTel()+" "+
					order.getSize()+" "+order.getType()+" "+order.getDelivery()+" "+order.getComments());
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	@Override
	public List<Order> get(String name) {

		
		if (conn == null) return null;
		
		
				
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery("SELECT * FROM Public.Pizzaorder WHERE name ='" + name + "'");
			}
			while ( rs.next() ) {
				Order order = new Order();
				order.setId(rs.getLong("id"));
				order.setName(rs.getString("name"));
				order.setTel(rs.getString("tel"));
				order.setEmail(rs.getString("email"));
				order.setSize(rs.getString("size"));
				order.setType(rs.getString("type"));
				order.setDelivery(rs.getString("delivery"));
				order.setComments(rs.getString("comments"));
				
				orders.add(order);
				logger.info("fetching orders: "+order.getId()+" "+order.getName()+" "+order.getTel()+" "+
						order.getSize()+" "+order.getType()+" "+order.getDelivery()+" "+order.getComments());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}
	

	@Override
	public long add(Order order) {
		long id=-1;
		if (conn != null){

			Statement stmt;
			ResultSet genKeys;
			try {				
				synchronized(conn){
				  stmt = conn.createStatement();
				  stmt.executeUpdate("INSERT INTO PIZZAORDER (name,tel,email,size,type,delivery,comments) VALUES('"
									+order.getName()+"','"
									+order.getTel()+"','"
									+order.getEmail()+"','"
									+order.getSize()+"','"
									+order.getType()+"','"
									+order.getDelivery()+"','"
									+order.getComments()+"')",Statement.RETURN_GENERATED_KEYS);
				
				  genKeys = stmt.getGeneratedKeys();
				}
				if (genKeys.next())
				    id = genKeys.getInt(1);
				
				logger.info("creating order: "+order.getName()+" "+order.getTel()+" "+
						order.getSize()+" "+order.getType()+" "+order.getDelivery()+" "+order.getComments());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public void save(Order order) {
		if (conn != null){

			Statement stmt;
			try {
				synchronized(conn){
				  stmt = conn.createStatement();
				  stmt.executeUpdate("UPDATE PIZZAORDER SET Name='"+order.getName()+"', Tel='"+order.getTel()+"', Email='"+order.getEmail()+"', Size='"+order.getSize()+"', Type='"+order.getType()+"', Delivery='"+order.getDelivery()+"', Comments ='"+order.getComments()+"' WHERE id = "+order.getId());
				}
				logger.info("updating order: "+order.getId()+" "+order.getName()+" "+order.getTel()+" "+
						order.getSize()+" "+order.getType()+" "+order.getDelivery()+" "+order.getComments());
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(long id) {
		if (conn != null){

			Statement stmt;
			try {
				synchronized(conn){
				  stmt = conn.createStatement();
				  stmt.executeUpdate("DELETE FROM PIZZAORDER WHERE id ="+id);
				}
				logger.info("deleting order: "+id);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void setConnection(Connection conn) {

		this.conn = conn;
	}
}
