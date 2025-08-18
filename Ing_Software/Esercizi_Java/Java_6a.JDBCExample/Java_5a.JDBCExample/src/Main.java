import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			String dbName = "~/test"; //DA ADATTARE
			String username = "admin";
			String password = "";
			Connection conn = 
					DriverManager.getConnection("jdbc:h2:"+dbName,
							username, password);
			
			Statement stmnt = conn.createStatement();
			ResultSet r = stmnt.executeQuery("SELECT * FROM TEST");
			
			
			while (r.next()){
				int id = r.getInt("ID");
				if (r.wasNull()) System.out.println("id is null"); 
				String s = r.getString("NAME");
				if (r.wasNull()) System.out.println("name is null"); 
				System.out.println(id + ":" + s);
			}
			
			r.close();
			stmnt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}
