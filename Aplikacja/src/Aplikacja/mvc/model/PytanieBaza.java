package Aplikacja.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PytanieBaza {

	public List<Pytanie> getPytania() {
		List<Pytanie> pytania = new ArrayList<Pytanie>();
		
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            
            Statement stmt = conn.createStatement();
            String query = "SELECT id_pytanie, tresc FROM pytanie";
            ResultSet rows = stmt.executeQuery(query);
            
            while(rows.next()){
            	pytania.add(new Pytanie(
            			rows.getInt("id_pytanie"),
            			rows.getString("tresc")));
            }
            
            stmt.close();
            conn.close();
        }  catch (Exception ex) {
        	ex.printStackTrace();
        }
        return pytania;
	}
	
	public void insertPytanie(Pytanie pytanie) {
        String command = String.format("INSERT INTO pytanie(tresc) " +
        		"VALUES('%s')",
        		pytanie.getTrescPytania());
		executeCommand(command);
	}
	
	public void updatePytanie(Pytanie pytanie) {
        String command = String.format("UPDATE pytanie SET tresc='%s' " +
        		"WHERE id_pytanie = %d",
        		pytanie.getTrescPytania(), pytanie.getIdPytanie());
		executeCommand(command);
	}
	
	public void deletePytanie(Integer pytanieId) {
        String command = String.format("DELETE FROM pytanie WHERE id_pytanie = %d", pytanieId);
		executeCommand(command);
	}
	
	private void executeCommand(String command) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(command);
            stmt.close();
            conn.close();
        }  catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
}

