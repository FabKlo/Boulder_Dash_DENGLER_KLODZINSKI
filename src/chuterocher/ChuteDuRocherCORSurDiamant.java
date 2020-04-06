package chuterocher;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurDiamant extends ChuteDuRocherCOR {

    public ChuteDuRocherCORSurDiamant(ChuteDuRocherCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls-1) instanceof Diamant)) {
            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}