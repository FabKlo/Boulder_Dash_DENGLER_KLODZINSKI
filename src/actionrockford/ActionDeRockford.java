package actionrockford;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public interface ActionDeRockford {
    
    /**
     * methode abstraite
     * @param grille la grille
     * @param cs colonne source
     * @param ls ligne source
     * @param ct colonne target
     * @param lt ligne target
     * @return vrai si l'action est realisee, faux si non
     * @throws BoulderMortException rockford est mort !
     */
    public boolean actionRockford(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

}