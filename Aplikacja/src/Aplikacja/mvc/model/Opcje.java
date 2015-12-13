package Aplikacja.mvc.model;

public class Opcje {
	private Integer id_opcje;
	private String nazwa;
	
	public Opcje(Integer id_opcje, String nazwa) {
		this(nazwa);
		this.id_opcje = id_opcje;
	}
	
	public Opcje(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public Integer getIdOpcje() {
		return id_opcje;
	}
	
	public String getNazwaOpcji() {
		return nazwa;
	}
}
