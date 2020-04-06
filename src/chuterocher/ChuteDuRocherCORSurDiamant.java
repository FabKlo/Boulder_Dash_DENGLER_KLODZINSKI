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

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Diamant)) {
            System.out.println("la case target n'est pas un diamant");

            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}