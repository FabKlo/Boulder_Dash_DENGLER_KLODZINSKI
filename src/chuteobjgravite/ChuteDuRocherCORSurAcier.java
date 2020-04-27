package chuteobjgravite;

import lagrille.Grille;
import lescases.Acier;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurAcier extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurAcier(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(ls + 1 >= grille.getYMAX()) {
            //System.out.println("Le rocher est deja tout en bas !");
            ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
            return true;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Acier)) {
            //System.out.println("la case target n'est pas de l'acier");
            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}