package chuterocher;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurRocher extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurRocher (ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Rocher)) {
            //System.out.println("la case target n'est pas un rocher");
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}