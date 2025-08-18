package it.unina.is.russo.ken.daoexaple;

public class Entity {
	
	String state = null;
	Integer id = null;
	
	public Entity(String state) {
		this.state=state;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
		
	public void printState(){
		String toPrint;
		if (state == null)
			toPrint = "state is null";
		else
			toPrint = state;
		System.out.println(toPrint);
	}

}
