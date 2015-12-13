package Aplikacja.mvc;
 
import Aplikacja.mvc.controller.PytanieController;
import Aplikacja.mvc.model.PytanieBaza;
import Aplikacja.mvc.view.PytanieView;

public class PytanieDBApplication {

	public static void main(String[] args) {
		
		PytanieBaza pytanieModel = new PytanieBaza();
		PytanieView pytanieView = new PytanieView();
		new PytanieController(pytanieModel, pytanieView);
		
		pytanieView.setVisible(true);
	}
}
