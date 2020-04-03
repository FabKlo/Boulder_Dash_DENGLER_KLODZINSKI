package chuterocher;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public interface ChuteDuRocher {
    public boolean deplaceRocher(Grille grille, int cs, int ls) throws BoulderMortException;
}