package chutediamant;

import lagrille.Grille;
import lescases.Acier;
import lescases.Diamant;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurAcier extends ChuteDuDiamantCOR {

    public ChuteDuDiamantCORSurAcier(ChuteDuDiamantCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerDiamantVersCase(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(ls - 1 < 0) {
            System.out.println("Le diamant est déjà tout en bas !");
            ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
            return true;
        }

        if(!(grille.getCaseDuTab(cs,ls-1) instanceof Acier)) {
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}