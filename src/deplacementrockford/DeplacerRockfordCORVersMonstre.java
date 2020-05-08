package deplacementrockford;

import entitesvivantes.Monstre;
import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Vide;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

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
            Rockford temp = (Rockford) grille.getCaseDuTab(cs,ls).getEstIci();
            temp.setCompteurDiamant(temp.getCompteurDiamant() + ((Monstre)(grille.getCaseDuTab(ct,lt).getEstIci())).getNbrDiamantsDonne());
            grille.setCaseDuTab(ct,lt, new Vide(ct, lt));
            grille.verifVieAll();
            grille.getCaseDuTab(cs,ls).setEstIci(null);
            grille.getCaseDuTab(ct,lt).mettrePersoSurCase(temp);

            if(grille.getCaseDuTab(ct,lt).getEstIci().getVie()>0)
                Musique.initBruitage(Musique.DMG);


            /*System.out.println("tableau["+ct+"]["+lt+"] = " + grille.getCaseDuTab(ct,lt).getClass().getSimpleName() +
            ", personnage dessus : " + grille.getCaseDuTab(ct,lt).getEstIci());
            System.out.println("tableau["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName() +
            ", personnage dessus : " + grille.getCaseDuTab(cs,ls).getEstIci());  

            System.out.println("vie de rockford situe en x = " + ct + " y = " + lt +
            " : " + grille.getCaseDuTab(ct,lt).getEstIci().getVie());
            System.out.println("nbr de diamants de rockford situe en x = " + ct + " y = " + lt +
            " : " + ((Rockford)(grille.getCaseDuTab(ct,lt).getEstIci())).getCompteurDiamant());*/
            return true;
        }

        return false;
    }
}