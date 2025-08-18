package esempio.is.entity;

public class Customer {
	
	public Customer() {
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
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
	public java.util.List<Long> getMailingListIDs() {
		return mailingListIDs;
	}
	public void setMailingListIDs(java.util.List<Long> mailingListIDs) {
		this.mailingListIDs = mailingListIDs;
	}

		@Override
	public String toString() {
		return "Customer [ID=" + ID + ", name=" + name + ", email=" + email + ", mailingListIDs=" + mailingListIDs
				+ "]";
	}

	private Long ID;
	private String name;
	private String email;
	private java.util.List<Long> mailingListIDs = new java.util.ArrayList<Long>();
	
	
}
