package chuteobjgravite;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Rocher;
import lescases.Vide;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

/**
 * DeplacerRockfordCORVersVide
 */
public class ChuteDuRocherCORSurRockford extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurRockford(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1).estOccupee() && grille.getCaseDuTab(cs,ls+1).getEstIci() instanceof Rockford)) {
            //System.out.println("la case target n'est pas rockford");

            return false;
        }

        if(((Rocher)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {
            grille.getCaseDuTab(cs,ls+1).getEstIci().setVie(grille.getCaseDuTab(cs,ls+1).getEstIci().getVie() - 1);
            grille.setCaseDuTab(cs,ls,new Vide(cs,ls));
            grille.verifVieAll();
            //System.out.println("tomber sur rockford");
            if(grille.getCaseDuTab(cs,ls+1).getEstIci().getVie()>0)
                Musique.initBruitage(Musique.DMG);
            return true; 
        }
        else {
            //System.out.println("rockford juste en dessous, on ne fait rien");
            return true;
        }
    }

}