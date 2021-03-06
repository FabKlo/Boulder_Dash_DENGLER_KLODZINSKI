package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import modele.obs.Observable;
import modele.obs.Observateur;

public class AfficheurTempsEcoule implements Observateur {
	private	double	secondes;
	private	Label labelTempsEcoule = new Label("");
	
	private final static String LABEL = "Temps ecoule : ";

	public AfficheurTempsEcoule(Pane panneau, double secondes) {
		panneau.getChildren().add(labelTempsEcoule);
		this.secondes = secondes;
	}

	@Override
	public void recevoirNotification(Observable observable) {
		Timer timer = (Timer)observable;
		secondes += timer.getLaps();
		
		// YL : astuces pour formater un reel avec 1 seul chiffre apres la virgule
		String strSecondes = String.format("%.1f", secondes);
		
		labelTempsEcoule.setText(LABEL+strSecondes+"	");
		labelTempsEcoule.setFont(Font.loadFont ("file:font/Boulder_Dash_Font.ttf" , 50));
		labelTempsEcoule.setTextFill(Color.WHITE);
	}

	public double getSecondes() {
		return secondes;
	}

	public void setSecondes(double secondes) {
		this.secondes = secondes;
	}
	
	
	
}
