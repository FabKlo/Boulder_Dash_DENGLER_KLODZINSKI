package chutediamant;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

/**
 * Interface pour la COR
 */
public interface ChuteDuDiamant {
    /**
     * 
     * @param grille la grille
     * @param cs colonne source
     * @param ls ligne source
     * @return vrai si le déplacement est effectué, faux si non
     * @throws BoulderMortException rockford est mort !
     */
    public boolean deplaceDiamant(Grille grille, int cs, int ls) throws BoulderMortException;
}