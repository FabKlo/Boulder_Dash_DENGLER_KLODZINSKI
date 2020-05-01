package deplacementrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Sortie;

public class DeplacerRockfordCORVersSortie extends DeplacerRockfordCOR {
    
    public DeplacerRockfordCORVersSortie(DeplacerRockfordCOR s) {
        super(s);
    }

	@Override
    protected boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if((ct < 0 || ct >= grille.getXMAX()) || (lt < 0 || lt >= grille.getYMAX())) {
            //System.out.println("Case target en dehors du tableau");
            return true;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Sortie)) {
            return false;
        }

        Sortie s = (Sortie)(grille.getCaseDuTab(ct,lt));

        if(s.isPorteOuverte()) {
            grille.getCaseDuTab(ct,lt).mettrePersoSurCase(grille.getCaseDuTab(cs,ls).getEstIci());
            grille.getCaseDuTab(cs,ls).setEstIci(null);
            return true;
        }
        
        return false;
    }

}