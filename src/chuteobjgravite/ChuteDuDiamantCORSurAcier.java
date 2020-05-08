package chuteobjgravite;

import lagrille.Grille;
import lescases.Acier;
import lescases.Diamant;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

public class ChuteDuDiamantCORSurAcier extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurAcier(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(ls + 1 >= grille.getYMAX()) {
            //System.out.println("Le diamant est deja tout en bas !");
            ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
            return true;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Acier)) {
            //System.out.println("la case target n'est pas de l'acier");
            return false;
        }

        if(((Diamant)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {
            Musique.initBruitage(Musique.DIAMANT);
        }
        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}