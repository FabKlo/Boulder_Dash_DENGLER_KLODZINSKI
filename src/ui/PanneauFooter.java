package ui;

import entitesvivantes.Rockford;
import javafx.scene.layout.HBox;
import lagrille.Grille;
import modele.exceptions.BoulderException;

public class PanneauFooter extends HBox {

	/**
	 * Timer qui se déclenche tous les 0.1 s
	 */
	private Timer timer = new Timer(0.1);

	public PanneauFooter(Grille grille, Rockford rockford) {
		super();

		// YL : j'ajoute uniquement l'afficheur du temps écoulé. Vous pouvez enrichir
		// cette partie de l'interface
		// avec le score, le nombre de vies, etc.

		// afficheur est un observateur du timer
		AfficheurTempsEcoule afficheur = new AfficheurTempsEcoule(this);
		AfficheurVieRockford vieRockford = new AfficheurVieRockford(this, rockford);
		AfficheurDiamantRockford diamsRockford = new AfficheurDiamantRockford(this, rockford);
		AfficheurNombreMonstre nbrMonstre = new AfficheurNombreMonstre(this, grille);
		AfficheurDiamantMap diamsMap = new AfficheurDiamantMap(this, grille);
		try {
			timer.add(afficheur);
			timer.add(vieRockford);
			timer.add(diamsRockford);
			timer.add(nbrMonstre);
			timer.add(diamsMap);
			
		} catch (BoulderException e) {
			e.printStackTrace();
		}
	}


}
