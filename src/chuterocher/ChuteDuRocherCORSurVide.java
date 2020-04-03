package chuterocher;

import lagrille.Grille;
import lescases.Rocher;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurVide extends ChuteDuRocherCOR {

    public ChuteDuRocherCORSurVide(ChuteDuRocherCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls-1) instanceof Vide) || grille.getCaseDuTab(cs,ls-1).estOccupee()) {
            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(true);
        grille.setCaseDuTab(cs,ls-1,grille.getCaseDuTab(cs,ls));
        grille.setCaseDuTab(cs,ls,new Vide(cs,ls));
        return true;
    }

}