package ui;

import entitesvivantes.Rockford;
import javafx.scene.layout.VBox;
import lagrille.Grille;
import modele.exceptions.BoulderException;

public class PanneauDiams extends VBox {

	/**
	 * Timer qui se declenche tous les 0.1 s
	 */
	private Timer timer = new Timer(0.1);

	public PanneauDiams(Grille grille, Rockford rockford) {
		super();

		// YL : j'ajoute uniquement l'afficheur du temps ecoule. Vous pouvez enrichir
		// cette partie de l'interface
		// avec le score, le nombre de vies, etc.

		// afficheur est un observateur du timer
		
		
		AfficheurDiamantRockford diamsRockford = new AfficheurDiamantRockford(this, rockford, grille);

		try {
			timer.add(diamsRockford);
			
			
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
