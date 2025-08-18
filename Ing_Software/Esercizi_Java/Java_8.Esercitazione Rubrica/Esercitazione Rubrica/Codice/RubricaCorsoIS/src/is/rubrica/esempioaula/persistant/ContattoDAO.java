package is.rubrica.esempioaula.persistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import is.rubrica.esempioaula.Contatto;
import is.rubrica.esempioaula.ContattoDiAzienda;
import is.rubrica.esempioaula.ContattoDiPersona;

/*
 * Classe DAO per l'entita' Contatto. 
 * Realizza le operazioni CRUD per l'entita' che gestisce (Create, Read, Update, Delete).
 * 
 * Per semplicita' la classe ContattoDAO gestisce sia i contatti di azienda che di persona 
 * che sono associati ad un'unica relazione della base dati.
 * 
 */
public class ContattoDAO {
	/*
	 *  Le persistanceMap ci permettono di garantire che per ogni entita' del mondo relazionale esiste 
	 *  solamente una entita' del mondo degli oggetti che la rappresenta e viceversa.
	 *  
	 *   Per avere maggiori dettagli si guardi anche l'esercitazione EsempioDAO
	 */
	private static java.util.Map<String, Contatto> persistanceMap = new java.util.HashMap<String, Contatto>();
	public static final String CONTATTO_PERSONA = "PERSONA";
	public static final String CONTATTO_AZIENDA = "AZIENDA";
	
	private static Contatto restoreContatto(ResultSet rs) throws SQLException {
		Contatto c;
		
		final String persistantID = rs.getString("ID");
		final String kind = rs.getString("Kind");
		final String telefono = rs.getString("Telefono");
		final String email = rs.getString("Email");
		if (kind.equalsIgnoreCase(CONTATTO_PERSONA)) {
			final String nome = rs.getString("Nome");
			final String cognome = rs.getString("Cognome");
			final String ddn = rs.getString("Ddn");
			c = new ContattoDiPersona(telefono, email, nome, cognome, ddn);
			
		} else if (kind.equalsIgnoreCase(CONTATTO_AZIENDA)) {
			final String ragioneSociale = rs.getString("RagioneSociale");
			c = new ContattoDiAzienda(telefono, email, ragioneSociale);
			
		} else {
			throw new RuntimeException("Invalid DB Schema!");
		}
		c.setPersistantID(persistantID); // Aggiungo ID!
		return c;
	}
	
	public static java.util.List<Contatto> readListaContatti() throws SQLException {
		java.sql.Connection conn = DBManager.getInstance().getConnection();
		Statement s = null;
		
		try { 
			s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM CONTATTI");
			java.util.ArrayList<Contatto> contatti = new java.util.ArrayList<Contatto>();
			
			while (rs.next()) {
				Contatto c;
				
				final String persistantID = rs.getString("ID");
				if (persistanceMap.containsKey(persistantID)) {
					c = persistanceMap.get(persistantID);
				} else {
					c = restoreContatto(rs);
					persistanceMap.put(c.getPersistantID(), c);
				}
				contatti.add(c);
			}
			return contatti;
		} finally {
			if (s != null) { s.close(); }
		}
	}
	
	static Contatto readContatto(String persistantID) throws SQLException {
		if (persistanceMap.containsKey(persistantID)) {
			return persistanceMap.get(persistantID);
		}
		
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM CONTATTI WHERE ID=?");
			s.setString(1, persistantID);
			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Contatto c = restoreContatto(rs);
				persistanceMap.put(c.getPersistantID(), c);
				return c;
			}
			return null;
		} finally {
			if (s != null) { s.close(); }
		}
	}
	
	public static void createContatto(Contatto c) throws SQLException {
		java.sql.Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("INSERT INTO CONTATTI (Telefono, Email, Kind, Nome, Cognome, Ddn, RagioneSociale) " +
					"VALUES (?,?,?,?,?,?,?)");
			
			s.setString(1, c.getTelefono());
			s.setString(2, c.getEmail());
			if (c instanceof ContattoDiPersona) {
				ContattoDiPersona p = (ContattoDiPersona)c;
				s.setString(3, CONTATTO_PERSONA);
				s.setString(4, p.getNome());
				s.setString(5, p.getCognome());
				s.setString(6, p.getDdn());
				s.setNull(7, java.sql.Types.VARCHAR);
			} else if (c instanceof ContattoDiAzienda) {
				ContattoDiAzienda a = (ContattoDiAzienda)c;
				s.setString(3, CONTATTO_AZIENDA);
				s.setNull(4, java.sql.Types.VARCHAR);
				s.setNull(5, java.sql.Types.VARCHAR);
				s.setNull(6, java.sql.Types.VARCHAR);
				s.setString(7, a.getRagioneSociale());
			}
			s.executeUpdate();
			
			/*
			 * Tramite il metodo getGeneratedKeys lasciamo al DBMS 
			 * il compito di generare la chiave primare dell'istanza.
			 */
			ResultSet generatedKeys = s.getGeneratedKeys();
			generatedKeys.next();
			String persistantID = generatedKeys.getString(1);
			
			c.setPersistantID(persistantID);
		} finally {
			if (s != null) { s.close(); }
		}
	}
	
	public static void updateContatto(Contatto c) throws SQLException {
		java.sql.Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement(
					"UPDATE CONTATTI SET Telefono=?, Email=?, Kind=?, Nome=?, Cognome=?, Ddn=?, RagioneSociale=? WHERE ID=?");
			
			s.setString(1, c.getTelefono());
			s.setString(2, c.getEmail());
			
			if (c instanceof ContattoDiPersona) {
				ContattoDiPersona p = (ContattoDiPersona)c;
				s.setString(3, CONTATTO_PERSONA);
				s.setString(4, p.getNome());
				s.setString(5, p.getCognome());
				s.setString(6, p.getDdn());
				s.setNull(7, java.sql.Types.VARCHAR);
			} else if (c instanceof ContattoDiAzienda) {
				ContattoDiAzienda a = (ContattoDiAzienda)c;
				s.setString(3, CONTATTO_AZIENDA);
				s.setNull(4, java.sql.Types.VARCHAR);
				s.setNull(5, java.sql.Types.VARCHAR);
				s.setNull(6, java.sql.Types.VARCHAR);
				s.setString(7, a.getRagioneSociale());
			}
			s.setString(8, c.getPersistantID());
			s.executeUpdate();
		} finally {
			if (s != null) { s.close(); }
		}
	}
	
	public static void deleteContatto(Contatto c) throws SQLException {
		java.sql.Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("DELETE CONTATTI WHERE ID=?");
			s.setString(1, c.getPersistantID());
			
			s.executeUpdate();
		} finally {
			if (s != null) { s.close(); }
		}
	}
}
