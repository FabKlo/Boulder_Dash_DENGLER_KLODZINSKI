package ui;

import javafx.scene.layout.HBox;
import modele.exceptions.BoulderException;

public class PanneauTime extends HBox {

	/**
	 * Timer qui se declenche tous les 0.1 s
	 */
	private Timer timer = new Timer(0.1);

	public PanneauTime() {
		super();

		// YL : j'ajoute uniquement l'afficheur du temps ecoule. Vous pouvez enrichir
		// cette partie de l'interface
		// avec le score, le nombre de vies, etc.

		// afficheur est un observateur du timer
		
        AfficheurTempsEcoule afficheur = new AfficheurTempsEcoule(this);


		try {
			timer.add(afficheur);	
			
			
		} catch (BoulderException e) {
			e.printStackTrace();
		}
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}
