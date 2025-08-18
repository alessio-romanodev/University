package esempio.is.entity;

public class MailingList {
	
	public MailingList() {
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getSentMessages() {
		return sentMessages;
	}
	public void setSentMessages(Integer sentMessages) {
		this.sentMessages = sentMessages;
	}
	public java.util.List<Long> getCustomerIDs() {
		return customerIDs;
	}
	public void setCustomerIDs(java.util.List<Long> customerIDs) {
		this.customerIDs = customerIDs;
	}
	

	@Override
	public String toString() {
		return "MailingList [ID=" + ID + ", topic=" + topic + ", sentMessages=" + sentMessages + ", customerIDs="
				+ customerIDs + "]";
	}

	private Long ID;
	private String topic;
	private int sentMessages;
	private java.util.List<Long> customerIDs = new java.util.ArrayList<Long>();
	
}
