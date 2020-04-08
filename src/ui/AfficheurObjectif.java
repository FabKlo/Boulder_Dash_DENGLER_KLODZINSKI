package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lagrille.Grille;
import modele.obs.Observable;
import modele.obs.Observateur;

public class AfficheurObjectif implements Observateur {

    private String obj;
	private	Label objectif = new Label();
    private Grille grille;
	
	private final static String LABEL = "OBJECTIF : ";

	public AfficheurObjectif(Pane panneau, Grille grille) {
		panneau.getChildren().add(objectif);
		this.grille = grille;
        this.obj = grille.getObjectif();
	}

	public void recevoirNotification(Observable observable) {

        switch(obj) {
            case "COLLECTER_DIAMANT":
                objectif.setText(LABEL+obj+" : "+grille.getDiamsMax());
                break;
            case "ELIMINER_MONSTRES":
                objectif.setText(LABEL+obj);
                break;
            default:
                objectif.setText(LABEL+" y en a pas lol");
        }

	}
    

}