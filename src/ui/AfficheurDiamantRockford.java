package ui;

import entitesvivantes.Rockford;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modele.obs.Observable;
import modele.obs.Observateur;

public class AfficheurDiamantRockford implements Observateur {

    private	int compteurDiams = 0;
	private	Label vieRockford = new Label();
	private Rockford rockford;
	
	private final static String LABEL = "	Diamants ramassés par Rockford : ";

	public AfficheurDiamantRockford(Pane panneau, Rockford rockford) {
		panneau.getChildren().add(vieRockford);
		this.rockford = rockford;
	}

	public void recevoirNotification(Observable observable) {
		
		this.compteurDiams = rockford.getCompteurDiamant();
		vieRockford.setText(LABEL+compteurDiams);
	}
    

}