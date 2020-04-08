package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lagrille.Grille;
import lescases.Case;
import modele.obs.Observable;
import modele.obs.Observateur;
import java.util.ArrayList;



public class AfficheurDiamantMap implements Observateur {

    private int compteur = 0;
	private	Label nbrDiamant = new Label();
	private ArrayList<Case> allDiamant;
	private Grille grille;
	
	private final static String LABEL = "   Nombre de diamants restant : ";

	public AfficheurDiamantMap(Pane panneau, Grille grille) {
		panneau.getChildren().add(nbrDiamant);
		this.grille = grille;
		this.allDiamant = grille.searchAllDiamantMap();
	}

	public void recevoirNotification(Observable observable) {

		this.allDiamant = grille.searchAllDiamantMap();

        compteur = allDiamant.size();

		nbrDiamant.setText(LABEL+compteur);
	}
    

}