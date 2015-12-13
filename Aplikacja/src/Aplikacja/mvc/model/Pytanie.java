package Aplikacja.mvc.model;

public class Pytanie {
	private Integer id_pytanie;
	private String tresc;
		
	public Pytanie(Integer id_pytanie, String tresc) {
		this(tresc);
		this.id_pytanie = id_pytanie;
	}
		
	public Pytanie(String tresc) {
		this.tresc = tresc;
	}
	
	public Integer getIdPytanie() {
		return id_pytanie;
	}
	
	public String getTrescPytania() {
		return tresc;
	}
}

