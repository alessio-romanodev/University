package is.rubrica.esempioaula.persistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import is.rubrica.esempioaula.Contatto;
import is.rubrica.esempioaula.ContattoNotInRubricaException;
import is.rubrica.esempioaula.Gruppo;

/*
 * Classe DAO per l'entita' Contatto. 
 * Realizza le operazioni CRUD per l'entita' che gestisce (Create, Read, Update, Delete).
 * 
 */
public class GruppoDAO {
	/*
	 *  Le persistanceMap ci permettono di garantire che per ogni entita' del mondo relazionale esiste 
	 *  solamente una entita' del mondo degli oggetti che la rappresenta e viceversa.
	 *  
	 *   Per avere maggiori dettagli si guardi anche l'esercitazione EsempioDAO
	 */
	private static java.util.Map<String, Gruppo> persistanceMap = new java.util.HashMap<String, Gruppo>();
	
	private static Gruppo restoreGruppo(ResultSet rs) throws SQLException, ContattoNotInRubricaException {
		String ID = rs.getString("ID");
		String label = rs.getString("Label");
		Gruppo g = new Gruppo(label);
		g.setPersistantID(ID);
		
		/*
		 * Importante notare come una classe DAO collabora con le altre del proprio package per ripristinare
		 * correttamente le entita' associate ad essa.
		 * 
		 *  In particolare, grazie alla persistanceMap, vengono ripristinate associazioni tra entita' che
		 *  l'utente puo' avere gia' ripristinato o meno.
		 */
		for (Contatto c : GruppoContattiDAO.readContattiGruppo(ID)) {
			g.addContatto(c);
		}
		return g;
	}
	
	public static java.util.ArrayList<Gruppo> readGruppi() throws SQLException, ContattoNotInRubricaException {
		Connection conn = DBManager.getInstance().getConnection();
		Statement s = null;
		
		try { 
			s = conn.createStatement();
			
			ResultSet rs = s.executeQuery("SELECT * FROM GRUPPI");
			java.util.ArrayList<Gruppo> gruppi = new java.util.ArrayList<Gruppo>();
			
			while (rs.next()) {
				String ID = rs.getString("ID");
				Gruppo c = null;
				
				if (persistanceMap.containsKey(ID)) {
					c = persistanceMap.get(ID);
				} else {
					c = restoreGruppo(rs);
					persistanceMap.put(c.getPersistantID(), c);
				}
				gruppi.add(c);
			}
			return gruppi;
		} finally {
			if (s != null) { s.close(); }
		}
	}
	
	public static void createGruppo(Gruppo g) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("INSERT INTO GRUPPI (Label) VALUES (?)");
			s.setString(1, g.getLabel());
			s.executeUpdate();
			
			/*
			 * Tramite il metodo getGeneratedKeys lasciamo al DBMS 
			 * il compito di generare la chiave primare dell'istanza.
			 */
			ResultSet rs = s.getGeneratedKeys();
			rs.next();
			String persistantID = rs.getString(1);
					
			g.setPersistantID(persistantID);
			GruppoContattiDAO.updateContattiGruppo(g);
		} finally {
			if (s != null) { s.close(); }
		}
	}
	
	public static void updateGruppo(Gruppo g) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement ps = null;
		
		try { 
			ps = conn.prepareStatement("UPDATE GRUPPI SET Label=? WHERE ID=?");
			ps.setString(1, g.getLabel());
			ps.setString(2, g.getPersistantID());
			
			/*
			 * Importante notare come una classe DAO collabora con le altre del proprio package per ripristinare
			 * correttamente le entita' associate ad essa.
			 * 
			 *  In particolare, grazie alla persistanceMap, vengono ripristinate associazioni tra entita' che
			 *  l'utente puo' avere gia' ripristinato o meno.
			 */
			ps.executeUpdate();
			GruppoContattiDAO.updateContattiGruppo(g);
		} finally {
			if (ps != null) { ps.close(); }
		}
	}
	
	

	public static void deleteGruppo(Gruppo g) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("DELETE FROM GRUPPI WHERE ID=?");
			s.setString(1, g.getPersistantID());
			s.execute();
		} finally {
			if (s != null) { s.close(); }
		}
	}
	
}
