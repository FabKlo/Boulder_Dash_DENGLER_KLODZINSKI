package chutediamant;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurRocher extends ChuteDuDiamantCOR {

    public ChuteDuDiamantCORSurRocher (ChuteDuDiamantCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerDiamantVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls-1) instanceof Rocher)) {
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}