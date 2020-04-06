package ui;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import lagrille.Grille;
import lescases.Case;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderException;


public class PanneauFooter extends HBox {

	
	/**
	 * Timer qui se déclenche tous les 0.1 s
	 */
	private	Timer timer = new Timer(1);
	

	public PanneauFooter(Grille grille, ArrayList<Case> tousLesRochersEtDiams) {
		super();

// YL : j'ajoute uniquement l'afficheur du temps écoulé. Vous pouvez enrichir cette partie de l'interface
// avec le score, le nombre de vies, etc.
		
		// afficheur est un observateur du timer
		AfficheurTempsEcoule afficheur = new AfficheurTempsEcoule(this);
		try {
			timer.add(afficheur);

			for (Case c : tousLesRochersEtDiams) {
				if(c instanceof Rocher)
					grille.déplacerRocher(c.getPositionX(), c.getPositionY());
				if(c instanceof Diamant)
					grille.déplacerDiamant(c.getPositionX(), c.getPositionY());
			}

		} catch (BoulderException e) {
			e.printStackTrace();
		}
	}


}
