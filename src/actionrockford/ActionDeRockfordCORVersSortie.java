package actionrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Sortie;
import modele.exceptions.BoulderMortException;

public class ActionDeRockfordCORVersSortie extends ActionDeRockfordCOR {

    public ActionDeRockfordCORVersSortie(ActionDeRockfordCOR s) {
        super(s);
    }

    @Override
    protected boolean actionRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if((ct < 0 || ct >= grille.getXMAX()) || (lt < 0 || lt >= grille.getYMAX())) {
            return true;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Sortie)) {
            return false;
        }

        return true;
    }
    
}