package deplacementrockford;

import entitesvivantes.Monstre;
import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

/**
 * DeplacerRockfordCORVersVide
 */
public class DeplacerRockfordCORVersMonstre extends DeplacerRockfordCOR {

    public DeplacerRockfordCORVersMonstre(DeplacerRockfordCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Vide))
            return false;

        if(grille.getCaseDuTab(ct,lt).estOccupee() && grille.getCaseDuTab(ct,lt).getEstIci() instanceof Monstre) {
            grille.getCaseDuTab(cs,ls).getEstIci().setVie(grille.getCaseDuTab(cs,ls).getEstIci().getVie() - 1);
            grille.getCaseDuTab(ct,lt).getEstIci().setVie(grille.getCaseDuTab(ct,lt).getEstIci().getVie() - 1);
            grille.verifVieAll();
            grille.setCaseDuTab(ct,lt, new Vide(ct, lt));
            Rockford temp = (Rockford) grille.getCaseDuTab(cs,ls).getEstIci();
            temp.setCompteurDiamant(temp.getCompteurDiamant() + 1);
            grille.getCaseDuTab(cs,ls).setEstIci(null);
            grille.getCaseDuTab(ct,lt).mettrePersoSurCase(temp);

            System.out.println("tableau["+ct+"]["+lt+"] = " + grille.getCaseDuTab(ct,lt).getClass().getSimpleName() +
            ", personnage dessus : " + grille.getCaseDuTab(ct,lt).getEstIci());
            System.out.println("tableau["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName() +
            ", personnage dessus : " + grille.getCaseDuTab(cs,ls).getEstIci());  

            System.out.println("vie de rockford situé en x = " + ct + " y = " + lt +
            " : " + grille.getCaseDuTab(ct,lt).getEstIci().getVie());
            return true;
        }

        return false;
    }
}