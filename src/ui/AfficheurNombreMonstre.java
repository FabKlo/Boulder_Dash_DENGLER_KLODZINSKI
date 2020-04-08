package ui;

import entitesvivantes.Monstre;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lagrille.Grille;
import modele.obs.Observable;
import modele.obs.Observateur;
import java.util.ArrayList;



public class AfficheurNombreMonstre implements Observateur {

    private int compteur = 0;
	private	Label nbrMonstre = new Label();
	private ArrayList<Monstre> allMonstre;
	private Grille grille;
	
	private final static String LABEL = "Nombre de monstres restant : ";

	public AfficheurNombreMonstre(Pane panneau, Grille grille) {
		panneau.getChildren().add(nbrMonstre);
		this.grille = grille;
		this.allMonstre = grille.searchAllMonstre();
	}

	public void recevoirNotification(Observable observable) {

		this.allMonstre = grille.searchAllMonstre();

        compteur = allMonstre.size();

		nbrMonstre.setText(LABEL+compteur);
	}
    

}