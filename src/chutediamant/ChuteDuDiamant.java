package chutediamant;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public interface ChuteDuDiamant {
    public boolean deplaceDiamant(Grille grille, int cs, int ls) throws BoulderMortException;
}