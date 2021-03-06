package deplacementrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Diamant;
import lescases.Vide;
import toutlessongs.Musique;

/**
 * DeplacerRockfordCORVersVide
 */
public class DeplacerRockfordCORVersDiamant extends DeplacerRockfordCOR {

    public DeplacerRockfordCORVersDiamant(DeplacerRockfordCOR s) {
        super(s);
    }

	@Override
    protected boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Diamant)) {
            return false;
        }

        if(grille.getCaseDuTab(ct,lt).getEstIci() == null && !((Diamant)(grille.getCaseDuTab(ct,lt))).isEnMouvement()) {
            Rockford temp = (Rockford) grille.getCaseDuTab(cs,ls).getEstIci();
            //System.out.println("nbr de diamant AVANT recup de rockford en x =" +cs+", y = " +ls+" : " +temp.getCompteurDiamant());
            temp.setCompteurDiamant(temp.getCompteurDiamant() + 1);
            grille.setCaseDuTab(ct,lt,new Vide(ct,lt));
            grille.getCaseDuTab(ct,lt).mettrePersoSurCase(temp);
            grille.setCaseDuTab(cs,ls,new Vide(cs,ls));
            Musique.initBruitage(Musique.PIECE);
            //System.out.println("nbr de diamant APRES recup de rockford en x =" +ct+", y = " +lt+" : " +temp.getCompteurDiamant());
            return true;
        }

        return false;
    }

}