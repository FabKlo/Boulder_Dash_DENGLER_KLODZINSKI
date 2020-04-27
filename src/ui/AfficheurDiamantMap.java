package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lagrille.Grille;
import modele.obs.Observable;
import modele.obs.Observateur;




public class AfficheurDiamantMap implements Observateur {

    private int compteur = 0;
	private	Label nbrDiamant = new Label();

	
	private final static String LABEL = "Nombre de diamants restant : ";

	public AfficheurDiamantMap(Pane panneau) {
		panneau.getChildren().add(nbrDiamant);
	}

	public void recevoirNotification(Observable observable) {


        compteur = Grille.allDiamant.size();

		nbrDiamant.setText(LABEL+compteur);
	}
    

}