package Aplikacja.mvc;
 
import Aplikacja.mvc.controller.AnkietaController;
import Aplikacja.mvc.model.AnkietaBaza;
import Aplikacja.mvc.view.AnkietaView;

public class AnkietaDBApplication {

	public static void main(String[] args) {
		
		AnkietaBaza ankietaModel = new AnkietaBaza();
		AnkietaView ankietaView = new AnkietaView();
		new AnkietaController(ankietaModel, ankietaView);
		
		ankietaView.setVisible(true);
	}
}
