package chuterocher;

import entitesvivantes.Monstre;
import lagrille.Grille;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurMonstre extends ChuteDuRocherCOR {

    public ChuteDuRocherCORSurMonstre(ChuteDuRocherCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls-1).estOccupee() && grille.getCaseDuTab(cs,ls-1).getEstIci() instanceof Monstre)) {
            return false;
        }

        grille.getCaseDuTab(cs,ls - 1).getEstIci().setVie(grille.getCaseDuTab(cs, ls-1).getEstIci().getVie()-1);
        grille.verifVieAll();
        return true;
    }

}