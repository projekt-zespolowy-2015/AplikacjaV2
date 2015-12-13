package Aplikacja.mvc.model;

public class Ankieta {

	private Integer id_ankieta;
	private String opis;
	//private java.math.BigDecimal data_od;
	//private java.math.BigDecimal data_do;
	private String data_od;
	private String data_do;
	
	public Ankieta(Integer id_ankieta, String opis, String data_od, String data_do) {
		this(opis, data_od, data_do);
		this.id_ankieta = id_ankieta;
	}
	
	public Ankieta(String opis, String data_od, String data_do) {
		this.opis = opis;
		this.data_od = data_od;
		this.data_do = data_do;
	}
		
	public Integer getIdAnkieta() {
		return id_ankieta;
	}
	
	public String getOpis() {
		return opis;
	}
	
	public String getDataOd() {
		return data_od;
	}
	
	public String getDataDo() {
		return data_do;
	}
}

