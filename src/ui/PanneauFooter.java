package ui;

import entitesvivantes.Rockford;
import javafx.scene.layout.VBox;
import lagrille.Grille;
import modele.exceptions.BoulderException;

public class PanneauFooter extends VBox {

	/**
	 * Timer qui se declenche tous les 0.1 s
	 */
	private Timer timer = new Timer(0.1);

	public PanneauFooter(Grille grille, Rockford rockford) {
		super();

		// YL : j'ajoute uniquement l'afficheur du temps ecoule. Vous pouvez enrichir
		// cette partie de l'interface
		// avec le score, le nombre de vies, etc.

		// afficheur est un observateur du timer
		AfficheurObjectif obj = new AfficheurObjectif(this, grille);
		AfficheurTempsEcoule afficheur = new AfficheurTempsEcoule(this);
		AfficheurVieRockford vieRockford = new AfficheurVieRockford(this, rockford);
		AfficheurDiamantRockford diamsRockford = new AfficheurDiamantRockford(this, rockford);
		AfficheurNombreMonstre nbrMonstre = new AfficheurNombreMonstre(this);
		AfficheurDiamantMap diamsMap = new AfficheurDiamantMap(this);
		try {
			timer.add(obj);
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
