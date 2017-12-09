package es.unex.giiis.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.giiis.pi.model.Order;

public interface OrderDAO {
	
	public List<Order> getAll();
	public Order get(long id);
	public List<Order> get(String name);
	public long add(Order order);
	public void save(Order order);
	public void delete(long id);
	
	public void setConnection(Connection conn);
}
