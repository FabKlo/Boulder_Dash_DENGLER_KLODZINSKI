package chuterocher;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public interface ChuteObjGravite {

    /**
     * méthode abstraite
     * @param grille la grille
     * @param cs colonne source
     * @param ls ligne source
     * @return vrai si le déplacement est effectué, faux si non
     * @throws BoulderMortException rockford est mort !
     */
    public boolean deplaceRocher(Grille grille, int cs, int ls) throws BoulderMortException;
}