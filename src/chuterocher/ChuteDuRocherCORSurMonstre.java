package chuterocher;

import entitesvivantes.Monstre;
import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurMonstre extends ChuteDuRocherCOR {

    public ChuteDuRocherCORSurMonstre(ChuteDuRocherCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls+1).estOccupee() && grille.getCaseDuTab(cs,ls+1).getEstIci() instanceof Monstre)) {
            System.out.println("la case target n'est pas un monstre");
            return false;
        }

        grille.getCaseDuTab(cs,ls + 1).getEstIci().setVie(grille.getCaseDuTab(cs, ls+1).getEstIci().getVie()-1);
        grille.verifVieAll();
        System.out.println("le rocher a tapé un monstre :\ntableau "+cs+" "+(ls+1)+" = "+grille.getCaseDuTab(cs,ls-1));
        return true;
    }

}