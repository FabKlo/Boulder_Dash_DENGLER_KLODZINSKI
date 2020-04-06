package chutediamant;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Diamant;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

/**
 * DeplacerRockfordCORVersVide
 */
public class ChuteDuDiamantCORSurRockford extends ChuteDuDiamantCOR {

    public ChuteDuDiamantCORSurRockford(ChuteDuDiamantCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerDiamantVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls-1).estOccupee() && grille.getCaseDuTab(cs,ls-1).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(((Diamant)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {
            grille.getCaseDuTab(cs,ls-1).getEstIci().setVie(grille.getCaseDuTab(cs,ls-1).getEstIci().getVie() - 1);
            grille.setCaseDuTab(cs,ls,new Vide(cs,ls));
            grille.verifVieAll();
            return true; 
        }
        else {
            System.out.println("rockford juste en dessous, on ne fait rien");
            return true;
        }
    }

}