package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lagrille.Grille;
import modele.obs.Observable;
import modele.obs.Observateur;


public class AfficheurNombreMonstre implements Observateur {

    private int compteur = 0;
	private	Label nbrMonstre = new Label();

	
	private final static String LABEL = "Nombre de monstres restant : ";

	public AfficheurNombreMonstre(Pane panneau) {
		panneau.getChildren().add(nbrMonstre);
	}

	public void recevoirNotification(Observable observable) {

        compteur = Grille.allMonstres.size();

		nbrMonstre.setText(LABEL+compteur);
		nbrMonstre.setFont(Font.loadFont ("file:font/Boulder_Dash_Font.ttf" , 30));
		nbrMonstre.setTextFill(Color.WHITE);
	}
    

}