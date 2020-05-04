package ui;

import entitesvivantes.Rockford;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lagrille.Grille;
import modele.obs.Observable;
import modele.obs.Observateur;

public class AfficheurDiamantRockford implements Observateur {

    private	int compteurDiams = 0;
	private	Label vieRockford = new Label();
	private Rockford rockford;
	private Grille grille;
	
	private final static String LABEL = "Diamants ramasses : ";

	public AfficheurDiamantRockford(Pane panneau, Rockford rockford, Grille grille) {
		panneau.getChildren().add(vieRockford);
		this.rockford = rockford;
		this.grille = grille;
	}

	public void recevoirNotification(Observable observable) {
		
		this.compteurDiams = rockford.getCompteurDiamant();
		vieRockford.setText(LABEL+compteurDiams+" / "+grille.getDiamsMax());
		vieRockford.setFont(Font.loadFont ("file:font/Boulder_Dash_Font.ttf" , 50));
		vieRockford.setTextFill(Color.WHITE);
	}
    

}