package ui;

import javafx.scene.layout.HBox;
import modele.exceptions.BoulderException;

public class PanneauTime extends HBox {

	/**
	 * Timer qui se declenche tous les 0.1 s
	 */
	private Timer timer = new Timer(0.1);
	private double secondes;
	private AfficheurTempsEcoule afficheur = new AfficheurTempsEcoule(this, secondes);


	public PanneauTime(double secondes) {
		super();
		this.secondes = secondes;

		// YL : j'ajoute uniquement l'afficheur du temps ecoule. Vous pouvez enrichir
		// cette partie de l'interface
		// avec le score, le nombre de vies, etc.

		// afficheur est un observateur du timer

		afficheur = new AfficheurTempsEcoule(this, secondes);
		
        //AfficheurTempsEcoule afficheur = new AfficheurTempsEcoule(this, secondes);


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

	public double getSecondes() {
		return secondes;
	}

	public void setSecondes(double secondes) {
		this.secondes = secondes;
	}

	public AfficheurTempsEcoule getAfficheur() {
		return afficheur;
	}

	public void setAfficheur(AfficheurTempsEcoule afficheur) {
		this.afficheur = afficheur;
	}

}
