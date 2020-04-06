package deplacementrockford;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public interface DeplacerRockford {

    /**
     * méthode abstraite
     * @param grille la grille
     * @param cs colonne source
     * @param ls ligne source
     * @param ct colonne target
     * @param lt ligne target
     * @return vrai si le déplacement est effectué, faux si non
     * @throws BoulderMortException rockford est mort !
     */
    public boolean deplaceRockford(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

}