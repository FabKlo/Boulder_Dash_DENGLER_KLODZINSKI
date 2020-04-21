package chuteobjgravite;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Diamant;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

/**
 * DeplacerRockfordCORVersVide
 */
public class ChuteDuDiamantCORSurRockford extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurRockford(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1).estOccupee() && grille.getCaseDuTab(cs,ls+1).getEstIci() instanceof Rockford)) {
            //System.out.println("la case target n'est pas rockford");
            return false;
        }

        if(((Diamant)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {
            grille.getCaseDuTab(cs,ls+1).getEstIci().setVie(grille.getCaseDuTab(cs,ls+1).getEstIci().getVie() - 1);
            Rockford oui = (Rockford)(grille.getCaseDuTab(cs,ls+1).getEstIci());
            oui.setCompteurDiamant(oui.getCompteurDiamant()+1);
            grille.setCaseDuTab(cs,ls,new Vide(cs,ls));

            //System.out.println("tab["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName());
            //System.out.println("tab["+cs+"]["+(ls+1)+"] = " + grille.getCaseDuTab(cs,ls+1).getClass().getSimpleName()); 
            //System.out.println("tab["+cs+"]["+(ls+1)+"] = " + grille.getCaseDuTab(cs,ls+1).getEstIci()); 
            //System.out.println("vie rockford : "+grille.getCaseDuTab(cs,ls+1).getEstIci().getVie());
            //System.out.println("diams rockford : "+((Rockford)(grille.getCaseDuTab(cs,ls+1).getEstIci())).getCompteurDiamant());

            grille.verifVieAll();
            return true; 
        }
        else {
           // System.out.println("rockford juste en dessous, on ne fait rien");
            return true;
        }
    }

}