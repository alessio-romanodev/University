package is.rubrica.esempioaula.persistant;

import is.rubrica.esempioaula.Contatto;
import is.rubrica.esempioaula.Gruppo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Classe DAO utilizzata per ripristinare l'associazione di contenimento tra i Gruppi e i Contatti. 
 * Realizza le operazioni CRUD per l'entita' che gestisce (Create, Read, Update, Delete).
 * 
 * Poiche' non e' necessario navigare dai contatti ai gruppi, e' possibile solo ripristinare e salvare
 * la relazione secondo la direzione Gruppo->Contatto.
 * 
 */
class GruppoContattiDAO {

	public static void updateContattiGruppo(Gruppo g) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM ContattiGruppiCross WHERE GruppoID=?");	
			ps.setString(1, g.getPersistantID());
			ps.executeUpdate();
			ps.close();
			
			ps = conn.prepareStatement("INSERT INTO ContattiGruppiCross (GruppoID, ContattoID) VALUES (?, ?)");
			ps.setString(1, g.getPersistantID());
			
			/*
			 * Utilizza il construtto Java foreach per scorrere ordinatamente
			 * gli elementi della lista. Il costrutto foreach solleva una eccezione 
			 * se il riferimento alla collection/array da iterare e' null.  
			 */
			for (Contatto c : g.getContatti()) {
				ps.setString(2, c.getPersistantID());
				ps.executeUpdate();
			}
		} finally {
			if (ps != null) { ps.close(); }
		}
		
	}
	
	public static java.util.List<Contatto> readContattiGruppo(String gruppoID) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT ContattoID FROM ContattiGruppiCross WHERE GruppoID=?");
			s.setString(1, gruppoID);
			
			ResultSet rs = s.executeQuery();
			java.util.ArrayList<Contatto> contatti = new java.util.ArrayList<Contatto>();
			
			/*
			 * Importante notare come una classe DAO collabora con le altre del proprio package per ripristinare
			 * correttamente le entita' associate ad essa.
			 * 
			 *  In particolare, grazie alla persistanceMap, vengono ripristinate associazioni tra entita' che
			 *  l'utente puo' avere gia' ripristinato o meno.
			 */
			while (rs.next()) {
				String idContatto = rs.getString(1);
				Contatto c = ContattoDAO.readContatto(idContatto);
				contatti.add(c);
			}
			return contatti;
		} finally {
			if (s != null) { s.close(); }
		}
	}
}
