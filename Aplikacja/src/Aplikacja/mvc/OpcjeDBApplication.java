package Aplikacja.mvc;
 
import Aplikacja.mvc.controller.OpcjeController;
import Aplikacja.mvc.model.OpcjeBaza;
import Aplikacja.mvc.view.OpcjeView;

public class OpcjeDBApplication {

	public static void main(String[] args) {
		
		OpcjeBaza opcjeModel = new OpcjeBaza();
		OpcjeView opcjeView = new OpcjeView();
		new OpcjeController(opcjeModel, opcjeView);
		
		opcjeView.setVisible(true);
	}
}
