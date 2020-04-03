package chuterocher;

import lagrille.Grille;
import lescases.Acier;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurAcier extends ChuteDuRocherCOR {

    public ChuteDuRocherCORSurAcier(ChuteDuRocherCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            return false;
        }

        if(ls - 1 < 0) {
            System.out.println("Le rocher est déjà tout en bas !");
            ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
            return true;
        }

        if(!(grille.getCaseDuTab(cs,ls-1) instanceof Acier)) {
            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}