package actionrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Acier;
import modele.exceptions.BoulderMortException;

public class ActionDeRockfordCORVersAcier extends ActionDeRockfordCOR {

    public ActionDeRockfordCORVersAcier(ActionDeRockfordCOR s) {
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

        if(!(grille.getCaseDuTab(ct,lt) instanceof Acier)) {
            return false;
        }

        return true;
    }
    
}