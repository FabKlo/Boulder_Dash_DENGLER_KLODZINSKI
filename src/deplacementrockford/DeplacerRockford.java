package deplacementrockford;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public interface DeplacerRockford {

    public boolean deplaceRockford(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

}