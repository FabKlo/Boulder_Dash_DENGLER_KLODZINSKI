package entitesvivantes;

import lescases.Diamant;
import lescases.Sortie;
import lescases.Terre;
import lescases.Vide;

public class Rockford extends Personnage {

    private int compteurDiamant;

    /**
     * Creation de rockford avec vie = 3, son compteur de diamant = 0
     */
    public Rockford() {
        setVie(3);
        setCompteurDiamant(0);
        addCasesTraversables(Vide.class.getSimpleName());
        addCasesTraversables(Diamant.class.getSimpleName());
        addCasesTraversables(Terre.class.getSimpleName());
        addCasesTraversables(Sortie.class.getSimpleName());
    } 

public int getCompteurDiamant() {
	return compteurDiamant;
}

public void setCompteurDiamant(int compteurDiams) {
	this.compteurDiamant = compteurDiams;
}



}