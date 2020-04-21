package chuterocher;

import lagrille.Grille;
import lescases.Rocher;
import lescases.Terre;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurTerre extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurTerre(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Terre)) {
            //System.out.println("la case target n'est pas de l'acier");
            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}