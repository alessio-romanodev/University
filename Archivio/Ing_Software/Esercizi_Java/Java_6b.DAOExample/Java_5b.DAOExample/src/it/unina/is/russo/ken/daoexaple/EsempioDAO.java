package it.unina.is.russo.ken.daoexaple;

public class EsempioDAO {

	public static void main(String[] args) {
		Entity e = EntityDAO.create("state"); 
		EntityDAO.read(1); 
		EntityDAO.delete(e);
	}

}
