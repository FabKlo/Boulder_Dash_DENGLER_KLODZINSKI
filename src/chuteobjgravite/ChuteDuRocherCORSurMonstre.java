package chuteobjgravite;

import entitesvivantes.Monstre;
import lagrille.Grille;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurMonstre extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurMonstre(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1).estOccupee() && grille.getCaseDuTab(cs,ls+1).getEstIci() instanceof Monstre)) {
            //System.out.println("la case target n'est pas un monstre");
            return false;
        }

        grille.getCaseDuTab(cs,ls + 1).getEstIci().setVie(grille.getCaseDuTab(cs, ls+1).getEstIci().getVie()-1);
        if(grille.getCaseDuTab(cs,ls + 1).getEstIci().getVie() <= 0)
            grille.setCaseDuTab(cs, ls+1,new Diamant(cs, ls+1));
        grille.verifVieAll();
        //System.out.println("le rocher a tape un monstre :\ntableau "+cs+" "+(ls+1)+" = "+grille.getCaseDuTab(cs,ls-1));
        return true;
    }

}