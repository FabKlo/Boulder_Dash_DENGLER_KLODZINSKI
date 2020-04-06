package chutediamant;

import lagrille.Grille;
import lescases.Diamant;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurDiamant extends ChuteDuDiamantCOR {

    public ChuteDuDiamantCORSurDiamant(ChuteDuDiamantCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerDiamantVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls-1) instanceof Diamant)) {
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}