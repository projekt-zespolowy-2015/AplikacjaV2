package Aplikacja.mvc.controller;

import java.util.List;

import Aplikacja.mvc.model.Opcje;
import Aplikacja.mvc.model.OpcjeBaza;
import Aplikacja.mvc.view.OpcjeView;

public class OpcjeController {

	private OpcjeBaza opcjeModel;
	private OpcjeView opcjeView;
	
	public OpcjeController(OpcjeBaza model, OpcjeView view) {
		this.opcjeModel = model;
		this.opcjeView = view;
		
		view.setController(this);
		
		refreshOpcjeList();
	}
	
	public void insertOpcje(Opcje opcje) {
		opcjeModel.insertOpcje(opcje);
		refreshOpcjeList();
	}
	
	public void updateOpcje(Opcje opcje) {
		opcjeModel.updateOpcje(opcje);
		refreshOpcjeList();
	}
	
	public void deleteOpcje(Integer opcjeId) {
		opcjeModel.deleteOpcje(opcjeId);
		refreshOpcjeList();
	}
	
	private void refreshOpcjeList() {
		List<Opcje> opcjeList = opcjeModel.getOpcjeList();
		opcjeView.refreshOpcjeList(opcjeList);
	}
}

