package Aplikacja.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpcjeBaza {

	public List<Opcje> getOpcjeList() {
		List<Opcje> opcjeList = new ArrayList<Opcje>();
		
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            
            Statement stmt = conn.createStatement();
            String query = "SELECT id_opcje, nazwa FROM opcje";
            ResultSet rows = stmt.executeQuery(query);
            
            while(rows.next()){
            	opcjeList.add(new Opcje(
            			rows.getInt("id_opcje"),
            			rows.getString("nazwa")));
            }
            
            stmt.close();
            conn.close();
        }  catch (Exception ex) {
        	ex.printStackTrace();
        }
        return opcjeList;
	}
	
	public void insertOpcje(Opcje opcje) {
        String command = String.format("INSERT INTO opcje(nazwa) " +
        		"VALUES('%s')",
        		opcje.getNazwaOpcji());
		executeCommand(command);
	}
	
	public void updateOpcje(Opcje opcje) {
        String command = String.format("UPDATE opcje SET nazwa='%s' " +
        		"WHERE id_opcje = %d",
        		opcje.getNazwaOpcji(), opcje.getIdOpcje());
		executeCommand(command);
	}
	
	public void deleteOpcje(Integer opcjeId) {
        String command = String.format("DELETE FROM opcje WHERE id_opcje = %d", opcjeId);
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

