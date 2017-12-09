package es.unex.giiis.pi.model;

import java.util.Map;


public class Order {

	private Long id;
	private String name;
	private String email;
	private String tel;
	private String size;
	private String type;
	private String delivery;
	private String comments;

	public boolean validate(Map<String, String> messages){
		   if(name.trim().isEmpty()||name==null) {
		      messages.put("error", "Empty name");
			     } else if(!name.trim().matches("[A-Za-záéíóúñÁÉÍÓÚ]{2,}([\\s][A-Za-záéíóúñÁÉÍÓÚ]{2,})*")) {
			           messages.put("error", "Invalid name: " + name.trim());
			     }
		   if(messages.isEmpty()) return true; 
			   else return false;
		}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
