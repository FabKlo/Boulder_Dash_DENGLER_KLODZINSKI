package ui;

import entitesvivantes.Rockford;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modele.obs.Observable;
import modele.obs.Observateur;

public class AfficheurVieRockford implements Observateur {

    private	int vie = 0;
	private	Label vieRockford = new Label();
	private Rockford rockford;
	
	private final static String LABEL = "	Vie restante de Rockford : ";

	public AfficheurVieRockford(Pane panneau, Rockford rockford) {
		panneau.getChildren().add(vieRockford);
		this.rockford = rockford;
	}

	public void recevoirNotification(Observable observable) {
		
		this.vie = rockford.getVie();
		vieRockford.setText(LABEL+vie);
	}
    

}