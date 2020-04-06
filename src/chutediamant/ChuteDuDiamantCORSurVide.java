package chutediamant;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurVide extends ChuteDuDiamantCOR {

    public ChuteDuDiamantCORSurVide(ChuteDuDiamantCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerDiamantVersCase(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls-1) instanceof Vide) || grille.getCaseDuTab(cs,ls-1).estOccupee()) {
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(true);
        grille.setCaseDuTab(cs,ls-1,grille.getCaseDuTab(cs,ls));
        grille.setCaseDuTab(cs,ls,new Vide(cs,ls));
        return true;
    }

}