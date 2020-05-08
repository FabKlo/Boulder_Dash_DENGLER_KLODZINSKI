package chuteobjgravite;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

public class ChuteDuRocherCORSurDiamant extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurDiamant(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Diamant)) {
            //System.out.println("la case target n'est pas un diamant");

            return false;
        }

        if(((Rocher)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {
            Musique.initBruitage(Musique.ROCHER);
        }
        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}