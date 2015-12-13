package Aplikacja.mvc.controller;

import java.util.List;

import Aplikacja.mvc.model.Pytanie;
import Aplikacja.mvc.model.PytanieBaza;
import Aplikacja.mvc.view.PytanieView;

public class PytanieController {

	private PytanieBaza pytanieModel;
	private PytanieView pytanieView;
	
	public PytanieController(PytanieBaza model, PytanieView view) {
		this.pytanieModel = model;
		this.pytanieView = view;
		
		view.setController(this);
		
		refreshPytania();
	}
	
	public void insertPytanie(Pytanie pytanie) {
		pytanieModel.insertPytanie(pytanie);
		refreshPytania();
	}
	
	public void updatePytanie(Pytanie pytanie) {
		pytanieModel.updatePytanie(pytanie);
		refreshPytania();
	}
	
	public void deletePytanie(Integer pytanieId) {
		pytanieModel.deletePytanie(pytanieId);
		refreshPytania();
	}
	
	private void refreshPytania() {
		List<Pytanie> pytania = pytanieModel.getPytania();
		pytanieView.refreshPytania(pytania);
	}
}

