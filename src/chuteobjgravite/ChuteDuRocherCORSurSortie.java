package chuteobjgravite;

import lagrille.Grille;
import lescases.Rocher;
import lescases.Sortie;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurSortie extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurSortie(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)){
            return false;
        }

        if(ls + 1 >= grille.getYMAX()) {
            //System.out.println("Le diamant est deja tout en bas !");
            ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
            return true;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Sortie)) {
            //System.out.println("la case target n'est pas de l'acier");
            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }
    
}