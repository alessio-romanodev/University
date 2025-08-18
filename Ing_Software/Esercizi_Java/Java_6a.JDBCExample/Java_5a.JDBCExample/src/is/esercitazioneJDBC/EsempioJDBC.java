package is.esercitazioneJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EsempioJDBC {
	
	/*
	 * In questo esempio non ci curiamo delle possibili eccezioni che possono essere 
	 * sollevate nella nostra interazioni con il DB: demandiamo al chiamante la gestione
	 * delle eccezioni (throws Exception).
	 * 
	 */
	public static void main(String[] args) throws Exception {
		// Esempio Query che restituisce risultati
		esempioQueryDB();
		
		// Esempio operazione che non restituisce risultati
		esempioUpdateDB();

		// Esempio di un prelievo di dati dal DB con Statement
		int id = 3;
		List<String> p = esempioPrelievoDati_Stmt(id);
		System.out.println("Ora abbiamo un array p con:");
		for (String s : p){
			System.out.println("\t"+s);
		}
		
		// Esempio di salvataggio dati su DB con PreparedStatement
		esempioSalvataggioDati_PreparedStmt(1, "Uno");
		
		esempioConTryWithResources();
	}
	
	


	/*
	 * L'apertura della connessione al DB la isoliamo in un unico metodo, in modo
	 * da ridurre le dipendenze nel codice dal cambiamento dei parametri necessari
	 * per l'instaurazione della connessione con la base dati.
	 * 
	 * I parametri sono dipendenti dal Driver utilizzato per connettersi al DB.
	 * In questo esempio si e' utilizzato il DBMS H2 (http://www.h2database.com).
	 * 
	 * NOTA: In questo esercizio instauriamo e chiudiamo una connessione (sessione di 
	 * comunicazione) con il DBMS per ogni esempio che mostriamo nei metodi.
	 * In generale la connessione ad una base di dati puo' essere mantenuta attiva
	 * per condividerla in piu' interazioni con la base dati.
	 * 
	 */
	public static Connection getConnection() throws Exception {
		
		// Chiediamo al DriverManager di aprire la connessione con la base dati
		String pathDB_H2 = "~/test"; //oppure "./test"
		String url = "jdbc:h2:" + pathDB_H2;
		String username = "admin";
		Connection connection = DriverManager.getConnection(url, username, "");
		System.out.println("returning connection with "+ url +"; username="+username);
		return connection;
		
	}
	
	public static void esempioQueryDB() throws Exception {
		System.out.println("---ESEMPIO QUERY DB---");
		
		// 1. Apertura Connessione
		Connection conn = getConnection();
		
		// 2. Creazione di un oggetto Statement
		Statement stmt = conn.createStatement();
		
		/* 3. Interazione con la base dati
		 * 
		 * Per eseguire interrogazioni sulla base dati possiamo utilizzare il metodo:
		 * 		ResultSet executeQuery(String sql);
		 * esso esegue l'interrogazione SQL. I risultati hanno la forma di una relazione 
		 * (tabella) che viene prodotta in output dal DBMS: il metodo restituisce all'utente
		 * un oggetto ResultSet che contiene un cursore (iteratore) che permette di scorrere 
		 * ordinatamente le tuple restituite dall'interrogazione.
		 */
		String query = "SELECT * FROM TEST";
		System.out.println("executing query: "+query);
		ResultSet rs = stmt.executeQuery(query);
		
		/* 4. Lettura dei risultati
		 * 
		 * All'inizio un ResultSet punta ad una posizione fittizia, precedente alla prima tupla. 
		 * Per agire sugli elementi ottenuti dalla query si utilizza un ciclo while che scorre 
		 * ordinatamente le tuple contenute nel ResultSet sino all'ultimo elemento. 
		 * Il metodo:
		 * 		boolean next();
		 *  fa avanzare il cursore ResultSet di una tupla rispetto alla posizione
		 *  corrente. Restituisce False se non ci sono ulteriori righe, altrimenti True.
		 */
		System.out.println("retrieving results...");
		while (rs.next()) {
			/*
			 * Quando un ResultSet punta ad una riga valida, e' possibile leggere
			 * gli attributi della tupla mediante un insieme di metodi get che ResultSet espone.
			 * 
			 * Esiste un metodo get per almeno ogni tipo di dato primitivo verso cui si vuole
			 * convertire un attributo della tupla (getInt, getShort, getBoolean, getDouble, ...). 
			 * 
			 * Le funzioni membro get sono overloaded: si puo' specificare la colonna da leggere 
			 * in base al nome o all'indice numerico (i-esima colonna).
			 * 		> Gli indici di colonna partono da 1!
			 */
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			System.out.format("\t%d %s\n", id, name);
		}
		
		/* 5. Rilascio delle risorse
		 * 
		 * Al termine dell'uso della base dati occorre rilasciare le risorse.
		 * Si rilasciano le risorse degli oggetti Statement, (ResultSet) e Connection.
		 * 
		 * Nota: i ResultSet sono legati agli Statement. La chiusura di uno statement
		 * chiude il ResultSet associato; per ogni Statement al piu' un unico ResultSet puo'
		 * essere aperto allo stesso tempo. Se uno Statement e' utilizzato per eseguire 
		 * due query, la seconda query chiude il primo ResultSet.
		 */
		System.out.println("Releasing resources");
		stmt.close();
		conn.close();
	}
	
	public static void esempioUpdateDB() throws Exception {
		System.out.println("---ESEMPIO UPDATE DB---");

		// 1. Apertura Connessione
		Connection conn = getConnection();
		
		// 2. Creazione di un oggetto Statement
		Statement stmt = conn.createStatement();
		
		/* 3 e 4. Interazione con la base dati e lettura dei risultati
		 * 
		 * Per eseguire operazioni DDL opppure di INSERT, DELETE, UPDATE (DML) sulla base dati possiamo
		 * utilizzare il metodo:
		 * 		int executeUpdate(String sql);
		 * esso esegue l'operazione SQL e restituisce o 
		 *    (1) il numero delle righe coinvolte dall'istruzione SQL oppure 
		 *    (2) 0 se l'istruzione SQL non restituisce nessun dato 
		 */
		
		// Esempio operazione di INSERT
		String query = "INSERT INTO TEST VALUES (100, 'cento')";
		System.out.println("executing query: "+query);
		int n = stmt.executeUpdate(query);
		
		System.out.println("inserted " + Integer.toString(n)+" tuples");
		
		System.out.println("Stato DB dopo operazione di INSERT:");
		ResultSet rs = stmt.executeQuery("SELECT * FROM TEST");
		while (rs.next()) {
			System.out.format("\t%d %s\n", rs.getInt("ID"), rs.getString("NAME"));
		}
		
		// Esempio operazione di DELETE
		query = "DELETE FROM TEST WHERE ID=100";
		System.out.println("executing query: "+query);
		int m = stmt.executeUpdate(query);
		
		System.out.println("deleted " + Integer.toString(m) +" tuples");
		System.out.println("Stato DB dopo operazione di DELETE:");
		rs = stmt.executeQuery("SELECT * FROM TEST");
		while (rs.next()) {
			System.out.format("\t%d %s\n", rs.getInt("ID"), rs.getString("NAME"));
		}
		
		// 5. Rilascio delle risorse
		System.out.println("Releasing resources");
		stmt.close();
		conn.close();
	}
	
	public static List<String> esempioPrelievoDati_Stmt(int id) throws Exception {
		System.out.println("---ESEMPIO PRELIEVO DATI STMT---");

		// 1. Apertura Connessione
		Connection conn = getConnection();
		
		// 2. Creazione di un oggetto Statement
		Statement stmt = conn.createStatement();
		
		// 3 e 4. Interazione con la base dati e lettura dei risultati
		/*
		 * Gli oggetti Statement permettono di eseguire le istruzioni SQL
		 * contenute completamente in stringhe testuali. Pertanto, se occorre interagire 
		 * in maniera interattiva con il DBMS, le istruzioni SQL devono essere composte 
		 * in oggetti String prima di inviarle al Driver del DBMS.
		 */
												/* Conversione di un intero in String *
												 * mediante metodo in classe Wrapper  */
		String sql = "SELECT * FROM TEST WHERE ID < " + Integer.toString(id);
		System.out.println("executing query: " + sql);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		// Nell'esempio scegliamo di salvare i risultati della SELECT in un array dinamico
		System.out.println("retrieving results...");
		List<String> res = new ArrayList<String>();
		while (rs.next()) {
			int rowID = rs.getInt("ID");
			String rowName = rs.getString("NAME"); 
			res.add(rowName); // aggiunta dell'elemento alla lista
			
			System.out.format("\t%d %s\n", rowID, rowName);
		}
		
		stmt.close();
		conn.close();
		
		return res;
	}
	
	public static void esempioSalvataggioDati_PreparedStmt(int id, String s) throws Exception {
		System.out.println("---ESEMPIO SALVATAGGIO DATI PREPAREDSTMT---");

		// 1. Apertura Connessione
		Connection conn = getConnection();
		
		/* 2. Creazione di un oggetto Statement o PreparedStatement
		 * 
		 * Come abbiamo visto nell'esempio precedente, se interagiamo interattivamente con il DBMS
		 * tramite un oggetto Statement e' necessario comporre prima la stringa con le istruzioni 
		 * SQL che devono essere inoltrate da JDBC al Driver del DBMS. 
		 * 
		 * L'operazione di composizione dell'istruzione SQL mediante concatenazione di sottostringhe
		 * e' una operazione delicata, che puo' portare facilmente a istruzioni SQL malformate e spesso
		 * vulnerabili ad attacchi di SQL-Injection. Pertanto, laddove non e' facile assicurare
		 * la corretta composizione dell'istruzione SQL, si utilizzano gli oggetti PreparedStatement,
		 * che si assumono la responsabilita' di creare statement SQL ben formattati.
		 * 
		 * Il trade-off che si paga e' una riduzione delle prestazioni, perche' i PreparedStatement sono meno 
		 * efficienti degli oggetti Statement. Tuttavia, perche' le istruzioni SQL con cui si costruiscono 
		 * gli oggetti PreparedStatement possono essere soggette a precompilazione, i PreparedStatement 
		 * possono risultare piu' efficienti degli Statement se essi vengono eseguiti piu' volte, modificando 
		 * all'occorrenza i campi parametrizzati.
		 * 
		 */
		
		// Se avessimo voluto utilizzare uno Statement avremmo dovuto comporre la seguente istruzione SQL 
		//String esempioComposizioneSql = "UPDATE TEST SET NAME='" + s + "'  WHERE ID=" + Integer.toString(id);
		
		/*
		 * Con un PreparedStatement creiamo la stringa SQL ponendo dei placeholder in luogo dei parametri
		 * effettivi che vogliamo utilizzare per completare lo statement.
		 */
		String query = "UPDATE TEST SET NAME=? WHERE ID=?";
		System.out.println("preparing statement: "+query);
		PreparedStatement stmt = conn.prepareStatement(query);
		
		/*
		 * Successivamente possiamo impostare i parametri effettivi utilizzando le operazioni di set
		 * esposte dall'oggetto: esistono set almeno per ogni tipo di dato primitivo Java.
		 * 
		 * Il primo argomento di ogni metodo di set di PreparedStatement indentifica l'indice del placeholder 
		 * che deve essere sostituito dal parametro effettivo.
		 * 	> Gli indici dei placeholder partono da 1!     
		 * 
		 */
		System.out.println("setting 1="+s+" and 2="+id);
		stmt.setString(1, s);
		stmt.setInt(2, id);
		
		// 3 e 4. Interazione con la base dati e lettura dei risultati
		int n = stmt.executeUpdate();
		System.out.println("updated " + Integer.toString(n)+" rows");
		System.out.println("Stato DB dopo operazione di UPDATE:");
		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TEST");
		while (rs.next()) {
			System.out.format("\t%d %s\n", rs.getInt("ID"), rs.getString("NAME"));
		}

		
		/* 
		 * I parametri impostati in un PreparedStatement sopravvivono alle esecuzioni: possiamo reimpostare 
		 * solo quei parametri che effettivamente cambiano in piu' interazioni con il DBMS.
		 * 
		 */
		System.out.println("setting 1="+s.toUpperCase());
		stmt.setString(1, s.toUpperCase());
		n = stmt.executeUpdate();
		System.out.println("updated " + Integer.toString(n)+" rows");
		System.out.println("Stato DB dopo operazione di UPDATE:");
		rs = conn.createStatement().executeQuery("SELECT * FROM TEST");
		while (rs.next()) {
			System.out.format("\t%d %s\n", rs.getInt("ID"), rs.getString("NAME"));
		}
		
		stmt.close();
		conn.close();
	}

	private static void esempioConTryWithResources() {
		System.out.println("---ESEMPIO TRY WITH RESOURCES---");

		try(Connection conn = getConnection()){
			
			try(Statement stmt = conn.createStatement()){
				ResultSet rs = stmt.executeQuery("SELECT * FROM TEST");
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("NAME");
					System.out.format("%d %s\n", id, name);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
