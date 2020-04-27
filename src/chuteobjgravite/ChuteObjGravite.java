package chuteobjgravite;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public interface ChuteObjGravite {

    /**
     * methode abstraite
     * @param grille la grille
     * @param cs colonne source
     * @param ls ligne source
     * @return vrai si le deplacement est effectue, faux si non
     * @throws BoulderMortException rockford est mort !
     */
    public boolean deplaceRocher(Grille grille, int cs, int ls) throws BoulderMortException;
}