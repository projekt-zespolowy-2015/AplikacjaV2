package Aplikacja.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnkietaBaza {

	public List<Ankieta> getAnkiety() {
		List<Ankieta> ankiety = new ArrayList<Ankieta>();
		
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            
            Statement stmt = conn.createStatement();
            String query = "SELECT id_ankieta, opis, data_od, data_do FROM ankieta";
            ResultSet rows = stmt.executeQuery(query);
            
            while(rows.next()){
            	ankiety.add(new Ankieta(
            			rows.getInt("id_ankieta"),
            			rows.getString("opis"),
            			rows.getString("data_od"),
            			rows.getString("data_do")));
            }
            
            stmt.close();
            conn.close();
        }  catch (Exception ex) {
        	ex.printStackTrace();
        }
        return ankiety;
	}
	
	public void insertAnkieta(Ankieta ankieta) {
        String command = String.format("INSERT INTO ankieta(opis, data_od, data_do) " +
        		"VALUES('%s', '%s', '%s')",
        		ankieta.getOpis(), ankieta.getDataOd(), ankieta.getDataDo());
		executeCommand(command);
	}
	
	public void updateAnkieta(Ankieta ankieta) {
        String command = String.format("UPDATE ankieta SET opis='%s', data_od='%s', data_do='%s' " +
        		"WHERE id_ankieta = %d",
        		ankieta.getOpis(), ankieta.getDataOd(), ankieta.getDataDo(), ankieta.getIdAnkieta());
		executeCommand(command);
	}
	
	public void deleteAnkieta(Integer ankietaId) {
        String command = String.format("DELETE FROM ankieta WHERE id_ankieta = %d", ankietaId);
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

